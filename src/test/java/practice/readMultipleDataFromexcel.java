package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import genericutilities.ExcelFileUtility;

public class readMultipleDataFromexcel {
public static void main(String[] args) throws EncryptedDocumentException, IOException {
	
/*	FileInputStream fis= new FileInputStream("excel path");
	Workbook wb= WorkbookFactory.create(fis);
	Sheet sh=wb.getSheet("practice");
	
	int rowcount= sh.getLastRowNum();
	System.out.println(rowcount);*/
	
	
	ExcelFileUtility elib = new ExcelFileUtility();
	int rowCount =elib.getRowCount("practice");
	System.out.println(rowCount);
	
	
	
	
	for(int row=1;row<=rowCount;row++)
	{
		//String data=sh.getRow(row).getCell(0).getStringCellValue();
		String data=elib.readDataFromExcel("practice", row, 0);
		System.out.println(data);
	}
	
	
	
}
	
}
