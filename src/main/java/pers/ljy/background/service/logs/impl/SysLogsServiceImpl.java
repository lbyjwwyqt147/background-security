package pers.ljy.background.service.logs.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.ljy.background.mapper.SysLogsDao;
import pers.ljy.background.model.SysLogsEntity;
import pers.ljy.background.service.logs.SysLogsService;
import pers.ljy.background.share.dao.BaseDao;
import pers.ljy.background.share.service.impl.BaseServiceImpl;

//@Service
public class SysLogsServiceImpl extends BaseServiceImpl<SysLogsEntity, Integer> implements SysLogsService {

	//@Autowired
	//private SysLogsDao logsDao;

	@Override
	public BaseDao<SysLogsEntity, Integer> getDao() {
		return null;
	}

	
}
