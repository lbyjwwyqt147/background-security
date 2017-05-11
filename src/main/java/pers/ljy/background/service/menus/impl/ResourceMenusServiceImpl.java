package pers.ljy.background.service.menus.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.ljy.background.service.menus.ResourceMenusService;

@Transactional
@Service
public class ResourceMenusServiceImpl implements ResourceMenusService {

	@Override
	public void save() {
		String aString = "11111";
		int i = Integer.valueOf(aString);

	}

}
