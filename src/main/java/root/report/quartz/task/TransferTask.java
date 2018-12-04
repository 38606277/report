package root.report.quartz.task;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import root.report.db.DbFactory;
import root.report.quartz.Service.IEtlJobService;
import root.report.quartz.Util.BaseJob;
import root.transfer.main.TransferWithMultiThread;
import root.transfer.pojo.Root;
import root.transfer.pojo.TransferInfo;
import root.transfer.util.XmlUtil;


import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * @Auther: pccw
 * @Date: 2018/11/29 15:56
 * @Description:
 *    导库的定时任务代码
 */
public class TransferTask implements BaseJob {

    private static final Logger logger = Logger.getLogger(TransferTask.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        // TODO :  动态实现任务处
        /*
           整改思想 ： job 只是承载定时任务执行的代码处，他与 trigger 是1 对多 的关系，
           而 job 在我们页面暴露出来 的是需要添加一个  root.xx.xx 一个全限定名的 job 的类路径，
           此时 这个  job 会包含  name , group_name , trigger 等，而我们的数据库会生成新的一条记录，
           我们还需要做的是  在执行时机到来的时候  去数据库查询  [job-script-reletion] 表查询出真正的 path 位置 或者脚本
           然后传递到下面去执行即可。
         */
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();   // 得到 dataMap 以便进行 动态找到对应的sql
       //  JobDetail jobDetail = jobExecutionContext.getJobDetail();
        //  JobKey jobKey = jobDetail.getKey();
       //  String jobName = jobKey.getName()+jobKey.getGroup();    // 得到 对应的此Job的名称跟组别名，


        logger.info("开始抽取数据......");
        // 得到当前的 Job的 name 跟 groupName ，关联查询正在执行的脚本， 组装成 root 节点
        String content = null;
        Root root = null;
        try{// transfer.xml E:\GitCode\report\config\transfer.xml
           //  root = XmlUtil.pareseXmlToJavaBean(new FileInputStream(path));
            // 切换到数据库当中查询,不再使用 配置文件方式
            SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
            Map<String,String> map = sqlSession.selectOne("transfer.getTransferById",dataMap.getIntValue("transfer_id"));
            content = map.get("transfer_content");
            if(StringUtils.isBlank(content)){
                throw new Exception("脚本内容为空,无法执行");
            }
            // 利用 jaxb 转换对象
            ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(content.getBytes());
            root = XmlUtil.pareseXmlToJavaBean(tInputStringStream);
            // content
        }catch (JobExecutionException e){
            logger.error("解析transfer的内容出错,内容为:"+content);
            e.printStackTrace();
        } catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        if(root == null)  return;
        List<TransferInfo> pojos = root.getTransferInfo();   // 得到所有要转换的节点信息
        TransferWithMultiThread transferWithMultiThread = new TransferWithMultiThread();
        for (int i = 0; i < pojos.size(); i++) {
            transferWithMultiThread.transfer(pojos.get(i));    // 对每一个 对象进行导库
        }
        // 执行最后需要的回调sql
        try {
            transferWithMultiThread.executeCallBack(root.getCallBackInfo());
        } catch (Exception e) {
            logger.error("执行回调sql出错：",e);
        }
    }

}
