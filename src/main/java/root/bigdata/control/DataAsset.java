package root.bigdata.control;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
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
public class DataAsset extends RO {

    @RequestMapping(value = "/reportServer/dataAsset/getDataList", produces = "text/plain;charset=UTF-8")
    public String getDataTable(@RequestBody JSONObject pJson)  {
        List<Map> list = DbFactory.Open(DbFactory.FORM).selectList("dataAsset.getDataView",pJson);
        return SuccessMsg("",list);

    }


    @RequestMapping(value = "/reportServer/dataAsset/getTablesByHost", produces = "text/plain;charset=UTF-8")
    public String getTablesByHost(@RequestBody JSONObject pJson)  {
        List<Map> list = DbFactory.Open(DbFactory.FORM).selectList("dataAsset.getTablesByHost",pJson);

        return SuccessMsg("",list);

    }

    @RequestMapping(value = "/reportServer/dataAsset/getTablesBySource", produces = "text/plain;charset=UTF-8")
    public String getTablesBySource(@RequestBody JSONObject pJson)  {
        List<Map> list = DbFactory.Open(DbFactory.FORM).selectList("dataAsset.getTablesBySource",pJson);

        return SuccessMsg("",list);

    }

    @RequestMapping(value = "/reportServer/dataAsset/getTablesByCatalog", produces = "text/plain;charset=UTF-8")
    public String getTablesByCatalog(@RequestBody JSONObject pJson)  {
        List<Map> list = DbFactory.Open(DbFactory.FORM).selectList("dataAsset.getTablesByCatalog",pJson);

        return SuccessMsg("",list);

    }

    @RequestMapping(value = "/reportServer/dataAsset/getTablesByDbType", produces = "text/plain;charset=UTF-8")
    public String getTablesByDbType(@RequestBody JSONObject pJson)  {
        List<Map> list = DbFactory.Open(DbFactory.FORM).selectList("dataAsset.getTablesByDbType",pJson);

        return SuccessMsg("",list);

    }



    @RequestMapping(value = "/reportServer/dataAsset/getValueByHostAndTable", produces = "text/plain;charset=UTF-8")
    public String getValueByHostAndTable(@RequestBody JSONObject pJson) {
        Map<String, Object> map1 = new HashMap<>();
        try {
            Map pam=new HashMap();
            pam.put("dbtype_id",pJson.getString("dbtype_id"));
            pam.put("host_id",pJson.getString("host_id"));
            pam.put("table_name",pJson.getString("table_name"));
            SqlSession sqlSession = DbFactory.Open(pJson.getString("host_id"));
            RowBounds bounds = null;
            if (pJson == null) {
                bounds = RowBounds.DEFAULT;
            } else {
                Integer startIndex = Integer.parseInt(pJson.getString("startIndex"));
                Integer perPage = Integer.parseInt(pJson.getString("perPage"));
                if (startIndex == 1 || startIndex == 0) {
                    startIndex = 0;
                } else {
                    startIndex = (startIndex - 1) * perPage;
                }
                bounds = new PageRowBounds(startIndex, perPage);
            }
            List<Map<String, Object>> resultList = sqlSession.selectList("dataAsset.getValueByHostAndTable", pam, bounds);
            Long totalSize = 0L;
            if (pJson != null && pJson.size() != 0) {
                totalSize = ((PageRowBounds) bounds).getTotal();
            } else {
                totalSize = Long.valueOf(resultList.size());
            }
            if (pJson.getString("dbtype_id").equals("hive")) {
                List<Map> hivelist = new ArrayList<Map>();
                for (Map aRow : resultList) {

                    Map aObject = (Map) aRow.get(pJson.getString("table_name"));
                    hivelist.add(aObject);
                }
                map1.put("list", hivelist);
            } else {
                map1.put("list", resultList);
            }
            map1.put("total", totalSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SuccessMsg("", map1);
        //List<Map> list =sqlSession.selectList("dataAsset.getValueByHostAndTable",pJson);
//        if(pJson.getString("dbtype_id").equals("hive"))
//        {
//
//            List<Map> hivelist=new ArrayList<Map>();
//            for(Map aRow : list) {
//
//              Map aObject=(Map)aRow.get(pJson.getString("table_name"));
//              hivelist.add(aObject);
//            }
//            return  SuccessMsg("",hivelist);
//
//        }else
//        {
//            return SuccessMsg("",list);
//        }


    }




}
