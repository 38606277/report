package root.report.control.nlp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.common.RO;
import root.report.db.DbFactory;
import root.report.db.DbManager;
import root.report.service.NLPService;
import root.report.util.ErpUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/reportServer/nlp")
public class NLPControl extends RO {
    private static final Logger log = Logger.getLogger(NLPControl.class);
    DbManager dbManager=new DbManager();

    @Autowired
    NLPService nlpService;
    private static ErpUtil erpUtil = new ErpUtil();

    @RequestMapping(value = "/getResult/{aText}", produces = "text/plain;charset=UTF-8")
    public String GetResult(@PathVariable("aText") String aText) {

//        CustomDictionary.add("供应商信息");
//        CoNLLSentence coNLLSentence= HanLP.parseDependency("查询华为的供应商信息");
//        System.out.println(coNLLSentence);
//        return  SuccessMsg("",new HashMap<Object,String>());

        try{

           Map map=nlpService.ExecNLP(aText);
           return  SuccessMsg("",map);

        }catch(Exception ex) {
            return  ExceptionMsg(ex.getMessage());
        }

    }

    @RequestMapping(value = "/tableDefinition", produces = "text/plain;charset=UTF-8")
    public String tableDefinition(@RequestBody String dbname){
        JSONObject obj = JSON.parseObject(dbManager.getDBConnectionByName(dbname));
        String dbtype = obj.getString("dbtype");
        List tableList =null;
        if(dbtype.equals("Oracle")){
            try {
                Connection conn= dbManager.getConnection(dbname);
                 tableList = this.getTableNameList(conn,dbname);
                conn.close();
                log.info(tableList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return SuccessMsg("修改数据成功",tableList);
    }
    @RequestMapping(value = "/getColumnList", produces = "text/plain;charset=UTF-8")
    public String getColumnList(@RequestBody String pjson){
        JSONObject obj = JSON.parseObject(pjson);
        String dbname = obj.getString("dbname");
        JSONObject objtwo = JSON.parseObject(dbManager.getDBConnectionByName(dbname));
        String dbtype = objtwo.getString("dbtype");
        String tableName = obj.getString("tableName");
        List ColumnList =null;
        if(dbtype.equals("Oracle")){
            try {
                Connection conn= dbManager.getConnection(dbname);
                ColumnList = this.getColumnNameList(conn,dbname,tableName);
                conn.close();
                log.info(ColumnList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return SuccessMsg("修改数据成功",ColumnList);
    }
    public List getTableNameList(Connection conn,String dbName) throws SQLException {
        DatabaseMetaData dbmd = conn.getMetaData();
        //访问当前用户ANDATABASE下的所有表
        ResultSet rs = dbmd.getTables("null", dbName.toUpperCase(), "%", new String[] { "TABLE" });
        List tableNameList = new ArrayList();
        while (rs.next()) {
            tableNameList.add(rs.getString("TABLE_NAME"));
        }
        return tableNameList;
    }
    // 获取数据表中所有列的列名，并添加到列表结构中。
    public List getColumnNameList(Connection conn, String dbname,String tableName)throws SQLException {

        List<HashMap<String,String>> columns = new ArrayList<HashMap<String,String>>();
        try{
            Statement stmt = conn.createStatement();
            String sql=
                    "select "+
                            "         comments as \"COMMENTS\","+
                            "         a.column_name \"COLUMN_NAME\","+
                            "         a.DATA_TYPE as \"DATA_TYPE\","+
                            "        DECODE (a.data_precision, null,DECODE (a.data_type, 'CHAR', a.char_length,'VARCHAR'," +
                            "       a.char_length, 'VARCHAR2', a.char_length, 'NVARCHAR2', a.char_length, 'NCHAR', a.char_length,a.data_length),a.data_precision)\n" +
                            "              AS COLUMN_SIZE,"+
                            "         decode(c.column_name,null,'FALSE','TRUE') as \"PRIMARY\","+
                            "         decode(a.NULLABLE,'N','NO','Y','YES','') as \"NULLABLE\""+
                            "   from "+
                            "       all_tab_columns a, "+
                            "       all_col_comments b,"+
                            "       ("+
                            "        select a.constraint_name, a.column_name"+
                            "          from user_cons_columns a, user_constraints b"+
                            "         where a.constraint_name = b.constraint_name"+
                            "               and b.constraint_type = 'P'"+
                            "               and a.table_name = '"+tableName+"'"+
                            "       ) c"+
                            "   where "+
                            "     a.Table_Name=b.table_Name "+
                            "     and a.column_name=b.column_name"+
                            "     and a.Table_Name='"+tableName+"'"+
                            "     and a.owner=b.owner "+
                            "     and a.owner='"+dbname.toUpperCase()+"'"+
                            "     and a.COLUMN_NAME = c.column_name(+)" +
                            "  order by a.COLUMN_ID";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                HashMap<String,String> map = new HashMap<String,String>();
                map.put("COLUMN_NAME", rs.getString("COLUMN_NAME"));
                map.put("DATA_TYPE", rs.getString("DATA_TYPE"));
                map.put("COLUMN_SIZE", rs.getString("COLUMN_SIZE"));
                map.put("COMMENTS", rs.getString("COMMENTS"));
                map.put("PRIMARY", rs.getString("PRIMARY"));
                map.put("NULLABLE", rs.getString("NULLABLE"));
                columns.add(map);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }finally{
            conn.close();
        }
        // DatabaseMetaData dbmd = conn.getMetaData();
        //ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
//        List<Map<String,Object>> columnNameList = new ArrayList();
//        while (rs.next()) {
//            Map<String,Object> m=new HashMap<>();
//            m.put("COLUMN_NAME",rs.getString("COLUMN_NAME"));
//            m.put("DATA_TYPE",rs.getString("TYPE_NAME"));
//            m.put("COLUMN_SIZE",rs.getObject("COLUMN_SIZE"));
////            m.put("DATA_TYPE",rs.getString("DATA_TYPE"));
//            m.put("NULLABLE",rs.getObject("IS_NULLABLE"));
//            m.put("DATA_DEFAULT",rs.getObject("COLUMN_DEF"));
////            m.put("ORDINAL_POSITION",rs.getObject("ORDINAL_POSITION"));
//
////            m.put("BUFFER_LENGTH",rs.getObject("BUFFER_LENGTH")==null?null:rs.getString("BUFFER_LENGTH"));
////            m.put("DECIMAL_DIGITS",rs.getString("DECIMAL_DIGITS"));
//            m.put("REMARKS",rs.getObject("REMARKS"));
//            columnNameList.add(m);
//        }
        return columns;
    }
}
