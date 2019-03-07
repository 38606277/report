package root.report.control.nlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.common.RO;
import root.report.service.NLPService;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/reportServer/nlp")
public class NLPControl extends RO {


    @Autowired
    NLPService nlpService;

    @RequestMapping(value = "/getResult/{aText}", produces = "text/plain;charset=UTF-8")
    public String GetResult(@PathVariable("aText") String aText) {

//        CustomDictionary.add("供应商信息");
//        CoNLLSentence coNLLSentence= HanLP.parseDependency("查询华为的供应商信息");
//        System.out.println(coNLLSentence);
//        return  SuccessMsg("",new HashMap<Object,String>());

        try{

           Map map=nlpService.ExecNLP(aText);
           return  SuccessMsg("",map);

        }catch(Exception ex) {
            return  ExceptionMsg(ex.getMessage());
        }

    }
}
