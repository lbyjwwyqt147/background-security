package pers.ljy.background.datasource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/***
 * 文件名称: DataSourceAop.java
 * 文件描述: AOP 拦截数据源  
 * 公 司: 
 * 内容摘要: 
 * 其他说明:  拦截mapper 中的方法来切换不同数据源
 * 完成日期:2017年04月24日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Aspect  
@Component 
public class DataSourceAop {
	public static final Logger logger = LoggerFactory.getLogger(DataSourceAop.class);
	@Before("execution(* pers.ljy.background.mapper..*.select*(..)) || execution(* pers.ljy.background.mapper..*.get*(..)) || execution(* pers.ljy.background.mapper..*.find*(..))")  
    public void setReadDataSourceType() {  
        DbContextHolder.read();  
        logger.info("dataSource切换到从库(读库)");  
    }  
  
    @Before("execution(* pers.ljy.background.mapper..*.insert*(..)) || execution(* pers.ljy.background.mapper..*.update*(..)) || execution(* pers.ljy.background.mapper..*.delete*(..))")  
    public void setWriteDataSourceType() {  
    	DbContextHolder.write();  
    	logger.info("dataSource切换到主库(写库)");  
    } 
}
