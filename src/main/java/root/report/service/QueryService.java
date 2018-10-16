package root.report.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import root.configure.AppConstants;
import root.report.util.XmlUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
            map.put("link", jsonObject.getString("link"));
            map.put("width", jsonObject.getString("width"));    // double类型的数据，在xml文件要指定jdbc类型
            map.put("render", jsonObject.getString("render"));
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
    public int deleteFunctionIn(SqlSession sqlSession,Map map) {
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
            deleteFunctionIn(sqlSession,deleteMap);   // 先删除后插入
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
    public void deleteFunctionOut(SqlSession sqlSession,Map map) {
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
            deleteFunctionOut(sqlSession,deleteMap);   // 先删除后插入
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
    public int createQueryClass(String class_name, SqlSession sqlSession) {
        return sqlSession.insert("query.createQueryClass", class_name);
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
}