package root.report.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import root.report.db.DbFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Component
public class FunctionService {

    private static Logger log = Logger.getLogger(FunctionService.class);

    public String getFunctionName(Map<String,String> map){
        List<Map<String,String>> resultList = new  ArrayList<Map<String,String>>();
        List<Map<String,String>> selectMap = DbFactory.Open(DbFactory.FORM).selectList("function.getFunctionName",map);
        if(selectMap!=null && selectMap.size()>0){
            resultList.addAll(selectMap);
        }
        // 默认返回第一个
        return JSONObject.toJSONString(resultList.get(0));
    }


    /**
     *
     * 功能描述: 往func_name表增加记录
     */
    public int addFunctionName(List<Map<String,String>> mapList){
        // 事务管理 放在了controller层,return 0 意味着要进行事务回滚
        for(Map<String,String> tempMap : mapList){
            int number = DbFactory.Open(DbFactory.FORM).insert("function.addFunctionName",tempMap);
            if( number!=1 ) {
                return 0;
            }
        }
        return 1;
    }

    /**
     *
     * 功能描述: 根据传递过来的JSONObject，对其解析，然后往func_name表增加记录
     *
     */
    public int addFunctionNameForJson(JSONObject jsonObject){
        List<Map<String,String>>  tempTestMapList = new ArrayList<Map<String,String>>();
        // '${class}', '${name}', '${desc}', '${type}', '${file}', '${url}'
        Map<String,String> tempMap = new HashMap<String,String>();
        JSONObject jsonParse = jsonObject.getJSONObject("comment");
        tempMap.put("class",jsonObject.getString("namespace"));
        tempMap.put("name",jsonObject.getString("id"));
        tempMap.put("desc",jsonParse.getString("desc"));
        tempMap.put("type",jsonParse.getString("type"));
        // tempMap.put("file",null);
        // tempMap.put("url",null);
        tempTestMapList.add(tempMap);
        return this.addFunctionName(tempTestMapList);
    }


    /**
     *
     * 功能描述: 删除func_name当中的记录
     */
    public void deleteFunctionName(int funcId){
        DbFactory.Open(DbFactory.FORM).delete("function.deleteFunctionName",funcId);
    }

    /**
     *
     * 功能描述: 查询 func_in表
     *
     */
   /* public String getFunctionIn(List<Map<String,String>> map){
        return "";
    } */

    /**
     *
     * 功能描述: 增加func_in表记录
     *
     */
    public int addFunctionIn(List<Map<String,String>> mapList){
        // 事务管理 放在了controller层,return 0 意味着要进行事务回滚
        for(Map<String,String> tempMap : mapList){
            int number = DbFactory.Open(DbFactory.FORM).insert("function.addFunctionIn",tempMap);
            if( number!=1 ) {
                return 0;
            }
        }
        return 1;
    }

    /**
     *
     * 功能描述: 根据传递过来的JSONObject，对其解析，然后往func_name表增加记录
     *
     */
    public int addFunctionInForJson(JSONObject jsonObject,String funcId){
        JSONObject jsonParse = jsonObject.getJSONObject("comment");
        List<Map<String,String>> funcInMapList = new ArrayList<Map<String,String>> ();
        JSONArray jsonArray = jsonParse.getJSONArray("in");
        String funcInStr = JSONArray.toJSONString(jsonArray,SerializerFeature.WriteMapNullValue);
        List<Map> parseFuncInMap =  JSONObject.parseArray(funcInStr,Map.class);
        int addFuncInNumber = 0;
        for( Map funcInMap : parseFuncInMap){
            Map<String,String> paramMap = new HashMap<String,String>();
            paramMap.put("func_id",funcId);
            paramMap.put("in_id",String.valueOf(funcInMap.get("id")));
            paramMap.put("in_name",String.valueOf(funcInMap.get("name")));
            paramMap.put("datatype",String.valueOf(funcInMap.get("datatype")));
            paramMap.put("dict",String.valueOf(funcInMap.get("dict")));
            paramMap.put("default_value",String.valueOf(funcInMap.get("default")));
            paramMap.put("isformula",String.valueOf(funcInMap.get("isformula")));
            funcInMapList.add(paramMap);
        }
        addFuncInNumber = this.addFunctionIn(funcInMapList);
        return addFuncInNumber;
    }

    /**
     *
     * 功能描述: 删除func_in表的记录
     */
    public void deleteFunctionIn(int funcId){
        DbFactory.Open(DbFactory.FORM).delete("function.deleteFunctionIn",funcId);
    }

    /**
     *
     * 功能描述: 查询func_out表的记录
     *
     */
    /* public String selectFunctionOut(List<Map<String,String>> map){
        return "";
    } */

    /**
     *
     * 功能描述: 新增func_out表的记录
     */
    public int addFunctionOut(List<Map<String,String>> mapList){
        // 事务管理 放在了controller层,return 0 意味着要进行事务回滚
        for(Map<String,String> tempMap : mapList){
            int number = DbFactory.Open(DbFactory.FORM).insert("function.addFunctionOut",tempMap);
            if( number!=1 ) {
                return 0;
            }
        }
        return 1;
    }

    public int addFunctionOutForJson(JSONObject jsonObject,String funcId){
        JSONObject jsonParse = jsonObject.getJSONObject("comment");
        List<Map<String,String>> funcOutMapList = new ArrayList<Map<String,String>> ();
        JSONArray jsonFuncOutArray = jsonParse.getJSONArray("out");
        String funcOutStr = JSONArray.toJSONString(jsonFuncOutArray,SerializerFeature.WriteMapNullValue);
        List<Map> parseFuncOutMap =  JSONObject.parseArray(funcOutStr,Map.class);
        int addFuncOutNumber = 0;
        for( Map funcOutMap : parseFuncOutMap){
            Map<String,String> paramMap = new HashMap<String,String>();
            paramMap.put("func_id",funcId);
            paramMap.put("out_id",String.valueOf(funcOutMap.get("id")));
            paramMap.put("out_name",String.valueOf(funcOutMap.get("name")));
            paramMap.put("link",String.valueOf(funcOutMap.get("link")));
            funcOutMapList.add(paramMap);
        }
        return this.addFunctionOut(funcOutMapList);
    }

    /**
     *
     * 功能描述: 删除func_out表的记录
     *
     */
    public void deleteFunctionOut(int funcId){
        DbFactory.Open(DbFactory.FORM).delete("function.deleteFunctionOut",funcId);
    }

    // 往 func_name 、 func_in 、 fuc_out 3张表当中插入记录
    public void insertRecordsToFunc(JSONObject jsonObject,SqlSession sqlSession) throws Exception{

        int addFuncNumber = this.addFunctionNameForJson(jsonObject);
        if(addFuncNumber!=1){
            sqlSession.getConnection().rollback();
            // throw new  Exception("添加func_name记录失败");
            log.error("添加func_name记录失败");
            throw new Exception("添加func_name记录失败");
        }
        log.info("增加func_name记录成功");

        HashMap<String,String> selectFuncNameMap = new HashMap<String,String>();
        selectFuncNameMap.put("name",jsonObject.getString("id"));
        Map  funcNameResult =  (Map)JSON.parse( this.getFunctionName(selectFuncNameMap));
        String insertResultFuncNameID = String.valueOf(funcNameResult.get("func_id"));

        int addFuncInNumber = this.addFunctionInForJson(jsonObject,insertResultFuncNameID);
        if(addFuncInNumber!=1){
            sqlSession.getConnection().rollback();
            log.error("添加func_in记录失败");
            throw new Exception("添加func_in记录失败");
        }
        log.info("增加func_in记录成功");

        int addFuncOutNumber = this.addFunctionOutForJson(jsonObject,insertResultFuncNameID);
        if(addFuncOutNumber!=1){
            sqlSession.getConnection().rollback();
            log.error("添加func_out记录失败");
            throw new Exception("添加func_out记录失败");
        }
        log.info("增加func_out记录成功");

    }

}
