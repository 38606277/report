package root.report.control.etl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.common.RO;
import root.report.db.DbFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: pccw
 * @Date: 2018/12/3 15:50
 * @Description:
 *      基于数据库动态更改
 */
@RestController
@RequestMapping("/reportServer/transfer")
public class TransferControl extends RO {

    @RequestMapping(value = "/createTransfer", produces = "text/plain;charset=UTF-8")
    public String createATransfer(@RequestBody String pJson) throws SQLException {

        JSONObject jsonObject = JSON.parseObject(pJson);
        String transfer_content = jsonObject.getString("transfer_content");
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);

        try {
            sqlSession.getConnection().setAutoCommit(false);
            // 新增一条记录
            sqlSession.insert("transfer.createTransfer",transfer_content);
            sqlSession.getConnection().commit();
        }catch (Exception e){
            sqlSession.getConnection().rollback();
            return ErrorMsg("3000",e.getMessage());
        }

        return SuccessMsg("1000","新增成功");
    }

    @RequestMapping(value = "/updateTransfer", produces = "text/plain;charset=UTF-8")
    public String updateTransfer(@RequestBody String pJson) throws SQLException {

        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);

        JSONObject jsonObject = JSON.parseObject(pJson);
        int transfer_id = jsonObject.getIntValue("transfer_id");
        String transfer_content = jsonObject.getString("transfer_content");
        Map<String,Object> map = new HashMap<>();
        map.put("transfer_id",transfer_id);
        map.put("transfer_content",transfer_content);

        try {
            sqlSession.getConnection().setAutoCommit(false);
            // 修改一条记录
            sqlSession.update("transfer.updateTransfer",map);
            sqlSession.getConnection().commit();
        }catch (Exception e){
            sqlSession.getConnection().rollback();
            return ErrorMsg("3000",e.getMessage());
        }

        return SuccessMsg("1000","修改成功");
    }


    @RequestMapping(value = "/deleteTransfer", produces = "text/plain;charset=UTF-8")
    public String deleteTransfer(@RequestBody String pJson) throws SQLException {

        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        JSONArray jsonArray = JSON.parseArray(pJson);
        try {
            sqlSession.getConnection().setAutoCommit(false);
            // 删除多条记录
            List list =  new ArrayList<>();
            jsonArray.forEach(e ->
                    sqlSession.delete("transfer.deleteTransfer",Integer.parseInt(String.valueOf(e)))
            );
            sqlSession.getConnection().commit();
        }catch (Exception e){
            sqlSession.getConnection().rollback();
            return ErrorMsg("3000",e.getMessage());
        }
        return SuccessMsg("1000","删除成功");
    }

    @RequestMapping(value = "/getAllTransfer", produces = "text/plain;charset=UTF-8")
    public String getAllTransfer(){
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        List<Map> mapList = sqlSession.selectList("transfer.getAllTransfer");
        return SuccessMsg("1000",mapList);
    }

}
