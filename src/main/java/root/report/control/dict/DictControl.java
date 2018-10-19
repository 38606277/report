package root.report.control.dict;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import root.configure.AppConstants;
import root.report.common.RO;
import root.report.db.DbFactory;
import root.report.query.FuncMetaData;
import root.report.query.SqlTemplate;
import root.report.service.DictService;
import root.report.service.FunctionService;
import root.report.util.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/reportServer/dict")
public class DictControl extends RO {

    private static Logger log = Logger.getLogger(DictControl.class);

    @Autowired
    private DictService dictService;



    //查询所有的数据字典
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


     //返回数据字典的定义头，out
    @RequestMapping(value = "/getDictByID/{Dict_id}", produces = "text/plain;charset=UTF-8")
    public String getDictByID(@PathVariable("dict_id") String dict_id) {
        return "未实现";
    }



    @RequestMapping(value = "/createDict", produces = "text/plain;charset=UTF-8")
    public String createDict(@RequestBody String pJson) throws Exception
    {
        //

        return "未实现";
    }

    @RequestMapping(value = "/updateDict", produces = "text/plain;charset=UTF-8")
    public String updateDict(@RequestBody String pJson) throws SQLException {


        return "未实现";
    }


    @RequestMapping(value = "/deleteDict", produces = "text/plain;charset=UTF-8")
    public String deleteDict(@RequestBody String pJson) throws SQLException {
        return "未实现";
    }


    //查询数据字典的值
    @RequestMapping(value = "/getDictValueByID/{dict_id}", produces = "text/plain;charset=UTF-8")
    public String getDictValueByID(@PathVariable("dict_id") String dict_id) {
        try{
            List<Map<String, String>> list  = dictService.getDictValueByID(dict_id);
            return  SuccessMsg("查询成功",list);
        }catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }


    //初始化导入
    @RequestMapping(value = "/initImportDictValue/{dict_id}", produces = "text/plain;charset=UTF-8")
    public String importDictValue(@PathVariable("dict_id") String dict_id) {
        //源数据加载到内存

        //写入到func_dict_value，返回是进度给前端



        return "未实现";
    }
    //增量数据导入
    @RequestMapping(value = "/changeImportDictValue/{dict_id}", produces = "text/plain;charset=UTF-8")
    public String changeDictValue(@PathVariable("dict_id") String dict_id) {
        //源数据加载到内存

        //写入到func_dict_value，返回是进度给前端



        return "未实现";
    }

    //新增字典值
    @RequestMapping(value = "/createDictValue", produces = "text/plain;charset=UTF-8")
    public String createDictValue(@RequestBody String pJson) {
        //源数据加载到内存

        //写入到func_dict_value，返回是进度给前端



        return "未实现";
    }
    //修改字典值
    @RequestMapping(value = "/updateDictValue", produces = "text/plain;charset=UTF-8")
    public String updateDictValue(@RequestBody String pJson) {
        //源数据加载到内存

        //写入到func_dict_value，返回是进度给前端



        return "未实现";
    }

    /*
    *参数格式为{dict_id:100,dict_value:['1','2','3']}
    *
    *
    * */
    @RequestMapping(value = "/deleteDictValue", produces = "text/plain;charset=UTF-8")
    public String deleteDictValue(@RequestBody String pJson) {
        //源数据加载到内存

        //写入到func_dict_value，返回是进度给前端



        return "未实现";
    }


}
