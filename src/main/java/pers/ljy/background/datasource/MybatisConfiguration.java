package pers.ljy.background.datasource;

import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/***
 * 文件名称: MybatisConfiguration.java
 * 文件描述: 多数据源 注入
 * 公 司: 
 * 内容摘要: 通过MybatisAutoConfiguration来实现多数据源注入的
 * 其他说明:
 * 完成日期:2017年04月24日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Configuration
@AutoConfigureAfter(DataSourceConfiguration.class)
public class MybatisConfiguration extends MybatisAutoConfiguration {
      
	  /* 主库数据源 */
	  @Resource(name = "masterDataSource")
	  private DataSource masterDataSource;
	  /* 从库数据源 */
	  @Resource(name = "slaveDataSource")
	  private DataSource slaveDataSource;
	 
	  /**
	   * 获取源的时候通过threadlocal中不同的标识给出不同的sqlSession
	   * @return
	   * @throws Exception
	   */
	  @Bean
	  public SqlSessionFactory sqlSessionFactory() throws Exception {
	    return super.sqlSessionFactory(roundRobinDataSouceProxy());
	  }
	 
	  /**
	   * 获取不同数据源
	   * @return
	   */
	  @SuppressWarnings("unchecked")
	  public AbstractRoutingDataSource roundRobinDataSouceProxy(){
		   ReadWriteSplitRoutingDataSource proxy = new ReadWriteSplitRoutingDataSource();
		   Map<Object,Object> targetDataResources = new ClassLoaderRepository.SoftHashMap();
		   targetDataResources.put(DbContextHolder.DbType.MASTER,masterDataSource);
		   targetDataResources.put(DbContextHolder.DbType.SLAVE,slaveDataSource);
		   //默认主库数据源
		   proxy.setDefaultTargetDataSource(masterDataSource);
		   proxy.setTargetDataSources(targetDataResources);
		   return proxy;
	  }

	
}
