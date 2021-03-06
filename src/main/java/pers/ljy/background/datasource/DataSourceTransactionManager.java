package pers.ljy.background.datasource;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/***
 * 文件名称: DataSourceTransactionManager.java
 * 文件描述: 自定义事物
 * 公 司: 
 * 内容摘要:  MyBatis自动参与到spring事务管理中，无需额外配置，只要org.mybatis.spring.SqlSessionFactoryBean引用的数据源与DataSourceTransactionManager引用的数据源一致即可，否则事务管理会不起作用。 
 * 其他说明:
 * 完成日期:2017年04月24日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Configuration  
@EnableTransactionManagement  
public class DataSourceTransactionManager extends DataSourceTransactionManagerAutoConfiguration{

 
	/* 主库数据源 */
	@Resource(name = "masterDataSource")
	private DataSource masterDataSource;
	  
    @Bean(name = "transactionManager")  
    public org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManagers() {  
        return new org.springframework.jdbc.datasource.DataSourceTransactionManager(masterDataSource);  
    }  
}
