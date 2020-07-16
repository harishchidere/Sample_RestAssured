package harish;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Put_Request {
	
	@Test
	public void sample(){
		
		RestAssured.baseURI = "https://reqres.in/";
		RequestSpecification reqspec = RestAssured.given().request().urlEncodingEnabled(false);
		
		Map<String, Object> query = new HashMap<String, Object>();
		
		Map<String, Object> form = new HashMap<String, Object>();
		
		form.put("name", "TestName");
		form.put("job", "newJob");
		
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("Content-Type", "application/json");
		
		reqspec.formParameters(form);
		reqspec.headers(headers);
		
		Response resp = reqspec.get("api/users");
		
		System.out.println(resp.asString());
		System.out.println(resp.getStatusCode());
		System.out.println(resp.getHeaders().toString());
		System.out.println(resp.getContentType());
		
		
		
		
		
		
		
		
		
	}
	

}
