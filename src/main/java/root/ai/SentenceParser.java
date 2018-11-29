package root.ai;

import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import org.apache.commons.lang3.StringUtils;

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
			String verbString = term.nature.name();
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
