package root.bd.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.bd.service.ModelTableService;
import root.report.common.RO;
import root.report.db.DbFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/bdModelTableColumn")
public class ModelTableController extends RO {
    @Autowired
    public ModelTableService modelTableService;

    @RequestMapping(value = "/table/getTableList", produces = "text/plain;charset=UTF-8")
    public String getModelList(@RequestBody JSONObject pJson)  {
        try {
            Map<String,String> map=new HashMap();
            map.put("startIndex",pJson.getString("startIndex"));
            map.put("perPage",pJson.getString("perPage"));
            map.put("table_name",pJson.get("table_name")==null?"":pJson.get("table_name").toString());
            map.put("table_title",pJson.get("table_title")==null?"":pJson.get("table_title").toString());
            map.put("model_id",pJson.get("model_id")==null?"":pJson.get("model_id").toString());
            Map<String,Object> resultMap = modelTableService.getListPage(map);
            return SuccessMsg("", resultMap);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }
    @RequestMapping(value = "/table/getTableListByModelId", produces = "text/plain;charset=UTF-8")
    public String getTableListByModelId(@RequestBody JSONObject pjson)  {
        List<Map> list=modelTableService.getTableListByModelId(pjson);
        return SuccessMsg("", list);
    }

    @RequestMapping(value = "/table/getModelTableById", produces = "text/plain;charset=UTF-8")
    public String getModelTableById(@RequestBody JSONObject pJson)  {
        try{
            Map resultObject =modelTableService.getModelTableById(pJson);
            return SuccessMsg("",resultObject);
        }catch (Exception ex){
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }

    }

    @RequestMapping(value = "/table/getTableColumnById", produces = "text/plain;charset=UTF-8")
    public String getTableColumnById(@RequestBody JSONObject pJson)  {
        try{
            Map resultObject =modelTableService.getTableColumnById(pJson);
            return SuccessMsg("",resultObject);
        }catch (Exception ex){
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }

    }

    @RequestMapping(value = "/table/createModelTable", produces = "text/plain;charset=UTF-8")
    public String createModelTable(@RequestBody JSONObject pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            String id  = this.modelTableService.saveOrUpdateBdModelTable(sqlSession,pJson);
            sqlSession.getConnection().commit();
            return SuccessMsg("保存成功",id);
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/table/updateModel", produces = "text/plain;charset=UTF-8")
    public String updateModel(@RequestBody JSONObject pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            String id  = this.modelTableService.saveOrUpdateBdModelTable(sqlSession,pJson);
            sqlSession.getConnection().commit();
            return SuccessMsg("保存成功",id);
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }

    }

    @RequestMapping(value = "/table/deleteModel", produces = "text/plain;charset=UTF-8")
    public String deleteModel(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONArray jsonArray =  JSONObject.parseArray(pJson);
            for(int i = 0; i < jsonArray.size(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String model_id=jsonObject.getString("model_id");
                //删除
                this.modelTableService.deletedbmodelTableById(sqlSession,model_id);
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
    @RequestMapping(value = "/table/deleteTableId", produces = "text/plain;charset=UTF-8")
    public String deleteTableId(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject jsonObject = JSONObject.parseObject(pJson);
            String table_id=jsonObject.getString("table_id");
            //删除
            this.modelTableService.deletedbmodelTableById(sqlSession,table_id);
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
