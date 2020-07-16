package wrappers;

import java.util.HashMap;

import io.restassured.response.Response;

public interface Wrappers {

	public Response sendRequest(String MethodName, String Endpoint, HashMap<String, Object> formparams,
			HashMap<String, Object> Headers, HashMap<String, Object> queryparams, int ExpectedResponseCode);

	public String sendCustomRequest(String MethodName, String Endpoint, HashMap<String, Object> formparams,
			HashMap<String, Object> Headers, HashMap<String, Object> queryparams, String expectedjsonpath,
			int ExpectedResponseCode);

	public HashMap<String, Object> responseAsMap(Response response);

	public HashMap<String, Object> responseAsMap(String MethodName, String Endpoint, HashMap<String, Object> formparams,
			HashMap<String, Object> Headers, HashMap<String, Object> queryparams, int ExpectedResponseCode);

}
