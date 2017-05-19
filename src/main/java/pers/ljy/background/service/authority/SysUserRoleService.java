package pers.ljy.background.service.authority;

import java.util.concurrent.CopyOnWriteArrayList;

import pers.ljy.background.model.SysUserRoleEntity;
import pers.ljy.background.share.service.BaseService;

/***
 * 文件名称: SysUserRoleService.java
 * 文件描述: 用户角色service接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2017年05月09日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
public interface SysUserRoleService extends BaseService<SysUserRoleEntity, Integer> {

	/**
	 * 根据用户ID 获取用户所属的角色
	 * @param userId
	 * @return
	 */
	CopyOnWriteArrayList<SysUserRoleEntity> selectUserRoleByUserId(Integer userId);
}
