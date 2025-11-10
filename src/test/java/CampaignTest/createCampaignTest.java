package CampaignTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericutilities.BaseClass;
import genericutilities.ExcelFileUtility;
import genericutilities.JavaUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectReposatiory.CampaignPage;
import objectReposatiory.CreateCampaignPage;
import objectReposatiory.HomePage;
import objectReposatiory.Loginpage;
@Listeners(genericutilities.ListenerImplementation.class)// also do configure it in suite file<listeners>
//<listener class-name="genericutilities.ListenerImplementation"></listner>
//<test--->add before test

//took photo 4/11/25

public class createCampaignTest extends BaseClass{
private static final String groups = null;

@Test(groups="smoke")
	public void CreateContactWithMandatoryFieldsTest() throws EncryptedDocumentException, IOException
	{
	

			String CAMPAIGN_NAME = eLib.readDataFromExcel("Campaign", 1, 2);
			String TARGET_SIZE = eLib.readDataFromExcel("Campaign", 1, 3);

			// Create Campaign with mandatory fields
			CampaignPage campaignsPage = new CampaignPage(driver);
			campaignsPage.getAddCreateCampaignBtn().click();
			
			CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
			createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
			createCampaignPage.getTargetSizeTF().clear();
			createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
			createCampaignPage.getCreateCampaignBtn().click();

			// Verify the toast msg
			HomePage homepage = new HomePage(driver);
			WebElement toastMsg = homepage.getToastMsg();
			wLib.waitUntilElementToBeVisible(driver, toastMsg);
			String msg = toastMsg.getText();
		// hard assert
			Assert.assertTrue(msg.contains("Successfully Added"));
			
			
			/*if (msg.contains("Successfully Added"))
				System.out.println("Campaign Created");
			else
				System.out.println("Campaign Not Created");*/
			homepage.getCloseToastMsg().click();

		}
	
	@Test(groups ="smoke")
	
	public void createCampaignWithStatusTest() throws IOException
	{

		//read from excel
	 FileInputStream fis1 = new FileInputStream("path of excel saved on local");
	 Workbook workbook= WorkbookFactory.create(fis1);
	  String CAMPAIGN_NAME =workbook.getSheet("campaign").getRow(4).getCell(2).getStringCellValue();
       String STATUS=workbook.getSheet("campaign").getRow(4).getCell(3).getStringCellValue();
        String TARGET_SIZE=workbook.getSheet("campaign").getRow(4).getCell(4).getStringCellValue();
	
        

	
		// Create Campaign
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys("Campaigntest");
		driver.findElement(By.name("campaignStatus")).sendKeys("Active");
		WebElement targetSize = driver.findElement(By.name("targetSize"));
		targetSize.clear();
		targetSize.sendKeys("8");
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

		// Verification
		WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));
		if (toastMsg.getText().contains("Successfully Added"))
			System.out.println("Campaign Created");
		else
			System.out.println("Campaign Not Created");
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();


		
		// Close the browser
		driver.quit();
		
	}
	
	@Test(groups="regression")
	public void  createCampaignWithExpectedCloseDate() throws IOException
	
	{
		// Read data from properties file
		PropertyFileUtility pLib = new PropertyFileUtility();
		String BROWSER = pLib.readDataFromProperty("Browser");
		String URL = pLib.readDataFromProperty("URL");
		String USERNAME = pLib.readDataFromProperty("Username");
		String PASSWORD = pLib.readDataFromProperty("Password");

		// Read test script data from excel file
		ExcelFileUtility eLib = new ExcelFileUtility();
		String CAMPAIGN_NAME = eLib.readDataFromExcel("Campaigns", 7, 2);
		String TARGET_SIZE = eLib.readDataFromExcel("Campaigns", 7, 3);

		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		// Launch the browser
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);

		WebDriver driver = null;
		if (BROWSER.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
		else if (BROWSER.equalsIgnoreCase("Chrome"))
			driver = new ChromeDriver(settings);
		else if (BROWSER.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else if (BROWSER.equalsIgnoreCase("Safari"))
			driver = new SafariDriver();

		driver.manage().window().maximize();
		wLib.implicitWait(driver);
		// Login
		Loginpage loginPage = new Loginpage(driver);
		loginPage.loginToApp(URL, USERNAME, PASSWORD);

		// Create Campaign with mandatory fields
		CampaignPage campaignsPage = new CampaignPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getExpectedCloseDateTF().sendKeys(jLib.getRequiredDate(50));
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();

		// Verify the toast msg
		HomePage homepage = new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		String msg = toastMsg.getText();
		if (msg.contains("Successfully Added"))
			System.out.println("Campaign Created");
		else
			System.out.println("Campaign Not Created");
		homepage.getCloseToastMsg().click();

		// Logout
		homepage.logout();

		// Close browser
		driver.quit();
	}

	}

