package root.report.algorithm.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.algorithm.service.AiAlgorithmService;
import root.report.algorithm.service.AiModelService;
import root.report.common.RO;
import root.report.db.DbFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/algorithm")
public class AiModelController extends RO {
    @Autowired
    public AiModelService aiModelService;

    @RequestMapping(value = "/getAiModelList", produces = "text/plain;charset=UTF-8")
    public String getAiModelList(@RequestBody JSONObject pJson)  {
        try {
            Map<String,Object> map=new HashMap();
            map.put("startIndex",pJson.getString("startIndex"));
            map.put("perPage",pJson.getString("perPage"));
            map.put("algorithm_name",pJson.getString("algorithm_name")==null?"":pJson.getString("algorithm_name"));
            String aiclassid=pJson.getString("algorithm_class_id")==null?"":pJson.getString("algorithm_class_id");
            map.put("algorithm_class_id",aiclassid);
            Map<String,Object> resultMap = aiModelService.getListPage(map);
            return SuccessMsg("", resultMap);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }
    @RequestMapping(value = "/getAllList", produces = "text/plain;charset=UTF-8")
    public String getAllList()  {
        List<Map> list=aiModelService.getAllList();
        return SuccessMsg("", list);
    }

    @RequestMapping(value = "/getAiModelById", produces = "text/plain;charset=UTF-8")
    public String getAiModelById(@RequestBody JSONObject pJson)  {
        try{
            Map resultObject = aiModelService.getAiModelByID(pJson);
            return SuccessMsg("",resultObject);
        }catch (Exception ex){
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }

    }

    @RequestMapping(value = "/createAiModel", produces = "text/plain;charset=UTF-8")
    public String getTablesBySource(@RequestBody JSONObject pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            Map<String,Object> map  = this.aiModelService.saveOrUpdateAiModel(sqlSession,pJson);
            if(Boolean.valueOf(map.get("status").toString())){
                sqlSession.getConnection().commit();
                return SuccessMsg("保存成功",map.get("info"));
            }else{
                return ErrorMsg("保存失败",map.get("info").toString());
            }

        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/deleteAiModel", produces = "text/plain;charset=UTF-8")
    public String deleteAiModel(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONArray jsonArray =  JSONObject.parseArray(pJson);
            for(int i = 0; i < jsonArray.size(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int model_id=jsonObject.getInteger("model_id");
                //删除
                this.aiModelService.deleteAiModelById(sqlSession,model_id);
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

    @RequestMapping(value = "/deleteAiModelById", produces = "text/plain;charset=UTF-8")
    public String deleteAiModelById(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject jsonObject = JSONObject.parseObject(pJson);
            int model_id=jsonObject.getInteger("model_id");
            //删除
            this.aiModelService.deleteAiModelById(sqlSession,model_id);
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
