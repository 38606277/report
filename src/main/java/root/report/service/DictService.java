package root.report.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;
import root.configure.AppConstants;
import root.report.db.DbFactory;
import root.report.util.JsonUtil;
import root.report.util.XmlUtil;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DictService {

    private static Logger log = Logger.getLogger(DictService.class);


    public List<Map<String,String>> getAllDictName(){
        List<Map<String,String>> resultList = new  ArrayList<Map<String,String>>();
        try
        {
            SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
            resultList=sqlSession.selectList("dict.getAllDictName");
            return resultList;

        }catch (Exception ex){

            throw  ex;
        }
    }

    public List<Map<String,String>> getDictValueByID(String dict_id){
        List<Map<String,String>> resultList = new  ArrayList<Map<String,String>>();
        try
        {
            SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
            int dictID = Integer.parseInt(dict_id);
            Map<String,Object> map = new HashMap<>();
            map.put("dict_id",dictID);
            resultList=sqlSession.selectList("dict.getDictValueByID",map);
            return resultList;

        }catch (Exception ex){

            throw  ex;
        }
    }

    /**
     * 功能描述: 根据JSON数据解析 对应数据，生成func_dict记录
     */
    public String createFuncDict(SqlSession sqlSession,JSONObject jsonObject){
        Map<String,Object> map  = new HashMap<>();
        map.put("dict_name",jsonObject.getString("dict_name"));
        map.put("dict_desc",jsonObject.getString("dict_desc"));
        map.put("dict_db",jsonObject.getString("dict_db"));
        map.put("loaddata_mode",jsonObject.getString("loaddata_mode"));
        map.put("loaddata_state",jsonObject.getString("loaddata_state"));
        sqlSession.insert("dict.createFuncDict",map);
        return String.valueOf(map.get("id"));
    }

    /**
     * 功能描述: 根据JSON数据解析对应数据，生成func_dict_out 记录
     */
    public void createFuncDictOut(SqlSession sqlSession,JSONObject jsonObject,String dict_id){
        Map<String,Object> map  = new HashMap<>();
        map.put("dict_id",dict_id);
        JSONArray jsonArray = jsonObject.getJSONArray("out");
        for(int i=0;i<jsonArray.size();i++){
            JSONObject outJson = jsonArray.getJSONObject(i);
            map.put("out_id",outJson.getString("out_id"));
            map.put("out_name",outJson.getString("out_name"));
            map.put("datatype",outJson.getString("datatype"));
            sqlSession.insert("dict.createFuncDictOut",map);
        }
    }

    // 功能描述: 根据 dict_id 批量删除 func_dict的信息
    public void deleteFuncDict(SqlSession sqlSession,JSONArray jsonArray){
       for(int i=0;i<jsonArray.size();i++){
           JSONObject jsonObject = jsonArray.getJSONObject(i);
           sqlSession.delete("dict.deleteFuncDict",jsonObject.getIntValue("dict_id"));
       }
    }

    // 功能描述: 根据 dict_id 和 out_id 批量删除 func_dict的信息
    public void deleteFuncDictOut(SqlSession sqlSession,JSONArray jsonArray){
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Map<String,Object> map  = new HashMap<>();
            map.put("dict_id",jsonObject.getIntValue("dict_id"));
            map.put("out_id",jsonObject.getString("out_id"));
            sqlSession.delete("dict.deleteFuncDictOut",map);
        }
    }

    // 功能描述 : 修改 func_dict_out 表的信息
    public void updateFuncDictOut(SqlSession sqlSession,JSONObject jsonObject){
        JSONArray jsonArray = jsonObject.getJSONArray("out");
        Map<String,Object> map  = new HashMap<>();
        map.put("dict_id",jsonObject.getIntValue("dict_id"));
        for(int i=0;i<jsonArray.size();i++){
            JSONObject deleteJson = jsonArray.getJSONObject(i);
            map.put("out_id",deleteJson.getString("out_id"));
            sqlSession.delete("dict.deleteFuncDictOut",map);
        }
        String dict_id = String.valueOf(jsonObject.getIntValue("dict_id"));
        this.createFuncDictOut(sqlSession,jsonObject,dict_id);
    }

    // 功能描述 : 修改 func_dict 表的信息
    public  void updateFuncDict(SqlSession sqlSession,JSONObject jsonObject){
        Map<String,Object> map  = new HashMap<>();
        map.put("dict_id",jsonObject.getIntValue("dict_id"));
        map.put("dict_name",jsonObject.getString("dict_name"));
        map.put("dict_desc",jsonObject.getString("dict_desc"));
        map.put("dict_db",jsonObject.getString("dict_db"));
        map.put("loaddata_mode",jsonObject.getString("loaddata_mode"));
        map.put("loaddata_state",jsonObject.getString("loaddata_state"));
        sqlSession.update("dict.updateFuncDict",map);
    }

    // 功能描述 : 根据dict_id 查询 func_dict信息
    public JSONObject getFuncDictInfo(SqlSession sqlSession,int dict_id){
        Map<String,Object>  map =   sqlSession.selectOne("dict.getFuncDictInfoByDictId",dict_id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dict_id",map.get("dict_id"));
        jsonObject.put("dict_name",map.get("dict_name"));
        jsonObject.put("dict_desc",map.get("dict_desc"));
        jsonObject.put("dict_db",map.get("dict_db"));
        jsonObject.put("loaddata_mode",map.get("loaddata_mode"));
        jsonObject.put("loaddata_state",map.get("loaddata_state"));

        List<Map<String,Object>> listMap = sqlSession.selectList("dict.getFuncDictOutInfoByDicId",dict_id);
        jsonObject.put("out",JSON.toJSONString(listMap));

        return jsonObject;
    }

    // 根据传递进来的 dict_id 查询 对应的namespace当中的sql并执行得到 结果插入到 func_dict_value表
    public void createFuncDictValueByDictId(SqlSession sqlSession,int dict_id) throws SQLException, SAXException, DocumentException {
        //
        Statement st = null;
        try{
            // dict_id 关联查询到 func_dict 查找dict_db 这个
            String dbName = sqlSession.selectOne("dict.getDictDbByDictId",dict_id);
            if(StringUtils.isBlank(dbName)) throw new RuntimeException("此DictId所对应的数据库为空,无法操作!");

            // 初始化对应的数据库
            SqlSession sourceSqlSession = DbFactory.Open(dbName);
            st = sourceSqlSession.getConnection().createStatement();
            String sql = this.getSqlTemplate("数据字典",String.valueOf(dict_id),false);
            if(StringUtils.isNotBlank(sql)){
                List<Map<String,Object>> list = new ArrayList<>();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    String code = rs.getString("code");
                    String name = rs.getString("name");
                    Map<String,Object> tempMap = new HashMap<>();
                    tempMap.put("dict_id",dict_id);
                    tempMap.put("value_code",code);
                    tempMap.put("value_name",name);
                    list.add(tempMap);
                    // System.out.println(id+","+name);
                }
                if(list!=null && !list.isEmpty()){
                    this.createFuncDictValue(sqlSession,list);
                }
            }
        }catch (Exception e){
            throw e;
        }
    }

    // 往 func_dict_value 表中插入记录
    public void createFuncDictValue(SqlSession sqlSession,List<Map<String,Object>> list){
        for(int i=0;i<list.size();i++){
            Map<String,Object> map = list.get(i);
            sqlSession.insert("dict.createFuncDictValue",map);
        }
    }

    // 得到命名空间的SQL
    public String getSqlTemplate(String TemplateName, String SelectID,Boolean bool) throws DocumentException, SAXException {

        String namespace = TemplateName;
        String sqlId = SelectID;
        String userSqlPath = AppConstants.getUserDictionaryPath() + File.separator + namespace + ".xml";

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setSuppressDeclaration(true);
        format.setIndentSize(2);
        format.setNewlines(true);
        format.setTrimText(false);

        XMLWriter writer = null;
        Document userDoc = null;

        try {
            userDoc = XmlUtil.parseXmlToDom(userSqlPath);
            Element select = (Element)userDoc.selectSingleNode("//select[@id='"+sqlId+"']");
            String tempStr = "";
            if(bool){
                tempStr = select.getStringValue();   // 按照原格式取出
            }else {
                tempStr = select.getTextTrim();   // 编译了一些html代码，导致不是原格式了，输入无格式的sql
            }
            log.debug("获取到的SQL为:" +tempStr);
            return tempStr;
        } catch (java.lang.Exception e) {
            throw e;
        }
    }

    // 修改 func_dict_value 表当中的记录
    public void updateFuncDictValue(SqlSession sqlSession,JSONObject jsonObject){
        // 只更新  value_name 即可
        Map<String,Object> map = new HashMap<>();
        map.put("dict_id",jsonObject.getIntValue("dict_id"));
        map.put("value_code",jsonObject.getString("value_code"));
        map.put("value_name",jsonObject.getString("value_name"));
        sqlSession.update("dict.updateFuncDictValue",map);
    }

    // 删除 func_dict_value 表当中的记录
    public void deleteFuncDictValue(SqlSession sqlSession,JSONArray jsonArray){
        Map<String,Object> map = new HashMap<>();
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            map.put("dict_id",jsonObject.getIntValue("dict_id"));
            map.put("value_code",jsonObject.getString("value_code"));
            sqlSession.update("dict.deleteFuncDictValue",map);
        }
    }
}
