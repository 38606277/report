package root.report.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import root.configure.AppConstants;
import root.configure.MybatisCacheConfiguration;
import root.report.db.DbFactory;
import root.report.util.JsonUtil;
import root.report.util.XmlUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 功能描述: 对query表的增删改查功能
 *
 */
@Service
public class QueryService {

    public static final String headModel = "-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd";

    private static Logger log = Logger.getLogger(QueryService.class);

    /**
     * 功能描述: 往query_name表中增加记录
     */
    public String createQueryName(SqlSession sqlSession, JSONObject jsonFunc) throws Exception {
        Map<String, Object> mapFunc = new HashMap<>();   // 必须设定为Object,因为我们想要让其返回自增长类型值
        mapFunc.put("class_id", jsonFunc.getString("class_id"));
        // mapFunc.put("func_id", jsonFunc.getString("func_id"));  // func_id 自增长
        mapFunc.put("qry_name", jsonFunc.getString("qry_name"));
        mapFunc.put("qry_desc", jsonFunc.getString("qry_desc"));
        mapFunc.put("qry_type", jsonFunc.getString("qry_type"));
        mapFunc.put("qry_file", jsonFunc.getString("qry_file"));
        mapFunc.put("qry_db", jsonFunc.getString("qry_db"));
        // mapFunc.put("func_sql", jsonFunc.getString("func_sql"));
        sqlSession.insert("query.createQueryName", mapFunc);
        return mapFunc.get("id").toString();
    }

    /**
     * 功能描述:   往query_in表插入数据
     */
    public void createQueryIn(SqlSession sqlSession, JSONArray jsonArrayIn, String qry_id) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < jsonArrayIn.size(); i++) {
            JSONObject jsonIn = jsonArrayIn.getJSONObject(i);
            map.put("qry_id",qry_id);
            map.put("in_id", jsonIn.getString("in_id"));
            map.put("in_name", jsonIn.getString("in_name"));
            map.put("datatype", jsonIn.getString("datatype"));
            map.put("dict_id", jsonIn.getString("dict_id"));
            map.put("validate", jsonIn.getString("validate"));
            map.put("default_value", jsonIn.getString("default_value"));
            map.put("authtype_id", jsonIn.getString("authtype_id"));
            sqlSession.insert("query.createQueryIn", map);
        }
    }

    /**
     * 功能描述: 往 query_out表插入数据
     */
    public void createQueryOut(SqlSession sqlSession,JSONArray jsonArray,String qry_id) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            map.put("qry_id", qry_id);
            map.put("out_id", jsonObject.getString("out_id"));
            map.put("out_name", jsonObject.getString("out_name"));
            map.put("datatype", jsonObject.getString("datatype"));
            // map.put("link", jsonObject.getString("link"));
            map.put("width", jsonObject.getString("width"));    // double类型的数据，在xml文件要指定jdbc类型
            map.put("render", jsonObject.getString("render"));

            JSONObject outJsonObject = jsonObject.getJSONObject("link");
            Map<String,Object> outMap = new HashMap<>();
            outMap.put("qry_id",qry_id);
            outMap.put("out_id",jsonObject.getString("out_id"));
            outMap.put("link_qry_id",outJsonObject.getString("link_qry_id"));
            if(outJsonObject!=null && !outJsonObject.isEmpty()){
                map.put("link",outJsonObject.getString("link_qry_id"));
            }
            JSONArray linkIdJSONArray = outJsonObject.getJSONArray("param");
            for(int j=0; j<linkIdJSONArray.size();j++){
                if(outMap!=null && !outMap.isEmpty()){
                    JSONObject tempJSONObject = linkIdJSONArray.getJSONObject(j);
                    Map<String,Object> insertMap = new HashMap<>();
                    insertMap.putAll(outMap);
                    insertMap.put("link_in_id",tempJSONObject.getString("link_in_id"));
                    insertMap.put("link_in_id_value_type",tempJSONObject.getString("link_in_id_value_type"));
                    insertMap.put("link_in_id_value",tempJSONObject.getString("link_in_id_value"));
                    sqlSession.insert("query.createFuncOutLink",insertMap);
                }
            }
            sqlSession.insert("query.createQueryOut", map);
        }
    }

    /**
     * 功能描述: 新增query包下的对应的mapper映射文件中的sql语句
     */
    public void createSqlTemplate(String TemplateName, String SelectID, String aSQLTemplate) throws DocumentException, SAXException, IOException {

        String namespace = TemplateName;
        String sqlId = SelectID;
        String userSqlPath = AppConstants.getUserSqlPath() + File.separator + namespace + ".xml";

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setSuppressDeclaration(true);
        format.setIndentSize(2);
        format.setNewlines(true);
        format.setTrimText(false);

        XMLWriter writer = null;
        Document userDoc = null;
        try {
            userDoc = XmlUtil.parseXmlToDom(userSqlPath);

            Element root = (Element) userDoc.selectSingleNode("/mapper");
            Element newSql = root.addElement("select");
            newSql.addAttribute("id", sqlId);
            newSql.addAttribute("resultType", "BigDecimal");
            newSql.addAttribute("parameterType", "Map");
            //  设置2级缓存
            newSql.addAttribute("useCache", MybatisCacheConfiguration.USE_CACHE_FALSE);
            // newSql.addText(aSQLTemplate);
            addSqlText(newSql,aSQLTemplate);

            log.debug("新增SQL:" + newSql.asXML());
            writer = new XMLWriter(new FileOutputStream(userSqlPath), format);
            //删除空白行
            Element rootEle = userDoc.getRootElement();
            writer.write(userDoc);
            writer.flush();
            writer.close();
        } catch (java.lang.Exception e) {
            throw e;
        }
    }

    // 往指定节点当中增加内容
    private void addSqlText(Element select, String sqlText) throws DocumentException{
        String xmlText = "<sql>"+sqlText+"</sql>";
        Document doc = DocumentHelper.parseText(xmlText);
        //获取根节点    
        Element root = doc.getRootElement();
        List<Node> content = root.content();
        for (int i = 0; i < content.size(); i++) {
            Node node = content.get(i);
            select.add((Node)node.clone());
        }
    }

    /**
     * 功能描述:  修改 query_name 表当中的记录
     */
    public int updateQueryName(SqlSession sqlSession, JSONObject jsonFunc)
    {
        Map<String, String> mapFunc = new HashMap<>();
        mapFunc.put("class_id", jsonFunc.getString("class_id"));
        mapFunc.put("qry_id", jsonFunc.getString("qry_id"));
        mapFunc.put("qry_name", jsonFunc.getString("qry_name"));
        mapFunc.put("qry_desc", jsonFunc.getString("qry_desc"));
        mapFunc.put("qry_type", jsonFunc.getString("qry_type"));
        mapFunc.put("qry_file", jsonFunc.getString("qry_file"));
        mapFunc.put("qry_db", jsonFunc.getString("qry_db"));
        return sqlSession.update("query.updateQueryName", mapFunc);
    }

    /**
     * 功能描述: 删除 query_in表当中的一条记录 （map中包含主键信息)
     */
    public int deleteQueryIn(SqlSession sqlSession,Map map) {
        return sqlSession.delete("query.deleteQueryIn", map);
    }

    /**
     * 功能描述: 删除func_in表的记录
     */
    public void updateQueryIn(SqlSession sqlSession,JSONArray jsonArrayIn) {
        Map<String, String> map = new HashMap<>();
        Map<String, String> deleteMap = new HashMap<>();
        for (int i = 0; i < jsonArrayIn.size(); i++) {
            JSONObject jsonIn = jsonArrayIn.getJSONObject(i);
            deleteMap.put("qry_id",jsonIn.getString("qry_id"));
            deleteMap.put("in_id",jsonIn.getString("in_id"));
            deleteQueryIn(sqlSession,deleteMap);   // 先删除后插入
            map.put("qry_id",jsonIn.getString("qry_id"));
            map.put("in_id", jsonIn.getString("in_id"));
            map.put("in_name", jsonIn.getString("in_name"));
            map.put("datatype", jsonIn.getString("datatype"));
            map.put("dict_id", jsonIn.getString("dict_id"));
            map.put("validate", jsonIn.getString("validate"));
            map.put("default_value", jsonIn.getString("default_value"));
            map.put("authtype_id", jsonIn.getString("authtype_id"));
            sqlSession.insert("query.createQueryIn", map);
        }
    }


    /**
     * 功能描述: 删除qry_out表的记录
     */
    public void deleteQueryOut(SqlSession sqlSession,Map map) {
        sqlSession.delete("query.deleteQueryOut", map);
    }

    /**
     * 功能描述: 修改qry_out表的记录
     */
    public void updateQueryOut(SqlSession sqlSession,JSONArray jsonArrayIn) {
        Map<String, String> map = new HashMap<>();
        Map<String, String> deleteMap = new HashMap<>();
        for (int i = 0; i < jsonArrayIn.size(); i++) {
            JSONObject jsonOut = jsonArrayIn.getJSONObject(i);
            deleteMap.put("qry_id",jsonOut.getString("qry_id"));
            deleteMap.put("out_id",jsonOut.getString("out_id"));
            deleteQueryOut(sqlSession,deleteMap);   // 先删除后插入
            map.put("qry_id", jsonOut.getString("qry_id"));
            map.put("out_id", jsonOut.getString("out_id"));
            map.put("out_name", jsonOut.getString("out_name"));
            map.put("datatype", jsonOut.getString("datatype"));
            map.put("link", jsonOut.getString("link"));
            map.put("width", jsonOut.getString("width"));    // double类型的数据，在xml文件要指定jdbc类型
            map.put("render", jsonOut.getString("render"));
            sqlSession.insert("query.createQueryOut", map);
        }
    }

    /**
     * 功能描述:  得到指定文件指定id的 sql内容  ,若bool为true则代表保留原格式，为false则代表只要sql不要转义
     */
    public String getSqlTemplate(String TemplateName, String SelectID,Boolean bool) throws DocumentException, SAXException {

        String namespace = TemplateName;
        String sqlId = SelectID;
        String userSqlPath = AppConstants.getUserSqlPath() + File.separator + namespace + ".xml";

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

    /**
     * 功能描述: 修改query包下的对应的mapper映射文件中的sql语句
     */
    public String updateSqlTemplate(String TemplateName, String SelectID, String aSQLTemplate) throws DocumentException, SAXException, IOException {

        String namespace = TemplateName;
        String sqlId = SelectID;
        String userSqlPath = AppConstants.getUserSqlPath() + File.separator + namespace + ".xml";

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
            select.clearContent();
            this.addSqlText(select,aSQLTemplate);

            log.debug("修改SQL:" + select.asXML());
            writer = new XMLWriter(new FileOutputStream(userSqlPath), format);
            //删除空白行
            Element rootEle = userDoc.getRootElement();
            writer.write(userDoc);
            writer.flush();
            writer.close();
            return "";
        } catch (java.lang.Exception e) {
            throw e;
        }
    }

    /**
     * 功能描述: 删除qry_name当中的记录
     */
    public void deleteQueryName(SqlSession sqlSession,int qry_id) {
        sqlSession.delete("query.deleteQueryName", qry_id);
    }

    /**
     * 功能描述: 针对传递进来的JSONAarray进行批量删除qry_in数据
     */
    public void deleteQueryInForJsonArray(SqlSession sqlSession,int qry_id) {
        sqlSession.delete("query.deleteQueryInByFuncId",qry_id);
    }

    /**
     * 功能描述: 针对传递进来的JSONAarray进行批量删除func_out数据
     */
    public void deleteQueryOutForJsonArray(SqlSession sqlSession,int qry_id) {
        sqlSession.delete("query.deleteQueryOutByFuncId",qry_id);
    }

    public void deleteSqlTemplate(String TemplateName, String SelectID) throws DocumentException, SAXException, IOException {

        String namespace = TemplateName;
        String sqlId = SelectID;
        String userSqlPath = AppConstants.getUserSqlPath() + File.separator + namespace + ".xml";

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setSuppressDeclaration(true);
        format.setIndentSize(2);
        format.setNewlines(true);
        format.setTrimText(false);

        Document userDoc = null;
        XMLWriter writer = null;
        try {
            userDoc = XmlUtil.parseXmlToDom(userSqlPath);
            moveSqlId(userDoc,sqlId);
            log.debug("删除SQL,其id为:" +userSqlPath+"-"+sqlId);
            writer = new XMLWriter(new FileOutputStream(userSqlPath), format);
            //删除空白行
            Element root = userDoc.getRootElement();
            removeBlankNewLine(root);
            writer.write(userDoc);
            writer.flush();
            writer.close();
        } catch (java.lang.Exception e) {
            throw e;
        }
    }

    //移除某个节点
    protected void moveSqlId(Document userDoc, String sqlId)
    {
        List<Element> list = userDoc.selectNodes("//select[@id='"+sqlId+"']");
        for (int i = 0; i < list.size(); i++)
        {
            list.get(i).getParent().remove(list.get(i));
        }
    }

    private void removeBlankNewLine(Node node){
        List<Node> list = ((Element)node).content();
        boolean textOnly = true;
        if(node.getNodeType()==Node.ELEMENT_NODE){
            for(Node temp:list){
                if(temp.getNodeType()!=Node.TEXT_NODE){
                    textOnly = false;
                    break;
                }
            }
        }
        for(Node temp:list){
            int nodeType = temp.getNodeType();
            switch (nodeType) {
                case Node.ELEMENT_NODE:
                    removeBlankNewLine(temp);
                    break;
                case Node.CDATA_SECTION_NODE:
                    break;
                case Node.COMMENT_NODE:
                    break;
                case Node.TEXT_NODE:
                    Text text =  (Text)temp;
                    String value = text.getText();
                    if(!value.trim().equals("")){
                        //清空右边空白
                        value = value.substring(0,value.indexOf(value.trim().substring(0, 1))+value.trim().length());
                        if(textOnly){
                            value+="\n";
                        }
                    }else{
                        value = value.trim()+"\n";
                    }
                    text.setText(value);
                    break;
                default:break;
            }
        }
    }

    // 创建一个qry函数类别
    public String createQueryClass(String class_name, SqlSession sqlSession) throws IOException {
        Map<String,Object> map = new HashMap<>();
        map.put("class_name",class_name);
        sqlSession.insert("query.createQueryClass", map);
        String class_id  = String.valueOf(map.get("id"));
        // 生成 xml文件
        String userSqlPath = AppConstants.getUserSqlPath() + File.separator + class_id + ".xml";
        File file = new File(userSqlPath);   // 自增長ID不會重名
        file.createNewFile();
        Document doc = DocumentHelper.createDocument();
        Element mapper = DocumentHelper.createElement("mapper");
        mapper.addAttribute("namespace",class_id);
        // 增加缓存信息  -> 每次sqlSession都会关闭掉，所以一级缓存不起作用，要开启二级缓存
        Element cacheElement = mapper.addElement("cache");
        // eviction="LRU" flushInterval="100000" size="1024" readOnly="true"
        cacheElement.addAttribute("eviction", MybatisCacheConfiguration.EVICTION_VALUE);
        cacheElement.addAttribute("flushInterval",MybatisCacheConfiguration.FLUSH_INTERVAL_VALUE);
        cacheElement.addAttribute("size",MybatisCacheConfiguration.SIZE_VALUE);
        cacheElement.addAttribute("readOnly", MybatisCacheConfiguration.READONLY_VALUE);
        doc.add(mapper);
        doc.addDocType("mapper", headModel, null);
        writeToXml(doc, file);
        return class_id;
    }

    // 把doc节点当中的信息写入到指定file文件当中去
    private void writeToXml(Document doc, File file) throws IOException {
        //写入XML文件
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        format.setTrimText(false);
        format.setIndent(false);
        format.setExpandEmptyElements(true);  // 设置标签 mapper标签不闭合
        XMLWriter writer = null;
        try
        {
            writer = new XMLWriter(new FileOutputStream(file),format);
            writer.write(doc);
            writer.flush();
            writer.close();
        }catch (java.lang.Exception e){
            log.error("写入XML异常!"+file.getAbsolutePath());
            e.printStackTrace();
        }finally {
            if(writer!=null)
            {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    // 删除一个函数类别，但要判断是否有qry_name 关联qry_class的class_id
    // getFuncInfoRelationClass
    public int deleteQueryClassForRelation(int class_id, SqlSession sqlSession) {
        int i = sqlSession.selectOne("query.getQueryInfoRelationClass",class_id);
        if(i>0){
            return 2;  // 代表 存在关联关系,不能删除
        }
        return sqlSession.delete("query.deleteQueryClass", class_id);
    }

    // 修改一个函数类别
    public int updateQueryClass(int class_id, String class_name, SqlSession sqlSession) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("class_id", class_id);
        map.put("class_name", class_name);
        // 修改一个函数，传递2个参数
        return sqlSession.update("query.updateQueryClass", map);
    }

    /**
     * 功能描述:  根据qry_id 查找qry表相关的信息
     */
    public JSONObject getQueryByID(SqlSession sqlSession,String qry_id) throws SAXException, DocumentException {
        Map<String, String> param = new HashMap<String, String>();
        param.put("qry_id", qry_id);
        JSONObject jResult = new JSONObject();

        // 查找qry_name
        Map<String, String> mapFunc = new HashMap<String, String>();
        mapFunc = sqlSession.selectOne("query.getNameByID", param);
        //查找定义的SQL语句，先找到对应的类别，然后打开类别对应的文件，找到相的SQL
        if(mapFunc !=null && !mapFunc.isEmpty()){
            String class_id = String.valueOf(mapFunc.get("class_id"));
            String sql = getSqlTemplate(class_id,qry_id,true);
            if(StringUtils.isNotBlank(sql)){
                mapFunc.put("qry_sql",sql);
            }
            jResult = JSONObject.parseObject(JSON.toJSONString(mapFunc, JsonUtil.features));
        }

        //查找函数定义输入参数 qry_in
        List<Map<String, String>> inList = sqlSession.selectList("query.getInByID", param);
        JSONArray inArray = JSONArray.parseArray(JSONArray.toJSONString(inList, JsonUtil.features));
        jResult.put("in", inArray);

        //查找函数定义输出参数 qry_out
        List<Map<String, String>> outList = sqlSession.selectList("query.getOutByID", param);
        JSONArray outArray = JSONArray.parseArray(JSONArray.toJSONString(outList, JsonUtil.features));
        jResult.put("out", outArray);

        return jResult;
    }

    /**
     * 功能描述: 根据  class_id 查询出 func_name 表当中的信息
     */
    public String getQueryByClassID(int class_id) throws SAXException, DocumentException {
        JSONObject jResult = new JSONObject();
        List<Map<String,Object>> listQueryName = DbFactory.Open(DbFactory.FORM).
                selectList("query.getQueryNameInfoByClassID",class_id);
        return JSON.toJSONString(listQueryName, JsonUtil.features);
    }

    public JSONObject getQueryParam(String qry_id){
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);

        Map<String, String> param = new HashMap<String, String>();
        param.put("qry_id", qry_id);
        JSONObject jResult = new JSONObject();

        //查找函数定义输入参数 qry_in
        List<Map<String, String>> inList = sqlSession.selectList("query.getInByID", param);
        JSONArray inArray = JSONArray.parseArray(JSONArray.toJSONString(inList, JsonUtil.features));
        jResult.put("in", inArray);

        //查找函数定义输出参数 qry_out
        List<Map<String, String>> outList = sqlSession.selectList("query.getOutByID", param);
        JSONArray outArray = JSONArray.parseArray(JSONArray.toJSONString(outList, JsonUtil.features));
        jResult.put("out", outArray);

        return jResult;
    }

    /**
     * 功能描述: 查找 qry_name所有记录
     */
    public List<Map<String, String>> getAllQueryName() {
        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        resultList = sqlSession.selectList("query.getAllQueryName");
        return resultList;
    }

    // 取函数类别
    public List<Map<String, String>> getAllQueryClass(SqlSession sqlSession) {
        return sqlSession.selectList("query.getAllQueryClass");
    }
}
