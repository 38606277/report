package root.report.dataStandard.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.common.RO;
import root.report.dataStandard.service.DataStandardService;
import root.report.db.DbFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/dataStandard")
public class DataStandardController extends RO {
    @Autowired
    public DataStandardService dataStandardService;

    @RequestMapping(value = "/getDataStandardList", produces = "text/plain;charset=UTF-8")
    public String getDataStandardList(@RequestBody JSONObject pJson)  {
        try {
            Map<String,String> map=new HashMap();
            map.put("startIndex",pJson.getString("startIndex"));
            map.put("perPage",pJson.getString("perPage"));
            map.put("standard_name",pJson.get("standard_name")==null?"":pJson.get("standard_name").toString());
            map.put("standard_code",pJson.get("standard_code")==null?"":pJson.get("standard_code").toString());
            map.put("catalog_id",pJson.get("catalog_id")==null?"":pJson.get("catalog_id").toString());
            Map<String,Object> resultMap = dataStandardService.getListPage(map);
            return SuccessMsg("", resultMap);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/getDataStandardById", produces = "text/plain;charset=UTF-8")
    public String getDataStandardById(@RequestBody JSONObject pJson)  {
        try{
            Map resultObject =dataStandardService.getDataStandardByID(pJson);
            return SuccessMsg("",resultObject);
        }catch (Exception ex){
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }

    }

    @RequestMapping(value = "/createDataStandard", produces = "text/plain;charset=UTF-8")
    public String getTablesBySource(@RequestBody JSONObject pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            Map<String,Object> map  = this.dataStandardService.saveOrUpdateBdDataStandard(sqlSession,pJson);
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

    @RequestMapping(value = "/createCatalog", produces = "text/plain;charset=UTF-8")
    public String createCatalog(@RequestBody JSONObject pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            Map<String,Object> map  = this.dataStandardService.createOrUpdateCatalog(sqlSession,pJson);
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


    @RequestMapping(value = "/deleteDataStandardById", produces = "text/plain;charset=UTF-8")
    public String deleteDataStandardById(@RequestBody JSONObject jsonObject) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            String standard_id=jsonObject.getString("standard_id");
            //删除
            this.dataStandardService.deleteBdDataStandardById(sqlSession,standard_id);
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

    @RequestMapping(value = "/getCatalogList", produces = "text/plain;charset=UTF-8")
    public String getAllList(@RequestBody JSONObject pjson) {
        try {
            Map map = new HashMap();
            map.put("catalog_pid",pjson.get("catalog_pid"));
            List<Map> map1 = dataStandardService.getStandardCatalogByPid(map);
            map.put("catalog_id","0");
            map.put("catalog_name","全部");
            map.put("key","0");
            map.put("title","全部");
            map.put("children",map1);
            List<Map> newamp = new ArrayList<>();
            newamp.add(map);
            return SuccessMsg("", newamp);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/deleteCatalogById", produces = "text/plain;charset=UTF-8")
    public String deleteCatalogById(@RequestBody JSONObject jsonObject) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            String catalog_id=jsonObject.getString("catalog_id");
            //删除
            Map maps= this.dataStandardService.deleteCatalogById(sqlSession,catalog_id);
            sqlSession.getConnection().commit();
            return SuccessMsg("删除成功",maps);
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }finally {
            sqlSession.getConnection().setAutoCommit(true);
        }

    }

}
