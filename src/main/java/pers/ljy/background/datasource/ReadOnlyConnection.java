package pers.ljy.background.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * 文件名称: ReadOnlyConnection.java
 * 文件描述: 自定义选择数据源注解   
 * 公 司: 
 * 内容摘要: 
 * 其他说明:  通过注解标识出当前的service方法使用哪个库
 * 完成日期:2017年04月24日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public  @interface ReadOnlyConnection {

}
