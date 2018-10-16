package root.report.control.function;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.configure.AppConstants;
import root.report.common.RO;
import root.report.db.DbFactory;
import root.report.service.FunctionService;
import root.report.util.XmlUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/function1")
public class FunctionControl1 extends RO {

    private static Logger log = Logger.getLogger(FunctionControl1.class);

    @Autowired
    private FunctionService functionService;



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
            JSONObject jsonObject=functionService.getFunctionByID(func_id);
            return  SuccessMsg("",jsonObject);
        }catch (Exception ex){
            return ExceptionMsg(ex.getMessage());

        }

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
        }finally {
            sqlSession.getConnection().setAutoCommit(true);
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
        }finally {
            sqlSession.getConnection().setAutoCommit(true);
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
                functionService.deleteFunctionInForJsonArray(sqlSession,jsonObject.getJSONArray("in"));
                functionService.deleteFunctionOutForJsonArray(sqlSession,jsonObject.getJSONArray("out"));
                functionService.deleteSqlTemplate(jsonObject.getString("class_id"),
                        jsonObject.getString("func_id"),
                        jsonObject.getString("func_sql"));
            }
            sqlSession.getConnection().commit();
            return SuccessMsg("删除报表成功",null);
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            return ExceptionMsg(ex.getMessage());
        }
    }


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
        int flag = this.functionService.createFunctionClass(class_name,sqlSession);
        if(flag!=1){
            return ErrorMsg("","插入数据失败");
        }
        return SuccessMsg("插入数据成功",null);
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

}
