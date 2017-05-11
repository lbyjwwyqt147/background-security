package pers.ljy.background.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/***
 * 文件名称: ReadOnlyConnectionInterceptor.java
 * 文件描述: AOP 拦截数据源  
 * 公 司: 
 * 内容摘要: 
 * 其他说明:  通过Spring AOP实现拦截注解并注入不同的标识到threadlocal中
 * 完成日期:2017年04月24日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Aspect
@Component
public class ReadOnlyConnectionInterceptor implements Ordered {

	public static final Logger logger = LoggerFactory.getLogger(ReadOnlyConnectionInterceptor.class);
	 
	@Around("@annotation(readOnlyConnection)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint,ReadOnlyConnection readOnlyConnection) throws Throwable {
	    try {
	      logger.info(".当前方法走从库...");
	      DbContextHolder.setDbType(DbContextHolder.DbType.SLAVE);
	      Object result = proceedingJoinPoint.proceed();
	      return result;
	    }finally {
	      DbContextHolder.clearDbType();
	      logger.info("restore database connection");
	    }
	}
	 
	 

	
	@Override
	public int getOrder() {
		return 0;
	}

}
