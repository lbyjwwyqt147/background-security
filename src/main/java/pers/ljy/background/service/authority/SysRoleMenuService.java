package pers.ljy.background.service.authority;


import java.util.concurrent.CopyOnWriteArrayList;

import pers.ljy.background.model.SysRoleMenuEntity;
import pers.ljy.background.share.service.BaseService;

/***
 * 文件名称: SysRoleMenuService.java
 * 文件描述: 角色菜单资源service接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2017年05月09日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenuEntity, Integer>  {
 
	/**
	 * 根据角色ID获取角色菜单资源
	 * @param roleId 角色ID
	 * @return
	 */
	CopyOnWriteArrayList<SysRoleMenuEntity> selectRoleMenuByRoleId(Integer roleId);
}
