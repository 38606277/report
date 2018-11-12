package root.report.util;

import org.apache.ibatis.mapping.*;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: pccw
 * @Date: 2018/11/9 16:14
 * @Description:
 */
public class ExecuteSqlUtil {

    private static Logger log = Logger.getLogger(ExecuteSqlUtil.class);

    /**
     *
     * 功能描述:
     *      对符合 mybatis.dtd形式的sql进行动态sql解析并执行，返回Map结构的数据集
     * @param: executeSql 要执行的sql ， sqlSession 数据库会话，namespace 命名空间，mapper_id mapper的ID，bounds 分页参数 ,statementType statement的类型
     * @auther:
     * @date: 2018/11/9 16:17
     */
    public static List<?> executeDataBaseSql(String executeSql, SqlSession sqlSession, String namespace, String mapper_id, RowBounds bounds,
                                               Class<?> clazz,Object param,StatementType statementType){
        if(statementType==null){
            statementType = StatementType.PREPARED; // 默认为 prepared
        }
        // 1. 对executeSql 加上script标签
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append(executeSql);
        sb.append("</script>");
        log.info(sb.toString());
        Configuration configuration = sqlSession.getConfiguration();
        LanguageDriver languageDriver = configuration.getDefaultScriptingLanguageInstance();  // 2. languageDriver 是帮助我们实现dynamicSQL的关键
        SqlSource sqlSource = languageDriver.createSqlSource(configuration,sb.toString(),clazz);  //  泛型化入参
        newSelectMappedStatement(configuration,namespace+"."+mapper_id,sqlSource,clazz,statementType);
        List<?> list = null;
        if(bounds!=null){
            list = sqlSession.selectList(namespace+"."+mapper_id,param,bounds);
        }else {
            list = sqlSession.selectList(namespace+"."+mapper_id,param);
        }

        return list;
    }

    //
    private  static MappedStatement newSelectMappedStatement(Configuration configuration,String msId, SqlSource sqlSource, final Class<?> resultType,StatementType statementType) {
        // 加强逻辑 ： 一定要防止 MappedStatement 重复问题
        MappedStatement msTest = null;
        try{
            synchronized (configuration) {   // 防止并发插入多次
                msTest = configuration.getMappedStatement(msId);
                if (msTest != null) {
                    configuration.getMappedStatementNames().remove(msTest.getId());
                }

            }
        }catch (IllegalArgumentException e){
            log.info("没有此mappedStatment,可以注入此mappedStatement到configuration当中");
        }
        // 构建一个 select 类型的ms ，通过制定SqlCommandType.SELECT
        MappedStatement ms = new MappedStatement.Builder(
                configuration, msId, sqlSource, SqlCommandType.SELECT)
                .statementType(statementType)
                .resultMaps(new ArrayList<ResultMap>() {
                    {
                        add(new ResultMap.Builder(configuration,
                                "defaultResultMap",
                                resultType,
                                new ArrayList<ResultMapping>(0)).build());
                    }
                })
                .build();
        synchronized (configuration){
            configuration.addMappedStatement(ms); // 加入到此中去
        }
        return ms;
    }

}
