package root.report.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mysql.cj.x.json.JsonArray;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import root.report.db.DbFactory;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Component
public class FunctionService {

    private static Logger log = Logger.getLogger(FunctionService.class);


    //查找func主表
    public JSONObject getFunctionByID(String func_id){

        Map<String,String> param=new HashMap<String, String>();
        param.put("func_id",func_id);

        JSONObject jResult=new JSONObject();
        //查找函数定义头
        try{

            Map<String,String> mapFunc =new HashMap<String,String>();
            mapFunc = DbFactory.Open(DbFactory.FORM).selectOne("function.getNameByID",param);
            jResult.put("func_id", mapFunc.get("func_id"));
            jResult.put("func_name", mapFunc.get("func_name"));
            jResult.put("func_desc", mapFunc.get("func_desc"));
            jResult.put("func_db", mapFunc.get("func_db"));
            jResult.put("class_id", mapFunc.get("class_id"));
            jResult.put("class_name", mapFunc.get("class_name"));
            jResult.put("func_sql", mapFunc.get("func_sql"));
            jResult.put("func_type", mapFunc.get("func_type"));
            //查找定义的SQL语句



            //查找函数定义输入参数
            List<Map<String,String>> inList = DbFactory.Open(DbFactory.FORM).selectList("function.getInByID",param);
            JSONArray inArray=JSONArray.parseArray(JSONArray.toJSONString(inList));
            jResult.put("in",inArray);

            //查找函数定义输出参数
            List<Map<String,String>> outList = DbFactory.Open(DbFactory.FORM).selectList("function.getOutByID",param);
            JSONArray outArray=JSONArray.parseArray(JSONArray.toJSONString(outList));
            jResult.put("out",outArray);

            // 默认返回第一个
            return jResult;

        }catch (Exception ex){
           throw  ex;
        }

    }

    @Transactional
    public String saveFunction(String aJson){

        //拿到sqlSerssion
        SqlSession sqlSession=DbFactory.Open(DbFactory.FORM);
        JSONObject jsonFunc=JSONObject.parseObject(aJson);

        //保存头
        Map<String,String> mapFunc=new HashMap<>();

        mapFunc.put("class_id",jsonFunc.getString("class_id"));
        mapFunc.put("func_id",jsonFunc.getString("func_id"));
        mapFunc.put("func_name",jsonFunc.getString("func_name"));
        mapFunc.put("func_desc",jsonFunc.getString("func_desc"));
        mapFunc.put("func_url",jsonFunc.getString("func_url"));
        mapFunc.put("program",jsonFunc.getString("program"));

        sqlSession.insert("function.addFunctionName",mapFunc);

        //保存IN
        Map<String,String> in=new HashMap<>();
        sqlSession.insert("function.addFunctionIn",in);

        //保存Out
        Map<String,String> out=new HashMap<>();
        sqlSession.insert("function.addFunctionOut",out);

        //保存SQL到mybatis配置文件文件
        SaveSqlTemplate("2.xml","2",jsonFunc.getString("program"));
        return "";
    }

    public  String SaveSqlTemplate(String TemplateName,String SelectID,String aSQLTemplate)
    {


        return  "";
    }

    //查找func主表
    public String getFunctionName(Map<String,String> map){
        List<Map<String,String>> resultList = new  ArrayList<Map<String,String>>();
        List<Map<String,String>> selectMap = DbFactory.Open(DbFactory.FORM).selectList("function.getFunctionName",map);
        if(selectMap!=null && selectMap.size()>0){
            resultList.addAll(selectMap);
        }
        // 默认返回第一个
        return JSONObject.toJSONString(resultList.get(0));
    }
    //查找输入参数
    public String getIn(Map<String,String> map){
        List<Map<String,String>> resultList = new  ArrayList<Map<String,String>>();
        List<Map<String,String>> selectMap = DbFactory.Open(DbFactory.FORM).selectList("function.getFunctionName",map);
        if(selectMap!=null && selectMap.size()>0){
            resultList.addAll(selectMap);
        }
        // 默认返回第一个
        return JSONObject.toJSONString(resultList.get(0));
    }
    //查询输出参数
    public String getOut(Map<String,String> map){
        List<Map<String,String>> resultList = new  ArrayList<Map<String,String>>();
        List<Map<String,String>> selectMap = DbFactory.Open(DbFactory.FORM).selectList("function.getFunctionName",map);
        if(selectMap!=null && selectMap.size()>0){
            resultList.addAll(selectMap);
        }
        // 默认返回第一个
        return JSONObject.toJSONString(resultList.get(0));
    }




    public List<Map<String,String>> getAllFunctionName(){
        List<Map<String,String>> resultList = new  ArrayList<Map<String,String>>();
        try
        {
            SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
            resultList=sqlSession.selectList("function.getAllFunctionName");
            return resultList;

        }catch (Exception ex){

           throw  ex;
        }


       //JSONArray.toJSONString(resultList);
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
    @Transactional
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

    // 取函数类别
    public List<Map<String,String>> getAllFunctionClass(){

        SqlSession sqlSession=DbFactory.Open(DbFactory.FORM);
        List<Map<String,String>> list =sqlSession.selectList("function.getAllFunctionClass");
        return list;

    }
    // 创建一个函数类别
    public void CreateFunctionClass() throws Exception{


    }
    // 删除一个函数类别
    public void DeleteFunctionClass() throws Exception{


    }
    // 修改一个函数类别
    public void UpdateFunctionClass() throws Exception{


    }


}
