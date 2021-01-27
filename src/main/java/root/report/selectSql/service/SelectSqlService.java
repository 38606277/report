package root.report.selectSql.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import root.report.db.DbFactory;

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

    public List<Map<String, Object>> excueSelectSql(String selectsql) {
        try {
            return DbFactory.Open(DbFactory.FORM).selectList("selectSql.tempSql",selectsql);
        }catch (Exception e){
            e.printStackTrace();
            if(SQLSyntaxErrorException)
            return null;
        }

    }
}
