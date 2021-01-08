package root.bd;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.fnd.entity.TreeNode;
import root.fnd.service.TreeBuilder;
import root.report.common.RO;
import root.report.db.DbFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ModelControl extends RO {

    @RequestMapping(value = "/reportServer/bd/model/getModelList", produces = "text/plain;charset=UTF-8")
    public String getModelList(@RequestBody JSONObject pJson)  {
        List<Map> list = DbFactory.Open(DbFactory.FORM).selectList("dataAsset.getDataView",pJson);
        return SuccessMsg("",list);

    }


    @RequestMapping(value = "/reportServer/bd/model/getModelById", produces = "text/plain;charset=UTF-8")
    public String getModelById(@RequestBody JSONObject pJson)  {
        List<Map> list = DbFactory.Open(DbFactory.FORM).selectList("dataAsset.getTablesByHost",pJson);

        return SuccessMsg("",list);

    }

    @RequestMapping(value = "/reportServer/bd/model/createModel", produces = "text/plain;charset=UTF-8")
    public String getTablesBySource(@RequestBody JSONObject pJson)  {
        List<Map> list = DbFactory.Open(DbFactory.FORM).selectList("dataAsset.getTablesBySource",pJson);

        return SuccessMsg("",list);

    }

    @RequestMapping(value = "/reportServer/bd/model/updateModel", produces = "text/plain;charset=UTF-8")
    public String updateModel(@RequestBody JSONObject pJson)  {
        List<Map> list = DbFactory.Open(DbFactory.FORM).selectList("dataAsset.getTablesByCatalog",pJson);

        return SuccessMsg("",list);

    }

    @RequestMapping(value = "/reportServer/bd/model/deleteModel", produces = "text/plain;charset=UTF-8")
    public String deleteModel(@RequestBody JSONObject pJson)  {
        List<Map> list = DbFactory.Open(DbFactory.FORM).selectList("dataAsset.getTablesByCatalog",pJson);

        return SuccessMsg("",list);

    }


}
