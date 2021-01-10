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
            List<Map<String, Object>> resultList = sqlSession.selectList("dbmodelTable.getListPage", map, bounds);
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
        map.put("table_title",jsonObject.getString("table_title"));//注释
        map.put("table_ddl",jsonObject.getString("table_ddl"));//SQL预览
        map.put("update_by",user.getId());
        if(null==jsonObject.getString("table_id")|| "".equals(jsonObject.getString("table_id"))){
            map.put("create_by",user.getId());
            Integer tableId= sqlSession.selectOne("dbmodelTable.getMaxId");
            tableId = tableId==null?1:tableId;
            map.put("table_id",tableId);
            sqlSession.insert("dbmodelTable.createBdTable",map);
            Map modelTable =new HashMap();
            modelTable.put("model_id",jsonObject.getString("model_id"));
            modelTable.put("table_id",tableId);
            sqlSession.insert("dbmodelTable.createBdModelTable",modelTable);
            id=String.valueOf(map.get("table_id"));
            /**
             * 保存字段列
             * */
            JSONArray columnList = jsonObject.getJSONArray(jsonObject.getString("columnlist"));
            Integer colId= sqlSession.selectOne("dbTableColumn.getMaxId");
            colId = colId==null?1:colId;
            insertColumnListItem(sqlSession,columnList,colId,tableId);
            /**
             * 保存索引
             * table_index
             * */
            //insertIndexListItem(sqlSession,indexList,colId,tableId);
            /**
             * 保存外键
             * table_fk
             * */
            //insertForeignKeyListItem(sqlSession,foreignKeyList,colId,tableId);
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

        }else{
            map.put("table_id",jsonObject.getString("table_id"));
            sqlSession.update("dbmodelTable.updateBdTable",map);
            id=jsonObject.getString("table_id");


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
            mapVal.put("id",colId);
            mapVal.put("table_id",tableId);
            mapVal.put("column_name",obj.getString("column_name"));
            mapVal.put("column_code",obj.getString("column_code"));
            mapVal.put("column_type",obj.getString("column_type"));
            mapVal.put("column_source",obj.getString("column_source"));
            mapVal.put("column_desc",obj.getString("column_desc"));
            sqlSession.insert("dbTableColumn.createTableColumn",mapVal);
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
            mapVal.put("column_code",obj.getString("column_code"));
            mapVal.put("column_type",obj.getString("column_type"));
            mapVal.put("column_source",obj.getString("column_source"));
            mapVal.put("column_desc",obj.getString("column_desc"));
            sqlSession.insert("dbTableColumn.createTableColumn",mapVal);
        }
        return colId;
    }
    /**
     * 保存外键
     * */
    public Integer insertForeignKeyListItem (SqlSession sqlSession,JSONArray columnList,Integer colId,Integer tableId) {
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
            mapVal.put("column_code",obj.getString("column_code"));
            mapVal.put("column_type",obj.getString("column_type"));
            mapVal.put("column_source",obj.getString("column_source"));
            mapVal.put("column_desc",obj.getString("column_desc"));
            sqlSession.insert("dbTableColumn.createTableColumn",mapVal);
        }
        return colId;
    }

    // 功能描述: 根据 dict_id 和 out_id 批量删除 func_dict的信息
    public void deletedbmodelTableById(SqlSession sqlSession,int model_id){
            Map<String,Object> map=new HashMap();
            map.put("model_id",model_id);
            sqlSession.delete("dbmodelTable.deletedbmodelTableByID",map);
    }

    public Map getdbmodelTableByID(Map m) {
        return DbFactory.Open(DbFactory.FORM).selectOne("dbmodelTable.getdbmodelTableById",m);
    }


    public List<Map> getAllList() {
        return DbFactory.Open(DbFactory.FORM).selectList("dbmodelTable.getAllList");
    }

    public List<Map> getTableListByModelId(JSONObject pjson) {
        return DbFactory.Open(DbFactory.FORM).selectList("dbmodelTable.getTableList",pjson);
    }

    public Map getModelTableById(JSONObject pJson) {
        return  DbFactory.Open(DbFactory.FORM).selectOne("dbmodelTable.getdbmodelTableById",pJson);
    }
}
