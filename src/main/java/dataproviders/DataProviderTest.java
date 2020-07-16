package dataproviders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProviderTest {

	@DataProvider(name = "APIData")
	public static Object[][] getData() throws IOException {

		FileInputStream fis = new FileInputStream("./TestData/TestData.xlsx");
		XSSFWorkbook Wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = Wb.getSheet("TS003");
		int rowCount = sheet.getLastRowNum();
		int columCount = sheet.getRow(1).getLastCellNum();
		// System.out.println(rowCount+" "+columCount);
		Object[][] testData = new Object[rowCount][1];
		for (int i = 1; i <= rowCount; i++) {
			XSSFRow row = sheet.getRow(i);
			HashMap<String, Object> map = new HashMap<String, Object>();
			for (int j = 0; j < columCount; j++) {
				String key = sheet.getRow(0).getCell(j).getStringCellValue();
				CellType type = row.getCell(j).getCellType();
				if (type == type.STRING) {
					Object cellvalue = row.getCell(j).getStringCellValue();
					map.put(key, cellvalue);
				} else if (type == type.NUMERIC) {
					int cellvalue = (int) row.getCell(j).getNumericCellValue();
					map.put(key, cellvalue);
				}

			}
			testData[i - 1][0] = map;
		}

		return testData;

	}

}
