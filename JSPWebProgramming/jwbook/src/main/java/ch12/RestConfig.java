package ch12;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")//REST API서비스 진입점
public class RestConfig extends Application {
	
	public Map<String,Object> getProperties(){
		
		Map<String,Object> properties = new HashMap<String,Object>();
		
		//ch12 패키지의 클래스에서 REST API 서비스를 찾는다는 설정
		properties.put("jersey.config.server.provider.packages", "ch12");
		
		return properties;
	}
	
}
