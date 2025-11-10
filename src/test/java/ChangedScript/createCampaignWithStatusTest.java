package ChangedScript;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import genericutilities.ExcelFileUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectReposatiory.CampaignPage;
import objectReposatiory.CreateCampaignPage;
import objectReposatiory.HomePage;
import objectReposatiory.Loginpage;

public class createCampaignWithStatusTest {

	public static void main(String[] args, CharSequence campaignStatus) throws IOException {

		PropertyFileUtility plib=new PropertyFileUtility();
		String BROWSER =plib.readDataFromProperty("Browser");
		String URL =plib.readDataFromProperty("URL");
		String USERNAME =plib.readDataFromProperty("Username");
		String PASSWORD =plib.readDataFromProperty("password");
  
		//read from excel
		 ExcelFileUtility eLib = new ExcelFileUtility();
		  String CAMPAIGN_NAME =eLib.readDataFromExcel("Campaign", 1, 2);
	        String TARGET_SIZE=eLib.readDataFromExcel("Campaign", 1, 3);
			String TOAST_MSG_VERIFICATION = eLib.readDataFromExcel("Campaign", 4, 5);

        
   	 WebDriverUtility wLib  = new WebDriverUtility();

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
         wLib.implicitWait(driver);
		// Login
	
		Loginpage loginpage=  new Loginpage(driver);
		loginpage.loginToApp(URL, USERNAME, PASSWORD);
		// Create Campaign
		CampaignPage campaignpage = new CampaignPage(driver);
        campaignpage.getAddCreateCampaignBtn().click();

        CreateCampaignPage createcampaignpage = new CreateCampaignPage(driver);
        createcampaignpage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
        createcampaignpage.getCampaignStatusTF().sendKeys(campaignStatus);

        createcampaignpage.getTargetSizeTF().clear();
        createcampaignpage.getTargetSizeTF().sendKeys(TARGET_SIZE);
        createcampaignpage.getCreateCampaignBtn().click();
			
		// Verification
		WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		
	//HARDassert
		Assert.assertTrue(toastMsg.getText().contains(TOAST_MSG_VERIFICATION));
		/*
		if (toastMsg.getText().contains(TOAST_MSG_VERIFICATION))
			System.out.println("Campaign Created");
		else
			System.out.println("Campaign Not Created");*/
		
     //   homepage.getCloseToastMsg().click()


		

		// Logout
        

		HomePage homepage= new HomePage(driver);
		homepage.logout();
         
		
		
		// Close the browser
		driver.quit();
	}

	}


