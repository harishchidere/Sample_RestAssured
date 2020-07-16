package testcases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BrokenLinks {
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		
		List<String> list = ExcelRead.getData("./TestData/Links.xlsx");
		
		for (String string : list) {
			HttpURLConnection huc = (HttpURLConnection) new URL(string).openConnection();
			
			huc.setRequestMethod("HEAD");
			
			huc.connect();
			int respcode = huc.getResponseCode();
			
			if(respcode==200){
				System.out.println("Link is active");
				System.out.println("Response Code is - "+respcode);
			}	
		}
		
		
		
		
	}

}
