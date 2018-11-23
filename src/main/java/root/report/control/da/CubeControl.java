package root.report.control.da;

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
import root.report.service.DictService;
import root.report.util.ExecuteSqlUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/cube")
public class CubeControl extends RO {

    private static Logger log = Logger.getLogger(CubeControl.class);



    //查询所有的数据字典
    @RequestMapping(value = "/getAllCube", produces = "text/plain;charset=UTF-8")
    public String getAllDictName() {
        return "未实现";

    }


     //返回数据字典的定义头，out
    @RequestMapping(value = "/getCubeByID/{cube_id}", produces = "text/plain;charset=UTF-8")
    public String getDictByID(@PathVariable("cube_id") String cube_id) {
        return "未实现";
    }

    /**
     * 功能描述: 接收JSON格式参数，往func_dict跟func_dict_out 中插入相关数据
     */
    @RequestMapping(value = "/createCube", produces = "text/plain;charset=UTF-8")
    public String createCube(@RequestBody String pJson) throws Exception
    {
        return "未实现";

    }

    @RequestMapping(value = "/updateCube", produces = "text/plain;charset=UTF-8")
    public String updateCube(@RequestBody String pJson) throws SQLException {
        return "未实现";

    }

    @RequestMapping(value = "/deleteCube", produces = "text/plain;charset=UTF-8")
    public String deleteCube(@RequestBody String pJson) throws SQLException {

        return "未实现";
    }


    @RequestMapping(value = "/getCubeValueByID/{cube_id}", produces = "text/plain;charset=UTF-8")
    public String getCubeValueByID(@PathVariable("cube_id") String dict_id,@RequestBody String pjson) {
        return "未实现";
    }




}
