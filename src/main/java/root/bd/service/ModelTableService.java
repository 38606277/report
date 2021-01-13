package root.bd.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.form.constant.ColumnType;
import root.form.user.UserModel;
import root.report.datastorage.mysql.DataModelingService;
import root.report.db.DbFactory;
import root.report.sys.SysContext;

import java.util.*;

@Service
public class ModelTableService {

    private static Logger log = Logger.getLogger(ModelTableService.class);
    @Autowired
    private DataModelingService dataModelingService;

    public Map<String,Object> getListPage(Map<String,String> map) {
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
            List<Map<String, Object>> resultList = sqlSession.selectList("bdmodelTable.getListPage", map, bounds);
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
    public String saveOrUpdateBdModelTable(SqlSession sqlSession,JSONObject jsonObject){
        UserModel user = SysContext.getRequestUser();
        Map<String,Object> map  = new HashMap<>();
        String id="";
        Map<String,Object> mapmodel  = new HashMap<>();
        mapmodel.put("model_id",jsonObject.getString("model_id"));
        Map model=sqlSession.selectOne("bdmodel.getbdmodelById",mapmodel);
        map.put("table_name",jsonObject.getString("table_name"));
        map.put("table_id", jsonObject.getString("table_id"));
        Integer count = Integer.parseInt(sqlSession.selectOne("bdmodelTable.countTable",map));
        if(count==0) {
            map.put("table_title",jsonObject.getString("table_title"));//注释
            map.put("update_by",user.getId());
            String tableName = jsonObject.getString("table_name");
            String dbType=model.get("db_type").toString();
            if (null == jsonObject.getString("table_id") || "".equals(jsonObject.getString("table_id"))) {
                map.put("create_by", user.getId());
                String tableId = sqlSession.selectOne("bdmodelTable.getMaxId");
                tableId = tableId == null ? "1" : tableId;
                map.put("table_id", tableId);
                JSONArray columnList = jsonObject.getJSONArray("columnlist");
                String createSql="";

                if("mysql".equalsIgnoreCase(dbType)) {
                    if (columnList.size() > 0) {
                        StringBuffer sb = new StringBuffer();
                        //拼接建表sql
                        columnList.forEach(col -> {
                            JSONObject jsonCol = (JSONObject) col;
                            sb.append(jsonCol.getString("column_name") + " " + ColumnType.getDbType(jsonCol.getString("column_type")));
                            String length = jsonCol.getString("column_length");
                            String columnDecimal = jsonCol.getString("column_decimal") == null ? "0" : jsonCol.getString("column_decimal");
                            if (null != length && !"".equals(length) && columnDecimal!=null && !"".equalsIgnoreCase(columnDecimal)) {
                                sb.append("(" + length + "," + columnDecimal + ")");
                            }else if (null != length && !"".equals(length)){
                                sb.append("(" + length +  ")");
                            }
                            String isnull = jsonCol.getString("column_isnull");
                            if (null != isnull && !"".equals(isnull)) {
                                sb.append(" NOT NULL ");
                            } else {
                                sb.append(" DEFAULT NULL ");
                            }
                            sb.append(",");
                        });
                        createSql = "create table " + jsonObject.getString("table_name") + "(" + sb.deleteCharAt(sb.length() - 1) + ")";
                        map.put("table_ddl", createSql);//SQL预览
                    }

                }else  if("hive".equalsIgnoreCase(dbType)) {
                    JSONObject maphive=new JSONObject();
                    maphive.put("tableName",tableName);
                    List<JSONObject> objectList=new ArrayList<>();
                    if (columnList.size() > 0) {
                        //拼接建表sql
                        columnList.forEach(col -> {
                            JSONObject jsonCol = (JSONObject) col;
                            JSONObject newjson = new JSONObject();
                            newjson.put("fieldName", jsonCol.getString("column_name"));
                            newjson.put("fieldType", jsonCol.getString("column_type"));
                            objectList.add(newjson);
                        });
                    }
                    maphive.put("tableFields",objectList);
                    dataModelingService.createHiveTable(maphive);
                }
                sqlSession.insert("bdmodelTable.createBdTable", map);
                Map modelTable = new HashMap();
                modelTable.put("model_id", jsonObject.getString("model_id"));
                modelTable.put("table_id", tableId);
                sqlSession.insert("bdmodelTable.createBdModelTable", modelTable);


                id = String.valueOf(map.get("table_id"));
                /**
                 * 保存字段列
                 * */

                Integer colId = sqlSession.selectOne("bdTableColumn.getMaxId");
                colId = colId == null ? 1 : colId;
                insertColumnListItem(sqlSession, columnList, colId, tableId);

                /**
                 * 保存外键
                 * table_fk
                 * */
                JSONArray linkList = jsonObject.getJSONArray("linkList");
                Integer linkId = sqlSession.selectOne("bdTableColumn.getLinkMaxId");
                linkId = linkId == null ? 1 : linkId;

                if(linkList.size()>0) {
                    insertLinkListItem(sqlSession, linkList, linkId, tableId, tableName);
                }
                if("mysql".equalsIgnoreCase(dbType)) {
                    if (columnList.size() > 0) {
                        sqlSession.update("bdTableColumn.createNewTable", createSql);
                        sqlSession.commit();
                    }
                }
                /**
                 * 保存索引
                 * table_index
                 * */
                //insertIndexListItem(sqlSession,indexList,colId,tableId);

                /**
                 * 保存触发器
                 *table_target
                 * */

                /**
                 * 保存选项
                 *
                 * */

                /**
                 * 保存注释
                 * bd_table 表 table_title
                 * 保存SQL预览
                 * bd_table 表 table_ddl
                 * */

            } else {
                JSONArray columnList = jsonObject.getJSONArray("columnlist");
                String createSql="";
                if("mysql".equalsIgnoreCase(dbType)) {
                    if (columnList.size() > 0) {
                        StringBuffer sb = new StringBuffer();
                        //拼接建表sql
                        columnList.forEach(col -> {
                            JSONObject jsonCol = (JSONObject) col;
                            sb.append(jsonCol.getString("column_name") + " " + ColumnType.getDbType(jsonCol.getString("column_type")));
                            String length = jsonCol.getString("column_length");
                            if (null != length && !"".equals(length)) sb.append("(" + length + ")");
                            String isnull = jsonCol.getString("column_isnull");
                            if (null != isnull && !"".equals(isnull)) {
                                sb.append(" NOT NULL ");
                            } else {
                                sb.append(" DEFAULT NULL ");
                            }
                            sb.append(",");
                        });
                        createSql = "create table " + jsonObject.getString("table_name") + "(" + sb.deleteCharAt(sb.length() - 1) + ")";
                        map.put("table_ddl", createSql);//SQL预览
                    }
                    sqlSession.update("bdmodelTable.updateBdTable", map);
                }else  if("hive".equalsIgnoreCase(dbType)) {
                    JSONObject maphive=new JSONObject();
                    maphive.put("tableName",tableName);
                    List<JSONObject> objectList=new ArrayList<>();
                    if (columnList.size() > 0) {
                        //拼接建表sql
                        columnList.forEach(col -> {
                            JSONObject jsonCol = (JSONObject) col;
                            JSONObject newjson = new JSONObject();
                            newjson.put("fieldName", jsonCol.getString("column_name"));
                            newjson.put("fieldType", jsonCol.getString("column_type"));
                            objectList.add(newjson);
                        });
                    }
                    maphive.put("tableFields",objectList);
                    dataModelingService.createHiveTable(maphive);
                }
                String tableId = jsonObject.getString("table_id");
                /**
                 * 保存字段列
                 * */
                if(columnList.size()>0) {
                    updateColumnListItem(sqlSession, columnList, null, tableId, tableName,model);
                }
                /**
                 * 保存外键
                 * table_fk
                 * */

                JSONArray linkList = jsonObject.getJSONArray("linkList");
                if(linkList.size()>0) {
                    updateLinkListItem(sqlSession, linkList, null, tableId, tableName);
                }
            }
        }else{
            id="模型已存在";
        }
        return id;
    }

    /**
     * 保存字段
     * */
    public Integer insertColumnListItem (SqlSession sqlSession,JSONArray columnList,Integer colId,String tableId) {
        if (columnList.isEmpty()) {
            return colId;
        }
        for (int i = 0; i < columnList.size(); i++) {
            colId++;
            JSONObject obj = columnList.getJSONObject(i);
            Map mapVal=new HashMap();
            mapVal.put("id",null);
            mapVal.put("table_id",tableId);
            mapVal.put("column_name",obj.getString("column_name"));
            Integer count = sqlSession.selectOne("bdTableColumn.countColName",mapVal);
            if(count==0) {
                mapVal.put("id",colId);
                mapVal.put("column_length", obj.getString("column_length"));
                mapVal.put("column_type", obj.getString("column_type"));
                mapVal.put("column_source", obj.getString("column_source"));
                mapVal.put("column_title", obj.getString("column_title"));
                mapVal.put("column_decimal", obj.getString("column_decimal"));
                mapVal.put("column_isnull", obj.getString("column_isnull"));
                sqlSession.insert("bdTableColumn.createTableColumn", mapVal);
            }
        }
        return colId;
    }


    /**
     * 编辑字段列
     * */
    public Integer updateColumnListItem (SqlSession sqlSession,JSONArray columnList,Integer newcolId,String tableId,String tablename,Map model) {
        if (columnList.isEmpty()) {
            return 0;
        }
        String dbtype=model.get("db_type").toString();
        List<String> one = Arrays.asList("int", "tinyint", "integer","bigint","varchar","varchar2","char","");
        List<String> two = Arrays.asList("double", "float", "decimal","numeric");
        List<String> three = Arrays.asList("date", "datetime", "blob","text","tinytext","longtext","longblob","tinyblob");
        for (int i = 0; i < columnList.size(); i++) {
            JSONObject obj = columnList.getJSONObject(i);
            if(null!=obj.getString("id") && !"".equals(obj.getString("id"))){
                Map columnMap = sqlSession.selectOne("bdTableColumn.getBdTableColumnById",Integer.parseInt(obj.getString("id")));
                Map mapVal=new HashMap();
                mapVal.put("id",obj.getString("id"));
                mapVal.put("table_id",obj.getString("table_id"));
                mapVal.put("column_length", obj.getString("column_length"));
                mapVal.put("column_type", obj.getString("column_type"));
                mapVal.put("column_source", obj.getString("column_source"));
                mapVal.put("column_title", obj.getString("column_title"));
                mapVal.put("column_decimal", obj.getString("column_decimal"));
                mapVal.put("column_isnull", obj.getString("column_isnull"));
                sqlSession.update("bdTableColumn.updateTableColumn", mapVal);

                if("mysql".equalsIgnoreCase(dbtype)) {
                    String sql = "ALTER TABLE " + tablename;
                    if (columnMap.get("column_name").toString().equalsIgnoreCase(obj.getString("column_name"))) {
                        sql = sql + " MODIFY " + obj.getString("column_name") + " ";
                    } else {
                        sql = sql + " MODIFY " + columnMap.get("column_name") + " " + obj.getString("column_name") + " ";
                    }
                    String colname = "", columnLength = "", columnType = "", columnTitle = "", columnDecimal = "", columnIsnull = "";
                    columnType = obj.getString("column_type");
                    columnDecimal = obj.getString("column_decimal");
                    columnLength = obj.getString("column_length");
                    columnIsnull = obj.getString("columnIsnull");
                    columnTitle = obj.getString("column_title");
                    if (one.contains(columnType)) {
                        sql = sql + columnType + "(" + columnLength + ")";
                    }
                    if (two.contains("columnType")) {
                        sql = sql + columnType + "(" + columnLength + "," + columnDecimal + ") ";
                    }
                    if (three.contains("columnType")) {
                        sql = sql + columnType;
                    }
                    if ("2".equalsIgnoreCase(columnIsnull)) {
                        sql = sql + " DEFAULT NULL ";
                    } else {
                        sql = sql + " NOT NULL ";
                    }
                    if (!"".equalsIgnoreCase(columnTitle)) {
                        sql = sql + " COMMENT '" + columnTitle + "'";
                    }
                    sqlSession.update("bdTableColumn.updateTableColumnForDB", sql);
                }
            }else{
                String colname="",columnLength="",columnType="",columnTitle="",columnDecimal="",columnIsnull="";
                Map mapVal=new HashMap();
                mapVal.put("id",null);
                mapVal.put("table_id",tableId);
                mapVal.put("column_name",obj.getString("column_name"));
                Integer count = sqlSession.selectOne("bdTableColumn.countColName",mapVal);
                if(count==0) {
                    Integer colId = sqlSession.selectOne("bdTableColumn.getMaxId");
                    colId = colId == null ? 1 : colId;
                    mapVal.put("id",colId);
                    mapVal.put("column_length", obj.getString("column_length"));
                    mapVal.put("column_type", obj.getString("column_type"));
                    mapVal.put("column_source", obj.getString("column_source"));
                    mapVal.put("column_title", obj.getString("column_title"));
                    mapVal.put("column_decimal", obj.getString("column_decimal"));
                    mapVal.put("column_isnull", obj.getString("column_isnull"));
                    sqlSession.insert("bdTableColumn.createTableColumn", mapVal);
                    if("mysql".equalsIgnoreCase(dbtype)) {
                        columnType = obj.getString("column_type");
                        columnDecimal = obj.getString("column_decimal");
                        columnLength = obj.getString("column_length");
                        columnIsnull = obj.getString("columnIsnull");
                        columnTitle = obj.getString("column_title");
                        String sql = "ALTER TABLE " + tablename + " ADD COLUMN " + obj.getString("column_name") + " ";
                        if (one.contains(columnType)) {
                            sql = sql + columnType + "(" + columnLength + ")";
                        }
                        if (two.contains(columnType)) {
                            sql = sql + columnType + "(" + columnLength + "," + columnDecimal + ") ";
                        }
                        if (three.contains(columnType)) {
                            sql = sql + columnType;
                        }
                        if ("2".equalsIgnoreCase(columnIsnull)) {
                            sql = sql + " DEFAULT NULL ";
                        } else {
                            sql = sql + " NOT NULL ";
                        }
                        if (!"".equalsIgnoreCase(columnTitle)) {
                            sql = sql + " COMMENT '" + columnTitle + "'";
                        }
                        sqlSession.update("bdTableColumn.updateTableColumnForDB", sql);
                    }
                }
            }

        }
        return 0;
    }
    /**
     * 保存外键
     * */
    public Integer insertLinkListItem (SqlSession sqlSession,JSONArray linkList,Integer linkId,String tableId,String tableName) {
        if (linkList.isEmpty()) {
            return linkId;
        }
        for (int i = 0; i < linkList.size(); i++) {
            linkId++;
            JSONObject obj = linkList.getJSONObject(i);
            Map mapVal=new HashMap();
            mapVal.put("id",null);
            mapVal.put("table_id",tableId);
            mapVal.put("name",obj.getString("name"));
            Integer count = sqlSession.selectOne("bdTableColumn.countLinkName",mapVal);
            if(count==0) {
                mapVal.put("id", linkId);
                mapVal.put("table_name", tableName);
                mapVal.put("column_name", obj.getString("column_name"));
                mapVal.put("link_table_name", obj.getString("link_table_name"));
                mapVal.put("link_column_name", obj.getString("link_column_name"));
                sqlSession.insert("bdTableColumn.createTableLink", mapVal);
            }
        }
        return linkId;
    }

    /**
     * 编辑外键
     * */
    public Integer updateLinkListItem (SqlSession sqlSession,JSONArray linkList,Integer ollinkId,String tableId,String tableName) {
        if (linkList.isEmpty()) {
            return ollinkId;
        }
        for (int i = 0; i < linkList.size(); i++) {
            JSONObject obj = linkList.getJSONObject(i);
            if(null!=obj.getString("id") && !"".equalsIgnoreCase(obj.getString("id"))){
                Map mapVal = new HashMap();
                mapVal.put("id", obj.getString("id"));
                mapVal.put("table_id", tableId);
                mapVal.put("name", obj.getString("name"));
                Integer count = sqlSession.selectOne("bdTableColumn.countLinkName", mapVal);
                if (count == 0) {
                    Integer linkId = sqlSession.selectOne("bdTableColumn.getLinkMaxId");
                    linkId = linkId == null ? 1 : linkId;
                    mapVal.put("id", linkId);
                    mapVal.put("table_name", tableName);
                    mapVal.put("column_name", obj.getString("column_name"));
                    mapVal.put("link_table_name", obj.getString("link_table_name"));
                    mapVal.put("link_column_name", obj.getString("link_column_name"));
                    sqlSession.update("bdTableColumn.createTableLink", mapVal);
                }
            }else {
                Map mapVal = new HashMap();
                mapVal.put("id", null);
                mapVal.put("table_id", tableId);
                mapVal.put("name", obj.getString("name"));
                Integer count = sqlSession.selectOne("bdTableColumn.countLinkName", mapVal);
                if (count == 0) {
                    Integer linkId = sqlSession.selectOne("bdTableColumn.getLinkMaxId");
                    linkId = linkId == null ? 1 : linkId;
                    mapVal.put("id", linkId);
                    mapVal.put("table_name", tableName);
                    mapVal.put("column_name", obj.getString("column_name"));
                    mapVal.put("link_table_name", obj.getString("link_table_name"));
                    mapVal.put("link_column_name", obj.getString("link_column_name"));
                    sqlSession.insert("bdTableColumn.createTableLink", mapVal);
                }
            }
        }
        return ollinkId;
    }

    /**
     * 保存索引
     * */
    public Integer insertIndexListItem (SqlSession sqlSession,JSONArray columnList,Integer colId,Integer tableId) {
        return colId;
    }


    // 功能描述: 根据 dict_id 和 out_id 批量删除 func_dict的信息
    public void deletedbmodelTableById(SqlSession sqlSession,String table_id){
        Map<String,Object> map=new HashMap();
        map.put("table_id",table_id);
        Map model =sqlSession.selectOne("bdmodelTable.getModelBytabId",table_id);
        Map table=sqlSession.selectOne("bdTableColumn.getBdTableById",map);
        sqlSession.delete("bdmodelTable.deleteBdModelTableByTableId",map);
        sqlSession.delete("bdmodelTable.deleteBdTableByID",map);
        sqlSession.delete("bdTableColumn.deleteBdTableColumnByTablId",map);
        sqlSession.delete("bdTableColumn.deleteBdLinkByTableId",map);
        if("mysql".equalsIgnoreCase(model.get("db_type").toString())) {
            try {
                sqlSession.delete("bdTableColumn.dropNewTable", table.get("table_name"));
            }catch (Exception e){
                e.printStackTrace();
            }finally {

            }

        }

    }

    public List<Map> getTableListByModelId(JSONObject pjson) {
        return DbFactory.Open(DbFactory.FORM).selectList("bdmodelTable.getTableList",pjson);
    }

    public Map getModelTableById(JSONObject pJson) {
        Map resMap=new HashMap();
        Map  table=DbFactory.Open(DbFactory.FORM).selectOne("bdTableColumn.getBdTableById",pJson);
        List<Map> columnList=DbFactory.Open(DbFactory.FORM).selectList("bdTableColumn.getBdTableColumnByTabId",pJson);
        List<Map> linkList=DbFactory.Open(DbFactory.FORM).selectList("bdTableColumn.getBdTableLinkByTabId",pJson);
        resMap.put("table",table);
        resMap.put("column",columnList);
        resMap.put("tableLink",linkList);
        return resMap;
    }

    public Map getTableColumnById(JSONObject pJson) {
        Map columnMap = DbFactory.Open(DbFactory.FORM).selectOne("bdTableColumn.getBdTableColumnById",Integer.parseInt(pJson.getString("id")));
        return columnMap;
    }
}
