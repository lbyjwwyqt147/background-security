package pers.ljy.background.datasource;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * 扫描mybatis的接口
 * @author ljy
 *
 */
@Configuration
//因为这个对象的扫描，需要在MybatisConfiguration的后面注入，所以加上下面的注解
@AutoConfigureAfter(MybatisConfiguration.class)
public class MyBatisMapperScannerConfig {

	@Value("${mybatis.mapperDaoPackge}")
	private String basePackage;
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		// 获取之前注入的beanName为sqlSessionFactory的对象
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		// 指定xml配置文件的路径
		mapperScannerConfigurer.setBasePackage("pers.ljy.background.mapper");
		return mapperScannerConfigurer;
	}
}
