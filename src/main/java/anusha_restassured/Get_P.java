package anusha_restassured;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Get_P {
	
	
	@Test
	public void get_p() {
		
		RestAssured.baseURI = "https://reqres.in/";
		
		RequestSpecification req = RestAssured.given().request();
		
		req.auth().none();
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("content-type", "application/json");
		
		req.headers(headers);
		
		req.queryParameter("page", "2");
		
		Response response = req.get("/api/users/");
		
		response.prettyPrint();
		
		System.out.println(response.asString());
		
		System.out.println(response.getStatusLine());
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
	
		System.out.println(response.getBody().jsonPath().get("data.email[0]"));
		
	}
		
}
