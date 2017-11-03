package person.txm.eurekaClient.restcontroller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {

	@Autowired
    private DiscoveryClient client;
	
	@RequestMapping("/hello")
	public Object hello(@RequestBody Map<String, Object> params){
		ServiceInstance instance = client.getLocalServiceInstance();
		System.out.println("hello:"+instance.getPort());
		int sleepTime = new Random().nextInt(3000);
		System.out.println(sleepTime);
		
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("res1", "v1");
		result.put("res2", "v2");
		return result;
	}
	
	@RequestMapping(value = "/testGet", method = RequestMethod.GET)
	public String testGet(@RequestParam("name") String name,@RequestParam("mobile") String mobile){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("name", name);
		result.put("mobile", mobile);
		return name + "..." + mobile;
	}
}
