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
    @RequestMapping(value = "/getTableNamesByDbname", method = RequestMethod.POST)
    public String  getTablesV3(@RequestBody JSONObject pJson) throws SQLException {
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
        final List<String> list = DbManagerHutool.getTableNameNew(jdbcUrl,user,password);

        return list.toString();
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

}
