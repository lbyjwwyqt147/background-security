package pers.ljy.background.logs;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

import pers.ljy.background.model.SysLogsEntity;
import pers.ljy.background.service.logs.SysLogsService;
import pers.ljy.background.share.logs.SystemControllerLog;
import pers.ljy.background.share.utils.ControllerUtil;
import pers.ljy.background.share.utils.DateUtils;

/***
 * logs 日志AOP
 * @author ljy
 *
 */
@Aspect
@Component
@Order(5)  
public class WebLogAspect {
	    @Autowired
        private SysLogsService logsService; 
	    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
	    ThreadLocal<Long> startTime = new ThreadLocal<>();
	    //请求url地址 
        private String url;
        //请求IP 地址
        private String ip;
        //方法开始执行时间
        private String startDateTime;
        //请求用户
        private Integer userId;
        //请求类路径
        private String classPath;
        //请求方法
        private String askMethod;
        //请求方法全路径
        private String methodPath;
        //请求方式
        private String requestMethod;
        //请求参数 数组格式
        private String arrayParams;
        //请求参数 json格式
        private String jsonParams;
        //描述
        private String description;
        // 1001: 正常   1002：异常
        private String type;
        //异常代码
        private String errorCode;
        //异常信息
        private String errorMsg;
        //方法执行结束时间
        private Long endDateTime;
        //方法执行消耗总时间(秒为单位)
        private Long seconds;
        private String wasteTimeMsg;
        //方法返回值
        private String methodResultValue;
	  
	    /**
	     * 定义一个切入点.
	     * 解释下：
	     *
	     * ~ 第一个 * 代表任意修饰符及任意返回值.
	     * ~ 第二个 * 任意包名
	     * ~ 第三个 * 代表任意方法.
	     * ~ 第四个 * 定义在controller包或者子包
	     * ~ 第五个 * 任意方法
	     * ~ .. 匹配任意数量的参数.
	     */
	    @Pointcut("@annotation(pers.ljy.background.share.logs.SystemControllerLog)")
	    public void webLog(){}

	    /**
	     * 方法调用前触发
	     * @param joinPoint
	     * @throws Exception 
	     * @throws Throwable
	     */
	    @Before("webLog()")
	    public void doBefore(JoinPoint joinPoint) throws Exception{
	        startTime.set(System.currentTimeMillis());
	   	    // 接收到请求，记录请求内容
	        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	        HttpServletRequest request = attributes.getRequest();
	        // 记录下请求内容
	        this.setIp(request.getRemoteAddr());
	        this.setStartDateTime(DateUtils.format(new Date()));
	        this.setUrl(request.getRequestURL().toString());
	        this.setRequestMethod(request.getMethod());
	        this.setMethodPath(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
	        this.setArrayParams(Arrays.toString(joinPoint.getArgs()));
	        this.setJsonParams(JSONObject.toJSONString(ControllerUtil.getFormData(request)));
	        this.setDescription(getControllerMethodDescription(joinPoint));
	        this.setAskMethod(joinPoint.getSignature().getName());
	        this.setClassPath(joinPoint.getSignature().getDeclaringTypeName());
	        
	    }
	    
	   

	    /**
	     * 声明后置通知 
	     * @param ret
	     * @throws Throwable
	     */
	    @AfterReturning(returning = "ret", pointcut = "webLog()")
	    public void doAfterReturning(Object ret) throws Throwable {
	        logger.info(" ********************************** Controller 控制台日志 开始  **********************************");
	        logger.info(" 开始执行时间 ："+this.getStartDateTime());
	        logger.info(" 方法描述："+this.getDescription());
	        logger.info(" 请求用户 ："+this.getUserId());
	        logger.info(" 请求IP ："+this.getIp());
	        logger.info(" 请求url地址 ："+this.getUrl());
	        logger.info(" 请求方法 ："+this.getMethodPath());
	        logger.info(" 请求参数 数组格式 ："+this.getArrayParams());
	        logger.info(" 请求参数 json格式 ："+this.getJsonParams());
	        logger.info(" 方法执行完毕时间 ："+DateUtils.format(new Date()));
	        this.setEndDateTime(System.currentTimeMillis());
	        this.setType("1001");
	        // 处理完请求，返回内容
	        this.setMethodResultValue(JSONObject.toJSONString(ret));
	        logger.info(" 方法返回值 ："+this.getMethodResultValue());
	        this.setSeconds(DateUtils.getDistanceSeconds(this.getEndDateTime(), startTime.get()));
	        this.setWasteTimeMsg(DateUtils.getDistanceTime(this.getEndDateTime(), startTime.get()));
	       	logger.info(" 执行总消耗时间  : " + this.getWasteTimeMsg());
	        //日志入库
		    this.saveLogs();
	       	logger.info(" ********************************** Controller 控制台日志 结束  ********************************** ");

	    }

	    /**
	     * 异常通知
	     * @param joinPoint
	     * @param e
	     */
	    @AfterThrowing(pointcut = "webLog()", throwing = "e")    
	    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {  
	    	 this.setErrorCode(e.getClass().getName());
	    	 this.setType("1002");
	    	 logger.info(" **********************************  控制台异常日志 开始  **********************************");
	         logger.info(" 开始执行时间 ："+this.getStartDateTime());
	         logger.info(" 请求用户 ："+this.getUserId());
	         logger.info(" 请求IP ："+this.getIp());
	         logger.info(" 请求url地址 ："+this.getUrl());
	    	 logger.info(" 异常方法："+this.getMethodPath());
	         logger.info(" 方法描述："+this.getDescription());
	         logger.info(" 异常代码："+this.getErrorCode());
	         StackTraceElement[] trace = e.getStackTrace();
	         StringBuffer errorMessageBuffer = new StringBuffer(e.getMessage());
	         errorMessageBuffer.append("\r\n");
	         for (StackTraceElement s : trace) {
	             errorMessageBuffer.append("\t at ").append(s).append("\r\n");
	         }
	         logger.info(" 异常信息： "+errorMessageBuffer.toString());
	         this.setErrorMsg(errorMessageBuffer.toString());
	        
	         logger.info(" 请求方法 ："+this.getMethodPath());
	         logger.info(" 请求参数 数组格式 ："+this.getArrayParams());
	         logger.info(" 请求参数 json格式 ："+this.getJsonParams());
	         logger.info(" 方法执行完毕时间 ："+DateUtils.format(new Date()));
	         this.setEndDateTime(System.currentTimeMillis());
	         this.setSeconds(DateUtils.getDistanceSeconds(this.getEndDateTime(), startTime.get()));
		     this.setWasteTimeMsg(DateUtils.getDistanceTime(this.getEndDateTime(), startTime.get()));
		     logger.info(" 执行总消耗时间  : " + this.getWasteTimeMsg());
		     //日志入库
		     this.saveLogs();
	         logger.info(" **********************************  控制台异常日志 结束  ********************************** ");
	    }
	    
	    /**  
	     * 获取注解中对方法的描述信息 用于Controller层注解  
	     *  
	     * @param joinPoint 切点  
	     * @return 方法描述  
	     * @throws Exception  
	     */    
	     public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {    
	        String targetName = joinPoint.getTarget().getClass().getName();    
	        String methodName = joinPoint.getSignature().getName();    
	        Object[] arguments = joinPoint.getArgs();    
	        Class<?> targetClass = Class.forName(targetName);    
	        Method[] methods = targetClass.getMethods();    
	        String description = "";    
	         for (Method method : methods) {    
	             if (method.getName().equals(methodName)) {    
	                Class<?>[] clazzs = method.getParameterTypes();    
	                 if (clazzs.length == arguments.length) {    
	                    description = method.getAnnotation(SystemControllerLog. class).description();    
	                     break;    
	                }    
	            }    
	        }    
	         return description;    
	    } 
	    
	    /**
	     * 保存日志信息 
	     */
	    private void saveLogs(){
	    	SysLogsEntity logsEntity =  new SysLogsEntity();
	    	logsEntity.setAskMethod(this.getAskMethod());
	    	logsEntity.setClassPath(this.getClassPath());
	    	logsEntity.setCreateDate(new Date());
	    	logsEntity.setDescription(this.getDescription());
	    	logsEntity.setErrorCode(this.getErrorCode());
	    	logsEntity.setErrorMsg(this.getErrorMsg());
	    	logsEntity.setIp(this.getIp());
	    	logsEntity.setLogsType(this.getType());
	    	logsEntity.setMethodPath(this.getMethodPath());
	    	logsEntity.setMethodResultValue(this.getMethodResultValue());
	    	logsEntity.setParams(this.getJsonParams());
	    	logsEntity.setRequestMethod(this.getRequestMethod());
	    	logsEntity.setUrl(this.getUrl());
	    	logsEntity.setUserId(this.getUserId());
	    	logsEntity.setWasteTime(this.getSeconds());
	    	logsEntity.setWasteTimeMsg(this.getWasteTimeMsg());
	    	logsService.insert(logsEntity);
	    }
	     
		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public String getStartDateTime() {
			return startDateTime;
		}

		public void setStartDateTime(String startDateTime) {
			this.startDateTime = startDateTime;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getMethodPath() {
			return methodPath;
		}

		public void setMethodPath(String methodPath) {
			this.methodPath = methodPath;
		}

		public String getArrayParams() {
			return arrayParams;
		}

		public void setArrayParams(String arrayParams) {
			this.arrayParams = arrayParams;
		}

		public String getJsonParams() {
			return jsonParams;
		}

		public void setJsonParams(String jsonParams) {
			this.jsonParams = jsonParams;
		}

		public String getRequestMethod() {
			return requestMethod;
		}

		public void setRequestMethod(String requestMethod) {
			this.requestMethod = requestMethod;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}

		public String getErrorMsg() {
			return errorMsg;
		}

		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
		}

		public String getClassPath() {
			return classPath;
		}

		public void setClassPath(String classPath) {
			this.classPath = classPath;
		}

		public String getAskMethod() {
			return askMethod;
		}

		public void setAskMethod(String askMethod) {
			this.askMethod = askMethod;
		}

		public Long getEndDateTime() {
			return endDateTime;
		}

		public void setEndDateTime(Long endDateTime) {
			this.endDateTime = endDateTime;
		}

		public Long getSeconds() {
			return seconds;
		}

		public void setSeconds(Long seconds) {
			this.seconds = seconds;
		}

		public String getWasteTimeMsg() {
			return wasteTimeMsg;
		}

		public void setWasteTimeMsg(String wasteTimeMsg) {
			this.wasteTimeMsg = wasteTimeMsg;
		}

		public String getMethodResultValue() {
			return methodResultValue;
		}

		public void setMethodResultValue(String methodResultValue) {
			this.methodResultValue = methodResultValue;
		}
	    
		
		
	    
	    
}
