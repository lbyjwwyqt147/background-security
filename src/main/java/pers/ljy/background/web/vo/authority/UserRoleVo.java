package pers.ljy.background.web.vo.authority;

import java.util.List;

import pers.ljy.background.model.SysUserRoleEntity;
import pers.ljy.background.web.vo.BasicVo;

/***
 * 用户权限vo
 * @author ljy
 *
 */
public class UserRoleVo extends BasicVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 /**
      * 用户ID
      */
    private Integer userId;
    
    /**
     * 用户角色
     */
    private List<SysUserRoleEntity> userRoleList;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<SysUserRoleEntity> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<SysUserRoleEntity> userRoleList) {
		this.userRoleList = userRoleList;
	}
    
    

}
