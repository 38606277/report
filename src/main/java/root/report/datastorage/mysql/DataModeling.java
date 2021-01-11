package root.report.datastorage.mysql;

import cn.hutool.db.Entity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import root.report.leeutils.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.web.bind.annotation.*;
import root.report.auth.RoleModel;
import root.report.common.RO;
import root.report.db.DbFactory;
import root.report.leeutils.DbManagerHutool;
import root.report.leeutils.IDUtil;
import root.report.leeutils.TreeBuilder;
import root.report.sys.SysContext;
import root.report.util.ErpUtil;
import root.report.util.XmlUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@RestController
@RequestMapping("/reportServer/dataModeling")
public class DataModeling extends RO
{
    private static final Logger log = Logger.getLogger(MysqlMetadata.class);
    private static ErpUtil erpUtil = new ErpUtil();
    private static final String DB_CONFIG_PATH = System.getProperty("user.dir")+"/config/DBConfig.xml";

    @RequestMapping(value = "/getDatabaseList", produces = "text/plain;charset=UTF-8")
    public List<String>  getDatabaseList(@RequestBody JSONObject pJson) {
        List<Map<String, Object>> list = new ArrayList<>();
        try{
            String jdbcurl = pJson.getString("jdbcurl");
            String username = pJson.getString("username");
            String password = pJson.getString("password");

            List<String> databaseList = new ArrayList<>();
            databaseList=DbManagerHutool.getDatabases(jdbcurl,username,password);
            return databaseList;
//           return SuccessMsg("dblist", list);
        }catch(Exception ex){
            ex.printStackTrace();
//            return ErrorMsg("3000", ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value="/getjdbcUrl",produces = "text/plain;charset=UTF-8")
    public String getDataBytableName(@RequestBody JSONObject pJson)  {
//        String tableName = pJson.getString("tableName");
        Map<String,String> map = new HashMap<String,String>();
//        map.put("tableName", tableName);
        List<Map> authList = DbFactory.Open("form").selectList("datamodeling.getjdbcurl",map);
        return JSON.toJSONString(authList);
    }

    /**
     * 获取数据库下所有表
     * */
    @RequestMapping(value = "/getTableNamesByDbname", produces = "text/plain;charset=UTF-8")
    public List<String>  getTableNamesByDbname(@RequestBody JSONObject pJson) throws SQLException {
        final String url= pJson.getString("url");
        final String user= pJson.getString("user");
        final String password= pJson.getString("password");
        final String dbName= pJson.getString("dbName");
        String jdbcUrl;
        final String con = "?";
        if(url.contains(con)){
            String jdbc = url.substring(0, url.indexOf(con));
            String jdbc2 = jdbc.substring(0, jdbc.lastIndexOf("/"));
            jdbcUrl = jdbc2+dbName;
        }else{
            String jdbc = url.substring(0, url.lastIndexOf("/")+1);
            jdbcUrl =jdbc + dbName;
        }
        List<String> list = new ArrayList<>();
        list = DbManagerHutool.getTableNameNew(jdbcUrl,user,password);

        return list;
    }
    /**
     * 获取表结构
     * */
    @RequestMapping(value = "/getStructureByTableName", method = RequestMethod.POST)
    public String getStructure(@RequestBody JSONObject pJson) throws SQLException {
        final String url= pJson.getString("url");
        final String username= pJson.getString("username");
        final String password= pJson.getString("password");
        final String  dbName= pJson.getString("dbName");
        final String tableName= pJson.getString("tableName");
        JSONArray fields = DbManagerHutool.getTableInfoVer4(url, username,password,dbName,tableName);
//        JSONObject msg = new JSONObject();
//        msg.put("structure", e);
        return fields.toString();
    }


    @RequestMapping(value="/createNewTable",produces = "text/plain;charset=UTF-8")
    public String createNewTable(@RequestBody JSONObject pJson)  {
        String tableName = pJson.getString("tableName");
        Map<String,String> map = new HashMap<String,String>();
        map.put("tableName", tableName);
       DbFactory.Open("form").selectList("datamodeling.createNewTable",map);
        return "";
    }


    @RequestMapping(value="/createNewTable2",produces = "text/plain;charset=UTF-8")
    public String createNewTable2(@RequestBody JSONObject pJson)  {
        String tableName = pJson.getString("tableName");
        String tableFields = pJson.getString("tableFields");
        String leftTableFields = pJson.getString("leftTableFields");
        JSONArray tableFieldsArray=JSONArray.parseArray(tableFields);
        JSONArray leftTableFieldsArray=JSONArray.parseArray(leftTableFields);
        long tableId =IDUtil.getId();
        long modelId =IDUtil.getId();
        insertBdtableTable(tableName,tableId);
//        insertBdcolumnTable3( column_name,column_type,modelId,tableName, dbName);
        //构建表语句
        StringBuffer tableSql=new StringBuffer();
        for(int i=0;i<tableFieldsArray.size();i++){
              JSONObject obj= (JSONObject) tableFieldsArray.get(i);
              String field=obj.get("fieldName").toString();
              String fieldtype=obj.get("fieldType").toString();
              tableSql.append(field);
              tableSql.append(" ");
              tableSql.append(fieldtype);
              tableSql.append(",");
              insertBdcolumnTable(field,fieldtype,modelId,tableId);
        }
        tableSql.deleteCharAt(tableSql.length()-1);
        String aa =  tableSql.toString();

        for(int i=0;i<leftTableFieldsArray.size();i++){
            JSONObject obj= (JSONObject) leftTableFieldsArray.get(i);
            String field=obj.get("fieldName").toString();
            String fieldtype=obj.get("fieldType").toString();
            String dbName=obj.get("dbName").toString();
            String leftTableName=obj.get("leftTableName").toString();
            insertBdcolumnTable3( field,fieldtype,modelId,leftTableName, dbName);

        }

        Map<String,String> map = new HashMap<String,String>();
        map.put("tableName", tableName);
        map.put("tableSql", tableSql.toString());
        DbFactory.Open("form").selectList("datamodeling.createNewTable2",map);
        DbFactory.close(DbFactory.FORM);
        return "";
    }


    /*
    *
    * */
    @RequestMapping(value = "/insertBdcolumnTable", produces = "text/plain; charset=utf-8")
    public String insertBdcolumnTable(final String column_name,final String column_type,final long modelId,final long tableId)
    {
        java.util.Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(date);
        JSONObject obj = new JSONObject();
        try{
            long column_id =IDUtil.getId();
            Map param = new HashMap<>();
            param.put("column_id", column_id);
            param.put("model_id", modelId);
            param.put("table_id", tableId);
            param.put("column_name", column_name);
            param.put("column_type", column_type);
            param.put("create_date", str);
            DbFactory.Open(DbFactory.FORM).insert("datamodeling.insertBdcolumnTable",param);
            obj.put("result", "success");
            DbFactory.close(DbFactory.FORM);
        }catch(Exception e){
            log.error("表信息插入元数据表失败!");
            obj.put("result", "error");
            obj.put("errMsg", "表信息插入元数据表失败!");
            e.printStackTrace();
        }
        return JSON.toJSONString(obj);
    }
    /*
    * 左侧选择数据源，选择表，选择的字段
    * */
    @RequestMapping(value = "/insertBdcolumnTable3", produces = "text/plain; charset=utf-8")
    public String insertBdcolumnTable3(final String column_name,final String column_type,final long modelId,final String tableName,final String dbName)
    {
        java.util.Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(date);
        JSONObject obj = new JSONObject();
        try{
            long column_id =IDUtil.getId();
            Map param = new HashMap<>();
            param.put("column_id", column_id);
            param.put("model_id", modelId);
            param.put("table_name", tableName);
            param.put("db_name", dbName);
            param.put("column_name", column_name);
            param.put("column_type", column_type);
            param.put("create_date", str);
            DbFactory.Open(DbFactory.FORM).insert("datamodeling.insertBdcolumnTable3",param);
            obj.put("result", "success");
            DbFactory.close(DbFactory.FORM);
        }catch(Exception e){
            log.error("表信息插入元数据表失败!");
            obj.put("result", "error");
            obj.put("errMsg", "表信息插入元数据表失败!");
            e.printStackTrace();
        }
        return JSON.toJSONString(obj);
    }

    @RequestMapping(value = "/insertBdtableTable", produces = "text/plain; charset=utf-8")
    public String insertBdtableTable(final String tableName,final long tableId)
    {
        java.util.Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(date);
        JSONObject obj = new JSONObject();
        try{
//            long id =IDUtil.getId();
            Map param = new HashMap<>();
            param.put("table_id", tableId);
            param.put("table_name", tableName);
            param.put("table_desc", "todo");
            param.put("catalog_id", 1);
            param.put("dbtype_id", "mysql");
            param.put("source_id", "内部数据");
            param.put("host_id", "form");

            param.put("url", "url");
            param.put("data_count", "data_count");

            param.put("create_date", str);

            param.put("create_by", "lee");
            param.put("update_date", str);
            param.put("update_by", "update_by");
            param.put("data_update_date", str);

            DbFactory.Open(DbFactory.FORM).insert("mysqlmetadata.insertMysqlMetadata",param);
            obj.put("result", "success");
            DbFactory.close(DbFactory.FORM);
        }catch(Exception e){
            log.error("表信息插入元数据表失败!");
            obj.put("result", "error");
            obj.put("errMsg", "表信息插入元数据表失败!");
            e.printStackTrace();
        }
        return JSON.toJSONString(obj);
    }

    /**
     * 获取表结构
     * */
    @RequestMapping(value = "/bloodlationshipAnalysisDisplay", method = RequestMethod.POST)
    public String bloodlationshipAnalysisDisplay(@RequestBody JSONObject pJson) throws SQLException {
        final String modelId= pJson.getString("modelId");
        Map param = new HashMap<>();
        param.put("modelId", modelId);
        List<Map> tableList = DbFactory.Open("form").selectList("datamodeling.bloodlationshipAnalysisDisplay2",param);
        return JSON.toJSONString(tableList);
    }


//    @RequestMapping(value="/createHiveTable",produces = "text/plain;charset=UTF-8")
//    public String createHiveTable(@RequestBody JSONObject pJson)  {
//        String tableName = pJson.getString("tableName");
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("tableName", tableName);
//        DbFactory.Open("hive").selectList("datamodeling.createHiveTable",map);
//        return "";
//    }

    /*
    * hive数据库中创建表
    * */
    @RequestMapping(value="/createHiveTable",produces = "text/plain;charset=UTF-8")
    public String createHiveTable2(@RequestBody JSONObject pJson)  {
        String tableName = pJson.getString("tableName");
        String tableFields = pJson.getString("tableFields");
        JSONArray tableFieldsArray=JSONArray.parseArray(tableFields);
        long tableId =IDUtil.getId();
        long modelId =IDUtil.getId();
        //构建表语句
        StringBuffer tableSql=new StringBuffer();
        tableSql.append("CREATE external TABLE IF NOT EXISTS  ");
        tableSql.append("  ");
        tableSql.append(tableName);
        tableSql.append("  (");
        for(int i=0;i<tableFieldsArray.size();i++){
            JSONObject obj= (JSONObject) tableFieldsArray.get(i);
            String field=obj.get("fieldName").toString();
            String fieldtype=obj.get("fieldType").toString();
            tableSql.append(field);
            tableSql.append(" ");
            tableSql.append(fieldtype);
            tableSql.append(",");
        }
        tableSql.deleteCharAt(tableSql.length()-1);
        tableSql.append("  )");
        tableSql.append(" ROW FORMAT   ");
        tableSql.append("  DELIMITED FIELDS TERMINATED BY ','");
        tableSql.append("  STORED AS TEXTFILE ");
        String aa =  tableSql.toString();
        Map<String,String> map = new HashMap<String,String>();
        map.put("tableSql", tableSql.toString());

        try{
            DbFactory.Open("hive").selectList("datamodeling.createHiveTable2",map);
            DbFactory.close("hive");
            return  tableSql.toString();
        }catch (Exception e){
             return "建表失败";
        }

    }
    /*
     * hive数据库中创建表(方法调用)
     * */
    @RequestMapping(value="/createHiveTableMethod",produces = "text/plain;charset=UTF-8")
    public String createHiveTableMethod(final String tableName,final String tableFields)  {
        JSONArray tableFieldsArray=JSONArray.parseArray(tableFields);
        //构建表语句
        StringBuffer tableSql=new StringBuffer();
        tableSql.append("CREATE external TABLE IF NOT EXISTS  ");
        tableSql.append("  ");
        tableSql.append(tableName);
        tableSql.append("  (");
        for(int i=0;i<tableFieldsArray.size();i++){
            JSONObject obj= (JSONObject) tableFieldsArray.get(i);
            String field=obj.get("fieldName").toString();
            String fieldtype=obj.get("fieldType").toString();
            tableSql.append(field);
            tableSql.append(" ");
            tableSql.append(fieldtype);
            tableSql.append(",");
        }
        tableSql.deleteCharAt(tableSql.length()-1);
        tableSql.append("  )");
        tableSql.append(" ROW FORMAT   ");
        tableSql.append("  DELIMITED FIELDS TERMINATED BY ','");
        tableSql.append("  STORED AS TEXTFILE ");
        String aa =  tableSql.toString();
        Map<String,String> map = new HashMap<String,String>();
        map.put("tableSql", tableSql.toString());
        try{
            DbFactory.Open("hive").selectList("datamodeling.createHiveTable2",map);
            DbFactory.close("hive");
            return  tableSql.toString();
        }catch (Exception e){
            return "建表失败";
        }
    }
    /*
     * 预览hive数据库中创建表语句
     * */
    @RequestMapping(value="/previewCreateHivetableStatement",produces = "text/plain;charset=UTF-8")
    public String previewCreateHivetableStatement(@RequestBody JSONObject pJson)  {
        String tableName = pJson.getString("tableName");
        String tableFields = pJson.getString("tableFields");
        JSONArray tableFieldsArray=JSONArray.parseArray(tableFields);
        long tableId =IDUtil.getId();
        long modelId =IDUtil.getId();
        //构建表语句
        StringBuffer tableSql=new StringBuffer();
        tableSql.append("CREATE external TABLE IF NOT EXISTS  ");
        tableSql.append("  ");
        tableSql.append(tableName);
        tableSql.append("  (");
        for(int i=0;i<tableFieldsArray.size();i++){
            JSONObject obj= (JSONObject) tableFieldsArray.get(i);
            String field=obj.get("fieldName").toString();
            String fieldtype=obj.get("fieldType").toString();
            tableSql.append(field);
            tableSql.append(" ");
            tableSql.append(fieldtype);
            tableSql.append(",");
        }
        tableSql.deleteCharAt(tableSql.length()-1);
        tableSql.append("  )");
        tableSql.append(" ROW FORMAT   ");
        tableSql.append("  DELIMITED FIELDS TERMINATED BY ','");
        tableSql.append("  STORED AS TEXTFILE ");
        String aa =  tableSql.toString();
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("tableSql", tableSql.toString());
//        DbFactory.Open("hive").selectList("datamodeling.createHiveTable2",map);
//        DbFactory.close("hive");
        return  tableSql.toString();
    }

//    @RequestMapping(value="/createHbaseTable",produces = "text/plain;charset=UTF-8")
//    public String createHbaseTable(@RequestBody JSONObject pJson)  {
//        String tableName = pJson.getString("tableName");
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("tableName", tableName);
//        DbFactory.Open("hbase").selectList("datamodeling.createHbaseTable",map);
//        DbFactory.close("hbase");
//        return "";
//    }

    /*
    * hbase数据库中创建表
    * */
    @RequestMapping(value="/createHbaseTable",produces = "text/plain;charset=UTF-8")
    public String createHbaseTable2(@RequestBody JSONObject pJson)  {
        String tableName = pJson.getString("tableName");
        String tableFields = pJson.getString("tableFields");
        String primaryKey = pJson.getString("primaryKey");
        JSONArray tableFieldsArray=JSONArray.parseArray(tableFields);

        //构建表语句
        StringBuffer tableSql=new StringBuffer();
        tableSql.append("CREATE  TABLE IF NOT EXISTS  ");
        tableSql.append("  ");
        tableSql.append(tableName);

        tableSql.append("  (");
        for(int i=0;i<tableFieldsArray.size();i++){
            JSONObject obj= (JSONObject) tableFieldsArray.get(i);
            String field=obj.get("fieldName").toString();
            String fieldtype=obj.get("fieldType").toString();
            tableSql.append(field);
            tableSql.append(" ");
            tableSql.append(fieldtype);
            tableSql.append(",");

        }
        tableSql.deleteCharAt(tableSql.length()-1);
        tableSql.append("   CONSTRAINT PK PRIMARY KEY  ");
        tableSql.append("  (");
        tableSql.append(primaryKey);
        tableSql.append("  )");
        tableSql.append("  )");

        String aa =  tableSql.toString();

        Map<String,String> map = new HashMap<String,String>();

        map.put("tableSql", tableSql.toString());
        try{
            DbFactory.Open("hbase").selectList("datamodeling.createHbaseTable2",map);
            DbFactory.close("hbase");
            return  tableSql.toString();
        }catch (Exception e){
             return "建表失败";
        }

    }
    /*
     * hbase数据库中创建表（方法调用）
     * */
    @RequestMapping(value="/createHbaseTableMethod",produces = "text/plain;charset=UTF-8")
    public String createHbaseTableMethod(final String tableName,final String tableFields,final String primaryKey)  {
        JSONArray tableFieldsArray=JSONArray.parseArray(tableFields);
        //构建表语句
        StringBuffer tableSql=new StringBuffer();
        tableSql.append("CREATE  TABLE IF NOT EXISTS  ");
        tableSql.append("  ");
        tableSql.append(tableName);

        tableSql.append("  (");
        for(int i=0;i<tableFieldsArray.size();i++){
            JSONObject obj= (JSONObject) tableFieldsArray.get(i);
            String field=obj.get("fieldName").toString();
            String fieldtype=obj.get("fieldType").toString();
            tableSql.append(field);
            tableSql.append(" ");
            tableSql.append(fieldtype);
            tableSql.append(",");

        }
        tableSql.deleteCharAt(tableSql.length()-1);
        tableSql.append("   CONSTRAINT PK PRIMARY KEY  ");
        tableSql.append("  (");
        tableSql.append(primaryKey);
        tableSql.append("  )");
        tableSql.append("  )");

        String aa =  tableSql.toString();

        Map<String,String> map = new HashMap<String,String>();

        map.put("tableSql", tableSql.toString());
        try{
            DbFactory.Open("hbase").selectList("datamodeling.createHbaseTable2",map);
            DbFactory.close("hbase");
            return  tableSql.toString();
        }catch (Exception e){
            e.printStackTrace();
            return  "建表失败";
        }

    }
    /*
     * 预览hbase数据库中创建表语句
     * */
    @RequestMapping(value="/previewCreateHbasetableStatement",produces = "text/plain;charset=UTF-8")
    public String previewCreateHbasetableStatement(@RequestBody JSONObject pJson)  {
        String tableName = pJson.getString("tableName");
        String tableFields = pJson.getString("tableFields");
        String primaryKey = pJson.getString("primaryKey");
        JSONArray tableFieldsArray=JSONArray.parseArray(tableFields);

        //构建表语句
        StringBuffer tableSql=new StringBuffer();
        tableSql.append("CREATE  TABLE IF NOT EXISTS  ");
        tableSql.append("  ");
        tableSql.append(tableName);

        tableSql.append("  (");
        for(int i=0;i<tableFieldsArray.size();i++){
            JSONObject obj= (JSONObject) tableFieldsArray.get(i);
            String field=obj.get("fieldName").toString();
            String fieldtype=obj.get("fieldType").toString();
            tableSql.append(field);
            tableSql.append(" ");
            tableSql.append(fieldtype);
            tableSql.append(",");

        }
        tableSql.deleteCharAt(tableSql.length()-1);
        tableSql.append("   CONSTRAINT PK PRIMARY KEY  ");
        tableSql.append("  (");
        tableSql.append(primaryKey);
        tableSql.append("  )");
        tableSql.append("  )");
        return  tableSql.toString();
    }



    public  static  void main(String args[]){
         DataModeling dataModeling = new DataModeling();
         //hive创建表
//       String createHiveTableSql= dataModeling.createHiveTableMethod("aalee","[{'fieldName':'id','fieldType':'bigint'},{'fieldName':'name','fieldType':'double'}]");
        // 注意：hbase通过phoenix创建表，表名大写，表字段大写
        String createHbaseTableSql= dataModeling.createHbaseTableMethod("AA20210111_2","[{'fieldName':'ID','fieldType':'bigint'},{'fieldName':'NAME','fieldType':'varchar'}]","ID");
    }
}
