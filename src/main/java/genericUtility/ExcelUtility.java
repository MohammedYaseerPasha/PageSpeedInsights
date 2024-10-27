package genericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public static Object[][] getMultipleDataFromExcel(String sheetName) throws Throwable {
		FileInputStream fis = new FileInputStream("C:/Users/Mohammed.pasha/Downloads/TestScriptData.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sheet = book.getSheet(sheetName);
		DataFormatter df = new DataFormatter();
		int rowCount = sheet.getPhysicalNumberOfRows();
		int cellCount = sheet.getRow(0).getPhysicalNumberOfCells();
		Object [][] data = new Object [rowCount-1][cellCount];
		
		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < cellCount; j++) {
				data [i-1][j] = df.formatCellValue(sheet.getRow(i).getCell(j));
			}
			
		}
		return data;
	}
	

}
