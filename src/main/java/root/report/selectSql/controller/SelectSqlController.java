package root.report.selectSql.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.common.RO;
import root.report.db.DbFactory;
import root.report.db.DbManager;
import root.report.selectSql.service.SelectSqlService;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/selectsql")
public class SelectSqlController extends RO {

    private static Logger log = Logger.getLogger(SelectSqlController.class);

    @Autowired
    public SelectSqlService selectSqlService;

    DbManager dbManager=new DbManager();

    /**
     * 功能描述: 接收JSON格式参数，
     */
    @RequestMapping(value = "/saveSelectSql", produces = "text/plain;charset=UTF-8")
    public String createSelectSql(@RequestBody JSONObject jsonObject) throws Exception
    {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            Map map  = this.selectSqlService.saveOrUpdateSelectSql(sqlSession,jsonObject);
            sqlSession.getConnection().commit();
            return SuccessMsg("保存成功",map);
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/getAllPage", produces = "text/plain;charset=UTF-8")
    public String getAllQueryNamePage(@RequestBody JSONObject jsonFunc) {
        try {
            Map<String,String> map=new HashMap();
            map.put("startIndex",jsonFunc.getString("startIndex"));
            map.put("perPage",jsonFunc.getString("perPage"));
            map.put("name",jsonFunc.get("name")==null?"":jsonFunc.get("name").toString());
            Map<String,Object> map1 = selectSqlService.getAllPage(map);
            return SuccessMsg("", map1);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }

     //返回数据
    @RequestMapping(value = "/getSelectSqlByID", produces = "text/plain;charset=UTF-8")
    public String getSelectSqlByID(@RequestBody JSONObject pjson) {
        //JSONObject pjson=JSON.parseObject(pjson);
        try{
            Map map = new HashMap();
            map.put("id",pjson.get("id"));
            Map jsonObject = this.selectSqlService.getSelectSqlByID(map);
            return SuccessMsg("查询成功",jsonObject);
        }catch (Exception ex){
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }
    }



    @RequestMapping(value = "/deleteSelectSqlById", produces = "text/plain;charset=UTF-8")
    public String deleteSelectSqlById(@RequestBody JSONObject pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            String ids = pJson.getString("id");
            String[] arrId=ids.split(",");
            for(int i = 0; i < arrId.length; i++){
                //删除
                this.selectSqlService.deleteSelectSqlById(sqlSession,arrId[0]);
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

    @RequestMapping(value = "/excueSelectSql", produces = "text/plain;charset=UTF-8")
    public String excueSelectSql(@RequestBody JSONObject jsonFunc) {
        try {
            String selectsql = jsonFunc.getString("selectsql");
            String fromdb = jsonFunc.getString("fromdb");
            Map<String,Object> map1 = selectSqlService.excueSelectSql(selectsql,fromdb);
            return SuccessMsg("", map1);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/getTableAndColumnList", produces = "text/plain;charset=UTF-8")
    public String getTableAndColumnList(@RequestBody JSONObject jsonFunc) {
        try {
            String fromdb = jsonFunc.getString("fromdb");
            Map<String,Object> map1 = selectSqlService.getTableAndColumnList(fromdb);
            return SuccessMsg("", map1);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/getTableList", produces = "text/plain;charset=UTF-8")
    public String getTableList(@RequestBody JSONObject jsonFunc) {
        try {
            String fromdb = jsonFunc.getString("fromdb");
            Map<String,Object> map1 = selectSqlService.getTableList(fromdb);
            return SuccessMsg("", map1);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/getTable", produces = "text/plain;charset=UTF-8")
    public String getTable(@RequestBody String dbname) throws SQLException,Exception {
        try {
            JSONObject obj = JSON.parseObject(dbManager.getDBConnectionByName(dbname));
            String dbtype = obj.getString("dbtype");
            List<String> tableList = new ArrayList<String>();
            Connection conn = null;
            if (dbtype.equals("Oracle")) {
                try {
                    conn = dbManager.getConnection(dbname);
                    tableList = this.getTableNameList(conn, dbname);
                } catch (SQLException e) {
                    if (null != conn) {
                        conn.close();
                    }
                    e.printStackTrace();
                } finally {
                    if (null != conn) {
                        conn.close();
                    }
                }
            } else if (dbtype.equals("Mysql")) {
                try {
                    conn = dbManager.getConnection(dbname);
                    Statement stmt = conn.createStatement();
                    stmt.executeQuery("use " + dbname);
                    ResultSet rs = stmt.executeQuery("SHOW TABLES ");
                    if (null != rs) {
                        while (rs.next()) {
                            tableList.add(rs.getString(1));
                        }
                    }
                    if (null != conn) {
                        conn.close();
                    }
                } catch (Exception e) {
                    if (null != conn) {
                        conn.close();
                    }
                    e.printStackTrace();
                } finally {
                    if (null != conn) {
                        conn.close();
                    }
                }
            }
            return SuccessMsg("表数据获取成功", tableList);
        }catch (Exception e){
            e.printStackTrace();
            return ErrorMsg("3000","查询失败");
        }
    }
    public List getTableNameList(Connection conn,String dbName) throws SQLException {
        DatabaseMetaData dbmd = conn.getMetaData();
        //访问当前用户ANDATABASE下的所有表
        ResultSet rs = dbmd.getTables("null", dbName.toUpperCase(), "%", new String[] { "TABLE" });
        List tableNameList = new ArrayList();
        while (rs.next()) {
            tableNameList.add(rs.getString("TABLE_NAME"));
        }
        return tableNameList;
    }
}
