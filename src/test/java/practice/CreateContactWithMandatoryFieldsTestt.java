package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericutilities.ExcelFileUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectReposatiory.Loginpage;

public class CreateContactWithMandatoryFieldsTestt {

			public static void main(String[] args) throws IOException {
			
			

				// Create campaign
				// Read common data from properties file
				// Read test script data from excel
				// Launch the browser
				// Login
				// Create Campaign
				// Click on contacts
				// click on +create contact
				// enter the manadatory fields
				// click on plus button
				// Switch the driver control to child
				// select campaignName from drop down
				// Enter Camapign name in search text field
				// click on select button
				// switch the driver control to parent
				// click on create contact
				// Verification
				// Logout
				// Close the browser
			
			/*	
				FileInputStream fis = new FileInputStream("C:\\Users\\QSP\\Documents\\http://CommondataE32.properties");
				Properties prop = new Properties();
				prop.load(fis);
				String BROWSER = prop.getProperty("Browser");
				String URL = prop.getProperty("URL");
				String USERNAME = prop.getProperty("Username");
				String PASSWORD = prop.getProperty("Password");
          */
				PropertyFileUtility plib=new PropertyFileUtility();
				String BROWSER =plib.readDataFromProperty("Browser");
				String URL =plib.readDataFromProperty("URL");
				String USERNAME =plib.readDataFromProperty("Username");
				String PASSWORD =plib.readDataFromProperty("password");
          
   
			/*	// Read test script data from excel
				FileInputStream fis1 = new FileInputStream("C:\\Users\\QSP\\Documents\\NINZA_CRM_E32.xlsx");
				Workbook wb = WorkbookFactory.create(fis1);
				String CAMPAIGN_NAME = wb.getSheet("Contacts").getRow(1).getCell(2).getStringCellValue();
				String TARGET_SIZE = wb.getSheet("Contacts").getRow(1).getCell(3).getStringCellValue();
				String ORGANIZATION = wb.getSheet("Contacts").getRow(1).getCell(4).getStringCellValue();
				String TITLE = wb.getSheet("Contacts").getRow(1).getCell(5).getStringCellValue();
				String CONTACT_NAME = wb.getSheet("Contacts").getRow(1).getCell(6).getStringCellValue();
				String MOBILE = wb.getSheet("Contacts").getRow(1).getCell(7).getStringCellValue();
				wb.close();
			*/	
				//read data from excel
				ExcelFileUtility eLib=new ExcelFileUtility();
				String Campaign;
				String CAMPAIGN_NAME=eLib.readDataFromExcel(Campaign,1,2);
				String TARGET_SIZE =eLib.readDataFromExcel(Campaign, 1, 3);

				// Launch the browser
				ChromeOptions settings = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<>();
				prefs.put("profile.password_manager_leak_detection", false);
				settings.setExperimentalOption("prefs", prefs);

				WebDriver driver = null;
				
				
			
				if (BROWSER.equalsIgnoreCase("chrome"))
					driver = new ChromeDriver(settings);
				else if (BROWSER.equalsIgnoreCase("edge"))
					driver = new EdgeDriver();
				else if (BROWSER.equalsIgnoreCase("firefox"))
					driver = new FirefoxDriver();
				else if (BROWSER.equalsIgnoreCase("safari"))
					driver = new SafariDriver();

				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

				// Login
				Loginpage loginpage= new LoginPage(driver);
				loginpage.loginToApp(URL, USERNAME, PASSWORD);
				
			/*	driver.get(URL);
				driver.findElement(By.id("username")).sendKeys(USERNAME);
				driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
				driver.findElement(By.xpath("//button[text()='Sign In']")).click();
*/
				// Create Campaign
				driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
				driver.findElement(By.name("campaignName")).sendKeys(CAMPAIGN_NAME1);
				WebElement targetSize = driver.findElement(By.name("targetSize"));
				targetSize.clear();
				targetSize.sendKeys(TARGET_SIZE1);
				driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
				driver.findElement(By.xpath("//button[@aria-label='close']")).click();
	 //click on contact
				
			driver.findElement(By.linkText("Contacts")).click();
			// click on +create contact
			driver.findElement(By.xpath("//span[text()='Create Contact']")).click();
			// enter the manadatory fields
			driver.findElement(By.name("organizationName")).sendKeys(ORGANIZATION);
			driver.findElement(By.name("title")).sendKeys(TITLE);
			driver.findElement(By.name("contactName")).sendKeys(CONTACT_NAME);
			driver.findElement(By.name("mobile")).sendKeys(MOBILE);
			
	//click on plus button
			driver.findElement(By.xpath("//*[name()='svg'and @data-icon='plus']")).click();
     //switch the driver control to child
			String parentwid = driver.getWindowHandle();
			 Set<String> alldwid = driver.getWindowHandles();
					 alldwid.remove(parentwid);
			 for(String id:alldwid)
			 {
				 driver.switchTo().window(id);
				 if(driver.getTitle().contains("Select Campaign"));
				 break;
			 }
			 
			// select campaignName from drop down
		WebElement campaignDD = driver.findElement(By.id("search-criteria"));
		
		
		WebDriverUtility wLib  = new WebDriverUtility();
		
		//	 Select obj= new Select(campaignDD);
			// obj.selectByValue("campaignName");
           wLib.select(campaignDD,"campaignName");			

			// Enter Camapign name in search text field
				driver.findElement(By.id("search-input")).sendKeys(CAMPAIGN_NAME1);

				// click on select button
			WebElement selectbtn = driver.findElement(By.className("select-btn"));
			WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOf(selectbtn));
			selectbtn.click();
			
			// switch the driver control to parent
			driver.switchTo().window(parentwid);
			
			
			// click on create contact
			driver.findElement(By.xpath("//button[text()='Create Contact']")).click();


			// Verification
			WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
			wait.until(ExpectedConditions.visibilityOf(toastMsg));
			if (toastMsg.getText().contains("Successfully Added"))
				System.out.println("Contact Created");
			else
				System.out.println("Contact Not Created");
			driver.findElement(By.xpath("//button[@aria-label='close']")).click();

			// Logout
			WebElement userIcon = driver.findElement(By.className("user-icon"));
	        wLib.mouseHoverOnWebElement(driver,userIcon);
			
			WebElement logoutBtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
		    wLib.clickOnWebDriverElement(driver,logoutBtn);
			
             
             // Close the browser
			driver.quit();
		}
			
			
			
			
			
			
			
			
			
			
			
			
			
			

//			Actions action = new Actions(driver);
			//action.moveToElement(userIcon).perform();
	 
//			action.moveToElement(logoutBtn).click().perform();
}

			
			
			
			
			
			
			
			


