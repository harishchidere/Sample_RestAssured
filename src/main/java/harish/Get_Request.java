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

public class Get_Request {

	@Test
	public void sample() throws JSONException{
		
		RestAssured.baseURI="https://reqres.in/";
		RequestSpecification request = RestAssured.given().request().urlEncodingEnabled(false);
		
		Response resp = request.get("api/users");
		
		System.err.println("Response");
		System.out.println(resp.asString());
		System.err.println("Response Code");
		System.out.println(resp.getStatusCode());
		System.err.println("Response Status Line");
		System.out.println(resp.getStatusLine());
		System.err.println("Response Time");
		System.out.println(resp.getTime());
		System.err.println("Response Customized");
		String data1 = resp.getBody().jsonPath().get("data[1].id").toString();
		System.out.println(data1);
		System.err.println("Response In Json");
		JSONObject json = new JSONObject(resp.asString());
		System.out.println(resp.prettyPrint());
			
		String respobj = json.toString();
		System.err.println("Response In Map");
		Map<String, Object> map = JsonFlattener.flattenAsMap(respobj);
		
		System.out.println(map);
		
	}
	
}
