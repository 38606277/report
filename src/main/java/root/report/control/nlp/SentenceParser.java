package root.report.control.nlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.ArrayList;
import java.util.List;

public class SentenceParser {


	public static SentenceParser parser(String input){
		Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);

		String testCase = input;
		List<String> paramsString = new ArrayList<String>();
		String functionNameString = "";
		List<Term> outPut = nShortSegment.seg(testCase);
		System.out.println(outPut.toString());
		for(Term term : outPut){
			String wordString = term.word;
			String verbString = term.nature.toString();
			if(verbString == "v" || verbString == "n" || verbString == "vn"){
				functionNameString+=wordString;
			}else if(verbString == "t" || verbString == "nt"||verbString == "ns"||verbString == "nz"){
				paramsString.add(wordString);
			}
		}

		SentenceParser businessParser=new SentenceParser();
		businessParser.setFunctionName(functionNameString);
		businessParser.setInNames(paramsString);

		return  businessParser;
	}

	public static String parserFuncName(String input){


		CustomDictionary.add("供应商资质");
		CustomDictionary.add("部门负责人");
		CustomDictionary.add("湖北移动");
		CustomDictionary.add("成本中心");
		CustomDictionary.add("采购订单");
		CoNLLSentence coNLLSentence= HanLP.parseDependency(input);
		for(CoNLLWord coNLLWord : coNLLSentence.word) {

			//查找最后一个动宾关系,
 			if (coNLLWord.ID == coNLLSentence.word.length) {
				if (coNLLWord.DEPREL.equals("动宾关系")) {
					return coNLLWord.HEAD.NAME + coNLLWord.NAME;


				}

			}
		}
		return "不能解析查询名称!";

	}


	public String getFunctionName() {
		return functionName;
	}



	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	private String functionName;

	public List<String> getInNames() {
		return inNames;
	}

	public void setInNames(List<String> inNames) {
		this.inNames = inNames;
	}

	private List<String> inNames;




}
