package harish;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.github.wnameless.json.flattener.JsonFlattener;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Get_2 {
	
	@org.junit.Test
	public void sample() throws JSONException{
		
		RestAssured.baseURI = "https://reqres.in/";
		
		RequestSpecification req = RestAssured.given().request().urlEncodingEnabled(false);
		req.queryParam("page", 2);
		Response resp = req.get("api/users");
		System.out.println(resp.asString());
		
		System.out.println(resp.prettyPrint());
		
		JSONObject obj = new JSONObject(resp.asString());
		Map<String, Object> map = JsonFlattener.flattenAsMap(obj.toString());
		System.out.println(map);
		
		
		
		
		
		
	}
	
	

}
