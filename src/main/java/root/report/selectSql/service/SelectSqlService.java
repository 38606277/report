package root.report.selectSql.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import root.report.db.DbFactory;

import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SelectSqlService {

    private static Logger log = Logger.getLogger(SelectSqlService.class);

    public Map<String,Object> getAllPage(Map<String,String> map) {
        Map<String,Object> map1=new HashMap<>();

        try {
            SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
            RowBounds bounds = null;
            if (map == null) {
                bounds = RowBounds.DEFAULT;
            } else {
                Integer startIndex = Integer.parseInt(map.get("startIndex").toString());
                Integer perPage = Integer.parseInt(map.get("perPage"));
                if (startIndex == 1 || startIndex == 0) {
                    startIndex = 0;
                } else {
                    startIndex = (startIndex - 1) * perPage;
                }
                bounds = new PageRowBounds(startIndex, perPage);
            }
            List<Map<String, Object>> resultList = sqlSession.selectList("selectSql.getAllPage", map, bounds);
            Long totalSize = 0L;
            if (map != null && map.size() != 0) {
                totalSize = ((PageRowBounds) bounds).getTotal();
            } else {
                totalSize = Long.valueOf(resultList.size());
            }

            map1.put("list", resultList);
            map1.put("total", totalSize);

        }catch (Exception e){
            e.printStackTrace();
        }
        return map1;
    }
    /**
     * 功能描述: 根据JSON数据解析 对应数据，生成func_dict记录
     */
    public Map<String,Object> saveOrUpdateSelectSql(SqlSession sqlSession,JSONObject jsonObject){
        Map<String,Object> resmap  = new HashMap<>();
        resmap.put("result",true);
        resmap.put("info","保存成功");
        resmap.put("id","");
        Map<String,Object> map  = new HashMap<>();
        String id="";
        map.put("name",jsonObject.getString("name"));
        map.put("id",jsonObject.getString("id"));
        Integer count = sqlSession.selectOne("selectSql.count",map);
        if(count==0) {
            map.put("fromdb", jsonObject.getString("fromdb"));
            map.put("selectsql", jsonObject.getString("selectsql"));
            map.put("dbtype", jsonObject.getString("dbtype"));
            if (null == jsonObject.getString("id") || "".equals(jsonObject.getString("id"))) {
                Integer newId = sqlSession.selectOne("selectSql.getMaxId");
                newId = newId == null ? 1 : newId;
                map.put("id", newId);
                sqlSession.insert("selectSql.createSelectSql", map);
                id = String.valueOf(map.get("id"));
            } else {
                map.put("id", jsonObject.getString("id"));
                sqlSession.update("selectSql.updateSelectSql", map);
                id = jsonObject.getString("id");
            }
            resmap.put("id",id);
        }else{
            resmap.put("result",false);
            resmap.put("info","名称已存在");
        }
        return resmap;
    }


    // 功能描述: 根据 dict_id 和 out_id 批量删除 func_dict的信息
    public void deleteSelectSqlById(SqlSession sqlSession,String id){
            Map<String,Object> map=new HashMap();
            map.put("id",id);
            sqlSession.delete("selectSql.deleteSelectSqlByID",map);
    }

    public Map getSelectSqlByID(Map m) {
        return DbFactory.Open(DbFactory.FORM).selectOne("selectSql.getSelectSqlById",m);
    }

    public Map<String, Object> excueBatchSql(String sql,String fromdb) {
        List<Map<String, Object>> list=null;
        Map<String,Object> resmap  = new HashMap<>();
        resmap.put("result",true);
        resmap.put("info","查询成功");
        resmap.put("data",list);
        try {
            if(fromdb.equalsIgnoreCase("dpass_data")){
                DbFactory.Open(fromdb).getConnection().createStatement().execute(sql);
            }else {
                list = DbFactory.Open(fromdb).selectList("selectSql.tempSql", sql);
            }
            resmap.put("data",list);
        }catch (PersistenceException e){
            e.printStackTrace();
            resmap.put("result",false);
            resmap.put("info","查询失败，请检查SQL语句");
        }catch (Exception e){
            e.printStackTrace();
            resmap.put("result",false);
            resmap.put("info","查询失败，请检查数据库是否连接正确");
        }
        return resmap;
    }

    public Map<String, Object> excueSelectSql(String selectsql,String fromdb) {
        List<Map<String, Object>> list=null;
        Map<String,Object> resmap  = new HashMap<>();
        resmap.put("result",true);
        resmap.put("info","查询成功");
        resmap.put("data",list);

        try {
            list= DbFactory.Open(fromdb).selectList("selectSql.tempSql",selectsql);
            resmap.put("data",list);
        }catch (PersistenceException e){
            e.printStackTrace();
            resmap.put("result",false);
            resmap.put("info","查询失败，请检查SQL语句");
        }catch (Exception e){
            e.printStackTrace();
            resmap.put("result",false);
            resmap.put("info","查询失败，请检查数据库是否连接正确");
        }
        return resmap;
    }

    public Map<String, Object> getTableAndColumnList(String fromdb) {
        Map<String, Object> resmap=new HashMap<>();
        SqlSession sqlSession=DbFactory.Open(fromdb);
        Map m=new HashMap();
        m.put("fromdb",fromdb);
        List<String> tableList = sqlSession.selectList("selectSql.tableList",m);
        Map<String,List<String>> colmap=new HashMap<>();
        if(tableList.size()>0){
            for (String  o :tableList ) {
                Map colpar=new HashMap();
                colpar.put("tablename",o);
                List<String> columnlist = sqlSession.selectList("selectSql.columnList",colpar);
                colmap.put(o,columnlist);
            }
        }
        resmap.put("tables",colmap);
        return resmap;
    }

    public Map<String, Object> getTableList(String fromdb) {
        Map<String, Object> resmap=new HashMap<>();
        SqlSession sqlSession=DbFactory.Open(fromdb);
        Map m=new HashMap();
        m.put("fromdb",fromdb);
        List<String> tableList = sqlSession.selectList("selectSql.tableList",m);
        resmap.put("tables",tableList);
        return resmap;
    }
}
