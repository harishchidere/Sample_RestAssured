package testcases;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssure {
	
	@Test
	public void sample(){
		
		RestAssured.baseURI="https://harry.com";
		
		RequestSpecification requestSpecification = RestAssured.given().request();
		
		Response errorString = requestSpecification.get();
		
		int codeString = errorString.getStatusCode();
		
		System.out.println(codeString);
		
	}

}
