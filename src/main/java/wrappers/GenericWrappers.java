package wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;

import com.github.wnameless.json.flattener.JsonFlattener;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import report.Report;

public class GenericWrappers extends Report implements Wrappers {

	Response resp;
	public static Properties prop;
	public static Logger log = LogManager.getLogger(GenericWrappers.class);

	public Response sendRequest(String MethodName, String Endpoint, HashMap<String, Object> formparams,
			HashMap<String, Object> Headers, HashMap<String, Object> queryparams, int ExpectedResponseCode) {
		RestAssured.baseURI = prop.getProperty("BaseURL");
		log.info("baseURI=" + prop.getProperty("BaseURL"));
		RequestSpecification reqSpc = RestAssured.given().request().urlEncodingEnabled(false);
		logRequestInfo(MethodName,prop.getProperty("BaseURL"), Endpoint, queryparams, Headers);
		log.info("Method=" + MethodName + "\n" + "EndPoint=" + Endpoint + "\n" + "Queryparams=" + queryparams + "\n"
				+ "Headers=" + Headers);
		if (MethodName.equals("POST")) {
			// JSONObject formobj = new JSONObject(formparams);
			reqSpc.formParams(formparams);
			reqSpc.headers(Headers);
			resp = reqSpc.post(Endpoint);
			if ((resp.getStatusCode() == ExpectedResponseCode)) {
				logResponse("pass", resp);
			} else {
				logResponse("Fail", resp);
			}

		} else if (MethodName.equals("GET")) {
			if (queryparams != null) {
				Set<Entry<String, Object>> entry = queryparams.entrySet();
				Object key = null;
				Object value = null;
				for (Entry<String, Object> a : entry) {
					key = a.getKey();
					value = a.getValue();
					reqSpc.queryParam(String.valueOf(key), String.valueOf(value));
				}
			}
			resp = reqSpc.get(Endpoint);
			if ((resp.getStatusCode() == ExpectedResponseCode)) {
				logResponse("pass", resp);
			} else {
				logResponse("Fail", resp);
			}
		} else if (MethodName.equals("PUT")) {
			reqSpc.formParams(formparams);
			reqSpc.headers(Headers);
			resp = reqSpc.put(Endpoint);
			if ((resp.getStatusCode() == ExpectedResponseCode)) {
				logResponse("pass", resp);
			} else {
				logResponse("Fail", resp);
			}

		} else if (MethodName.equals("DELETE")) {
			resp = reqSpc.put(Endpoint);
			if ((resp.getStatusCode() == ExpectedResponseCode)) {
				logResponse("pass", resp);
			} else {
				logResponse("Fail", resp);
			}

		}
		resp.getBody().prettyPrint();
		return resp;
	}

	public String sendCustomRequest(String MethodName, String Endpoint, HashMap<String, Object> formparams,
			HashMap<String, Object> Headers, HashMap<String, Object> queryparams, String expectedjsonpath,
			int ExpectedResponseCode) {

		Response resp = sendRequest(MethodName, Endpoint, formparams, Headers, queryparams, ExpectedResponseCode);
		String value = resp.getBody().jsonPath().get(expectedjsonpath).toString();
		return value;
	}

	public HashMap<String, Object> responseAsMap(Response response) {
		JSONObject jsonobj = null;
		try {
			jsonobj = new JSONObject(response.asString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<String, Object> map = (HashMap<String, Object>) JsonFlattener.flattenAsMap(jsonobj.toString());

		return map;
	}

	public HashMap<String, Object> responseAsMap(String MethodName, String Endpoint, HashMap<String, Object> formparams,
			HashMap<String, Object> Headers, HashMap<String, Object> queryparams, int ExpectedResponseCode) {
		Response resp = sendRequest(MethodName, Endpoint, formparams, Headers, queryparams, ExpectedResponseCode);
		HashMap<String, Object> map = responseAsMap(resp);
		return map;
	}

	public HashMap<String, Object> generateHeaderMap(HashMap<String, Object> map) {

		Set<Entry<String, Object>> entryset = map.entrySet();
		HashMap<String, Object> headermap = new HashMap<String, Object>();
		for (Entry<String, Object> entry : entryset) {
			String key = entry.getKey();
			if (key.contains("Header#")) {
				String newkey = key.substring(key.indexOf('#') + 1);
				// System.out.println(newkey);
				headermap.put(newkey, entry.getValue());

			}
		}
		System.out.println(headermap);

		return headermap;

	}

	public HashMap<String, Object> generateBodyMap(HashMap<String, Object> map) {
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();

		Set<Entry<String, Object>> entry = map.entrySet();
		for (Entry<String, Object> a : entry) {
			String key = a.getKey();
			if (key.contains("Body#")) {
				String insertKey = key.substring(key.indexOf("#") + 1);
				bodyMap.put(insertKey, a.getValue());
			}
		}

		return bodyMap;

	}

	public HashMap<String, Object> generateQueryMap(HashMap<String, Object> map) {
		HashMap<String, Object> queryMap = new HashMap<String, Object>();

		Set<Entry<String, Object>> entry = map.entrySet();
		for (Entry<String, Object> a : entry) {
			String key = a.getKey();
			if (key.contains("Query#")) {
				String insertKey = key.substring(key.indexOf("#") + 1);
				queryMap.put(insertKey, a.getValue());
			}
		}

		return queryMap;

	}

}
