package root.report.control.dict;

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
import root.report.service.DictService;
import root.report.service.FunctionService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/dict")
public class DictControl extends RO {

    private static Logger log = Logger.getLogger(DictControl.class);

    @Autowired
    private DictService dictService;



    @RequestMapping(value = "/getAllDictName", produces = "text/plain;charset=UTF-8")
    public String getAllDictName() {

        List<Map<String, String>> list ;
        try {
            list=dictService.getAllDictName();
            return SuccessMsg("", list);
        } catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }


    }

}
