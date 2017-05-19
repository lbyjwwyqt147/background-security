package pers.ljy.background.service.authority.impl;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.ljy.background.mapper.SysUserRoleDao;
import pers.ljy.background.model.SysUserRoleEntity;
import pers.ljy.background.service.authority.SysUserRoleService;
import pers.ljy.background.share.dao.BaseDao;
import pers.ljy.background.share.service.impl.BaseServiceImpl;

/***
 * 文件名称: SysUserRoleServiceImpl.java
 * 文件描述: 用户角色service接口实现
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2017年05月09日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleEntity, Integer> implements SysUserRoleService {

	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Override
	public BaseDao<SysUserRoleEntity, Integer> getDao() {
		return sysUserRoleDao;
	}
	
	@Override
	public CopyOnWriteArrayList<SysUserRoleEntity> selectUserRoleByUserId(Integer userId) {
		return this.sysUserRoleDao.selectUserRoleByUserId(userId);
	}

	

}
