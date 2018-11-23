package root.report.control.dashboard;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.common.RO;

import java.sql.SQLException;

@RestController
@RequestMapping("/reportServer/dashboard")
public class DashboardControl extends RO {

    private static Logger log = Logger.getLogger(DashboardControl.class);



    //查询所有的数据字典
    @RequestMapping(value = "/getAllDashboard", produces = "text/plain;charset=UTF-8")
    public String getAllDashboard() {
        return "未实现";

    }


     //返回数据字典的定义头，out
    @RequestMapping(value = "/getDashboardByID/{dashboard_id}", produces = "text/plain;charset=UTF-8")
    public String getDashboardByID(@PathVariable("dashboard_id") String dashboard_id) {
        return "未实现";
    }

    /**
     * 功能描述: 接收JSON格式参数，往func_dict跟func_dict_out 中插入相关数据
     */
    @RequestMapping(value = "/createDashboard", produces = "text/plain;charset=UTF-8")
    public String createDashboard(@RequestBody String pJson) throws Exception
    {
        return "未实现";

    }

    @RequestMapping(value = "/updateDashboard", produces = "text/plain;charset=UTF-8")
    public String updateDashboard(@RequestBody String pJson) throws SQLException {
        return "未实现";

    }

    @RequestMapping(value = "/deleteDashboard", produces = "text/plain;charset=UTF-8")
    public String deleteDashboard(@RequestBody String pJson) throws SQLException {

        return "未实现";
    }





}
