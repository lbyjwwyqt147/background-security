package pers.ljy.background.web.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.ljy.background.share.exception.BusinessException;
import pers.ljy.background.share.result.ApiResultView;
import pers.ljy.background.share.result.BaseApiResultView;

/***
 * 文件名称: BasicController.java
 * 文件描述: 
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2017年05月02日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@RequestMapping("/security/api")
public abstract class BasicController {

    private static final Logger LOG = LoggerFactory.getLogger(BasicController.class);
	
    /**
     * @InitBinder 标签在运行期注册一个属性编辑器(此处注册日期编辑器)
     * @param binder
     */
	@InitBinder
	public void initBinder(WebDataBinder binder){
	    binder.registerCustomEditor(Date.class, new DateEditor());
	}
	/**
	 * 日期编辑器
	 * @author ljy
	 *
	 */
	public class DateEditor extends PropertyEditorSupport {
	    @Override
	    public void setAsText(String text) throws IllegalArgumentException {
            if (StringUtils.isEmpty(text)){
				setValue(null);
				return;
			}

	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date date = null;
	        try {
	            date = format.parse(text);
	        } catch (ParseException e) {
	            format = new SimpleDateFormat("yyyy-MM-dd");
	            try {
	                date = format.parse(text);
	            } catch (ParseException pe) {
	            	LOG.error(pe.getMessage(), pe);
	            }
	        }
	        setValue(date);
	    }
	}
	
	/**
	 * 创建异常处理(统一处理异常)
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	public ApiResultView error(Exception ex){
		LOG.error(ex.getMessage(), ex);
		return new ApiResultView(BaseApiResultView.FAIL);
	}
	
	@ExceptionHandler
	public ApiResultView businessExceptionHandler(BusinessException bex){
		LOG.error(bex.getMessage(), bex);
		if (null != bex.getErrorCode() && null != buildErrDataPacket(bex)) {
			return buildErrDataPacket(bex);
		}
		return new ApiResultView(BaseApiResultView.FAIL.getStatus(), bex.getMessage());
	}
	
	protected ApiResultView buildErrDataPacket(BusinessException bex) {
		return null;
	}
	
	/**
	 * 默认返回 成功 (带data数据)
	 * 
	 * @param data
	 * @return
	 */
	protected ApiResultView buildDataPacket(Object data){
		return new ApiResultView(BaseApiResultView.SUCCESS, data);
	}
	
	/**
	 * 默认返回 成功 (不带data数据)
	 * 
	 * @return
	 */
	protected ApiResultView buildDefaultDatePacket(){
		return new ApiResultView(BaseApiResultView.SUCCESS);
	}
}
