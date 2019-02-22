package root.report.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.report.common.RO;
import root.report.control.nlp.SentenceParser;
import root.report.db.DbFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NLPService {

    @Autowired
    private QueryService queryService;
    @Autowired
    private DictService dictService;

    //根据语义识别匹配函数，并返回结果
    /*
    *根据匹配度确定调用哪个函数
    * 1、将函数名、输出参数注入到hanlp的字典中
    * 2、hanlp分词
    * 3、匹配函数名称：查找动词和最后一个动宾结构，组成函数名称，并匹配qry_name,qry_out的名称，如果匹配则
    * 4、函数参数匹配：查找名词，并和宾语构成定中结构，将参数在dict表中查找对应发字典，根据字典匹配函数定义对应的数据字典
    * 5、为函数输入参数赋值：
    * 6、执行函数：
    * 7、返回结果
    *
    * */


    public Map  ExecNLP( String aText) {

        SentenceParser sentenceParser = SentenceParser.parser(aText);
        String wordFuncName = sentenceParser.getFunctionName();


        String currentQryId="";
        String currentQryName="";
        String message="查询失败";
        Map resultMap=new HashMap<String,Object>();


        //1-----------查找函数名在name表中和out表中是否存在-------------------//
        List<Map<String, Object>> listQuery = queryService.getQueryByName(wordFuncName);

        if ((listQuery!=null)&&(listQuery.size()>1))
        {
            currentQryId=listQuery.get(0).get("qry_id").toString();
            currentQryName=listQuery.get(0).get("qry_name").toString();

        }
        //如果没找到继续查询out表
        else if(listQuery==null){

            List<Map<String, Object>> listOutQuery = queryService.getQueryByOutName(wordFuncName);
            if ((listOutQuery!=null)&&(listOutQuery.size()>1))
            {
                currentQryId=listQuery.get(0).get("qry_id").toString();
                currentQryName=listQuery.get(0).get("qry_name").toString();

            }//如果out表中也没找到则返回不存在
            else if(listOutQuery==null)
            {
                message="函数名："+wordFuncName+"不存在";
                resultMap.put("resultCode","2000");
                resultMap.put("message",message);
                return resultMap;
            }


        }


        //2---------查找查询的输入输出参数，匹配参数------------------//
        List<String> wordInName = sentenceParser.getInNames();//语言中的参数
        JSONObject queryParam = queryService.getQueryParam(currentQryId);//查询查询的参数定义
        JSONArray inParam = queryParam.getJSONArray("in");//

        JSONObject in = new JSONObject();//最终的参数
        //初始化参数为空串
        for(Object aIn:inParam)
        {
            in.put( ((JSONObject)aIn).getString("in_id"),"");
        }

        //按字典匹配
        for (int i = 0; i < wordInName.size(); i++) {
            //根据数据字典匹配参数
            String aInName = wordInName.get(i);

            //根据nlp中的参数匹配数据字典的定义
            String dict_id = dictService.getDictIdByValue(aInName);
            //匹配输入参数中定义的数据字典是该数据字典
            for (int j = 0; j < inParam.size(); j++) {
                JSONObject aIn = inParam.getJSONObject(j);
                String param_dict_id=aIn.getString("dict_id");
                if ((param_dict_id!=null)&&(param_dict_id.equals(dict_id))) {
                    //参数命中，则赋值
                    in.put(aIn.getString("in_id"), aInName);

                }
            }
            //根据nlp中的词性匹配参数
        }

        //3--------------生成查询的输入参数---------------//
        JSONArray params = new JSONArray();
        JSONObject param = new JSONObject();

        param.put("in", in);
        params.add(param);

        //4--------------执行查询返回结果-----------------------//
        Map map=new HashMap();
        try{
            map = queryService.executeSql("2", currentQryId, JSONArray.toJSONString(params, RO.features));
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return map;

    }

    //根据名称，查找函数
    private String GetFuncName(String aText) {

        Map<String, String> param = new HashMap<String, String>();
        SqlSession sqlSession = DbFactory.Open("form");
        param.put("name", aText);
        List<Map> userList = sqlSession.selectList("function.getFunctionName", param);

        return userList.get(0).toString();


    }

    //根据名称，查找函数
    private String GetInName(String aText) {

        String aFuncName = DbFactory.Open("form").selectOne("function.getFunctionIn", "");
        return aFuncName;


    }

    //根据名字，查找对应的字典
    private String GetDictName(String indict) {


        String aFuncName = DbFactory.Open("form").selectOne("", "");
        return "";
    }
}
