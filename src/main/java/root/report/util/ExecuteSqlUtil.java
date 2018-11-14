package root.report.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.cache.TransactionalCacheManager;
import org.apache.ibatis.cache.decorators.TransactionalCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.transaction.Transaction;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.mybatis.caches.ehcache.LoggingEhcache;
import root.report.db.DbFactory;

import java.sql.Connection;
import java.sql.SQLException;
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

    private final static TransactionalCacheManager tcm = new TransactionalCacheManager();

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

        List<?> list = null;
        List<?> cacheList = null;
        CacheKey cacheKey = null;
        if(bounds==null){
            bounds = new RowBounds();
        }

        // 1. 对executeSql 加上script标签
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append(executeSql);
        sb.append("</script>");
        log.info("转换后的sql为:->"+sb.toString());
        Configuration configuration = sqlSession.getConfiguration();
        configuration.setCacheEnabled(true);  // 开启二级缓存?

        LanguageDriver languageDriver = configuration.getDefaultScriptingLanguageInstance();  // 2. languageDriver 是帮助我们实现dynamicSQL的关键
        SqlSource sqlSource = languageDriver.createSqlSource(configuration,sb.toString(),clazz);  //  泛型化入参
      //  configuration.getCaches().forEach(e -> System.out.print(e.getId()));
        MappedStatement ms = null;
        // 如果我们从 configuration 当中可以取得到的话，则看缓存当中是否存在
        if(configuration.getMappedStatementNames().contains(namespace+"."+mapper_id)){
            ms = configuration.getMappedStatement(namespace+"."+mapper_id);
        }else {
            log.info("======不存在此mappedStatment=====");
        }
        if(ms == null){
            ms =  newSelectMappedStatement(configuration,namespace+"."+mapper_id,sqlSource,clazz,statementType);
        }
        // 组装cache
        cacheKey = sqlSession.getConfiguration().newExecutor(new Transaction() {
            @Override
            public Connection getConnection() throws SQLException {
                return this.getConnection();
            }
            @Override
            public void commit() throws SQLException {
                this.getConnection().commit();
            }
            @Override
            public void rollback() throws SQLException {
                this.getConnection().rollback();
            }
            @Override
            public void close() throws SQLException {
                this.getConnection().close();
            }
            @Override
            public Integer getTimeout() throws SQLException {
                return 5000;
            }
        }, ExecutorType.SIMPLE).createCacheKey(ms,param,bounds,ms.getBoundSql(param));
        cacheList = (List<?>)tcm.getObject(ms.getCache(),cacheKey);

        if(cacheList!=null && cacheList.size()>0){
            log.info("cache hit  缓存命中,命中率为:");
            return cacheList;
        }else {
            if(bounds!=null){
                list = sqlSession.selectList(namespace+"."+mapper_id,param,bounds);
                log.info("执行了一次查询");
            }else {
                list = sqlSession.selectList(namespace+"."+mapper_id,param);
                log.info("执行了一次查询");
            }
            // 装入缓存
            if(cacheKey!=null){
                tcm.putObject(ms.getCache(),cacheKey,list);
            }
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
                .cache(new LoggingEhcache(msId))
                .useCache(true)
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
