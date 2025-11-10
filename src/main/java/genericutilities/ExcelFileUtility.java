package genericutilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
public String readDataFromExcel(String sheetname,int rowNum,int cellNum) throws EncryptedDocumentException, IOException
{

FileInputStream fis = new FileInputStream("path of excel saved on local");
Workbook wb= WorkbookFactory.create(fis);

String data=wb.getSheet(sheetname).getRow(rowNum).getCell(cellNum).getStringCellValue();

wb.close();
return data;

}
public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
	


FileInputStream fis = new FileInputStream("path of excel saved on local");
Workbook wb= WorkbookFactory.create(fis);
int rowCount =wb.getSheet(sheetName).getLastRowNum();
return rowCount;

// not audible mam

}

}

