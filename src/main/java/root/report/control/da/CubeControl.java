package root.report.control.da;

import com.alibaba.druid.support.json.JSONUtils;
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
import root.report.service.CubeService;
import root.report.service.DictService;
import root.report.util.ExecuteSqlUtil;
import root.report.util.JsonUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/cube")
public class CubeControl extends RO {

    private static Logger log = Logger.getLogger(CubeControl.class);

    @Autowired
    CubeService cubeService;

    //查询所有的cube 记录
    @RequestMapping(value = "/getAllCube", produces = "text/plain;charset=UTF-8")
    public String getAllDictName() {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try {
            // TODO 是否需要分页？
            List<Map> mapList = sqlSession.selectList("cube.getAllCube");
            return SuccessMsg("",JSON.toJSONString(mapList,JsonUtil.features));
        }catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }


     //返回数据字典的定义头，out
    @RequestMapping(value = "/getCubeByID/{cube_id}", produces = "text/plain;charset=UTF-8")
    public String getCubeByID(@PathVariable("cube_id") String cube_id) {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try {
            Map map = sqlSession.selectOne("cube.getCubeById",Integer.parseInt(cube_id));
            return SuccessMsg("",JSON.toJSONString(map,JsonUtil.features));
        }catch (Exception ex){
            return ExceptionMsg(ex.getMessage());
        }
    }

    /**
     * 功能描述: 接收JSON格式参数，往func_dict跟func_dict_out 中插入相关数据
     *          往da_cube 当中插入对应数据
     */
    @RequestMapping(value = "/createCube", produces = "text/plain;charset=UTF-8")
    public String createCube(@RequestBody String pJson) throws Exception
    {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try {
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject jsonObject = JSON.parseObject(pJson);
            String uuid = this.cubeService.createCuBe(sqlSession,jsonObject);
            sqlSession.getConnection().commit();
            return SuccessMsg("新增cube记录成功",uuid);
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            return ExceptionMsg(ex.getMessage());
        }
    }

    @RequestMapping(value = "/updateCube", produces = "text/plain;charset=UTF-8")
    public String updateCube(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try {
            sqlSession.getConnection().setAutoCommit(false);
            JSONObject jsonObject = JSON.parseObject(pJson);
            this.cubeService.updateCuBe(sqlSession,jsonObject);
            sqlSession.getConnection().commit();
            return SuccessMsg("修改cube记录成功","");
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            return ExceptionMsg(ex.getMessage());
        }
    }

    // 批量删除 da_cube 表的记录
    @RequestMapping(value = "/deleteCube", produces = "text/plain;charset=UTF-8")
    public String deleteCube(@RequestBody String pJson) throws SQLException {
        SqlSession sqlSession = DbFactory.Open(DbFactory.FORM);
        try {
            sqlSession.getConnection().setAutoCommit(false);
            JSONArray jsonArray = JSON.parseArray(pJson);
            for(int i = 0; i < jsonArray.size(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int cube_id = jsonObject.getInteger("cube_id");
                sqlSession.delete("cube.deleteCubeById",cube_id);
            }
            sqlSession.getConnection().commit();
            return SuccessMsg("删除成功","");
        }catch (Exception ex){
            sqlSession.getConnection().rollback();
            return ExceptionMsg(ex.getMessage());
        }
    }


    @RequestMapping(value = "/getCubeValueByID/{cube_id}", produces = "text/plain;charset=UTF-8")
    public String getCubeValueByID(@PathVariable("cube_id") String dict_id,@RequestBody String pjson) {
        return "未实现";
    }




}
