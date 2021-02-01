package root.report.algorithm.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.algorithm.service.AiAlgorithmService;
import root.report.common.RO;
import root.report.db.DbFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/algorithm")
public class AiAlgorithmController extends RO {
    @Autowired
    public AiAlgorithmService aiAlgorithmService;

    @RequestMapping(value = "/getAlgorithmList", produces = "text/plain;charset=UTF-8")
    public String getAlgorithmList(@RequestBody JSONObject pJson)  {
        try {
            Map<String,Object> map=new HashMap();
            map.put("startIndex",pJson.getString("startIndex"));
            map.put("perPage",pJson.getString("perPage"));
            map.put("algorithm_name",pJson.getString("algorithm_name")==null?"":pJson.getString("algorithm_name"));
            String aiclassid=pJson.getString("algorithm_class_id")==null?"":pJson.getString("algorithm_class_id");
            String newid = "";
            if(null!=aiclassid && !"".equals(aiclassid)) {
                String[] arr = aiclassid.split(",");
                for (String id : arr) {
                    newid = newid + ",'" + id +"'";
                }
                if (null != newid && !"".equals(newid)) {
                    newid = newid.substring(1, newid.length());
                }
            }
            map.put("algorithm_class_id",newid);
            Map<String,Object> resultMap = aiAlgorithmService.getListPage(map);
            return SuccessMsg("", resultMap);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }
    @RequestMapping(value = "/getAllList", produces = "text/plain;charset=UTF-8")
    public String getAllList()  {
        List<Map> list=aiAlgorithmService.getAllList();
        return SuccessMsg("", list);
    }

    @RequestMapping(value = "/getAlgorithmById", produces = "text/plain;charset=UTF-8")
    public String getAlgorithmById(@RequestBody JSONObject pJson)  {
        try{
            Map resultObject = aiAlgorithmService.getAlgorithmByID(pJson);
            return SuccessMsg("",resultObject);
        }catch (Exception ex){
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }

    }

    @RequestMapping(value = "/createAlgorithm", produces = "text/plain;charset=UTF-8")
    public String getTablesBySource(@RequestBody JSONObject pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            Map<String,Object> map  = this.aiAlgorithmService.saveOrUpdateAlgorithm(sqlSession,pJson);
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

    @RequestMapping(value = "/deleteAlgorithm", produces = "text/plain;charset=UTF-8")
    public String deleteAlgorithm(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONArray jsonArray =  JSONObject.parseArray(pJson);
            for(int i = 0; i < jsonArray.size(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int algorithm_id=jsonObject.getInteger("algorithm_id");
                //删除
                this.aiAlgorithmService.deleteAlgorithmById(sqlSession,algorithm_id);
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

    @RequestMapping(value = "/deleteAlgorithmById", produces = "text/plain;charset=UTF-8")
    public String deleteAlgorithmById(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject jsonObject = JSONObject.parseObject(pJson);
            int algorithm_id=jsonObject.getInteger("algorithm_id");
            //删除
            this.aiAlgorithmService.deleteAlgorithmById(sqlSession,algorithm_id);
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
