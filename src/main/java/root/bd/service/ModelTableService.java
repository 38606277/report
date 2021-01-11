package root.bd.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import root.form.user.UserModel;
import root.report.db.DbFactory;
import root.report.sys.SysContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModelTableService {

    private static Logger log = Logger.getLogger(ModelTableService.class);

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
        map.put("model_id",jsonObject.getString("model_id"));
        map.put("table_name",jsonObject.getString("table_name"));
        map.put("table_id", jsonObject.getString("table_id"));
        Integer count = sqlSession.selectOne("bdmodelTable.countTable",map);
        if(count==0) {
            map.put("table_title",jsonObject.getString("table_title"));//注释
            map.put("table_ddl",jsonObject.getString("table_ddl"));//SQL预览
            map.put("update_by",user.getId());
            if (null == jsonObject.getString("table_id") || "".equals(jsonObject.getString("table_id"))) {
                map.put("create_by", user.getId());
                Integer tableId = sqlSession.selectOne("bdmodelTable.getMaxId");
                tableId = tableId == null ? 1 : tableId;
                map.put("table_id", tableId);
                sqlSession.insert("bdmodelTable.createBdTable", map);
                Map modelTable = new HashMap();
                modelTable.put("model_id", jsonObject.getString("model_id"));
                modelTable.put("table_id", tableId);
                sqlSession.insert("bdmodelTable.createBdModelTable", modelTable);
                id = String.valueOf(map.get("table_id"));
                /**
                 * 保存字段列
                 * */
                JSONArray columnList = jsonObject.getJSONArray(jsonObject.getString("columnlist"));
                Integer colId = sqlSession.selectOne("dbTableColumn.getMaxId");
                colId = colId == null ? 1 : colId;
                insertColumnListItem(sqlSession, columnList, colId, tableId);

                /**
                 * 保存外键
                 * table_fk
                 * */
                JSONArray linkList = jsonObject.getJSONArray(jsonObject.getString("linkList"));
                Integer linkId = sqlSession.selectOne("dbTableColumn.getLinkMaxId");
                linkId = linkId == null ? 1 : linkId;
                String tableName = map.get("table_name").toString();
                insertLinkListItem(sqlSession, linkList, linkId, tableId, tableName);

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
                sqlSession.update("bdmodelTable.updateBdTable", map);
                id = jsonObject.getString("table_id");


            }
        }else{
            id="模型已存在";
        }
        return id;
    }

    /**
     * 保存字段
     * */
    public Integer insertColumnListItem (SqlSession sqlSession,JSONArray columnList,Integer colId,Integer tableId) {
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
                sqlSession.insert("dbTableColumn.createTableColumn", mapVal);
            }
        }
        return colId;
    }
    /**
     * 保存索引
     * */
    public Integer insertIndexListItem (SqlSession sqlSession,JSONArray columnList,Integer colId,Integer tableId) {
        if (columnList.isEmpty()) {
            return colId;
        }
        for (int i = 0; i < columnList.size(); i++) {
            colId++;
            JSONObject obj = columnList.getJSONObject(i);
            Map mapVal=new HashMap();
            mapVal.put("id",colId);
            mapVal.put("table_id",tableId);
            mapVal.put("column_name",obj.getString("column_name"));
            mapVal.put("column_length",obj.getString("column_length"));
            mapVal.put("column_type",obj.getString("column_type"));
            mapVal.put("column_source",obj.getString("column_source"));
            mapVal.put("column_title",obj.getString("column_title"));
            mapVal.put("column_decimal",obj.getString("column_decimal"));
            mapVal.put("column_isnull",obj.getString("column_isnull"));
            sqlSession.insert("dbTableColumn.createTableColumn",mapVal);
        }
        return colId;
    }
    /**
     * 保存外键
     * */
    public Integer insertLinkListItem (SqlSession sqlSession,JSONArray linkList,Integer linkId,Integer tableId,String tableName) {
        if (linkList.isEmpty()) {
            return linkId;
        }
        for (int i = 0; i < linkList.size(); i++) {
            linkId++;
            JSONObject obj = linkList.getJSONObject(i);
            Map mapVal=new HashMap();
            mapVal.put("id",linkId);
            mapVal.put("table_id",tableId);
            mapVal.put("table_name",tableName);
            mapVal.put("column_name",obj.getString("column_name"));
            mapVal.put("link_table_name",obj.getString("link_table_name"));
            mapVal.put("link_column_name",obj.getString("link_column_name"));
            sqlSession.insert("dbTableColumn.createTableLink",mapVal);
        }
        return linkId;
    }

    // 功能描述: 根据 dict_id 和 out_id 批量删除 func_dict的信息
    public void deletedbmodelTableById(SqlSession sqlSession,int model_id){
            Map<String,Object> map=new HashMap();
            map.put("model_id",model_id);
            sqlSession.delete("bdmodelTable.deleteBdModelTableByTableIdOrModelId",map);



    }

    public Map getdbmodelTableByID(Map m) {
        return DbFactory.Open(DbFactory.FORM).selectOne("bdmodelTable.getdbmodelTableById",m);
    }


    public List<Map> getAllList() {
        return DbFactory.Open(DbFactory.FORM).selectList("bdmodelTable.getAllList");
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
}
