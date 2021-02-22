package root.report.datastorage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.algorithm.service.AiModelService;
import root.report.common.RO;
import root.report.datastorage.taos.TaosService;
import root.report.db.DbFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/dbTableColumn")
public class DbTableColumnController extends RO {

    @Autowired
    public TaosService taosService;

    @RequestMapping(value = "/getTableList", produces = "text/plain;charset=UTF-8")
    public String getAiModelList(@RequestBody JSONObject pJson)  {
        try {
            Map<String,Object> map=new HashMap();
            String tablename=pJson.getString("host_id");
            String dbType=pJson.getString("dbType");
            List resultMap = taosService.getTableList(tablename,dbType);
            return SuccessMsg("", resultMap);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }


}
