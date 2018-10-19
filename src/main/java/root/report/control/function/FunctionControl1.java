package root.report.control.function;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import root.configure.AppConstants;
import root.report.common.RO;
import root.report.db.DbFactory;
import root.report.query.FuncMetaData;
import root.report.query.SqlTemplate;
import root.report.service.FunctionService;
import root.report.util.JsonUtil;
import root.report.util.XmlUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/reportServer/function1")
public class FunctionControl1 extends RO {

    private static Logger log = Logger.getLogger(FunctionControl1.class);

    @Autowired
    private FunctionService functionService;


    /**
     * 功能描述: 查询 func_name 表当中的所有记录
     */
    @RequestMapping(value = "/getAllFunctionName", produces = "text/plain;charset=UTF-8")
    public String getAllFunctionName() {
        List<Map<String, String>> listFunc ;
        try {
            listFunc=functionService.getAllFunctionName();
            return SuccessMsg("", listFunc);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/getFunctionByID/{func_id}", produces = "text/plain;charset=UTF-8")
    public String getFunctionByID(@PathVariable("func_id") String func_id) {
        try{
            JSONObject jsonObject = functionService.getFunctionByID(func_id);
            return  SuccessMsg("",jsonObject);
        }catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }

    // 根据 class_id 查询所有的 func_name 表当中的信息
    @RequestMapping(value = "/getFunctionByClassID/{class_id}", produces = "text/plain;charset=UTF-8")
    public String getFunctionByClassID(@PathVariable("class_id") String class_id) throws DocumentException, SAXException {
        int intClassId = Integer.parseInt(class_id);
        return functionService.getFunctionByClassID(intClassId);
    }

    // 根据 func_id 查询出 func_in 跟func_out 表当中的数据
    @RequestMapping(value = "/getFunctionParamByFuncID/{func_id}", produces = "text/plain;charset=UTF-8")
    public String getFunctionParam(@PathVariable("func_id") String func_id) {
        JSONObject jsonObject = functionService.getFunctionParam(func_id);
        return JSON.toJSONString(jsonObject,JsonUtil.features);
    }

    @RequestMapping(value = "/createFunction", produces = "text/plain;charset=UTF-8")
    public String createFunction(@RequestBody String pJson) throws Exception
    {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject jsonFunc = JSONObject.parseObject(pJson);

            String func_id = functionService.createFunctionName(sqlSession,jsonFunc);

            functionService.createFunctionIn(sqlSession,jsonFunc.getJSONArray("in"),func_id);
            functionService.createFunctionOut(sqlSession,jsonFunc.getJSONArray("out"),func_id);
            functionService.createSqlTemplate(jsonFunc.getString("class_id"),
                    func_id,
                    jsonFunc.getString("func_sql"));
            sqlSession.getConnection().commit();
            return SuccessMsg("新增报表成功",func_id);

        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/updateFunction", produces = "text/plain;charset=UTF-8")
    public String updateFunction(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject jsonFunc = JSONObject.parseObject(pJson);

            functionService.updateFunctionName(sqlSession,jsonFunc);

            functionService.updateFunctionIn(sqlSession,jsonFunc.getJSONArray("in"));
            functionService.updateFunctionOut(sqlSession,jsonFunc.getJSONArray("out"));
            functionService.updateSqlTemplate(jsonFunc.getString("class_id"),
                    jsonFunc.getString("func_id"),
                    jsonFunc.getString("func_sql"));

            sqlSession.getConnection().commit();
            return SuccessMsg("修改报表成功","");

        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            return ExceptionMsg(ex.getMessage());
        }
    }


    @RequestMapping(value = "/deleteFunction", produces = "text/plain;charset=UTF-8")
    public String deleteFunction(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONArray jsonArray =  JSONObject.parseArray(pJson);

            for(int i = 0; i < jsonArray.size(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                functionService.deleteFunctionName(sqlSession,jsonObject.getIntValue("func_id"));
                functionService.deleteFunctionInForJsonArray(sqlSession,jsonObject.getIntValue("func_id"));
                functionService.deleteFunctionOutForJsonArray(sqlSession,jsonObject.getIntValue("func_id"));
                functionService.deleteSqlTemplate(jsonObject.getString("class_id"),
                        jsonObject.getString("func_id")
                        );
            }
            sqlSession.getConnection().commit();
            return SuccessMsg("删除报表成功",null);
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            return ExceptionMsg(ex.getMessage());
        }
    }

    /**
     * 功能描述:  查询 func_class 表当中的所有记录
     */
    @RequestMapping(value = "/getAllFunctionClass", produces = "text/plain;charset=UTF-8")
    public String getAllFunctionClass() {
        try{
            List<Map<String,String>> list=functionService.getAllFunctionClass(DbFactory.Open(DbFactory.FORM));
            return  SuccessMsg("",list);
        }catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }

    // 往fucn_class这张表插入一条记录
    @RequestMapping(value = "/createFunctionClassInfo", produces = "text/plain;charset=UTF-8")
    public String createFunctionClassInfo(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        JSONObject jsonObject = (JSONObject) JSON.parse(pJson);
        String class_name = jsonObject.getString("class_name");
        String class_id = "";
        try {
            class_id = this.functionService.createFunctionClass(class_name,sqlSession);
        } catch (IOException e) {
            sqlSession.getConnection().rollback();
            e.printStackTrace();
            return ErrorMsg("","插入数据失败");
        }
        return SuccessMsg("插入数据成功",class_id);
    }

    // 往fucn_class这张表删除一条记录
    @RequestMapping(value = "/deleteFunctionClassInfo", produces = "text/plain;charset=UTF-8")
    public String deleteFunctionClassInfo(@RequestBody String pJson){
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        JSONObject jsonObject = (JSONObject) JSON.parse(pJson);
        int class_id = jsonObject.getInteger("class_id");
        int flag = this.functionService.deleteFunctionClassForRelation(class_id,sqlSession);
        if(flag==2) return ErrorMsg("3000","此func_class正在被其他表关联引用,不能删除");
        return SuccessMsg("删除数据成功",null);
    }

    // 往fucn_class这张表修改一条记录
    @RequestMapping(value = "/updateFunctionClassInfo", produces = "text/plain;charset=UTF-8")
    public String updateFunctionClassInfo(@RequestBody String pJson){
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        JSONObject jsonObject = (JSONObject) JSON.parse(pJson);
        String class_name = jsonObject.getString("class_name");
        int class_id = jsonObject.getInteger("class_id");
        int flag = this.functionService.updateFunctionClass(class_id,class_name,sqlSession);
        if(flag!=1){
            return ErrorMsg("3000","修改数据失败");
        }
        return SuccessMsg("修改数据成功",null);
    }


    // 执行excute的代码 ：
    @RequestMapping(value = "/execFunction/{FunctionClassName}/{FunctionID}", produces = "text/plain;charset=UTF-8")
    public String execFunction(@PathVariable("FunctionClassName") String FunctionClassName,
                               @PathVariable("FunctionID") String FunctionID, @RequestBody String pJson) {
        System.out.println("开始执行查询:" + "selectClassName:" + FunctionClassName + "," + "selectID:" + FunctionID + ","
                + "pJson:" + pJson + ",");
        long t1 = System.nanoTime();
        Object aResult = null;
        try {
            String usersqlPath = AppConstants.getUserFunctionPath() + File.separator + FunctionClassName + ".xml";
            // SqlTemplate template = new SqlTemplate(usersqlPath, FunctionID);   // 组装了 comment 跟 func_id
            SqlTemplate template = new SqlTemplate();
            functionService.assemblySqlTemplate(template,FunctionClassName,FunctionID);
            // 输入参数放入map中
            JSONArray inTemplate = template.getIn();
            JSONArray inValue = JSONArray.parseArray(pJson);

            Map<String,Object> map = new LinkedHashMap<String,Object>();
            Map<String,Boolean> dataParam = new HashMap<String,Boolean>();
            if (inTemplate != null) {
                for (int i = 0; i < inTemplate.size(); i++) {
                    JSONObject aJsonObject = (JSONObject) inTemplate.get(i);
                    String id = aJsonObject.getString("in_id");
                    map.put(id, inValue.getString(i));
                    Boolean inFormula = aJsonObject.getBoolean("isformula");
                    dataParam.put(id, inFormula);
                }
            }
            Map<String,Object> funcParamMap = new HashMap<String,Object>();
            List<FuncMetaData> list = new ArrayList<FuncMetaData>();
            acquireFuncMetaData(list,map,funcParamMap,dataParam);
            if(list.size()!=0){
                aResult = excuteFunc(list,0,funcParamMap,template);
            }else{
                if(template.getSelectType().equals("sql")) {
                    String db = template.getDb();
                    String namespace = template.getNamespace();
                    String funcId = template.getId();
                    aResult = DbFactory.Open(db).selectOne(namespace + "." + funcId, map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            aResult=e.getMessage();
        }

        long t2 = System.nanoTime();
        System.out.println("结束执行查询:" + "FunctionClassName:" + FunctionClassName + "," + "selectID:" + FunctionID + ","
                + "pJson:" + pJson + ",\n" + "time:" + String.format("%.4fs", (t2 - t1) * 1e-9));
        return JSON.toJSONString(aResult, JsonUtil.features);

    }

    private BigDecimal excuteFunc(List<FuncMetaData> list, int index, Map<String,Object> paramMap, SqlTemplate template) throws Exception{
        BigDecimal sum = null;
        int size = list.size();
        FuncMetaData meta = list.get(index);
        String[] paramVal = meta.getParamVal();
        String id = meta.getId();
        String expression = meta.getFuncExpression();
        for(String s:paramVal){
            paramMap.put(id, s);
            if(index<size-1){
                sum = excuteFunc(list,index+1,paramMap,template);
            }else{
                if(template.getSelectType().equals("sql")) {
                    String db = template.getDb();
                    String namespace = template.getNamespace();
                    String funcId = template.getId();
                    sum = DbFactory.Open(db).selectOne(namespace + "." + funcId, paramMap);
                }
            }
            expression = expression.replace(s, sum.toString());
        }
        Object result = null;
        try{
            Expression exp = AviatorEvaluator.compile(expression);
            result = exp.execute();
        }catch(Exception e){
            throw new Exception("参数表达式不合法");
        }
        return new BigDecimal(result.toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
    }
    //获取函数的元数据
    private void acquireFuncMetaData(List<FuncMetaData> list,Map<String,Object> map,Map<String,Object> funcParamMap,Map<String,Boolean> dateParam){
        Set<String> keys = map.keySet();
        for (String key:keys) {
            String value = (String) map.get(key);
            Boolean inFormula = dateParam.get(key);
            if(inFormula!=null&&inFormula){
                FuncMetaData meta = new FuncMetaData();
                meta.setId(key);
                meta.setFuncExpression(value);
                String[] arr = value.split("\\+|\\-|\\*|\\/|\\(|\\)");
                List<String> tempList = new ArrayList<String>();
                for(String temp:arr){
                    if(temp!=null&&!temp.trim().equals("")){
                        tempList.add(temp.trim());
                    }
                }
                String[] paramVal = new String[tempList.size()];
                tempList.toArray(paramVal);
                meta.setParamVal(paramVal);
                list.add(meta);
            }else{
                funcParamMap.put(key, map.get(key));
            }
        }
    }
}
