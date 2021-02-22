package root.report.datastorage.taos;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import root.report.db.DbFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaosService {

    private static Logger log = Logger.getLogger(TaosService.class);

    public Map<String, Object> excueBatchSql(String sql, String fromdb, String dbType,String param) {
        List<Map<String, Object>> list=null;
        Map<String,Object> resmap  = new HashMap<>();
        resmap.put("result",true);
        resmap.put("info","查询成功");
        resmap.put("data",list);
        try {
            if(dbType.equalsIgnoreCase("TAOS")) {
                if (!"find".equalsIgnoreCase(param)) {
                    DbFactory.Open(fromdb).getConnection().createStatement().execute(sql);
                } else{
                    ResultSet resultSet = DbFactory.Open(fromdb).getConnection().createStatement().executeQuery(sql);
                    list = convertList(resultSet);
                    resultSet.close();
                }
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

    /**
     * ResultSet转List
     * */
    public List convertList(ResultSet rs) throws SQLException {
        List list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        while (rs.next()) {
            Map rowData = new HashMap();
            for (int ij = 1; ij <= columnCount; ij++) {
                rowData.put(md.getColumnName(ij), rs.getObject(ij));
            }
            list.add(rowData);
        }
        return list;
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

    public List<Map>  getTableList(String fromdb,String dbType) throws SQLException {
        // 查询数据库所有表
       /* DatabaseMetaData databaseMetaData = DbFactory.Open(fromdb).getConnection().getMetaData();
        //获取所有表
        ResultSet tableSet = databaseMetaData.getTables(null, "%", "%", new String[]{"TABLE"});
        List<Map> listone = convertList(tableSet);
        while (tableSet.next()){
            System.out.println(tableSet.getString(0));
        }
        tableSet.close();*/


        Statement stmt=DbFactory.Open(fromdb).getConnection().createStatement();
        String sql="show tables";
        System.out.println("Running:"+sql);
        ResultSet  res = stmt.executeQuery(sql);

        List<Map> tableNameList= new ArrayList<>();
        while (res.next()){
            System.out.println(res.getString(1));
            Map map= new HashMap();
            map.put("dbtype_id",dbType);
            map.put("host_id",fromdb);
            map.put("table_name",res.getString(1));
            tableNameList.add(map);
        }
        stmt.close();
        return tableNameList;
    }

}
