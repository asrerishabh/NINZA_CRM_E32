package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class readDAtafromExcelFIle {
public static void main(String[] args) throws EncryptedDocumentException, IOException {   // throws EncriptedDocumentException.IOException
   FileInputStream fis = new FileInputStream("path of excel saved on local");
   Workbook workbook= WorkbookFactory.create(fis);

   Sheet sheet = workbook.getSheet("campaign");
   Row row=sheet.getRow(1);
   Cell cell = row.getCell(2);
   String cellvalue = cell.getStringCellValue();
   System.out.println("cellvalue "+cellvalue);


//for targetsize
   
 String targetsize = workbook.getSheet("campaign").getRow(1).getCell(3).getStringCellValue();
   System.out.println( "targetsize"+targetsize);

	
}
}
