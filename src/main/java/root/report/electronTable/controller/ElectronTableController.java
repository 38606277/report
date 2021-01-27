package root.report.electronTable.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.common.RO;
import root.report.db.DbFactory;
import root.report.electronTable.service.ElectronTableService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/electronTable")
public class ElectronTableController extends RO {
    @Autowired
    public ElectronTableService electronTableService;

    @RequestMapping(value = "/getList", produces = "text/plain;charset=UTF-8")
    public String getList(@RequestBody JSONObject pJson)  {
        try {
            Map<String,String> map=new HashMap();
            map.put("startIndex",pJson.getString("startIndex"));
            map.put("perPage",pJson.getString("perPage"));
            map.put("name",pJson.get("name")==null?"":pJson.get("name").toString());
            Map<String,Object> resultMap = electronTableService.getListPage(map);
            return SuccessMsg("", resultMap);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/getObjById", produces = "text/plain;charset=UTF-8")
    public String getObjById(@RequestBody JSONObject pJson)  {
        try{
            Map<String,Object> resultObject =electronTableService.getObjById(pJson);
            return SuccessMsg("",resultObject);
        }catch (Exception ex){
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }

    }

    @RequestMapping(value = "/createElectronTable", produces = "text/plain;charset=UTF-8")
    public String createElectronTable(@RequestBody JSONObject pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            Map<String,Object> map  = this.electronTableService.saveOrUpdateElectronTable(sqlSession,pJson);
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

    @RequestMapping(value = "/deleteElectronTableById", produces = "text/plain;charset=UTF-8")
    public String deleteElectronTableById(@RequestBody JSONObject jsonObject) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            String id=jsonObject.getString("id");
            //删除
            this.electronTableService.deleteElectronTableById(sqlSession,id);
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
