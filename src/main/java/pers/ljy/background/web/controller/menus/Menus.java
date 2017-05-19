package pers.ljy.background.web.controller.menus;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import pers.ljy.background.service.menus.ResourceMenusService;
import pers.ljy.background.share.logs.SystemControllerLog;
import pers.ljy.background.share.redis.RedisService;
import pers.ljy.background.share.result.ApiResultView;
import pers.ljy.background.web.controller.BasicController;

@RestController
public class Menus extends BasicController{

	@Autowired
	private ResourceMenusService menusService;
	@Autowired
	private RedisService redisService;
	
	@GetMapping("/m")
	@SystemControllerLog(description = "删除用户")   
	public ApiResultView test(int i){
		
		redisService.hmSet("push-10000", "1-1", "a");
		redisService.hmSet("push-10000", "1-2", "b");
		redisService.hmSet("push-10000", "1-3", "c");
		
		System.out.println(redisService.hmGet("push-10000", "1-1"));

		
		
		menusService.save();
		return this.buildDefaultDatePacket();
	}
}
