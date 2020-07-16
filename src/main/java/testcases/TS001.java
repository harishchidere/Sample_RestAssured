package testcases;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import wrappers.GenericWrappers;
import wrappers.ProjectWrappers;

public class TS001 extends ProjectWrappers {

	@BeforeClass
	public void loadValues() {
		System.out.println("class executed");
		testcasename = "TC001";
		testDesc = "API";

	}

	@Test
	public JSONObject  testOne() throws JSONException {
		HashMap<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("page", "2");
		String id = sendCustomRequest("GET", "api/users", null, null, queryParam, "data[1].id", 200);
		String endpoint = "api/users/" + id;
		Response resp = sendRequest("GET", endpoint, null, null, null, 200);
		Headers head = resp.headers();
		HashMap<String, Object> formParam = new HashMap<String, Object>();
		formParam.put("name", "TestName");
		formParam.put("job", "newJob");
		HashMap<String, Object> headers = new HashMap<String, Object>();
		headers.put("Content-Type", "application/json");
		sendRequest("PUT", "api/users", formParam, headers, null, 400);
		//return null;
		return null;

	}

}
