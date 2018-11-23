package root.report.control.dashboard;

import com.alibaba.fastjson.JSON;
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
import root.report.service.DashboardService;
import root.report.util.JsonUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/dashboard")
public class DashboardControl extends RO {

    private static Logger log = Logger.getLogger(DashboardControl.class);

    @Autowired
    DashboardService dashboardService;

    //查询所有的数据字典
    @RequestMapping(value = "/getAllDashboard", produces = "text/plain;charset=UTF-8")
    public String getAllDashboard() {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try {
            // TODO 是否需要分页？
            List<Map> mapList = sqlSession.selectList("dashboard.getAllDashboard");
            return SuccessMsg("",JSON.toJSONString(mapList,JsonUtil.features));
        }catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }


     //返回数据字典的定义头，out
    @RequestMapping(value = "/getDashboardByID/{dashboard_id}", produces = "text/plain;charset=UTF-8")
    public String getDashboardByID(@PathVariable("dashboard_id") String dashboard_id) {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try {
            Map map = sqlSession.selectOne("dashboard.getDashBoardById",Integer.parseInt(dashboard_id));
            return SuccessMsg("",JSON.toJSONString(map,JsonUtil.features));
        }catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }

    /**
     * 功能描述: 接收JSON格式参数，往func_dict跟func_dict_out 中插入相关数据
     */
    @RequestMapping(value = "/createDashboard", produces = "text/plain;charset=UTF-8")
    public String createDashboard(@RequestBody String pJson) throws Exception
    {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try {
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject jsonObject = JSON.parseObject(pJson);
            String uuid = this.dashboardService.createDashBoard(sqlSession,jsonObject);
            sqlSession.getConnection().commit();
            return SuccessMsg("新增dashboard记录成功",uuid);
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/updateDashboard", produces = "text/plain;charset=UTF-8")
    public String updateDashboard(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try {
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject jsonObject = JSON.parseObject(pJson);
            this.dashboardService.updateDashBoard(sqlSession,jsonObject);
            sqlSession.getConnection().commit();
            return SuccessMsg("修改dashboard记录成功","");
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/deleteDashboard", produces = "text/plain;charset=UTF-8")
    public String deleteDashboard(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try {
            sqlSession.getConnection().setAutoCommit(false);
            JSONArray jsonArray = JSON.parseArray(pJson);
            for(int i = 0; i < jsonArray.size(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int dashboard_id = jsonObject.getInteger("dashboard_id");
                sqlSession.delete("dashboard.deleteDashBoardById",dashboard_id);
            }
            sqlSession.getConnection().commit();
            return SuccessMsg("删除成功","");
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            return ExceptionMsg(ex.getMessage());
        }
    }

}
