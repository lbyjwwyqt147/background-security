package pers.ljy.background.datasource;

/***
 * 文件名称: DbContextHolder.java
 * 文件描述: 标记不同数据源 (将不同的数据源标识记录在ThreadLocal中)
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2017年04月24日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
public class DbContextHolder {
	  private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
	 
	  public static ThreadLocal<String> getLocal() {  
	      return contextHolder;  
	  }  
	  
      /** 
       * 读可能是多个库 
       */  
      public static void read() {  
    	 contextHolder.set(DataSourceType.read.getType());  
      }  
	  
	   /** 
	    * 写只有一个库 
	    */  
	  public static void write() {  
	     contextHolder.set(DataSourceType.write.getType());  
	  }  
	  
	  public static String getJdbcType() {  
	      return contextHolder.get();  
	  }  
	  
	  public static void clearDbType(){
	      contextHolder.remove();
	  }


}
