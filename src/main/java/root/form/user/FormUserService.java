package root.form.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.report.db.DbFactory;
import root.report.util.ErpUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportServer/formUser")
public class FormUserService
{
    private static final Logger log = Logger.getLogger(FormUserService.class);
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/getUserList", produces = "text/plain; charset=utf-8")
    public String getUserList(@RequestBody JSONArray pJson)
    {
        String userName = pJson.getJSONObject(0).getString("userName");
        JSONObject page = null;
        if(pJson.size()>1){
            page = pJson.getJSONObject(1);  //分页对象
            PageHelper.offsetPage(page.getIntValue("startIndex"), page.getIntValue("perPage"));
        }
        List<UserModel> userList = userMapper.getUserList(userName);
        PageInfo<UserModel> pageInfo = new PageInfo<UserModel>(userList);
        JSONObject result = new JSONObject();
        result.put("totalSize",pageInfo.getTotal());
        result.put("list",pageInfo.getList());
        return JSON.toJSONString(result);
    }
    
    @RequestMapping(value = "/getErpUserList", produces = "text/plain; charset=utf-8")
    public String getErpUserList(@RequestBody String pJson)
    {
        JSONArray obj = (JSONArray)JSONObject.parse(pJson);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userName", ((JSONObject)obj.get(0)).get("userName"));
        map.put("startIndex", ((JSONObject)obj.get(1)).getIntValue("startIndex")+1);//startIndex从0开始,rownum从1开始
        map.put("endIndex", ((JSONObject)obj.get(1)).getIntValue("startIndex")+((JSONObject)obj.get(1)).getIntValue("perPage"));
        List<UserModel> userInfolist = DbFactory.Open(DbFactory.SYSTEM).selectList("formUser.getErpUserList",map);
        return JSON.toJSONString(userInfolist);
    }
    
    @RequestMapping(value = "/getErpUserListTotalRows", produces = "text/plain; charset=utf-8")
    public String getErpUserListTotalRows(@RequestBody String pJson)
    {
        JSONObject obj = JSONObject.parseObject(pJson);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userName", obj.get("userName"));
        int totalRows = DbFactory.Open(DbFactory.SYSTEM).selectOne("formUser.getErpUserListTotalRows",map);
        return JSON.toJSONString(totalRows);
    }

    @RequestMapping(value = "/getUserInfoById", produces = "text/plain; charset=utf-8")
    public String getUserInfoById(@RequestBody int id){
        return JSON.toJSONString(userMapper.getUserInfoById(id));
    }
    
    @RequestMapping(value = "/addUser", produces = "text/plain; charset=utf-8")
    public String addUser(@RequestBody String pJson)
    {
        JSONObject obj = new JSONObject();
        try{
            UserModel userModel = JSONObject.parseObject(pJson, UserModel.class);
            //对用户密码加密
            if(!userModel.getRegisType().equals("erp"))
            {
                ErpUtil erpUtil = new ErpUtil();
                String encryptPwd = erpUtil.encode(userModel.getEncryptPwd());
                userModel.setEncryptPwd(encryptPwd);
            }
            DbFactory.Open(DbFactory.FORM).insert("formUser.addUser", userModel);
            obj.put("result", "success");
        }catch(Exception e){
            log.error("新增用户失败!");
            obj.put("result", "error");
            obj.put("errMsg", "新增用户失败!");
            e.printStackTrace();
        }
        return JSON.toJSONString(obj);
    }
    
    @RequestMapping(value = "/updateUser", produces = "text/plain; charset=utf-8")
    public String updateUser(@RequestBody String pJson)
    {
        String result = "false";
        try{
            UserModel userModel = JSONObject.parseObject(pJson, UserModel.class);
            if(!userModel.getRegisType().equals("erp"))
            {
            	UserModel current_userModel = (UserModel)DbFactory.Open(DbFactory.FORM).selectOne("formUser.getUserInfoById",userModel.getId());
            	String old_password = current_userModel.getEncryptPwd();
            	if(old_password==null||!old_password.equals(userModel.getEncryptPwd())){
            		ErpUtil erpUtil = new ErpUtil();
                    String encryptPwd = erpUtil.encode(userModel.getEncryptPwd());
                    userModel.setEncryptPwd(encryptPwd);
                }
            }
            DbFactory.Open(DbFactory.FORM).update("formUser.updateUser", userModel);
            result = "success";
        }catch(Exception e){
            log.error("修改用户失败!");
            e.printStackTrace();
        }
        return result;
    }
    
    @RequestMapping(value = "/deleteUser", produces = "text/plain; charset=utf-8")
    public String deleteUser(@RequestBody int id)
    {
        String result = "false";
        try{
            DbFactory.Open(DbFactory.FORM).delete("formUser.deleteUser", id);
            result = "success";
        }catch(Exception e){
            log.error("删除用户失败!");
            e.printStackTrace();
        }
        return result;
    }
    
    @RequestMapping(value = "/getCountByUserId", produces = "text/plain; charset=utf-8")
    public String getUserInfoById(@RequestBody String userId)
    {
        int count = DbFactory.Open(DbFactory.FORM).selectOne("formUser.getCountByUserId",userId);
        return JSON.toJSONString(count);
    }
    
    @RequestMapping(value = "/getCountByUserName", produces = "text/plain; charset=utf-8")
    public String getCountByUserName(@RequestBody String userName)
    {
        int count = DbFactory.Open(DbFactory.FORM).selectOne("formUser.getCountByUserName",userName);
        return JSON.toJSONString(count);
    }
    
}
