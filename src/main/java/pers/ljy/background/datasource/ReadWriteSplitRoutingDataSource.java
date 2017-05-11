package pers.ljy.background.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/***
 * 文件名称: ReadWriteSplitRoutingDataSource.java
 * 文件描述: 根据标识获取不同源
 * 公 司: 
 * 内容摘要: 
 * 其他说明:  通过扩展AbstractRoutingDataSource来获取不同的源。它是Spring提供的一个可以根据用户发起的不同请求去转换不同的数据源，比如根据用户的不同地区语言选择不同的数据库。通过查看源码可以发现，它是通过determineCurrentLookupKey（）返回的不同key到sqlSessionFactory中获取不同源
 * 完成日期:2017年04月24日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
public class ReadWriteSplitRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		 return DbContextHolder.getDbType();
	}

}
