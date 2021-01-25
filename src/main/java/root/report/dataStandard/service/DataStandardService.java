package root.report.dataStandard.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.bd.service.ModelTableService;
import root.form.user.UserModel;
import root.report.db.DbFactory;
import root.report.sys.SysContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataStandardService {

    private static Logger log = Logger.getLogger(DataStandardService.class);

//    @Autowired
//    private DataStandardTableService modelTableService;

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
            List<Map<String, Object>> resultList = sqlSession.selectList("dataStandard.getListPage", map, bounds);
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
    public Map saveOrUpdateBdDataStandard(SqlSession sqlSession,JSONObject jsonObject){
        UserModel user = SysContext.getRequestUser();
        Map<String,Object> resultMap  = new HashMap<>();
        resultMap.put("status",true);
        Map<String,Object> map  = new HashMap<>();
        String id="";
        map.put("model_name",jsonObject.getString("model_name"));
        map.put("model_id",jsonObject.getString("model_id"));
        Integer count = sqlSession.selectOne("dataStandard.count",map);
        if(count==0) {
            map.put("db_source", jsonObject.getString("db_source"));
            map.put("db_type", jsonObject.getString("db_type"));
            map.put("create_by", user.getId());
            map.put("update_by", user.getId());
            if (null == jsonObject.getString("model_id") || "".equals(jsonObject.getString("model_id"))) {
                Integer newId = sqlSession.selectOne("dataStandard.getMaxId");
                newId = newId == null ? 1 : newId;
                map.put("model_id", newId);
                sqlSession.insert("dataStandard.createDataStandard", map);
                id = String.valueOf(map.get("id"));
            } else {

                sqlSession.update("dataStandard.updateBdDataStandard", map);
                id = jsonObject.getString("model_id");
            }
            resultMap.put("info",id);
            return resultMap;
        }else{
            resultMap.put("status",false);
            resultMap.put("info","模型名称已存在");
            return resultMap;
        }

    }


    // 功能描述: 根据 dict_id 和 out_id 批量删除 func_dict的信息
    public void deleteBdmodelById(SqlSession sqlSession,int model_id){
            Map<String,Object> map=new HashMap();
            map.put("model_id",model_id);
            List<String> tabListId=sqlSession.selectList("dataStandard.getbdmodelId",map);
            for(int i=0;i<tabListId.size();i++){
                //modelTableService.deletedbmodelTableById(sqlSession,tabListId.get(i));
            }
            sqlSession.delete("dataStandard.deleteDataStandardByID",map);

    }

    public Map getBdmodelByID(Map m) {
        return DbFactory.Open(DbFactory.FORM).selectOne("dataStandard.getbdmodelById",m);
    }


    public List<Map> getAllList() {
        return DbFactory.Open(DbFactory.FORM).selectList("dataStandard.getAllList");
    }

    public List<Map> getStandardCatalogByPid(Map map) {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        List<Map> list= sqlSession.selectList("dataStandard.getCatalogByPid",map);
        for(int i=0;i<list.size();i++){
            map.put("catalog_pid",list.get(i).get("catalog_id"));
            List<Map> chilerenlist= sqlSession.selectList("dataStandard.getCatalogByPid",map);
            if(null!=chilerenlist && chilerenlist.size()>0){
                list.get(i).put("children",childrenListItem(sqlSession,chilerenlist));
            }
        }
        return list;
    }

    public List<Map> childrenListItem (SqlSession sqlSession,List<Map> list) {
        if (list.isEmpty()) {
            return list;
        }
        Map map=new HashMap();
        for(int i=0;i<list.size();i++){
            map.put("catalog_pid",list.get(i).get("catalog_id"));
            List<Map> chilerenlist= sqlSession.selectList("dataStandard.getCatalogByPid",map);
            if(null!=chilerenlist && chilerenlist.size()>0){
                list.get(i).put("children",childrenListItem(sqlSession,chilerenlist));
            }
        }
        return list;
    }

    public Map<String, Object> createOrUpdateCatalog(SqlSession sqlSession, JSONObject jsonObject) {
        Map<String,Object> resultMap  = new HashMap<>();
        resultMap.put("status",true);
        Map<String,Object> map  = new HashMap<>();
        String id="";
        map.put("catalog_name",jsonObject.getString("catalog_name"));
        map.put("catalog_id",jsonObject.getString("catalog_id"));
        Integer count = sqlSession.selectOne("dataStandard.count",map);
        if(count==0) {
            map.put("catalog_pid", jsonObject.getString("catalog_pid"));
            if (null == jsonObject.getString("catalog_id") || "".equals(jsonObject.getString("catalog_id"))) {
                Integer newId = sqlSession.selectOne("dataStandard.getCatalogMaxId");
                newId = newId == null ? 1 : newId;
                map.put("catalog_id", newId);
                sqlSession.insert("dataStandard.createCatalog", map);
                id = String.valueOf(map.get("catalog_id"));
            } else {
                sqlSession.update("dataStandard.updateCatalog", map);
                id = jsonObject.getString("catalog_id");
            }
            resultMap.put("info",id);
            return resultMap;
        }else{
            resultMap.put("status",false);
            resultMap.put("info","名称已存在");
            return resultMap;
        }
    }
}
