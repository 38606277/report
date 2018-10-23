package root.report.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.report.db.DbFactory;
import root.report.util.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DictService {

    private static Logger log = Logger.getLogger(DictService.class);


    public List<Map<String,String>> getAllDictName(){
        List<Map<String,String>> resultList = new  ArrayList<Map<String,String>>();
        try
        {
            SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
            resultList=sqlSession.selectList("dict.getAllDictName");
            return resultList;

        }catch (Exception ex){

            throw  ex;
        }
    }

    public List<Map<String,String>> getDictValueByID(String dict_id){
        List<Map<String,String>> resultList = new  ArrayList<Map<String,String>>();
        try
        {
            SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
            Map<String,Object> map = new HashMap<>();
            map.put("dict_id",dict_id);
            resultList=sqlSession.selectList("dict.getDictValueByID");
            return resultList;

        }catch (Exception ex){

            throw  ex;
        }
    }

    /**
     * 功能描述: 根据JSON数据解析 对应数据，生成func_dict记录
     */
    public String createFuncDict(SqlSession sqlSession,JSONObject jsonObject){
        Map<String,Object> map  = new HashMap<>();
        map.put("dict_name",jsonObject.getString("dict_name"));
        map.put("dict_desc",jsonObject.getString("dict_desc"));
        map.put("dict_db",jsonObject.getString("dict_db"));
        map.put("loaddata_mode",jsonObject.getString("loaddata_mode"));
        map.put("loaddata_state",jsonObject.getString("loaddata_state"));
        sqlSession.insert("dict.createFuncDict",map);
        return String.valueOf(map.get("id"));
    }

    /**
     * 功能描述: 根据JSON数据解析对应数据，生成func_dict_out 记录
     */
    public void createFuncDictOut(SqlSession sqlSession,JSONObject jsonObject,String dict_id){
        Map<String,Object> map  = new HashMap<>();
        map.put("dict_id",dict_id);
        JSONArray jsonArray = jsonObject.getJSONArray("out");
        for(int i=0;i<jsonArray.size();i++){
            JSONObject outJson = jsonArray.getJSONObject(i);
            map.put("out_id",outJson.getString("out_id"));
            map.put("out_name",outJson.getString("out_name"));
            map.put("datatype",outJson.getString("datatype"));
            sqlSession.insert("dict.createFuncDictOut",map);
        }
    }

    // 功能描述: 根据 dict_id 批量删除 func_dict的信息
    public void deleteFuncDict(SqlSession sqlSession,JSONArray jsonArray){
       for(int i=0;i<jsonArray.size();i++){
           JSONObject jsonObject = jsonArray.getJSONObject(i);
           sqlSession.delete("dict.deleteFuncDict",jsonObject.getIntValue("dict_id"));
       }
    }

    // 功能描述: 根据 dict_id 和 out_id 批量删除 func_dict的信息
    public void deleteFuncDictOut(SqlSession sqlSession,JSONArray jsonArray){
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Map<String,Object> map  = new HashMap<>();
            map.put("dict_id",jsonObject.getIntValue("dict_id"));
            map.put("out_id",jsonObject.getString("out_id"));
            sqlSession.delete("dict.deleteFuncDictOut",map);
        }
    }

    // 功能描述 : 修改 func_dict_out 表的信息
    public void updateFuncDictOut(SqlSession sqlSession,JSONObject jsonObject){
        JSONArray jsonArray = jsonObject.getJSONArray("out");
        Map<String,Object> map  = new HashMap<>();
        map.put("dict_id",jsonObject.getIntValue("dict_id"));
        for(int i=0;i<jsonArray.size();i++){
            JSONObject deleteJson = jsonArray.getJSONObject(i);
            map.put("out_id",deleteJson.getString("out_id"));
            sqlSession.delete("dict.deleteFuncDictOut",map);
        }
        String dict_id = String.valueOf(jsonObject.getIntValue("dict_id"));
        this.createFuncDictOut(sqlSession,jsonObject,dict_id);
    }

    // 功能描述 : 修改 func_dict 表的信息
    public  void updateFuncDict(SqlSession sqlSession,JSONObject jsonObject){
        Map<String,Object> map  = new HashMap<>();
        map.put("dict_id",jsonObject.getIntValue("dict_id"));
        map.put("dict_name",jsonObject.getString("dict_name"));
        map.put("dict_desc",jsonObject.getString("dict_desc"));
        map.put("dict_db",jsonObject.getString("dict_db"));
        map.put("loaddata_mode",jsonObject.getString("loaddata_mode"));
        map.put("loaddata_state",jsonObject.getString("loaddata_state"));
        sqlSession.update("dict.updateFuncDict",map);
    }

    // 功能描述 : 根据dict_id 查询 func_dict信息
    public JSONObject getFuncDictInfo(SqlSession sqlSession,int dict_id){
        Map<String,Object>  map =   sqlSession.selectOne("dict.getFuncDictInfoByDictId",dict_id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dict_id",map.get("dict_id"));
        jsonObject.put("dict_name",map.get("dict_name"));
        jsonObject.put("dict_desc",map.get("dict_desc"));
        jsonObject.put("dict_db",map.get("dict_db"));
        jsonObject.put("loaddata_mode",map.get("loaddata_mode"));
        jsonObject.put("loaddata_state",map.get("loaddata_state"));

        List<Map<String,Object>> listMap = sqlSession.selectList("dict.getFuncDictOutInfoByDicId",dict_id);
        jsonObject.put("out",JSON.toJSONString(listMap));

        return jsonObject;
    }

}
