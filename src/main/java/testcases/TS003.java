package testcases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import dataproviders.DataProviderTest;
import wrappers.GenericWrappers;

public class TS003 extends GenericWrappers {
	@Test(dataProvider = "APIData", dataProviderClass = DataProviderTest.class)
	public void getdatafromExcel(HashMap<String, Object> map) {
		HashMap<String, Object> queryParam = generateQueryMap(map);
		String id = sendCustomRequest("GET", "api/users", null, null, queryParam, "data[1].id",
				Integer.parseInt(map.get("GetExpectedcode").toString()));
		String endpoint = "api/users/" + id;
		sendRequest("GET", endpoint, null, null, null, Integer.parseInt(map.get("GetExpectedcode").toString()));
		HashMap<String, Object> formParam = generateBodyMap(map);
		HashMap<String, Object> headers = generateHeaderMap(map);
		String actualName = sendCustomRequest("PUT", "api/users", formParam, headers, null, "name",
				Integer.parseInt(map.get("GetExpectedcode").toString()));
		Assert.assertEquals(actualName, formParam.get("name"));
	}

}
