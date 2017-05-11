package pers.ljy.background.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/***
 * 文件名称: DataSourceConfiguration.java
 * 文件描述: 数据源设置
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2017年04月24日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Configuration
@EnableTransactionManagement  //开启注解事物
public class DataSourceConfiguration {

	  /* 数据源类型 */
	  @Value("${druid.type}")
	  private Class<? extends DataSource> dataSourceType;
	 
	  /**
	   * 设置主库数据源
	   * @return 返回主库数据源
	   */
	  @Bean(name = "masterDataSource")
	  @Primary
	  @ConfigurationProperties(prefix = "druid.master")
	  public DataSource masterDataSource(){
	    return DataSourceBuilder.create().type(dataSourceType).build();
	  }
	 
	  /**
	   * 设置从库数据源 
	   * @return 返回从库数据源
	   */
	  @Bean(name = "slaveDataSource")
	  @ConfigurationProperties(prefix = "druid.slave")
	  public DataSource slaveDataSource1(){
	    return DataSourceBuilder.create().type(dataSourceType).build();
	  }

}
