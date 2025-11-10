package MamsCode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import genericutilities.ExcelFileUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectReposatiory.CampaignPage;
import objectReposatiory.CreateCampaignPage;
import objectReposatiory.HomePage;
import objectReposatiory.Loginpage;

public class CreateCampaignWithMandatoryFieldsTest {
	public static void main(String[] args) throws IOException {

		// Read common data from properties file
		PropertyFileUtility pLib = new PropertyFileUtility();
		String BROWSER = pLib.readDataFromProperty("Browser");
		String URL = pLib.readDataFromProperty("URL");
		String USERNAME = pLib.readDataFromProperty("Username");
		String PASSWORD = pLib.readDataFromProperty("Password");

		// Read test script data from excel
		ExcelFileUtility eLib = new ExcelFileUtility();
		String CAMPAIGN_NAME = eLib.readDataFromExcel("Campaign", 1, 2);
		String TARGET_SIZE = eLib.readDataFromExcel("Campaign", 1, 3);
		String TOAST_MSG_VERIFICATION = eLib.readDataFromExcel("Campaign", 1, 4);

		WebDriverUtility wLib = new WebDriverUtility();

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
		Loginpage loginPage = new Loginpage(driver);
		loginPage.loginToApp(URL, USERNAME, PASSWORD);

		// Create Campaign
		CampaignPage campaignsPage = new CampaignPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();

//		driver.findElement(http://By.name("campaignName")).sendKeys(CAMPAIGN_NAME);
//		WebElement targetSize = driver.findElement(http://By.name("targetSize"));
//		targetSize.clear();
//		targetSize.sendKeys(TARGET_SIZE);
//		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

		// Verification
		HomePage homePage = new HomePage(driver);
		WebElement toastMsg = homePage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		if (toastMsg.getText().contains(TOAST_MSG_VERIFICATION))
			System.out.println("Campaign Created");
		else
			System.out.println("Campaign Not Created");
		homePage.getCloseToastMsg().click();

		// Logout
		homePage.logout();

		// Close the browser
		driver.quit();

	}
}
