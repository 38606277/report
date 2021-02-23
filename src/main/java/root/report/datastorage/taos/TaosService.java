package root.report.datastorage.taos;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import root.report.db.DbFactory;
import root.report.sys.SysContext;

import java.sql.*;
import java.util.*;

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
        ResultSet  res = stmt.executeQuery(sql);

        List<Map> tableNameList= new ArrayList<>();
        while (res.next()){
            System.out.println(res.getString(1));
            Map map= new HashMap();
            map.put("dbtype_id",dbType.toLowerCase());
            map.put("host_id",fromdb);
            map.put("table_name",res.getString(1));
            Map mapss= DbFactory.Open(DbFactory.FORM).selectOne("bdmodelTable.findTableByDbAndTablename",map);
            if(null!=mapss){
                tableNameList.add(mapss);
            }else {
                tableNameList.add(map);
            }
        }
        stmt.close();
        return tableNameList;
    }

    public Map getTableCloumnList(JSONObject pJson)  {
        List<Map> list =new ArrayList<>();
        List<Map> columnList = new ArrayList<>();
        Map tablemap = new HashMap();
        try {
            String dbType = pJson.getString("dbType");
            String tablename = pJson.getString("table_name");
            String dbname = pJson.getString("host_id");
            DatabaseMetaData databaseMetaData = DbFactory.Open(dbname).getConnection().getMetaData();
            //获取所有表
            ResultSet rs = databaseMetaData.getColumns(null, "%", tablename, "%");
            list = convertList(rs);
            rs.close();


            Map paramMap = new HashMap();
            paramMap.put("dbtype_id", dbType);
            paramMap.put("host_id", dbname);
            paramMap.put("table_name", tablename);
            SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);

            tablemap = sqlSession.selectOne("bdmodelTable.findTableByDbAndTablename", paramMap);
            Integer colId = sqlSession.selectOne("bdTableColumn.getMaxId");
            colId = colId == null ? 1 : colId;
            if (null != tablemap) {
                String tableId = tablemap.get("table_id").toString();

                for (int i = 0; i < list.size(); i++) {
                    Map resm = list.get(i);
                    String tabtype = resm.get("TYPE_NAME").toString();
                    String columnname = resm.get("COLUMN_NAME").toString();
                    String remarks = resm.get("REMARKS").toString();
                    String column_length = resm.get("COLUMN_SIZE").toString();
                    String column_decimal = resm.get("DECIMAL_DIGITS")==null?null:resm.get("DECIMAL_DIGITS").toString();
                    String column_isnull = resm.get("IS_NULLABLE").toString();
                    Map mapVal = new HashMap();
                    mapVal.put("id", null);
                    mapVal.put("table_id", tableId);
                    mapVal.put("column_name", columnname);
                    Integer count = sqlSession.selectOne("bdTableColumn.countColName", mapVal);
                    if (count == 0) {
                        mapVal.put("id", colId);
                        mapVal.put("column_length", column_length);
                        mapVal.put("column_type", tabtype);
                        mapVal.put("column_source", "");
                        mapVal.put("column_title", remarks);
                        mapVal.put("column_decimal", column_decimal);
                        mapVal.put("column_isnull", column_isnull);
                        sqlSession.insert("bdTableColumn.createTableColumn", mapVal);
                        colId++;
                    }
                }
                Map colm=new HashMap();
                colm.put("table_id",tableId);
                List<Map> newColumnlist=sqlSession.selectList("bdTableColumn.getBdTableColumnByTabId",colm);
                for(int v=0;v<newColumnlist.size();v++){
                    Map nm1=newColumnlist.get(v);
                    String column_names = nm1.get("column_name").toString();
                    String colid = nm1.get("id").toString();
                    Boolean bl=false;
                    for (int ii = 0; ii < list.size(); ii++) {
                        Map nm2 = list.get(ii);
                        String columnname = nm2.get("COLUMN_NAME").toString();
                        if(column_names.equalsIgnoreCase(columnname)){
                            bl=true;
                            break;
                        }
                    }
                    if(!bl){
                        Map  dmap = new HashMap();
                        dmap.put("id",colid);
                        sqlSession.delete("bdTableColumn.deleteBdTableColumnByColId",dmap);
                    }
                }
                columnList=sqlSession.selectList("bdTableColumn.getBdTableColumnByTabId",colm);
            } else {
                paramMap.put("dbtype_id", dbType);
                paramMap.put("host_id", dbname);
                paramMap.put("table_name", tablename);
                paramMap.put("table_title", "");//注释
                paramMap.put("source_id", "");//注释
                paramMap.put("url", "");//注释
                paramMap.put("table_ddl", "");
                paramMap.put("catalog_id", 1);
                paramMap.put("update_by", SysContext.getId());
                paramMap.put("create_by", SysContext.getId());
                String tableId = sqlSession.selectOne("bdmodelTable.getMaxId");
                tableId = tableId == null ? "1" : tableId;
                paramMap.put("table_id", tableId);
                sqlSession.insert("bdmodelTable.createBdTable", paramMap);
                tablemap=paramMap;
                for (int i = 0; i < list.size(); i++) {
                    Map resm = list.get(i);
                    String tabtype = resm.get("TYPE_NAME").toString();
                    String columnname = resm.get("COLUMN_NAME").toString();
                    String remarks = resm.get("REMARKS").toString();
                    String column_length = resm.get("COLUMN_SIZE").toString();
                    String column_decimal = resm.get("DECIMAL_DIGITS")==null?null:resm.get("DECIMAL_DIGITS").toString();
                    String column_isnull = resm.get("IS_NULLABLE").toString();
                    Map mapVal = new HashMap();
                    mapVal.put("id", colId);
                    mapVal.put("table_id", tableId);
                    mapVal.put("column_name", columnname);
                    mapVal.put("column_length", column_length);
                    mapVal.put("column_type", tabtype);
                    mapVal.put("column_source", "");
                    mapVal.put("column_title", remarks);
                    mapVal.put("column_decimal", column_decimal);
                    mapVal.put("column_isnull", column_isnull);
                    sqlSession.insert("bdTableColumn.createTableColumn", mapVal);
                    colId++;
                    columnList.add(mapVal);
                }


            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Map mapss=new HashMap();
        mapss.put("list",list);
        mapss.put("columnList",columnList);
        mapss.put("maintable",tablemap);
        return mapss;
    }

    /**
     * @方法描述：获取两个ArrayList的差集
     * @param firstArrayList 第一个ArrayList
     * @param secondArrayList 第二个ArrayList
     * @return resultList 差集ArrayList
     */
    public static List<Map> receiveDefectList(List<Map> firstArrayList, List<Map> secondArrayList) {
        List<Map> resultList = new ArrayList<Map>();
        LinkedList<Map> result = new LinkedList<Map>(firstArrayList);// 大集合用linkedlist
        HashSet<Map> othHash = new HashSet<Map>(secondArrayList);// 小集合用hashset
        Iterator<Map> iter = result.iterator();// 采用Iterator迭代器进行数据的操作
        while(iter.hasNext()){
            if(othHash.contains(iter.next())){
                iter.remove();
            }
        }
        resultList = new ArrayList<Map>(result);
        return resultList;
    }
}
