package root.report.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import root.report.db.DbFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


/**
 * @Auther: pccw - liaoxufeng
 * @Date: 2018/8/6 15:56
 * @Description: 往mysql的 func_name  func_in func_out 插入对应的信息
 */
@Component
public class FunctionService {
    
    /**
     *
     * 功能描述: 查询func_name表
     *
     * @param: map
     * @return: 
     * @auther: pccw
     * @date: 2018/8/6 16:09
     */
    public String getFunctionName(Map<String,String> map){
        List<Map<String,String>> resultList = new  ArrayList<Map<String,String>>();
        List<Map<String,String>> selectMap = DbFactory.Open(DbFactory.FORM).selectList("function.getFunctionName",map);
        if(selectMap!=null && selectMap.size()>0){
            resultList.addAll(selectMap);
        }
        // 默认返回第一个
        return JSONObject.toJSONString(resultList.get(0));
    }


    /**
     *
     * 功能描述: 往func_name表增加记录
     *
     * @param:
     * @return:
     * @auther: pccw
     * @date: 2018/8/6 16:09
     */
    public int addFunctionName(List<Map<String,String>> mapList){
        // 事务管理 放在了controller层,return 0 意味着要进行事务回滚
        for(Map<String,String> tempMap : mapList){
            int number = DbFactory.Open(DbFactory.FORM).insert("function.addFunctionName",tempMap);
            if( number!=1 ) {
                return 0;
            }
        }
        return 1;
    }

    /**
     *
     * 功能描述: 删除func_name当中的记录
     *
     * @param:
     * @return:
     * @auther: pccw
     * @date: 2018/8/6 16:09
     */
    public String deleteFunctionName(List<Map<String,String>> map){
        return "";
    }

    /**
     *
     * 功能描述: 查询 func_in表
     *
     * @param:
     * @return:
     * @auther: pccw
     * @date: 2018/8/6 16:09
     */
    public String getFunctionIn(List<Map<String,String>> map){
        return "";
    }

    /**
     *
     * 功能描述: 增加func_in表记录
     *
     * @param:
     * @return:
     * @auther: pccw
     * @date: 2018/8/6 16:09
     */
    public int addFunctionIn(List<Map<String,String>> mapList){
        // 事务管理 放在了controller层,return 0 意味着要进行事务回滚
        for(Map<String,String> tempMap : mapList){
            int number = DbFactory.Open(DbFactory.FORM).insert("function.addFunctionIn",tempMap);
            if( number!=1 ) {
                return 0;
            }
        }
        return 1;
    }

    /**
     *
     * 功能描述: 删除func_in表的记录
     *
     * @param:
     * @return:
     * @auther: pccw
     * @date: 2018/8/6 16:09
     */
    public String deleteFunctionIn(List<Map<String,String>> map){
        return "";
    }

    /**
     *
     * 功能描述: 查询func_out表的记录
     *
     * @param:
     * @return:
     * @auther: pccw
     * @date: 2018/8/6 16:09
     */
    public String selectFunctionOut(List<Map<String,String>> map){
        return "";
    }

    /**
     *
     * 功能描述: 新增func_out表的记录
     *
     * @param:
     * @return:
     * @auther: pccw
     * @date: 2018/8/6 16:09
     */
    public int addFunctionOut(List<Map<String,String>> mapList){
        // 事务管理 放在了controller层,return 0 意味着要进行事务回滚
        for(Map<String,String> tempMap : mapList){
            int number = DbFactory.Open(DbFactory.FORM).insert("function.addFunctionOut",tempMap);
            if( number!=1 ) {
                return 0;
            }
        }
        return 1;
    }

    /**
     *
     * 功能描述: 删除func_out表的记录
     *
     * @param:
     * @return:
     * @auther: pccw
     * @date: 2018/8/6 16:09
     */
    public String deleteFunctionOut(List<Map<String,String>> map){
        return "";
    }


}
