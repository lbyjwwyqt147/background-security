package pers.ljy.background.mapper;


import org.apache.ibatis.annotations.Mapper;

import pers.ljy.background.model.SysUsersAccountEntity;
import pers.ljy.background.share.dao.BaseDao;

/***
 * 文件名称: SysUsersAccountDao.java
 * 文件描述: 账户dao接口
 * 公 司: 
 * 内容摘要: 
 * 其他说明:
 * 完成日期:2017年05月09日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Mapper
public interface SysUsersAccountDao extends BaseDao<SysUsersAccountEntity, Integer>{
    
	/**
	 * 根据用户名查询账户信息
	 * @param userName  用户名
	 * @return
	 */
	SysUsersAccountEntity selectUsersAccount(String userName);
}