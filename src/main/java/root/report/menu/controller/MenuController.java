package root.report.menu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageRowBounds;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.common.RO;
import root.report.db.DbFactory;
import root.report.menu.service.MenuService;
import root.report.service.DictService;
import root.report.util.ExecuteSqlUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/menu")
public class MenuController extends RO {

    private static Logger log = Logger.getLogger(MenuController.class);

    @Autowired
    public MenuService menuService;

    /**
     * 功能描述: 接收JSON格式参数，往func_dict跟func_dict_out 中插入相关数据
     */
    @RequestMapping(value = "/saveMenu", produces = "text/plain;charset=UTF-8")
    public String createMenu(@RequestBody String pJson) throws Exception
    {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject jsonObject = JSON.parseObject(pJson);
            String id  = this.menuService.saveOrUpdateMenu(sqlSession,jsonObject);
            sqlSession.getConnection().commit();
            return SuccessMsg("保存成功",id);
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/getAllPage", produces = "text/plain;charset=UTF-8")
    public String getAllQueryNamePage(@RequestBody String pJson) {
        try {
            JSONObject jsonFunc = JSONObject.parseObject(pJson);
            Map<String,String> map=new HashMap();
            map.put("startIndex",jsonFunc.getString("startIndex"));
            map.put("perPage",jsonFunc.getString("perPage"));
            map.put("func_name",jsonFunc.get("func_name")==null?"":jsonFunc.get("func_name").toString());
            Map<String,Object> map1 = menuService.getAllPage(map);
            return SuccessMsg("", map1);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/getAllList", produces = "text/plain;charset=UTF-8")
    public String getAllList() {
        try {
//            JSONObject jsonFunc = JSONObject.parseObject(pJson);
//            Map<String,String> map=new HashMap();
//            map.put("func_id","-1");
            List<Map> map1 = menuService.getAllList("0");
            return SuccessMsg("", map1);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }


     //返回数据
    @RequestMapping(value = "/getMenuByID", produces = "text/plain;charset=UTF-8")
    public String getMenuByID(@RequestBody String pjson) {
        JSONObject obj=JSON.parseObject(pjson);
        try{
            Map map = new HashMap();
            map.put("func_id",obj.get("func_id"));
            Map jsonObject = this.menuService.getMenuByID(map);
            return SuccessMsg("查询成功",jsonObject);
        }catch (Exception ex){
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }
    }


    // 从json数据当中解析 ，批量删除
    @RequestMapping(value = "/deleteMenuByArrId", produces = "text/plain;charset=UTF-8")
    public String deleteMenuByArrId(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONArray jsonArray =  JSONObject.parseArray(pJson);
            for(int i = 0; i < jsonArray.size(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String func_id=jsonObject.getString("func_id");
                //删除
                this.menuService.deleteMenuById(sqlSession,func_id);
            }
            sqlSession.getConnection().commit();
            return SuccessMsg("删除成功",null);
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }finally {
            sqlSession.getConnection().setAutoCommit(true);
        }
    }
    @RequestMapping(value = "/deleteMenuById", produces = "text/plain;charset=UTF-8")
    public String deleteMenuById(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession =  DbFactory.Open(DbFactory.FORM);
        try{
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject obj=JSON.parseObject(pJson);
            String ids = obj.getString("func_id");
            String[] arrId=ids.split(",");
            for(int i = 0; i < arrId.length; i++){
                //删除
                this.menuService.deleteMenuById(sqlSession,arrId[0]);
            }
            sqlSession.getConnection().commit();
            return SuccessMsg("删除成功",null);
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            ex.printStackTrace();
            return ExceptionMsg(ex.getMessage());
        }finally {
            sqlSession.getConnection().setAutoCommit(true);
        }
    }

}
