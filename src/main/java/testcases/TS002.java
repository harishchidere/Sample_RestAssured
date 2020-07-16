package testcases;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.testng.annotations.Test;

import dataproviders.DataProviderTest;
import wrappers.GenericWrappers;

public class TS002 extends GenericWrappers {
	@Test(dataProvider="APIData",dataProviderClass=DataProviderTest.class)
	public void getData(HashMap<String,Object> map){
		HashMap<String,Object> headmap = generateHeaderMap(map);
		
		
	}

}
