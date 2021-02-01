package root.report.algorithm.service;

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
public class AiModelService {

    private static Logger log = Logger.getLogger(AiModelService.class);

       public Map<String,Object> getListPage(Map<String,Object> map) {
        Map<String,Object> map1=new HashMap<>();
        try {
            SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
            RowBounds bounds = null;
            if (map == null) {
                bounds = RowBounds.DEFAULT;
            } else {
                Integer startIndex = Integer.parseInt(map.get("startIndex").toString());
                Integer perPage = Integer.parseInt(map.get("perPage").toString());
                if (startIndex == 1 || startIndex == 0) {
                    startIndex = 0;
                } else {
                    startIndex = (startIndex - 1) * perPage;
                }
                bounds = new PageRowBounds(startIndex, perPage);
            }
            List<Map<String, Object>> resultList = sqlSession.selectList("aimodel.getListPage", map, bounds);
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
    public Map saveOrUpdateAiModel(SqlSession sqlSession,JSONObject jsonObject){
        UserModel user = SysContext.getRequestUser();
        Map<String,Object> resultMap  = new HashMap<>();
        resultMap.put("status",true);
        Map<String,Object> map  = new HashMap<>();
        String id="";
        map.put("model_name",jsonObject.getString("model_name"));
        map.put("model_id",jsonObject.getString("model_id"));
        Integer count = sqlSession.selectOne("aimodel.count",map);
        if(count==0) {
            map.put("dataset_id", jsonObject.getString("dataset_id"));
            map.put("comment", jsonObject.getString("comment"));
            map.put("algorithm_id", jsonObject.getString("algorithm_id"));
            map.put("model_file_url", jsonObject.getString("model_file_url"));
            map.put("statues", jsonObject.getString("statues"));
            map.put("datasource_id", jsonObject.getString("datasource_id"));
            if (null == jsonObject.getString("model_id") || "".equals(jsonObject.getString("model_id"))) {
                Integer newId = sqlSession.selectOne("aimodel.getMaxId");
                newId = newId == null ? 1 : newId;
                map.put("model_id", newId);
                sqlSession.insert("aimodel.createAiModel", map);
                id = String.valueOf(map.get("id"));
                /**
                 * 调用  建中 训练模型方法
                 * */

            } else {
                sqlSession.update("aimodel.updateAiModel", map);
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
    public void deleteAiModelById(SqlSession sqlSession,int model_id){
            Map<String,Object> map=new HashMap();
            map.put("model_id",model_id);
            sqlSession.delete("aimodel.deleteAiModelByID",map);

    }

    public Map getAiModelByID(Map m) {
        return DbFactory.Open(DbFactory.FORM).selectOne("aimodel.getAiModelById",m);
    }


    public List<Map> getAllList() {
        return DbFactory.Open(DbFactory.FORM).selectList("aimodel.getAllList");
    }
}
