package pers.ljy.background.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.ljy.background.mapper.SysUsersAccountDao;
import pers.ljy.background.model.SysUsersAccountEntity;
import pers.ljy.background.service.user.SysUsersAccountService;
import pers.ljy.background.share.dao.BaseDao;
import pers.ljy.background.share.service.impl.BaseServiceImpl;

@Service
public class SysUsersAccountServiceImpl extends BaseServiceImpl<SysUsersAccountEntity, Integer> implements SysUsersAccountService {

	@Autowired
	private SysUsersAccountDao sysUsersAccountDao;

	@Override
	public BaseDao<SysUsersAccountEntity, Integer> getDao() {
		return sysUsersAccountDao;
	}
	
	
	@Override
	public SysUsersAccountEntity selectUsersAccount(String userName) {
		return this.sysUsersAccountDao.selectUsersAccount(userName);
	}

	

	
}
