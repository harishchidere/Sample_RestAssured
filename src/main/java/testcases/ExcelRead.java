package testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
	
	
	public static List<String> getData(String excelPath){
		
		List<String> list = new ArrayList<String>();
		
		try {
			FileInputStream fis = new FileInputStream(excelPath);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			
			int colNum=0;
			
			int cc = sheet.getRow(0).getLastCellNum();
			int rc = sheet.getLastRowNum();
			for(int i=0; i<cc; i++){
				String cellValue = sheet.getRow(0).getCell(i).getStringCellValue();
				if(cellValue.contains("http")){
					colNum=i;
					System.out.println(colNum);
					break;
				}
			}
			
			for(int j=1; j<=rc; j++){
				String link = sheet.getRow(j).getCell(colNum).getStringCellValue();
				list.add(link);
			}
			
			System.out.println(list);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
		
	}
	

}
