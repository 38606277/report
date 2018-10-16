package root.report.control.query;

import com.alibaba.fastjson.JSONArray;
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
import root.report.service.QueryService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/queryControl")
public class QueryControl extends RO {

    private static Logger log = Logger.getLogger(QueryControl.class);

    @Autowired
    private FunctionService functionService;

    @Autowired
    private QueryService queryService;



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


    @RequestMapping(value = "/createQryFunction", produces = "text/plain;charset=UTF-8")
    public String createQryFunction(@RequestBody String pJson) throws Exception
    {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject jsonFunc = JSONObject.parseObject(pJson);
            String qry_id = queryService.createFunctionName(sqlSession,jsonFunc);

            queryService.createFunctionIn(sqlSession,jsonFunc.getJSONArray("in"),qry_id);
            queryService.createFunctionOut(sqlSession,jsonFunc.getJSONArray("out"),qry_id);
            queryService.createSqlTemplate(jsonFunc.getString("class_id"),
                                                qry_id,
                                              jsonFunc.getString("qry_sql"));
            sqlSession.getConnection().commit();
            return SuccessMsg("新增报表成功",qry_id);
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/updateQryFunction", produces = "text/plain;charset=UTF-8")
    public String updateQryFunction(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject jsonFunc = JSONObject.parseObject(pJson);

            queryService.updateFunctionName(sqlSession,jsonFunc);
            queryService.updateFunctionIn(sqlSession,jsonFunc.getJSONArray("in"));
            queryService.updateFunctionOut(sqlSession,jsonFunc.getJSONArray("out"));
            queryService.updateSqlTemplate(jsonFunc.getString("class_id"),
                    jsonFunc.getString("qry_id"),
                    jsonFunc.getString("qry_sql"));

            sqlSession.getConnection().commit();
            return SuccessMsg("修改报表成功","");

        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            return ExceptionMsg(ex.getMessage());
        }
    }


    @RequestMapping(value = "/deleteQryFunction", produces = "text/plain;charset=UTF-8")
    public String deleteQryFunction(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONArray jsonArray =  JSONObject.parseArray(pJson);

            for(int i = 0; i < jsonArray.size(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                queryService.deleteFunctionName(sqlSession,jsonObject.getIntValue("qry_id"));
                queryService.deleteFunctionInForJsonArray(sqlSession,jsonObject.getIntValue("qry_id"));
                queryService.deleteFunctionOutForJsonArray(sqlSession,jsonObject.getIntValue("qry_id"));
                queryService.deleteSqlTemplate(jsonObject.getString("class_id"),
                        jsonObject.getString("qry_id")
                );
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



}
