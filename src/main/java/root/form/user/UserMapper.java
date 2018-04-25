package root.form.user;

import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper {
    @Select(" select t._id,t.user_id,t.user_name,t.creation_date,t.isAdmin,t.encrypted_user_password,t.import,\n" +
            "        case when t.isAdmin=1 then '管理员' else '普通用户' end isAdminText,t.start_date,\n" +
            "        t.end_date,t.description\n" +
            "   from fnd_user t \n" +
            "  where t.user_name like '%${userName}%'")
    @Results(id = "userModel",value = {
            @Result(property = "id",  column = "_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName",  column = "user_name"),
            @Result(property = "creationDate", column = "creation_date"),
            @Result(property = "isAdmin",  column = "isAdmin"),
            @Result(property = "encryptPwd", column = "encrypted_user_password"),
            @Result(property = "regisType",  column = "import"),
            @Result(property = "isAdminText", column = "isAdminText"),
            @Result(property = "startDate",  column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "description", column = "description")
    })
    public List<UserModel> getUserList(@Param("userName") String userName);

    @Select(" select t._id,t.user_id,t.user_name,t.creation_date,t.start_date,t.end_date,t.description,\n" +
            "               t.isAdmin,t.encrypted_user_password,t.import\n" +
            "   from fnd_user t \n" +
            "  where t._id=#{id} ")
    @ResultMap("userModel")
    public UserModel getUserInfoById(@Param("id") int id);
}
