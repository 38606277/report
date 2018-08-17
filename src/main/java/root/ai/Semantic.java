package root.ai;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.db.DbFactory;
import root.report.query.FunctionControl;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/reportServer/ai")
public class Semantic {


    //根据语义识别匹配函数，并返回结果
    @RequestMapping(value = "/getResult/{aText}", produces = "text/plain;charset=UTF-8")
    public String GetResult(@PathVariable("aText") String aText) {

        SentenceParser sentenceParser=SentenceParser.parser(aText);
        String wordFuncName=sentenceParser.getFunctionName();

        //查找函数名是否存在
        String sysFuncName=GetFuncName(wordFuncName);
        if(sysFuncName.equals(""))
        {
           return  "函数不存在";
        }

        //句子中参数
        List<String> wordInName=sentenceParser.getInNames();
        //系统函数参数
        Map<String,Object> param=new HashMap<String,Object>();
        //取系统函数参数
        FunctionControl functionControl=new FunctionControl();
        String aJson=functionControl.getFunctionParam("","");
        JSONArray jsonArray=JSONArray.parseArray(aJson);

        for(int i=0;i<wordInName.size();i++)
        {
            //到dict中查找名词，找到对应的字典名称
            String dictName="";
            //根据字典名称，匹配参数
            for(int j=0;j<jsonArray.size();j++)
            {
                String paramName="";//if((JSONObject)jsonArray[i]);
                String paramValue="";
                param.put(paramName,paramValue);

            }

            //根据词性匹配参数


        }
        //执行函数

        String aValue=functionControl.execFunction("","","");

         return aValue;




    }

    //根据名称，查找函数
    private String GetFuncName(String aText){

        Map<String,String> param=new HashMap<String,String >();
        SqlSession sqlSession=DbFactory.Open("form");
        param.put("name",aText);
        List<Map> userList=sqlSession.selectList("function.getFunctionName",param);

        return userList.get(0).toString();



    }
    //根据名称，查找函数
    private String GetInName(String aText){

        String aFuncName=DbFactory.Open("form").selectOne("function.getFunctionIn","");
        return aFuncName;



    }

    //根据名字，查找对应的字典
    private  String GetDictName(String indict){


          String aFuncName=DbFactory.Open("form").selectOne("","");
         return "";
    }

}
