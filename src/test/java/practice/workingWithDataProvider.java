package practice;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericutilities.ExcelFileUtility;
import objectReposatiory.HomePage;
import objectReposatiory.Loginpage;

@Test
public class workingWithDataProvider {

	
	@Test(dataProvider="loginDetails")
	public void login(String username,String password)
	{
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Loginpage loginPage= new Loginpage(driver);
		loginPage.loginToApp("http://49.249.28.218:8098/",username,password);
		
		HomePage homePage= new HomePage(driver);
		homePage.logout();
		driver.quit();
	}
	
	@DataProvider
	public Object [][]loginDetails() throws EncryptedDocumentException, IOException
	{
		Object[][] objArr= new Object[5][2];
		
		ExcelFileUtility elib= new ExcelFileUtility();
		
		for(int i=1;i<=elib.getRowCount("DataProvider");i++)
		{
		
        objArr[i-1][0]=elib.readDataFromExcelFile("DataProvider", i, 0);
		objArr[i-1][1]=elib.readDataFromExcelFile("DataProvider", i, 1);
		}	
		
		return objArr;

		
		
		/*Object[][] objectArr= new Object[5][2];
		objectArr[0][0]="rgmyantra";
		objectArr[0][1]="rgmy@9999";
		objectArr[0][0]="rgmyantra";
		objectArr[0][1]="rgmy@9999";
		objectArr[0][0]="rgmyantra";
		objectArr[0][1]="rgmy@9999";
		objectArr[0][0]="rgmyantra";
		objectArr[0][1]="rgmy@9999";
		objectArr[0][0]="rgmyantra";
		objectArr[0][1]="rgmy@9999";    */

		
				
	}
	
}
