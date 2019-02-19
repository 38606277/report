package root.report.control.nlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

public class App
{
    public static void main( String[] args )

    {



//        printNearestDocument("体育", documents, docVectorModel);
//        printNearestDocument("农业", documents, docVectorModel);
//        printNearestDocument("我要看比赛", documents, docVectorModel);
//        printNearestDocument("要不做饭吧", documents, docVectorModel);


        // 动态增加
//        CustomDictionary.add("采购订单");
//        CustomDictionary.add("华为公司");
        CustomDictionary.add("供应商资质");
        CustomDictionary.add("部门负责人");
        CustomDictionary.add("湖北移动");
        CustomDictionary.add("成本中心");
        CustomDictionary.add("采购订单");
//        CoNLLSentence coNLLSentence= HanLP.parseDependency("查询周多华的项目");
//        for(CoNLLWord coNLLWord : coNLLSentence.word)
//        {
//            System.out.println(coNLLWord.ID);
//            System.out.println(coNLLWord.CPOSTAG);
//            System.out.println(coNLLWord.DEPREL);
//            System.out.println(coNLLWord.LEMMA);
//            System.out.println(coNLLWord.POSTAG);
//            System.out.println(coNLLWord.NAME);
//        }
//        System.out.println(coNLLSentence);
        System.out.println(HanLP.parseDependency("查询湖北移动的采购订单"));
//        System.out.println(HanLP.segment("查询华为2018年的采购订单"));
//        System.out.println(HanLP.parseDependency("查询华为2018年的采购订单"));
//        System.out.println(CRFDependencyParser.compute("查询刘德华的项目金额信息"));

//        System.out.println(NLPTokenizer.analyze("查询刘德华的项目"));
//        System.out.println(NLPTokenizer.segment("查询刘德华的项目"));
        //System.out.println(HanLP.segment("查询2017年华为有限公司的采购订单"));
        //new Semantic().GetResult("查询2017年银行存款的科目余额");
//        new Semantic().GetResult("查询华为的供应商信息");
//        System.out.println(SentenceParser..parser("删除2015年的财务数据"));
//    	System.out.println(SentenceParser.parser("查询2017年华为有限公司的采购订单"));
//    	System.out.println(SentenceParser.parser("查询2015年电讯盈科的年报"));
//    	System.out.println(SentenceParser.parser("删除2015年的财务数据"));
    }
}
