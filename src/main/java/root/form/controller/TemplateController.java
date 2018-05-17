package root.form.controller;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.github.pagehelper.util.StringUtil;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import root.configure.AppConstants;
import root.form.constant.ColumnType;
import root.report.common.BaseControl;
import root.report.db.DbFactory;
import root.report.sys.SysContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
    @RequestMapping(value = "/createTemplate", method = RequestMethod.POST)
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
     * 下载模板
     * @param response
     * @param req
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/downloadTemplate", produces = "text/plain;charset=UTF-8")
    public void downloadTemplate(HttpServletResponse response, HttpServletRequest req) throws IOException {
        //JSONObject json = (JSONObject) JSON.parse(pJson);
        String path = req.getParameter("filePath");
        String fileName = req.getParameter("fileName");
        if(StringUtil.isEmpty(fileName)){
            fileName = path;
        }
        File file = new File(path);
        fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition",
                "attachment;fileName=" + fileName);// 设置文件名
        response.setCharacterEncoding("UTF-8");
        
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
                Object table_id = map.get("table_id");
                List taskList = session.selectList("dataCollect.getTableTaskList",table_id);
                map.put("taskList", taskList);
            }
            return result;
        });
    }

    /**
     * 根据模板ID删除模板，步骤：
     * 先查询模板对应的表ID,再查询表ID对应的任务，
     * 如果没有任务，则删除表定义及字段信息，再删除模板
     * @param templateId
     * @return
     */
    @RequestMapping(value = "/removeTemplate/{templateId}", produces = "text/plain; charset=utf-8")
    public String rmTemplate(@PathVariable("templateId") Integer templateId){
        return this.doExecuteWithROReturn(()->{
            SqlSession session = DbFactory.Open(DbFactory.FORM );
            Map<String, Object> params = new HashMap<>();
            params.put("templateId", templateId);
            //查询模板关联表
            Map<String, Object> table = session.selectOne("dataCollect.getFrmTable",params);
            //查询表关联任务
            Object tableId = table.get("table_id");
            List taskList = session.selectList("dataCollect.getTableTaskList",tableId);
            if(!CollectionUtils.isEmpty(taskList)) throw new RuntimeException("该模板关联的有任务，不能删除");
            //删除自定义表
            session.delete("dataCollect.dropNewTable", table.get("table_name"));
            //删除关联表定义和字段定义
            session.delete("dataCollect.deleteFromFrmTableField", tableId);
            session.delete("dataCollect.deleteFromFrmTable", tableId);
            //删除模板
            session.delete("dataCollect.rmTemplate", templateId);
            return "";
        });
    }

    @RequestMapping(value = "/listAllColumnTypes", produces = "text/plain;charset=UTF-8")
    public String listAllColumnTypes(){
        return this.doExecuteWithROReturn(()->{
            return ColumnType.listAllColumnTypes();
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
            session.insert("dataCollect.addTableRecord", json);
            //frm_table_filed添加数据
            Integer tableId = json.getInteger("_id");
            columns.forEach(j->((JSONObject)j).put("table_id", tableId));
            session.insert("dataCollect.addTableFields", columns);
            //创建表
            this.addCommonColumns(columns);
        	StringBuffer sb = new StringBuffer();
        	sb.append("_id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT, ");
        	//拼接建表sql
        	columns.forEach(col->{
        		JSONObject jsonCol = (JSONObject)col;
        		sb.append(jsonCol.getString("field_name") + " "+ ColumnType.getDbType(jsonCol.getString("data_type")));
        		String length = jsonCol.getString("data_length");
        		if(null != length && !"".equals(length)) sb.append("("+length+")");
        		sb.append(",");
        	});
        	String createSql = "create table "+ tableName + "(" + sb.deleteCharAt(sb.length()-1) + ")";
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
        taskCol.put("data_type", ColumnType.NUMBER);
        JSONObject userCol = new JSONObject();
        userCol.put("field_name","create_by");
        userCol.put("data_type", ColumnType.STRING);
        userCol.put("data_length","128");
        JSONObject createTimeCol = new JSONObject();
        createTimeCol.put("field_name","create_time");
        createTimeCol.put("data_type", ColumnType.DATE);
        columns.add(taskCol);
        columns.add(userCol);
        columns.add(createTimeCol);
    }

    /**
     * 查询当前用户创建的表及字段描述
     * @return
     */
    @RequestMapping(value = "/listTable", produces = "text/plain;charset=UTF-8")
    public String listTable(){
        return this.doExecuteWithROReturn(()->{
            String currentUser =  SysContext.getRequestUser().getUserName();
            SqlSession session = DbFactory.Open(DbFactory.FORM );
            //查询用户创建的表
            Map<String, Object> params = new HashMap<>();
            params.put("createBy", currentUser);
            List<Map<String, Object>> tableList = session.selectList("dataCollect.getFrmTable", params);
            //根据表id查询字段及描述
            tableList.forEach(t->{
                Object tableId = t.get("table_id");
                List<Map<String, Object>> fieldList = session.selectList("dataCollect.getTableFieldDesc", tableId);
                t.put("fieldList", fieldList);
            });
            return tableList;
        });
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
            if(taskId != null){
                List<Map> list = session.selectList("dataCollect.getTaskInfo", taskId);
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

    /**
     * 根据任务ID和当前用户查询他的填报数据
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/reportData/{table_name}/{task_id}", produces = "text/plain;charset=UTF-8")
    public String getReportData(@PathVariable("table_name") String tableName,@PathVariable("task_id") Integer taskId){
        return this.doExecuteWithROReturn(()->{
            SqlSession session = DbFactory.Open(DbFactory.FORM );
            String currentUser =  SysContext.getRequestUser().getUserName();
            Map<String, Object> params = new HashMap<>();
            params.put("tableName", tableName);
            params.put("taskId", taskId);
            params.put("createBy", currentUser);
            return session.selectList("dataCollect.reportData",params);
        });
    }

    /**
     * pJson格式
     * {
     *     tableName:"",
     *     taskId:"",
     *     columnNames:[col1,col2,col3],
     *     data:[{col1:'',col2:'',col3:''}]
     * }
     * @param pJson
     * @return
     */
    @RequestMapping(value = "/saveData", produces = "text/plain;charset=UTF-8")
    public String saveData(@RequestBody String pJson) throws SQLException {
        JSONObject json = (JSONObject) JSON.parse(pJson);
        JSONArray list = json.getJSONArray("data");
        if(CollectionUtils.isEmpty(list)) return "";
        JSONObject row = list.getJSONObject(0);

        List<String> columnNames = new ArrayList<>();
        for(String key : row.keySet()){
            columnNames.add(key);
        }
        String currentUser =  SysContext.getRequestUser().getUserName();
        json.put("columnNames", columnNames);
        SqlSession session = null;
        try {
            session = DbFactory.Open(false, DbFactory.FORM);
            //查询字段名称和字段类型
            /*List<Map<String, String>> columnNameAndTypes = session.selectList("dataCollect.getColumnNameAndType", json.getInteger("taskId"));
            Map<String, String> types = new HashMap<>();
            columnNameAndTypes.forEach(m->{
                types.put(m.get("field_name"), ColumnType.getJdbcType(m.get("data_type")));
            });*/
            //删除当前用户的任务填报数据
            json.put("createBy", currentUser);
            session.delete("dataCollect.deleteReportData",json);
            //保存填报数据
            addCommonColumnsToReportData(json);
            session.insert("dataCollect.saveReportData", json);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            return ExceptionMsg(e.getMessage());
        }
        return SuccessMsg("操作成功","");
    }

    /*
    添加任务id，创建人，创建时间到数据表
     */
    private void addCommonColumnsToReportData(JSONObject json) {
        JSONArray array = json.getJSONArray("data");
        if(array == null) return;
        Date now = new Date();
        array.forEach(j->{
            JSONObject js = (JSONObject)j;
            js.putIfAbsent("task_id", json.getInteger("taskId"));
            js.putIfAbsent("create_by", json.getString("createBy"));
            js.putIfAbsent("create_time", now);
        });
    }
}
