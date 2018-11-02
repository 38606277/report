package root.form.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
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
    
    @RequestMapping(value = "/getUserListTotalRows", produces = "text/plain; charset=utf-8")
    public String getUserListTotalRows(@RequestBody String pJson)
    {
        UserModel userModel = JSONObject.parseObject(pJson, UserModel.class);
        int totalRows = DbFactory.Open(DbFactory.FORM).selectOne("formUser.getUserListTotalRows", userModel);
        return JSON.toJSONString(totalRows);
    }
     
    @RequestMapping(value = "/getUserList", produces = "text/plain; charset=utf-8")
    public String getUserList(@RequestBody String pJson)
    {
        JSONArray obj = (JSONArray)JSONObject.parse(pJson);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userName", ((JSONObject)obj.get(0)).get("userName"));
        map.put("startIndex", ((JSONObject)obj.get(1)).get("startIndex"));
        map.put("perPage", ((JSONObject)obj.get(1)).get("perPage"));
        List<UserModel> userInfolist = DbFactory.Open(DbFactory.FORM).selectList("formUser.getUserList",map);
        return JSON.toJSONString(userInfolist);
    }
    
    @RequestMapping(value = "/getErpUserList", produces = "text/plain; charset=utf-8")
    public String getErpUserList(@RequestBody String pJson)
    {
        JSONArray obj = (JSONArray)JSONObject.parse(pJson);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userName", ((JSONObject)obj.get(0)).get("userName"));
        map.put("startIndex", ((JSONObject)obj.get(1)).getIntValue("startIndex")+1);//startIndex从0开始,rownum从1开始
        map.put("endIndex", ((JSONObject)obj.get(1)).getIntValue("startIndex")+((JSONObject)obj.get(1)).getIntValue("perPage"));
        List<UserModel> userInfolist = DbFactory.Open(DbFactory.SYSTEM).selectList("erpUser.getErpUserList",map);
        return JSON.toJSONString(userInfolist);
    }
    
    @RequestMapping(value = "/getErpUserListTotalRows", produces = "text/plain; charset=utf-8")
    public String getErpUserListTotalRows(@RequestBody String pJson)
    {
        JSONObject obj = JSONObject.parseObject(pJson);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userName", obj.get("userName"));
        int totalRows = DbFactory.Open(DbFactory.SYSTEM).selectOne("erpUser.getErpUserListTotalRows",map);
        return JSON.toJSONString(totalRows);
    }
    @RequestMapping(value = "/getUserInfoById", produces = "text/plain; charset=utf-8")
    public String getUserInfoById(@RequestBody int id)
    {
        UserModel usermodel = DbFactory.Open(DbFactory.FORM).selectOne("formUser.getUserInfoById",id);
        return JSON.toJSONString(usermodel);
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
            if(0!=userModel.getId()){
                Map<String,Object> map=new HashMap<String,Object>();
                map.put("user_id",userModel.getId());
                map.put("role_id",userModel.getIsAdmin());
                DbFactory.Open(DbFactory.FORM).insert("role.addUserRole", map);
            }

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
        JSONObject obj = new JSONObject();
        Map<String,Object> map1=new HashMap<String,Object>();
        try{
            UserModel userModel = JSONObject.parseObject(pJson, UserModel.class);
            UserModel current_userModel = (UserModel)DbFactory.Open(DbFactory.FORM).selectOne("formUser.getUserInfoById",userModel.getId());
            map1.put("user_id",current_userModel.getId());
            map1.put("role_id",null);
            if(!userModel.getRegisType().equals("erp"))
            {
                String old_password = current_userModel.getEncryptPwd();
            	if(old_password==null||!old_password.equals(userModel.getEncryptPwd())){
            		ErpUtil erpUtil = new ErpUtil();
                    String encryptPwd = erpUtil.encode(userModel.getEncryptPwd());
                    userModel.setEncryptPwd(encryptPwd);
                }
            }
            //修改數據
            DbFactory.Open(DbFactory.FORM).update("formUser.updateUser", userModel);
            //先刪除角色id
            DbFactory.Open(DbFactory.FORM).insert("role.deleteUserRole", map1);
            String roleIdlist=userModel.getIsAdminText();
           String[] arrRole= roleIdlist.split(",");

            Map<String,Object> map=new HashMap<String,Object>();
            if(arrRole.length>0) {
                for (int i = 0; i < arrRole.length; i++) {
                    map.put("user_id", userModel.getId());
                    map.put("role_id", arrRole[i]);
                    //重新保存角色id
                    DbFactory.Open(DbFactory.FORM).insert("role.addUserRole", map);
                }
            }


            result = "success";
            obj.put("result", "success");
        }catch(Exception e){
            log.error("修改用户失败!");
            obj.put("result", "error");
            e.printStackTrace();
        }
        return JSON.toJSONString(obj);
    }
    
    @RequestMapping(value = "/deleteUser", produces = "text/plain; charset=utf-8")
    public String deleteUser(@RequestBody int id)
    {
        String result = "false";
        JSONObject obj = new JSONObject();
        try{
            DbFactory.Open(DbFactory.FORM).delete("formUser.deleteUser", id);
            result = "success";
            obj.put("result", "success");
        }catch(Exception e){
            log.error("删除用户失败!");
            //e.printStackTrace();
            obj.put("result", "error");
        }
        return JSON.toJSONString(obj);
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


    @RequestMapping(value = "/getUserListReact", produces = "text/plain; charset=utf-8")
    public String getUserListReact(@RequestBody String pJson)
    {
        // JSONArray obj = (JSONArray)JSONObject.parse(pJson);
        JSONObject obj = JSONObject.parseObject(pJson);
        Map<String,Object> map = new HashMap<String,Object>();
        int currentPage=Integer.valueOf(obj.getString("pageNum"));
        int perPage=Integer.valueOf(obj.getString("perPage"));
        if(1==currentPage|| 0==currentPage){
            currentPage=0;
        }else{
            currentPage=(currentPage-1)*perPage;
        }
        map.put("startIndex", currentPage);
        map.put("perPage",perPage);
        map.put("userName",  obj.get("keyword")==null?"":obj.getString("keyword"));
        List<UserModel> userInfolist = DbFactory.Open(DbFactory.FORM).selectList("formUser.getUserList",map);
        String total=DbFactory.Open(DbFactory.FORM).selectOne("formUser.countUser", map);
        Map<String,Object> map2 =new HashMap<String,Object>();
        Map<String,Object> map3 =new HashMap<String,Object>();
        map3.put("list",userInfolist);
        map3.put("total",Integer.valueOf(total));
        map2.put("msg","查询成功");
        map2.put("data",map3);
        map2.put("status",0);
        return JSON.toJSONString(map2);
    }
    @RequestMapping(value = "/getUserListRole", produces = "text/plain; charset=utf-8")
    public String getUserListRole(@RequestBody String pJson)
    {
        // JSONArray obj = (JSONArray)JSONObject.parse(pJson);
        JSONObject obj = JSONObject.parseObject(pJson);
        Map<String,Object> map = new HashMap<String,Object>();
        int currentPage=Integer.valueOf(obj.getString("pageNumUser"));
        int perPage=Integer.valueOf(obj.getString("perPageUser"));
        if(1==currentPage|| 0==currentPage){
            currentPage=0;
        }else{
            currentPage=(currentPage-1)*perPage;
        }
        map.put("startIndex", currentPage);
        map.put("perPage",perPage);
        map.put("userName",  obj.get("userName")==null?"":obj.getString("userName"));
        List<UserModel> userInfolist = DbFactory.Open(DbFactory.FORM).selectList("formUser.getUserList",map);
        String total=DbFactory.Open(DbFactory.FORM).selectOne("formUser.countUser", map);
        Map<String,Object> map2 =new HashMap<String,Object>();
        Map<String,Object> map3 =new HashMap<String,Object>();
        map3.put("list",userInfolist);
        map3.put("total",Integer.valueOf(total));
        map2.put("msg","查询成功");
        map2.put("data",map3);
        map2.put("status",0);
        return JSON.toJSONString(map2);
    }
    @RequestMapping(value = "/getUserInfoByUserId", produces = "text/plain; charset=utf-8")
    public String getUserInfoByUserId(@RequestBody String pJson)
    {
        JSONObject obj = JSONObject.parseObject(pJson);
        int id=Integer.valueOf(obj.get("id").toString());
        UserModel usermodel = DbFactory.Open(DbFactory.FORM).selectOne("formUser.getUserInfoById",id);
        Map<String,Object> map2 =new HashMap<String,Object>();
        Map<String,Object> map3 =new HashMap<String,Object>();
        map3.put("userInfo",usermodel);
        map2.put("msg","查询成功");
        map2.put("data",map3);
        map2.put("status",0);
        return JSON.toJSONString(map2);
    }

    @RequestMapping(value = "/updatePwd", produces = "text/plain; charset=utf-8")
    public String updatePwd(@RequestBody String pJson)
    {
        String result = "false";
        JSONObject obj = JSONObject.parseObject(pJson);
        try{
            UserModel current_userModel = (UserModel)DbFactory.Open(DbFactory.FORM).selectOne("formUser.getUserInfoById",obj.getInteger("_id"));
            String oldP=obj.getString("oldPwd");
            String newPassword=obj.getString("encryptPwd");
            ErpUtil erpUtil = new ErpUtil();
            String webOldpasswd = erpUtil.encode(oldP);
            String webNewpasswd = erpUtil.encode(newPassword);

            String old_password = current_userModel.getEncryptPwd();
            if(old_password!=null) {
                if (webOldpasswd.equals(current_userModel.getEncryptPwd())) {
                    current_userModel.setEncryptPwd(webNewpasswd);
                    //修改數據
                    DbFactory.Open(DbFactory.FORM).update("formUser.updateUser", current_userModel);
                   result="success";
                }else{
                    result="faile";
            }
            }else{
                result="faile";
            }

        }catch(Exception e){
            log.error("修改用户失败!");
            result="error";
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
}
