package pers.ljy.background.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import pers.ljy.background.datasource.DataSourceAop;
import pers.ljy.background.model.SysUsersAccountEntity;
import pers.ljy.background.service.authority.SysRoleMenuService;
import pers.ljy.background.service.user.SysUsersAccountService;

/***
 * 文件名称: CustomUserDetailsService.java  用户请求登录会进入此类的loadUserByUsername方法 验证用户
 * 文件描述: 权限验证类
 * User userdetail该类实现 UserDetails 接口，该类在验证成功后会被保存在当前回话的principal对象中
 * 
 * 获得对象的方式：
 * WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 * 
 * 或在JSP中：
 * <sec:authentication property="principal.username"/>
 * 
 * 如果需要包括用户的其他属性，可以实现 UserDetails 接口中增加相应属性即可
 * 
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2017年05月15日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Component
public class MyUserDetailService implements UserDetailsService {
	public static final Logger logger = LoggerFactory.getLogger(DataSourceAop.class);
	@Autowired
	private SysUsersAccountService usersAccountService;
	@Autowired
    private SysRoleMenuService roleMenuService;	
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		logger.info(" userName " + userName);
		//获取账户信息
		SysUsersAccountEntity usersAccount = this.usersAccountService.selectUsersAccount(userName);
		if(usersAccount == null){
			throw new UsernameNotFoundException("UserName " + userName + " not found"); 
		}
		return null;
	}
	
	

}
