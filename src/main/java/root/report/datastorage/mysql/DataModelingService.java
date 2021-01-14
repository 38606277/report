package root.report.datastorage.mysql;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import root.form.constant.ColumnType;
import root.form.user.UserModel;
import root.report.db.DbFactory;
import root.report.leeutils.IDUtil;
import root.report.sys.SysContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataModelingService {

    private static Logger log = Logger.getLogger(DataModelingService.class);

    public String createHiveTable(JSONObject pJson) {
        String tableName = pJson.getString("tableName");
        String tableFields = pJson.getString("tableFields");
        JSONArray tableFieldsArray=JSONArray.parseArray(tableFields);
        long tableId = IDUtil.getId();
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

    public String createHbaseTable2(JSONObject pJson){
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

}
