package root.report.control.query;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import root.report.common.RO;
import root.report.db.DbFactory;
import root.report.service.QueryService;
import root.report.util.JsonUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/query")
public class QueryControl extends RO {

    private static Logger log = Logger.getLogger(QueryControl.class);

    @Autowired
    private QueryService queryService;



    @RequestMapping(value = "/getAllQueryName", produces = "text/plain;charset=UTF-8")
    public String getAllQueryName() {
        List<Map<String, String>> listFunc = new ArrayList<>();
        try {
            listFunc = queryService.getAllQueryName();
            return SuccessMsg("", listFunc);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }


    @RequestMapping(value = "/getQueryByID/{qry_id}", produces = "text/plain;charset=UTF-8")
    public String getQueryByID(@PathVariable("qry_id") String qry_id) {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try{
            JSONObject jsonObject = queryService.getQueryByID(sqlSession,qry_id);
            return  SuccessMsg("",jsonObject);
        }catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }

    // 根据 class_id 查询所有的 func_name 表当中的信息
    @RequestMapping(value = "/getQueryByClassID/{class_id}", produces = "text/plain;charset=UTF-8")
    public String getQueryByClassID(@PathVariable("class_id") String class_id) throws DocumentException, SAXException {
        int intClassId = Integer.parseInt(class_id);
        return queryService.getQueryByClassID(intClassId);
    }

    // 根据 func_id 查询出 func_in 跟func_out 表当中的数据
    @RequestMapping(value = "/getQueryParamByFuncID/{qry_id}", produces = "text/plain;charset=UTF-8")
    public String getQueryParam(@PathVariable("qry_id") String qry_id) {
        JSONObject jsonObject = queryService.getQueryParam(qry_id);
        return JSON.toJSONString(jsonObject,JsonUtil.features);
    }

    @RequestMapping(value = "/createQuery", produces = "text/plain;charset=UTF-8")
    public String createQuery(@RequestBody String pJson) throws Exception
    {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject jsonFunc = JSONObject.parseObject(pJson);
            String qry_id = queryService.createQueryName(sqlSession,jsonFunc);

            queryService.createQueryIn(sqlSession,jsonFunc.getJSONArray("in"),qry_id);
            queryService.createQueryOut(sqlSession,jsonFunc.getJSONArray("out"),qry_id);
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

    @RequestMapping(value = "/updateQuery", produces = "text/plain;charset=UTF-8")
    public String updateQuery(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject jsonFunc = JSONObject.parseObject(pJson);

            queryService.updateQueryName(sqlSession,jsonFunc);
            queryService.updateQueryIn(sqlSession,jsonFunc.getJSONArray("in"));
            queryService.updateQueryOut(sqlSession,jsonFunc.getJSONArray("out"));
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


    @RequestMapping(value = "/deleteQuery", produces = "text/plain;charset=UTF-8")
    public String deleteQuery(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONArray jsonArray =  JSONObject.parseArray(pJson);

            for(int i = 0; i < jsonArray.size(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                queryService.deleteQueryName(sqlSession,jsonObject.getIntValue("qry_id"));
                queryService.deleteQueryInForJsonArray(sqlSession,jsonObject.getIntValue("qry_id"));
                queryService.deleteQueryOutForJsonArray(sqlSession,jsonObject.getIntValue("qry_id"));
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

    // 往qry_class这张表插入一条记录 并生成mapper.xml文件
    @RequestMapping(value = "/createQueryClassInfo", produces = "text/plain;charset=UTF-8")
    public String createQueryClassInfo(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        JSONObject jsonObject = (JSONObject) JSON.parse(pJson);
        String class_name = jsonObject.getString("class_name");
        try {
            this.queryService.createQueryClass(class_name,sqlSession);
        } catch (IOException e) {
            sqlSession.getConnection().rollback();
            e.printStackTrace();
            return ErrorMsg("","插入数据失败");
        }
        return SuccessMsg("插入数据成功",null);
    }

    // 在 qry_class 这张表删除一条记录
    @RequestMapping(value = "/deleteQueryClassInfo", produces = "text/plain;charset=UTF-8")
    public String deleteQueryClassInfo(@RequestBody String pJson){
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        JSONObject jsonObject = (JSONObject) JSON.parse(pJson);
        int class_id = jsonObject.getInteger("class_id");
        int flag = this.queryService.deleteQueryClassForRelation(class_id,sqlSession);
        if(flag==2) return ErrorMsg("3000","此func_class正在被其他表关联引用,不能删除");
        return SuccessMsg("删除数据成功",null);
    }

    // 往fucn_class这张表修改一条记录
    @RequestMapping(value = "/updateQueryClassInfo", produces = "text/plain;charset=UTF-8")
    public String updateQueryClassInfo(@RequestBody String pJson){
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        JSONObject jsonObject = (JSONObject) JSON.parse(pJson);
        String class_name = jsonObject.getString("class_name");
        int class_id = jsonObject.getInteger("class_id");
        int flag = this.queryService.updateQueryClass(class_id,class_name,sqlSession);
        if(flag!=1){
            return ErrorMsg("3000","修改数据失败");
        }
        return SuccessMsg("修改数据成功",null);
    }

    @RequestMapping(value = "/getAllQueryClass", produces = "text/plain;charset=UTF-8")
    public String getAllQueryClass() {
        try{
            List<Map<String,String>> list = queryService.getAllQueryClass(DbFactory.Open(DbFactory.FORM));
            return  SuccessMsg("",list);
        }catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }

}
