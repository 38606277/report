package root.configure;

import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.net.InetAddress;

@Configuration
public class AppConstants {
    private String reportPath;//文件服务路径
    private String staticReportPath;//静态报表路径
    private String dynamicReportPath;//动态报表路径
    private String excelFilePath;
    private String mobileSubjectPath;//手机主题路径
    private String templatePath;//报表Excel模板路径
    private String fillTemplatePath;
    private String userSqlPath;//SQL模板文件服务路径
    private String userFunctionPath;
    private String userDictionaryPath;
    private String webServicePath;
    private String mongoTemplate;//mongdb元数据保存路径
    private String appFilePath;
    private String lambdaUrl;//LambdaAddress计算地址
    private String clientInstallFile;
    private String report2;//前端路径
    private String workPath;

    public AppConstants(){
        workPath = System.getProperty("user.dir")+ File.separator + "work";
    }

    public String getReportPath() {
        return  workPath+ File.separator+"report";
    }

    public String getStaticReportPath() {
        return workPath+ File.separator+"webpage"+ File.separator+"staticReport";
    }

    public String getDynamicReportPath() {
        return workPath+ File.separator+"webpage"+ File.separator+"dynamicReport";
    }

    public String getExcelFilePath() {
        return workPath+ File.separator+"excel";
    }

    public String getAppFilePath() {
        return workPath+ File.separator+"webpage"+ File.separator+"mobile"+ File.separator+"app";
    }

    public String getMobileSubjectPath() {
        return workPath+ File.separator+"webpage"+ File.separator+"mobile";
    }

    public String getTemplatePath() {
        return workPath+ File.separator+"template";
    }

    public String getFillTemplatePath() {
        return workPath+ File.separator+"filltemplate";
    }

    public String getUserSqlPath() {
        return workPath+ File.separator+"dbtemplate"+ File.separator+"query";
    }

    public String getUserFunctionPath() {
        return workPath+ File.separator+"dbtemplate"+ File.separator+"function";
    }

    public String getUserDictionaryPath() {
        return workPath+ File.separator+"dbtemplate"+ File.separator+"dictionary";
    }

    public String getWebServicePath() {
        return workPath+ File.separator+"dbtemplate"+ File.separator+"webservice";
    }

    public String getMongoTemplate() {
        return workPath+ File.separator+"mongotemplate";
    }

    public String getLambdaUrl(){
        String ip = "127.0.0.1";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress().toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return "http://"+ip+":8090/lambda";
    }

    public String getClientInstallFile() {
        return workPath+ File.separator+"clientInstallFile";
    }

    public String getReport2() {
        return workPath+ File.separator+"webapp";
    }

    @Override
    public String toString() {
        return "AppConstants{" +
                "reportPath='" + reportPath + '\'' +
                ", staticReportPath='" + staticReportPath + '\'' +
                ", dynamicReportPath='" + dynamicReportPath + '\'' +
                ", excelFilePath='" + excelFilePath + '\'' +
                ", mobileSubjectPath='" + mobileSubjectPath + '\'' +
                ", templatePath='" + templatePath + '\'' +
                ", fillTemplatePath='" + fillTemplatePath + '\'' +
                ", userSqlPath='" + userSqlPath + '\'' +
                ", userFunctionPath='" + userFunctionPath + '\'' +
                ", userDictionaryPath='" + userDictionaryPath + '\'' +
                ", webServicePath='" + webServicePath + '\'' +
                ", mongoTemplate='" + mongoTemplate + '\'' +
                ", appFilePath='" + appFilePath + '\'' +
                ", lambdaUrl='" + lambdaUrl + '\'' +
                ", clientInstallFile='" + clientInstallFile + '\'' +
                ", report2='" + report2 + '\'' +
                '}';
    }
}
