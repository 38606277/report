package root.report.mdmcodetable.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import root.report.common.RO;
import root.report.db.DbFactory;
import root.report.mdmcodetable.service.CodetableService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/codetable")
public class CodetableController extends RO {

    private static Logger log = Logger.getLogger(CodetableController.class);

    @Autowired
    public CodetableService codetableService;

    /**
     * 功能描述: 接收JSON格式参数，往func_dict跟func_dict_out 中插入相关数据
     */
    @RequestMapping(value = "/saveCodetable", produces = "text/plain;charset=UTF-8")
    public String saveCodetable(@RequestBody String pJson) throws Exception
    {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject jsonObject = JSON.parseObject(pJson);
            String id =null;
            if(jsonObject.getString("dict_type").equals("list")) {
                id = this.codetableService.saveOrUpdateCodetable(sqlSession, jsonObject);
            }else if(jsonObject.getString("dict_type").equals("tree")) {
                id = this.codetableService.saveOrUpdateCodetableTree(sqlSession, jsonObject);
            }
            sqlSession.getConnection().commit();
            return SuccessMsg("保存成功",id);
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/getAllPage", produces = "text/plain;charset=UTF-8")
    public String getAllPage(@RequestBody String pJson) {
        try {
            JSONObject jsonFunc = JSONObject.parseObject(pJson);
            Map<String,String> map=new HashMap();
            map.put("startIndex",jsonFunc.getString("startIndex"));
            map.put("perPage",jsonFunc.getString("perPage"));
            map.put("dict_code",jsonFunc.getString("dict_code"));
            map.put("dict_name",jsonFunc.getString("dict_name"));
            Map<String,Object> map1 = codetableService.getAllPage(map);
            return SuccessMsg("", map1);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }
    @RequestMapping(value = "/getAll", produces = "text/plain;charset=UTF-8")
    public String getAll(@RequestParam(required = false) String pJson) {
        try {
            JSONObject jsonFunc = JSONObject.parseObject(pJson);
            List<Map> map1 = codetableService.getAll(jsonFunc);
            return SuccessMsg("", map1);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }

     //返回数据
    @RequestMapping(value = "/getCodetableByID", produces = "text/plain;charset=UTF-8")
    public String getCodetableByID(@RequestBody String pjson) {
        JSONObject obj=JSON.parseObject(pjson);
        try{
            Map map = new HashMap();
            map.put("dict_id",obj.get("dict_id"));
            Map jsonObject = this.codetableService.getCodetableByID(map);
            List<Map> list = this.codetableService.getCodetableValueByCodetableId(map);
            Map mmm=new HashMap();
            mmm.put("mainForm",jsonObject);
            mmm.put("lineForm",list);
            return SuccessMsg("查询成功",mmm);
        }catch (Exception ex){
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }
    }

    //返回数据
    @RequestMapping(value = "/getCodetableByCode", produces = "text/plain;charset=UTF-8")
    public String getCodetableByCode(@RequestBody String pjson) {
        JSONObject obj=JSON.parseObject(pjson);
        try{
            Map map = new HashMap();
            map.put("dict_code",obj.get("dict_code"));
            Map jsonObject = this.codetableService.getCodetableByCode(map);
            List<Map> list =null;
            if(null!=jsonObject) {
                list = this.codetableService.getCodetableValueListByCodetableId(jsonObject.get("dict_id").toString());
            }
            Map mmm=new HashMap();
            mmm.put("mainForm",jsonObject);
            mmm.put("lineForm",list);
            return SuccessMsg("查询成功",list);
        }catch (Exception ex){
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }
    }


    // 从json数据当中解析 ，批量删除
    @RequestMapping(value = "/deleteCodetableById", produces = "text/plain;charset=UTF-8")
    public String deleteCodetableById(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject obj=JSON.parseObject(pJson);
            String ids = obj.getString("dict_id");
            String[] arrId=ids.split(",");
            for(int i = 0; i < arrId.length; i++){
                //删除
                this.codetableService.deleteCodetableById(sqlSession,arrId[0]);
            }
            sqlSession.getConnection().commit();
            return SuccessMsg("删除成功",null);
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }finally {
            sqlSession.getConnection().setAutoCommit(true);
        }
    }


}
