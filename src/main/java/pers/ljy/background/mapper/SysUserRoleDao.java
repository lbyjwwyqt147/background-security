package pers.ljy.background.mapper;

import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.ibatis.annotations.Mapper;

import pers.ljy.background.model.SysUserRoleEntity;
import pers.ljy.background.share.dao.BaseDao;

/***
 * 文件名称: SysUserRoleDao.java
 * 文件描述: 用户角色dao接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2017年05月09日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Mapper
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity, Integer> {
   
	/**
	 * 根据用户ID 获取用户所属的角色
	 * @param userId
	 * @return
	 */
	CopyOnWriteArrayList<SysUserRoleEntity> selectUserRoleByUserId(Integer userId);
}