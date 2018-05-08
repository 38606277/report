package root.form.controller;


import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import root.configure.AppConstants;
import root.report.common.BaseControl;
import root.report.db.DbFactory;
import root.report.sys.SysContext;



@RestController
@RequestMapping("/reportServer/dataCollect")
public class TemplateController extends BaseControl {
	
	@Autowired
	private AppConstants appConstant;

    /**
     * 添加模板
     * @param file
     * @return
     */
    @RequestMapping(value = "/createTemplate", method=RequestMethod.POST)
    public String create(@RequestPart(value="file", required=true) MultipartFile file) {
        return this.doExecuteWithROReturn(()->{
            //保存文件
            if(file == null) throw new RuntimeException("请上传模板文件");
            String destPath = appConstant.getTemplatePath()+"/"+SysContext.getRequestUser().getUserName();
            File destDir = new File(destPath);
            if(!destDir.exists()) destDir.mkdirs();
            File destFile = new File(destDir+"/"+file.getOriginalFilename());
            file.transferTo(destFile);
            //保存文件信息到数据据库
            Map<String, Object> map = new HashMap<>();
            map.put("template_name", file.getOriginalFilename());
            map.put("createby", SysContext.getRequestUser().getUserName());
            map.put("create_date", new Date());
            map.put("template_path", destFile.getAbsolutePath());
            DbFactory.Open(DbFactory.FORM).insert("dataCollect.addTemplate", map);
            return "";
        });
    }

    /**
     * 查询所有模板，附带模板关联的任务
     * @return
     */
    @RequestMapping(value = "/listTemplate")
    public String listTemplate() {
        return this.doExecuteWithROReturn(()->{
            String currentUser =  SysContext.getRequestUser().getUserName();
            SqlSession session = DbFactory.Open(DbFactory.FORM );
            List<Map<String, Object>> result =  session.selectList("dataCollect.getTemplateList", currentUser);
            //循环模板列表，添加任务列表
            for(Map<String, Object> map : result){
                Object templateId = map.get("template_id");
                List taskList = session.selectList("dataCollect.getTemplateTaskList",templateId);
                map.put("taskList", taskList);
            }
            return result;
        });
    }

    /**
     * 根据模板ID删除模板， 删除之前要先判断模板下是否有任务,需要同步删除自定义表和元数据信息
     * @param templateId
     * @return
     */
    @RequestMapping(value = "/removeTemplate/{templateId}", produces = "text/plain; charset=utf-8")
    public String rmTemplate(@PathVariable("templateId") Integer templateId){
        return this.doExecuteWithROReturn(()->{
            SqlSession session = DbFactory.Open(DbFactory.FORM );
            List taskList = session.selectList("dataCollect.getTemplateTaskList",templateId);
            if(!CollectionUtils.isEmpty(taskList)) throw new RuntimeException("该模板关联的有任务，不能删除");
            session.delete("dataCollect.rmTemplate", templateId);
            //删除自定义表和元数据信息 TODO
            return "";
        });
    }

    /**
     * 根据前台传递的表及字段信息建表
     * @param pJson
     * @return
     */
    @RequestMapping(value = "/createTable", produces = "text/plain;charset=UTF-8")
    public String createTable(@RequestBody String pJson){
        JSONObject json = (JSONObject) JSON.parse(pJson);
        String tableName = json.getString("table_name");
        JSONArray columns = json.getJSONArray("columns");
        try {
        	SqlSession session = DbFactory.Open(false,DbFactory.FORM );
            Date now = new Date();
            //frm_table添加数据
            String currentUser =  SysContext.getRequestUser().getUserName();
            json.put("create_by", currentUser);
            json.put("create_date", now);
            session.update("dataCollect.addTableRecord", json);
            //frm_table_filed添加数据
            Integer tableId = json.getInteger("_id");
            columns.forEach(j->((JSONObject)j).put("table_id", tableId));
            session.update("dataCollect.addTableFields", columns);
            //创建表
            this.addCommonColumns(columns);
        	StringBuffer sb = new StringBuffer();
        	sb.append("_id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT, ");
        	//拼接建表sql
        	columns.forEach(col->{
        		JSONObject jsonCol = (JSONObject)col;
        		sb.append(jsonCol.getString("field_name") + " "+ jsonCol.getString("data_type"));
        		String length = jsonCol.getString("data_length");
        		if(StringUtils.isNotBlank(length)) sb.append("("+length+")");
        		sb.append(",");
        	});
        	String createSql = "create table "+ tableName + "(" + StringUtils.removeEnd(sb.toString(), ",") + ")";
            session.update("dataCollect.createNewTable", createSql);
            session.commit();
            return this.SuccessMsg("操作成功", null);
        } catch (Exception e) {
            DbFactory.rollback(DbFactory.FORM);
            return this.ExceptionMsg(e.getMessage());
        }
    }

    //添加建表共有字段
    private void addCommonColumns(JSONArray columns){
        JSONObject taskCol = new JSONObject();
        taskCol.put("field_name","task_id");
        taskCol.put("data_type", "int");
        taskCol.put("data_length","11");
        JSONObject userCol = new JSONObject();
        userCol.put("field_name","create_by");
        userCol.put("data_type", "varchar");
        userCol.put("data_length","128");
        JSONObject createTimeCol = new JSONObject();
        createTimeCol.put("field_name","create_time");
        createTimeCol.put("data_type", "datetime");
        columns.add(taskCol);
        columns.add(userCol);
        columns.add(createTimeCol);
    }

    

    /**
     * 保存或发布任务
     * 保存任务：taskState:0
     * 发布任务：taskState：1
     * @param pJson
     * @return
     */
    @RequestMapping(value = "/saveTask", produces = "text/plain;charset=UTF-8")
    public String saveTask(@RequestBody String pJson) {
        JSONObject json = (JSONObject) JSON.parse(pJson);
        try {
            SqlSession session = DbFactory.Open(false, DbFactory.FORM );
            Integer taskId = json.getInteger("task_id");
            List<Map> list = session.selectList("dataCollect.getTaskInfo", taskId);
            if(!CollectionUtils.isEmpty(list)){
            	Map<String, Object> map = list.get(0);
            	//任务如果已经发布，则不允许修改
            	if("1".equals(map.get("task_state"))) throw new RuntimeException("任务已经发布，不能修改");
            	json.put("create_date", map.get("create_date"));
            	json.put("task_id", map.get("task_id"));
            	deleteTaskCascade(session, taskId);
            }
            taskId = saveTaskCascade(json, session);
            session.commit();
            return this.SuccessMsg("操作成功", taskId);
        } catch (Exception e) {
            DbFactory.rollback(DbFactory.FORM);
            return this.ExceptionMsg(e.getMessage());
        }
    }
    
    /**
     * 删除任务及任务用户信息
     * @param session
     * @param taskId
     */
	private void deleteTaskCascade(SqlSession session, Integer taskId) {
		//删除任务用户信息
		session.delete("dataCollect.deleteTaskUserInfo", taskId);
		//删除任务信息
		session.delete("dataCollect.deleteTaskInfo", taskId);
	}
    
    /**
     * 保存任务及任务用户
     * @param json
     * @param session
     */
	private Integer saveTaskCascade(JSONObject json, SqlSession session) {
		//添加任务
		Date now = new Date();
		String currentUser =  SysContext.getRequestUser().getUserName();
		json.put("create_by", currentUser);
		json.putIfAbsent("create_date", now);
		//如果是发布操作，则填充发布时间
		if("1".equals(json.get("task_state")))json.put("deploy_date", now);
		session.insert("dataCollect.insertTaskInfo", json);
		//添加任务用户中间表
		Integer taskId = json.getInteger("task_id");
		JSONArray taskUsers = json.getJSONArray("task_user");
		taskUsers.forEach(u->{
			JSONObject j = (JSONObject)u;
			j.put("task_id", taskId);
			j.put("create_date", now);
			
		});
		session.insert("dataCollect.inserTaskUserInfo", taskUsers);
		return taskId;
	}

    /**
     * 删除任务及任务用户信息
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/deleteTask/{task_id}", produces = "text/plain;charset=UTF-8")
    public String deleteTask(@PathVariable("task_id") Integer taskId){
        try {
            //如果任务已经发布，则不允许删除
            SqlSession session = DbFactory.Open(false, DbFactory.FORM );
            Map<String, Object> taskInfo = session.selectOne("dataCollect.getTaskInfo", taskId);
            if(taskInfo != null){
                Object taskState = taskInfo.get("task_state");
                if("0".equals(taskState)){
                	deleteTaskCascade(session, taskId);
                }else{
                	throw new RuntimeException("该任务已经发布，不能删除");
                }
            }
            session.commit();
            return this.SuccessMsg("操作成功", null);
        } catch (RuntimeException e) {
            DbFactory.rollback(DbFactory.FORM);
            return this.ExceptionMsg(e.getMessage());
        }
    }

    /**
     * 查询任务及任务用户信息
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/taskAndUsers/{task_id}", produces = "text/plain;charset=UTF-8")
    public String taskAndUsers(@PathVariable("task_id") Integer taskId){
        return this.doExecuteWithROReturn(()->{
            SqlSession session = DbFactory.Open(DbFactory.FORM );
            Map taskMap = session.selectOne("dataCollect.getTaskInfo", taskId);
            List<Map>  taskUserList = session.selectList("dataCollect.getTaskUserInfo", taskId);
            taskMap.put("taskUserList", taskUserList);
            return taskMap;
        });
    }
}
