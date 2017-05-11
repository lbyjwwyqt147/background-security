package pers.ljy.background.web.controller.menus;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import pers.ljy.background.service.menus.ResourceMenusService;
import pers.ljy.background.share.logs.SystemControllerLog;
import pers.ljy.background.share.result.ApiResultView;
import pers.ljy.background.web.controller.BasicController;

@RestController
public class Menus extends BasicController{

	@Autowired
	private ResourceMenusService menusService;
	
	@GetMapping("/m")
	@SystemControllerLog(description = "删除用户")   
	public ApiResultView test(int i){
		menusService.save();
		return this.buildDefaultDatePacket();
	}
}
