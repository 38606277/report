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
    private String appPath;

    public AppConstants(){
        appPath = System.getProperty("user.dir")+ File.separator + "app";
    }

    public String getReportPath() {
        return  appPath+ "/windowsapp/report";
    }

    public String getStaticReportPath() {
        return appPath+"/webapp/staticReport";
    }

    public String getDynamicReportPath() {
        return appPath+"/webapp/dynamicReport";
    }

    public String getExcelFilePath() {
        return appPath+"/windowsapp/excel";
    }

    public String getAppFilePath() {
        return appPath+"/app";
    }

    public String getMobileSubjectPath() {
        return appPath+"/mobileapp";
    }

    public String getTemplatePath() {
        return appPath+ "/mobileapp/template";
    }

    public String getFillTemplatePath() {
        return appPath+ "/windowsapp/filltemplate";
    }

    public String getUserSqlPath() {
        return appPath+"/windowsapp/dbtemplate/query";
    }

    public String getUserFunctionPath() {
        return appPath+"/windowsapp/dbtemplate/function";
    }

    public String getUserDictionaryPath() {
        return appPath+"/windowsapp/dbtemplate/dictionary";
    }

    public String getWebServicePath() {
        return appPath+"/windowsapp/dbtemplate/webservice";
    }

    public String getMongoTemplate() {
        return appPath+ "/windowsapp/mongotemplate";
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
        return appPath+ "/webapp/clientInstallFile";
    }

    public String getReport2() {
        return appPath+"/webapp/web";
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
