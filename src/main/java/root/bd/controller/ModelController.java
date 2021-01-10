package root.bd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.bd.service.ModelService;
import root.report.common.RO;
import root.report.db.DbFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/bdModel")
public class ModelController extends RO {
    @Autowired
    public ModelService modelService;

    @RequestMapping(value = "/getModelList", produces = "text/plain;charset=UTF-8")
    public String getModelList(@RequestBody JSONObject pJson)  {
        try {
            Map<String,String> map=new HashMap();
            map.put("startIndex",pJson.getString("startIndex"));
            map.put("perPage",pJson.getString("perPage"));
            map.put("model_name",pJson.get("model_name")==null?"":pJson.get("model_name").toString());
            map.put("db_source",pJson.get("db_source")==null?"":pJson.get("db_source").toString());
            map.put("db_type",pJson.get("db_type")==null?"":pJson.get("db_type").toString());
            Map<String,Object> resultMap = modelService.getListPage(map);
            return SuccessMsg("", resultMap);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }
    @RequestMapping(value = "/getAllList", produces = "text/plain;charset=UTF-8")
    public String getModelList()  {
        List<Map> list=modelService.getAllList();
        return SuccessMsg("", list);
    }

    @RequestMapping(value = "/getModelById", produces = "text/plain;charset=UTF-8")
    public String getModelById(@RequestBody JSONObject pJson)  {
        try{
            Map resultObject =modelService.getBdmodelByID(pJson);
            return SuccessMsg("",resultObject);
        }catch (Exception ex){
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }

    }

    @RequestMapping(value = "/createModel", produces = "text/plain;charset=UTF-8")
    public String getTablesBySource(@RequestBody JSONObject pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            String id  = this.modelService.saveOrUpdateBdModel(sqlSession,pJson);
            sqlSession.getConnection().commit();
            return SuccessMsg("保存成功",id);
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/updateModel", produces = "text/plain;charset=UTF-8")
    public String updateModel(@RequestBody JSONObject pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            String id  = this.modelService.saveOrUpdateBdModel(sqlSession,pJson);
            sqlSession.getConnection().commit();
            return SuccessMsg("保存成功",id);
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }

    }

    @RequestMapping(value = "/deleteModel", produces = "text/plain;charset=UTF-8")
    public String deleteModel(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONArray jsonArray =  JSONObject.parseArray(pJson);
            for(int i = 0; i < jsonArray.size(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int model_id=jsonObject.getInteger("model_id");
                //删除
                this.modelService.deleteBdmodelById(sqlSession,model_id);
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
