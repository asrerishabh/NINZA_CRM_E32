package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class writeDataBackToExcel {
public static void main(String[] args) throws EncryptedDocumentException, IOException {
	
	FileInputStream fis= new FileInputStream("excel path");
	Workbook wb= WorkbookFactory.create(fis);
	Sheet sh=wb.getSheet("practice");
	 Row r = sh.getRow(1);
	 
	 //creting cell
	 
	 Cell c = r.createCell(1, CellType.STRING);
	 // entering cell value
	 c.setCellValue("iphone 15");
	 
	 //saving cell value
	 FileOutputStream fos= new FileOutputStream("excel path");
	 wb.write(fos);
	 
	wb.close();
}
}
