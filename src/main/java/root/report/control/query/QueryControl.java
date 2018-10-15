package root.report.control.query;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.common.RO;
import root.report.db.DbFactory;
import root.report.service.FunctionService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/function1")
public class QueryControl extends RO {

    private static Logger log = Logger.getLogger(QueryControl.class);

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
            sqlSession.commit();
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

            sqlSession.commit();
            return SuccessMsg("修改报表成功","");

        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            return ExceptionMsg(ex.getMessage());
        }finally {
            sqlSession.getConnection().setAutoCommit(true);
        }

    }

   /*
    @RequestMapping(value = "/UpdateFunction", produces = "text/plain;charset=UTF-8")
    public String deleteFunction(@RequestBody String pJson)
    {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try{
            JSONObject jsonFunc = JSONObject.parseObject(pJson);

            functionService.deleteFunctionIn((sqlSession,jsonFunc.getJSONArray("in"));
            functionService.deleteFunctionOut(sqlSession,jsonFunc.getJSONArray("out"));
            functionService.deleteSqlTemplate();

            return SuccessMsg("新增报表成功",null);

        }catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }

    }*/

    @RequestMapping(value = "/getAllFunctionClass", produces = "text/plain;charset=UTF-8")
    public String getAllFunctionClass() {

        try{
            List<Map<String,String>> list=functionService.getAllFunctionClass(DbFactory.Open(DbFactory.FORM));
            return  SuccessMsg("",list);
        }catch (Exception ex){
            return ExceptionMsg(ex.getMessage());

        }

    }



}
