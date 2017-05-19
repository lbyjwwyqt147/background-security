package pers.ljy.background.datasource;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
@MapperScan(basePackages={"pers.ljy.background.mapper"}) 
public class MybatisConfiguration extends MybatisAutoConfiguration {
      
	  /** 从库数量(有多少个从库)*/
	  @Value("${druid.slaveSize}")
	  private String slaveSize;
	
	  /* 主库数据源 */
	  @Resource(name = "masterDataSource")
	  private DataSource masterDataSource;
	  
	  /*  从库数据源 */
	  @Resource(name = "slaveDataSources")
	  private CopyOnWriteArrayList<DataSource> slaveDataSources;
	  
	 
	  /**
	   * 获取源的时候通过threadlocal中不同的标识给出不同的sqlSession
	   * @return
	   * @throws Exception
	   */
	  @Bean
	  @ConditionalOnMissingBean  
	  public SqlSessionFactory sqlSessionFactory() throws Exception {
	      return super.sqlSessionFactory(roundRobinDataSouceProxy());
	  }
	 
	  /**
	   * 获取不同数据源
	   * @return
	   */
	  @SuppressWarnings("unchecked")
	  public AbstractRoutingDataSource roundRobinDataSouceProxy(){
		   int slaveDataSourceSize =  Integer.parseInt(slaveSize);
		   ReadWriteSplitRoutingDataSource proxy = new ReadWriteSplitRoutingDataSource(slaveDataSourceSize);
		   Map<Object,Object> targetDataResources = new ClassLoaderRepository.SoftHashMap();
		   //一个主库(写库)
		   targetDataResources.put(DataSourceType.write.getType(),masterDataSource);
		   //多个读库(从库)
		   for (int i = 0; i < slaveDataSourceSize; i++) {
			   targetDataResources.put(i,slaveDataSources.get(i));
		   }
		   //默认主库数据源
		   proxy.setDefaultTargetDataSource(masterDataSource);
		   proxy.setTargetDataSources(targetDataResources);
		   return proxy;
	  }

	
}
