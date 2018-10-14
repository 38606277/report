package root;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import root.report.db.DbFactory;
import root.report.db.DbManager;
import root.report.util.ErpUtil;

import javax.sql.DataSource;

@SpringBootApplication(exclude={MongoAutoConfiguration.class,MongoDataAutoConfiguration.class,DataSourceAutoConfiguration.class})
@PropertySource(value={"file:config/application.properties"})
@EnableScheduling
@EnableTransactionManagement
public class ReportServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportServerApplication.class, args);
	}

//	// 手动添加事务管理器
//	@Bean
//	public PlatformTransactionManager txManager() {
//		DruidDataSource dataSource = null;
//		try {
//			//  得到form数据库的相关连接信息，并组装到 DataSource当中去，且把此数据源DataSource交付给事务管理器
//			JSONObject dbJson = JSONObject.parseObject(new DbManager().getDBConnectionByName(DbFactory.FORM));
//			String dbtype = dbJson.getString("dbtype");
//			dataSource = new DruidDataSource();
//			dataSource.setUsername(dbJson.getString("username"));
//			dataSource.setPassword(new ErpUtil().decode(dbJson.getString("password")));
//			dataSource.setDriverClassName(dbJson.getString("driver"));
//			if ("Mysql".equals(dbtype)) {
//				// 由于使用的是mysql5.7以上版本，需要后缀如下信息
//				dataSource.setUrl(dbJson.getString("url") + "?serverTimezone=UTC&useSSL=true&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&rewriteBatchedStatements=true");
//			} else {
//				dataSource.setUrl(dbJson.getString("url"));
//			}
//			dataSource.setMaxWait(10000);//设置连接超时时间10秒
//			dataSource.setMaxActive(Integer.valueOf(dbJson.getString("maxPoolSize")));
//			dataSource.setInitialSize(Integer.valueOf(dbJson.getString("minPoolSize")));
//			dataSource.setTimeBetweenEvictionRunsMillis(60000);  //检测数据源空连接间隔时间
//			dataSource.setMinEvictableIdleTimeMillis(300000);    //连接空闲时间
//			dataSource.setTestWhileIdle(true);
//			dataSource.setTestOnBorrow(true);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new DataSourceTransactionManager(dataSource);
//	}

}
