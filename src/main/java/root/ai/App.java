package root.ai;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
public class App
{
    public static void main( String[] args )
    {
    	//System.out.println(HanLP.segment("查询2017年华为有限公司的采购订单"));
        //new Semantic().GetResult("查询2017年银行存款的科目余额");
        new Semantic().GetResult("查询华为的供应商信息");
//        System.out.println(SentenceParser..parser("删除2015年的财务数据"));
//    	System.out.println(SentenceParser.parser("查询2017年华为有限公司的采购订单"));
//    	System.out.println(SentenceParser.parser("查询2015年电讯盈科的年报"));
//    	System.out.println(SentenceParser.parser("删除2015年的财务数据"));
    }
}
