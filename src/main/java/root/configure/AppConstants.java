package root.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.net.InetAddress;
import java.net.URL;

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

        File   file=new   File(System.getProperty("user.dir"));
        workPath=file.getPath()+ File.separator + "work";
        //path=file.getPath();
//        String path = AppConstants.class.getClassLoader().getResource("").getPath();
//        if(path.contains("jar!")){
//            workPath = path.substring(0,path.substring(0,path.indexOf("!")).lastIndexOf("/")).substring(5);
//        }else{
//            workPath = path.substring(0,path.substring(0,path.substring(0,path.lastIndexOf("/")).lastIndexOf("/")).lastIndexOf("/"));
//        }
//        //if(workPath.contains(":")){
//            workPath = workPath.substring(1) + File.separator + "work";
//        //}
    }

    public String getReportPath() {
        return  workPath+"/report";
    }

    public String getStaticReportPath() {
        return workPath+"/webpage/staticReport";
    }

    public String getDynamicReportPath() {
        return workPath+"/webpage/dynamicReport";
    }

    public String getExcelFilePath() {
        return workPath+"/webpage/excel";
    }

    public String getAppFilePath() {
        return appFilePath;
    }

    public void setAppFilePath(String appFilePath) {
        this.appFilePath = appFilePath;
    }

    public String getMobileSubjectPath() {
        return workPath+"/webpage/mobile";
    }

    public String getTemplatePath() {
        return workPath+"/template";
    }

    public String getFillTemplatePath() {
        return workPath+"/filltemplate";
    }

    public String getUserSqlPath() {
        return workPath+"/dbtemplate/query";
    }

    public String getUserFunctionPath() {
        return workPath+"/dbtemplate/function";
    }

    public String getUserDictionaryPath() {
        return workPath+"/dbtemplate/dictionary";
    }

    public String getWebServicePath() {
        return workPath+"/dbtemplate/webservice";
    }

    public String getMongoTemplate() {
        return workPath+"/mongotemplate";
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
        return workPath+"/clientInstallFile";
    }

    public String getReport2() {
        return workPath+"/report2/web";
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
