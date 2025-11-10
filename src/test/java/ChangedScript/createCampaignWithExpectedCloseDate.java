package ChangedScript;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
//org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import genericutilities.ExcelFileUtility;
import genericutilities.JavaUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectReposatiory.CampaignPage;
import objectReposatiory.CreateCampaignPage;
import objectReposatiory.HomePage;
import objectReposatiory.Loginpage;
public class createCampaignWithExpectedCloseDate {
	public static void main(String[] args, CharSequence expectedCloseDate) throws IOException {

		
		PropertyFileUtility plib=new PropertyFileUtility();
		String BROWSER =plib.readDataFromProperty("Browser");
		String URL =plib.readDataFromProperty("URL");
		String USERNAME =plib.readDataFromProperty("Username");
		String PASSWORD =plib.readDataFromProperty("password");
  
				//read from excel
		          ExcelFileUtility eLib = new ExcelFileUtility();
				  String CAMPAIGN_NAME =eLib.readDataFromExcel("Campaign", 7, 2);
			        String TARGET_SIZE=eLib.readDataFromExcel("Campaign", 7, 3);
					String TOAST_MSG_VERIFICATION = eLib.readDataFromExcel("Campaign", 7, 4);
					
					//WebDriverUtility wLib= new WebDriverUtility();

			        
			        
				JavaUtility jlib= new JavaUtility();
				WebDriverUtility wLib  = new WebDriverUtility();
				
				//Generate date after 30 days
				Date date=new Date();
				SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
				sim.format(date);
				Calendar cal = sim.getCalendar();
				cal.add(Calendar.DAY_OF_MONTH, 30);
				String requiredDate = sim.format(cal.getTime());

				// Launch the browser
				ChromeOptions settings = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<>();
				prefs.put("profile.password_manager_leak_detection", false);
				settings.setExperimentalOption("prefs", prefs);
				
				WebDriver driver=null;
				
				if(BROWSER.equalsIgnoreCase("chrome"))
					driver=new ChromeDriver(settings);
				else if(BROWSER.equalsIgnoreCase("edge"))
					driver=new EdgeDriver();
				else if(BROWSER.equalsIgnoreCase("firefox"))
					driver=new FirefoxDriver();
				else if(BROWSER.equalsIgnoreCase("safari"))
					driver=new SafariDriver();
					
				driver.manage().window().maximize();
                 wLib.implicitWait(driver);
				// Login
							
				Loginpage loginpage= new Loginpage(driver);
				loginpage.loginToApp(URL, USERNAME, PASSWORD);
				
				
				
				// Create Campaign
               CampaignPage campaignpage = new CampaignPage(driver);
               campaignpage.getAddCreateCampaignBtn().click();
				
               
               CreateCampaignPage createcampaignpage = new CreateCampaignPage(driver);
               createcampaignpage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
               createcampaignpage.getTargetSizeTF().clear();
               createcampaignpage.getTargetSizeTF().sendKeys(TARGET_SIZE);
               driver.findElement(By.name("expectedCloseDate")).sendKeys(jlib.getRequiredDate(50));
				driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

               
               createcampaignpage.getExpectedCloseDateTF().sendKeys(expectedCloseDate);
               createcampaignpage.getCreateCampaignBtn().click();
			
               // Verification
				WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
				wLib.waitUntilElementToBeVisible(driver, toastMsg);
				
				Assert.assertTrue(toastMsg.getText().contains("Successfully Added"));
			/*	if (toastMsg.getText().contains("Successfully Added"))
					System.out.println("Campaign Created");
				else
					System.out.println("Campaign Not Created");*/
				
             //   HomePage.getCloseToastMsg().click();
				
			
				// Logout
				HomePage homepage= new HomePage(driver);
				homepage.logout();
	             
				// Close the browser
				driver.quit();

	}

}
 

