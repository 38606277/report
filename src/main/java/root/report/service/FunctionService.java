package root.report.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mysql.cj.x.json.JsonArray;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.common.i18n.Exception;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;
import root.configure.AppConstants;
import root.report.db.DbFactory;
import root.report.util.JsonUtil;
import root.report.util.XmlUtil;

import javax.print.DocFlavor;
import java.io.*;
import java.lang.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Service
public class FunctionService {

    private static Logger log = Logger.getLogger(FunctionService.class);



    //查找func主表
    public JSONObject getFunctionByID(String func_id) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("func_id", func_id);

        JSONObject jResult = new JSONObject();



            //查找函数定义头
            Map<String, String> mapFunc = new HashMap<String, String>();
            mapFunc = DbFactory.Open(DbFactory.FORM)
                    .selectOne("function.getNameByID", param);
            jResult = JSONObject.parseObject(JSON.toJSONString(mapFunc, JsonUtil.features));
            //查找定义的SQL语句，先找到对应的类别，然后打开类别对应的文件，找到相的SQL


            //查找函数定义输入参数
            List<Map<String, String>> inList = DbFactory.Open(DbFactory.FORM)
                    .selectList("function.getInByID", param);
            JSONArray inArray = JSONArray.parseArray(JSONArray.toJSONString(inList, JsonUtil.features));
            jResult.put("in", inArray);

            //查找函数定义输出参数
            List<Map<String, String>> outList = DbFactory.Open(DbFactory.FORM)
                    .selectList("function.getOutByID", param);
            JSONArray outArray = JSONArray.parseArray(JSONArray.toJSONString(outList, JsonUtil.features));
            jResult.put("out", outArray);


            return jResult;



    }

    public String createFunctionName(SqlSession sqlSession, JSONObject jsonFunc) throws Exception {
            Map<String, Object> mapFunc = new HashMap<>();   // 必须设定为Object,因为我们想要让其返回自增长类型值
            mapFunc.put("class_id", jsonFunc.getString("class_id"));
            // mapFunc.put("func_id", jsonFunc.getString("func_id"));  // func_id 自增长
            mapFunc.put("func_name", jsonFunc.getString("func_name"));
            mapFunc.put("func_desc", jsonFunc.getString("func_desc"));
            mapFunc.put("func_type", jsonFunc.getString("func_type"));
            mapFunc.put("func_db", jsonFunc.getString("func_db"));
            // mapFunc.put("func_sql", jsonFunc.getString("func_sql"));

            sqlSession.insert("function.createFunctionName", mapFunc);

            return mapFunc.get("id").toString();
    }


    public int updateFunctionName(SqlSession sqlSession, JSONObject jsonFunc)
    {
        Map<String, String> mapFunc = new HashMap<>();
        mapFunc.put("class_id", jsonFunc.getString("class_id"));
        mapFunc.put("func_id", jsonFunc.getString("func_id"));
        mapFunc.put("func_name", jsonFunc.getString("func_name"));
        mapFunc.put("func_desc", jsonFunc.getString("func_desc"));
        mapFunc.put("func_type", jsonFunc.getString("func_type"));
        mapFunc.put("func_db", jsonFunc.getString("func_db"));
        return sqlSession.update("function.updateFunctionName", mapFunc);
    }
  /*  public int deleteFunctionName(String aFunID) {

        //拿到sqlSerssion
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        JSONObject jsonFunc = JSONObject.parseObject(aJson);

        try {
            //删除In更新头

            //删除Out

            //删除头

            //删除mybatis配置文件文件


        } catch (java.lang.Exception ex) {

        }
        return "";
    }
    public  String getSqlTemplate(){

    }*/
    public void createSqlTemplate(String TemplateName, String SelectID, String aSQLTemplate) throws DocumentException, SAXException, IOException {

        String namespace = TemplateName;
        String sqlId = SelectID;
        String userSqlPath = AppConstants.getUserFunctionPath() + File.separator + namespace + ".xml";

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


    public String updateSqlTemplate(String TemplateName, String SelectID, String aSQLTemplate) throws DocumentException, SAXException, IOException {

        String namespace = TemplateName;
        String sqlId = SelectID;
        String userSqlPath = AppConstants.getUserFunctionPath() + File.separator + namespace + ".xml";

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

    public void deleteSqlTemplate(String TemplateName, String SelectID, String aSQLTemplate) throws DocumentException, SAXException, IOException {

        String namespace = TemplateName;
        String sqlId = SelectID;
        String userSqlPath = AppConstants.getUserFunctionPath() + File.separator + namespace + ".xml";

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

    public void createFunctionIn(SqlSession sqlSession,JSONArray jsonArrayIn,String func_id) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < jsonArrayIn.size(); i++) {
            JSONObject jsonIn = jsonArrayIn.getJSONObject(i);
            map.put("func_id",func_id);
            map.put("in_id", jsonIn.getString("in_id"));
            map.put("in_name", jsonIn.getString("in_name"));
            map.put("datatype", jsonIn.getString("datatype"));
            map.put("dict_id", jsonIn.getString("dict_id"));
            map.put("validate", jsonIn.getString("validate"));
            map.put("default_value", jsonIn.getString("default_value"));
            map.put("isformula", jsonIn.getString("isformula"));
            map.put("authtype_id", jsonIn.getString("authtype_id"));
            sqlSession.insert("function.createFunctionIn", map);
        }
    }

    /**
     * 功能描述: 删除func_in表的记录
     */
    public void updateFunctionIn(SqlSession sqlSession,JSONArray jsonArrayIn) {

        Map<String, String> map = new HashMap<>();
        Map<String, String> deleteMap = new HashMap<>();
        for (int i = 0; i < jsonArrayIn.size(); i++) {
            JSONObject jsonIn = jsonArrayIn.getJSONObject(i);
            deleteMap.put("func_id",jsonIn.getString("func_id"));
            deleteMap.put("in_id",jsonIn.getString("in_id"));
            deleteFunctionIn(sqlSession,deleteMap);   // 先删除后插入
            map.put("func_id",jsonIn.getString("func_id"));
            map.put("in_id", jsonIn.getString("in_id"));
            map.put("in_name", jsonIn.getString("in_name"));
            map.put("datatype", jsonIn.getString("datatype"));
            map.put("dict_id", jsonIn.getString("dict_id"));
            map.put("validate", jsonIn.getString("validate"));
            map.put("default_value", jsonIn.getString("default_value"));
            map.put("isformula", jsonIn.getString("isformula"));
            map.put("authtype_id", jsonIn.getString("authtype_id"));
            sqlSession.insert("function.createFunctionIn", map);
        }
    }

    /**
     *
     * 功能描述: 针对传递进来的JSONAarray进行批量删除func_in数据
     */
    public void deleteFunctionInForJsonArray(SqlSession sqlSession,JSONArray jsonArrayIn) {
        Map<String, String> deleteMap = new HashMap<>();
        for (int i = 0; i < jsonArrayIn.size(); i++) {
            JSONObject jsonIn = jsonArrayIn.getJSONObject(i);
            deleteMap.put("func_id",jsonIn.getString("func_id"));
            deleteMap.put("in_id",jsonIn.getString("in_id"));
            deleteFunctionIn(sqlSession,deleteMap);
        }
    }

    /**
     * 功能描述: 根据map结构删除func_in表的记录
     */
    public int deleteFunctionIn(SqlSession sqlSession,Map map) {
        return sqlSession.delete("function.deleteFunctionIn", map);
    }

    public void createFunctionOut(SqlSession sqlSession,JSONArray jsonArray,String func_id) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            map.put("func_id", func_id);
            map.put("out_id", jsonObject.getString("out_id"));
            map.put("out_name", jsonObject.getString("out_name"));
            map.put("datatype", jsonObject.getString("datatype"));
            map.put("link", jsonObject.getString("link"));
            sqlSession.insert("function.createFunctionOut", map);
        }
    }
    /**
     * 功能描述: 修改func_out表的记录
     */
    public void updateFunctionOut(SqlSession sqlSession,JSONArray jsonArrayIn) {
        Map<String, String> map = new HashMap<>();
        Map<String, String> deleteMap = new HashMap<>();
        for (int i = 0; i < jsonArrayIn.size(); i++) {
            JSONObject jsonOut = jsonArrayIn.getJSONObject(i);
            deleteMap.put("func_id",jsonOut.getString("func_id"));
            deleteMap.put("out_id",jsonOut.getString("out_id"));
            deleteFunctionOut(sqlSession,deleteMap);   // 先删除后插入
            map.put("func_id", jsonOut.getString("func_id"));
            map.put("out_id", jsonOut.getString("out_id"));
            map.put("out_name", jsonOut.getString("out_name"));
            map.put("datatype", jsonOut.getString("datatype"));
            map.put("link", jsonOut.getString("link"));
            sqlSession.insert("function.createFunctionOut", map);
        }
    }
    /**
     * 功能描述: 删除func_out表的记录
     */
    public void deleteFunctionOut(SqlSession sqlSession,Map map) {
        sqlSession.delete("function.deleteFunctionOut", map);
    }

    /**
     * 功能描述: 针对传递进来的JSONAarray进行批量删除func_out数据
     */
    public void deleteFunctionOutForJsonArray(SqlSession sqlSession,JSONArray jsonArrayOut) {
        Map<String, String> deleteMap = new HashMap<>();
        for (int i = 0; i < jsonArrayOut.size(); i++) {
            JSONObject jsonOut = jsonArrayOut.getJSONObject(i);
            deleteMap.put("func_id",jsonOut.getString("func_id"));
            deleteMap.put("out_id",jsonOut.getString("out_id"));
            deleteFunctionOut(sqlSession,deleteMap);
        }
    }

    //查找func主表
    public String getFunctionName(Map<String, String> map) {
        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
        List<Map<String, String>> selectMap = DbFactory.Open(DbFactory.FORM).selectList("function.getFunctionName", map);
        if (selectMap != null && selectMap.size() > 0) {
            resultList.addAll(selectMap);
        } else {
            return "";
        }
        // 默认返回第一个
        return JSONObject.toJSONString(resultList.get(0));
    }

    //查找输入参数
    public String getIn(Map<String, String> map) {
        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
        List<Map<String, String>> selectMap = DbFactory.Open(DbFactory.FORM).selectList("function.getFunctionName", map);
        if (selectMap != null && selectMap.size() > 0) {
            resultList.addAll(selectMap);
        } else {
            return "";
        }
        // 默认返回第一个
        return JSONObject.toJSONString(resultList.get(0));
    }

    //查询输出参数
    public String getOut(Map<String, String> map) {
        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
        List<Map<String, String>> selectMap = DbFactory.Open(DbFactory.FORM).selectList("function.getFunctionName", map);
        if (selectMap != null && selectMap.size() > 0) {
            resultList.addAll(selectMap);
        } else {
            return "";
        }
        // 默认返回第一个
        return JSONObject.toJSONString(resultList.get(0));
    }


    public List<Map<String, String>> getAllFunctionName() {
        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();

        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        resultList = sqlSession.selectList("function.getAllFunctionName");
        return resultList;

    }

    /**
     * 功能描述: 往func_name表增加记录
     */
    public int addFunctionName(List<Map<String, String>> mapList) {
        // 事务管理 放在了controller层,return 0 意味着要进行事务回滚
        for (Map<String, String> tempMap : mapList) {
            int number = DbFactory.Open(DbFactory.FORM).insert("function.addFunctionName", tempMap);
            if (number != 1) {
                return 0;
            }
        }
        return 1;
    }

    /**
     * 功能描述: 根据传递过来的JSONObject，对其解析，然后往func_name表增加记录
     */
    public int addFunctionNameForJson(JSONObject jsonObject) {
        List<Map<String, String>> tempTestMapList = new ArrayList<Map<String, String>>();
        // '${class}', '${name}', '${desc}', '${type}', '${file}', '${url}'
        Map<String, String> tempMap = new HashMap<String, String>();
        JSONObject jsonParse = jsonObject.getJSONObject("comment");
        tempMap.put("class", jsonObject.getString("namespace"));
        tempMap.put("name", jsonObject.getString("id"));
        tempMap.put("desc", jsonParse.getString("desc"));
        tempMap.put("type", jsonParse.getString("type"));
        // tempMap.put("file",null);
        // tempMap.put("url",null);
        tempTestMapList.add(tempMap);
        return this.addFunctionName(tempTestMapList);
    }


    /**
     * 功能描述: 删除func_name当中的记录
     */
    public void deleteFunctionName(SqlSession sqlSession,int funcId) {
        sqlSession.delete("function.deleteFunctionName", funcId);
    }



    /**
     * 功能描述: 根据传递过来的JSONObject，对其解析，然后往func_name表增加记录
     */
/*    public int addFunctionInForJson(JSONObject jsonObject, String funcId) {
        JSONObject jsonParse = jsonObject.getJSONObject("comment");
        List<Map<String, String>> funcInMapList = new ArrayList<Map<String, String>>();
        JSONArray jsonArray = jsonParse.getJSONArray("in");
        String funcInStr = JSONArray.toJSONString(jsonArray, SerializerFeature.WriteMapNullValue);
        List<Map> parseFuncInMap = JSONObject.parseArray(funcInStr, Map.class);
        int addFuncInNumber = 0;
        for (Map funcInMap : parseFuncInMap) {
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("func_id", funcId);
            paramMap.put("in_id", String.valueOf(funcInMap.get("id")));
            paramMap.put("in_name", String.valueOf(funcInMap.get("name")));
            paramMap.put("datatype", String.valueOf(funcInMap.get("datatype")));
            paramMap.put("dict", String.valueOf(funcInMap.get("dict")));
            paramMap.put("default_value", String.valueOf(funcInMap.get("default")));
            paramMap.put("isformula", String.valueOf(funcInMap.get("isformula")));
            funcInMapList.add(paramMap);
        }
        addFuncInNumber = this.createFunctionIn(funcInMapList);
        return addFuncInNumber;
    }*/



    /**
     *
     * 功能描述: 查询func_out表的记录
     *
     */
    /* public String selectFunctionOut(List<Map<String,String>> map){
        return "";
    } */

    /**
     * 功能描述: 新增func_out表的记录
     */
    public int addFunctionOut(List<Map<String, String>> mapList) {
        // 事务管理 放在了controller层,return 0 意味着要进行事务回滚
        for (Map<String, String> tempMap : mapList) {
            int number = DbFactory.Open(DbFactory.FORM).insert("function.addFunctionOut", tempMap);
            if (number != 1) {
                return 0;
            }
        }
        return 1;
    }

    public int addFunctionOutForJson(JSONObject jsonObject, String funcId) {
        JSONObject jsonParse = jsonObject.getJSONObject("comment");
        List<Map<String, String>> funcOutMapList = new ArrayList<Map<String, String>>();
        JSONArray jsonFuncOutArray = jsonParse.getJSONArray("out");
        String funcOutStr = JSONArray.toJSONString(jsonFuncOutArray, SerializerFeature.WriteMapNullValue);
        List<Map> parseFuncOutMap = JSONObject.parseArray(funcOutStr, Map.class);
        int addFuncOutNumber = 0;
        for (Map funcOutMap : parseFuncOutMap) {
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("func_id", funcId);
            paramMap.put("out_id", String.valueOf(funcOutMap.get("id")));
            paramMap.put("out_name", String.valueOf(funcOutMap.get("name")));
            paramMap.put("link", String.valueOf(funcOutMap.get("link")));
            funcOutMapList.add(paramMap);
        }
        return this.addFunctionOut(funcOutMapList);
    }

    /**
     * 功能描述: 删除func_out表的记录
     */
/*    public void deleteFunctionOut(int funcId) {
        DbFactory.Open(DbFactory.FORM).delete("function.deleteFunctionOut", funcId);
    }*/

    // 往 func_name 、 func_in 、 fuc_out 3张表当中插入记录
   /* public void insertRecordsToFunc(JSONObject jsonObject, SqlSession sqlSession) throws Exception {
        try {
            this.addFunctionNameForJson(jsonObject);

            HashMap<String, String> selectFuncNameMap = new HashMap<String, String>();
            selectFuncNameMap.put("name", jsonObject.getString("id"));
            Map funcNameResult = (Map) JSON.parse(this.getFunctionName(selectFuncNameMap));
            String insertResultFuncNameID = String.valueOf(funcNameResult.get("func_id"));

            this.addFunctionInForJson(jsonObject, insertResultFuncNameID);
            this.addFunctionOutForJson(jsonObject, insertResultFuncNameID);
        } catch (Exception e) {
            throw e;
        }
    }*/

    // 事务在controller层控制
/*    public void insertRecordsToFunction(JSONObject jsonObject, SqlSession sqlSession) throws Exception {
        try {
            this.deleteRecordsToFunction(jsonObject, sqlSession);
            this.insertRecordsToFunc(jsonObject, sqlSession);
        } catch (Exception e) {
            throw e;
        }
    }*/

    // 删除之前存在的信息
   /* public void deleteRecordsToFunction(JSONObject jsonObject, SqlSession sqlSession) throws Exception {
        try {
            // 先查询有没有记录
            HashMap<String, String> selectFuncNameMap = new HashMap<String, String>();
            selectFuncNameMap.put("name", jsonObject.getString("id"));
            Map funcNameResult = (Map) JSON.parse(this.getFunctionName(selectFuncNameMap));

            if (funcNameResult != null && StringUtils.isNotBlank(String.valueOf(funcNameResult.get("func_id")))) {
                String funcIdStr = String.valueOf(funcNameResult.get("func_id"));
                // 不为空 ，先删除记录
                int funcId = Integer.parseInt(funcIdStr);
                this.deleteFunctionIn(funcId);
                this.deleteFunctionOut(funcId);
                this.deleteFunctionName(funcId);
            }
        } catch (Exception e) {
            throw e;
        }
    }*/


    // 取函数类别
    public List<Map<String, String>> getAllFunctionClass(SqlSession sqlSession) {
        return sqlSession.selectList("function.getAllFunctionClass");
    }

    // 创建一个函数类别
    public int addFunctionClass(String class_name, SqlSession sqlSession) {
        return sqlSession.insert("function.addFunctionClass", class_name);

    }

    // 删除一个函数类别
    public int deleteFunctionClass(int class_id, SqlSession sqlSession) {
        return sqlSession.delete("function.deleteFunctionClass", class_id);
    }

    // 修改一个函数类别
    public int updateFunctionClass(int class_id, String class_name, SqlSession sqlSession) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("class_id", class_id);
        map.put("class_name", class_name);
        // 修改一个函数，传递2个参数
        return sqlSession.update("function.updateFunctionClass", map);
    }


}
