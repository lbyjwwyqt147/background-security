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
	public enum DbType{
	    MASTER,SLAVE
	  }
	 
	  private static final ThreadLocal<DbType> contextHolder = new ThreadLocal<>();
	 
	  public static void setDbType(DbType dbType){
	    if(dbType==null)throw new NullPointerException();
	    contextHolder.set(dbType);
	  }
	 
	  public static DbType getDbType(){
	    return contextHolder.get()==null?DbType.MASTER:contextHolder.get();
	  }
	 
	  public static void clearDbType(){
	    contextHolder.remove();
	  }


}
