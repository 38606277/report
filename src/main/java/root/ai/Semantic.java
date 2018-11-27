package root.ai;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.mongodb.util.JSON;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.control.dict.DictControl;
import root.report.control.query.QueryControl;
import root.report.db.DbFactory;
import root.report.query.FunctionControl;
import root.report.service.QueryService;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/reportServer/ai")
public class Semantic {


    @Autowired
    private QueryControl queryControl;
    @Autowired
    private QueryService queryService;
    @Autowired
    private DictControl dictControl;

    //根据语义识别匹配函数，并返回结果
    @RequestMapping(value = "/getResult/{aText}", produces = "text/plain;charset=UTF-8")
    public String GetResult(@PathVariable("aText") String aText) {

        SentenceParser sentenceParser = SentenceParser.parser(aText);
        String wordFuncName = sentenceParser.getFunctionName();

        //1---查找查询名和函数名是否存在
        Map<String, Object>  mapQuery = queryService.getQueryByChineseName(wordFuncName);

        if (mapQuery.equals(null)) {
            return "函数不存在";
        }
        String qry_id = mapQuery.get("qry_id").toString();

        //2---查找查询的输入输出参数，匹配参数
        List<String> wordInName = sentenceParser.getInNames();//语言中的参烽
        JSONObject queryParam = queryService.getQueryParam(qry_id);
        JSONArray inParam = queryParam.getJSONArray("out");//数据库中的参数定义
        JSONObject in = new JSONObject();//最终的参数


        for (int i = 0; i < wordInName.size(); i++) {
            //根据数据字典匹配参数
            String aInName = wordInName.get(i);
            String dict_id = dictControl.getDictIdByValue(aInName);
            for (int j = 0; j < inParam.size(); j++) {
                JSONObject aIn = inParam.getJSONObject(j);
                if (aIn.getString("dict_id").equals(dict_id)) {
                    //参数命中，则赋值
                    in.put(aIn.getString("in_id"), aInName);

                } else {
                    //没有命中，则赋空值
                    in.put(aIn.getString("in_id"), "");
                }
            }
            //根据词性匹配参数


        }

        //3---生成查询的输入参数
        JSONArray params = new JSONArray();
        JSONObject param = new JSONObject();

        param.put("in", param);
        params.add(param);

        //4---执行查询
        String aResult = queryControl.execQuery("", qry_id, JSONArray.toJSONString(params));

        return aResult;


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
