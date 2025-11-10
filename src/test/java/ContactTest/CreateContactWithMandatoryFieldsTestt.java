package ContactTest;

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
import org.testng.Assert;
import org.testng.annotations.Test;

import genericutilities.BaseClass;
import genericutilities.ExcelFileUtility;
import genericutilities.JavaUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectReposatiory.CampaignPage;
import objectReposatiory.ContactPage;
import objectReposatiory.CreateCampaignPage;
import objectReposatiory.CreateContactPage;
import objectReposatiory.HomePage;
import objectReposatiory.Loginpage;
import objectReposatiory.SelectCampaignPage;

public class CreateContactWithMandatoryFieldsTestt extends BaseClass {

	
	@Test(groups = "regression")
			public static void main(String[] args) throws IOException {
			
			
		// Create campaign
				// Read common data from properties file
				PropertyFileUtility pLib = new PropertyFileUtility();
				String BROWSER = pLib.readDataFromProperty("Browser");
				String URL = pLib.readDataFromProperty("URL");
				String USERNAME = pLib.readDataFromProperty("Username");
				String PASSWORD = pLib.readDataFromProperty("Password");

				// Read test script data from excel
				ExcelFileUtility eLib = new ExcelFileUtility();
				String CAMPAIGN_NAME = eLib.readDataFromExcel("Contacts", 1, 2);
				String TARGET_SIZE = eLib.readDataFromExcel("Contacts", 1, 3);
				String ORGANIZATION = eLib.readDataFromExcel("Contacts", 1, 4);
				String TITLE = eLib.readDataFromExcel("Contacts", 1, 5);
				String CONTACT_NAME = eLib.readDataFromExcel("Contacts", 1, 6);
				String SELECT_CAMPAIGN_PAGE_TITLE = eLib.readDataFromExcel("Contacts", 1, 8);
				String CAMPAIGN_DD_VALUE = eLib.readDataFromExcel("Contacts", 1, 9);
				String TOAST_MSG_VERIFICATION = eLib.readDataFromExcel("Contacts", 1, 10);

				JavaUtility jLib = new JavaUtility();
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
				HomePage homePage = new HomePage(driver);
				homePage.getCloseToastMsg().click();

				// Click on contacts
				homePage.getContactsLink().click();

				// click on +create contact
				ContactPage contactsPage = new ContactPage(driver);
				contactsPage.getAddCreateContactbtn().click();

				// enter the manadatory fields
				CreateContactPage createContactPage = new CreateContactPage(driver);
				createContactPage.getOrganisationTF().sendKeys(ORGANIZATION);
				createContactPage.getTitleTF().sendKeys(TITLE);
				createContactPage.getcontactNametf().sendKeys(CONTACT_NAME);
				createContactPage.getMobileTF().sendKeys("9" + jLib.generateNineDigitNumber());
				createContactPage.getPlusbtn().click();
//				driver.findElement(http://By.name("organizationName")).sendKeys(ORGANIZATION);
//				driver.findElement(http://By.name("title")).sendKeys(TITLE);
//				driver.findElement(http://By.name("contactName")).sendKeys(CONTACT_NAME);
//				driver.findElement(http://By.name("mobile")).sendKeys("9" + jLib.generateNineDigitNumber());
		//
//				// click on plus button
//				driver.findElement(By.xpath("//*[name()='svg'and @data-icon='plus']")).click();

				// get parentId
				String parentId = driver.getWindowHandle();

				// Switch the driver control to child
//				String parentId = driver.getWindowHandle();
//				Set<String> allIds = driver.getWindowHandles();
//				allIds.remove(parentId);
//				for (String id : allIds) {
//					driver.switchTo().window(id);
//					if (driver.getTitle().contains("Select Campaign"))
//						break;
//				}
//				wLib.switchDriverControlOnTitle(driver, "Select Campaign");
				wLib.switchDriverControlOnTitle(driver, SELECT_CAMPAIGN_PAGE_TITLE);

				// select campaignName from drop down
				SelectCampaignPage selectCampaignPage = new SelectCampaignPage(driver);
				WebElement campaignDD = selectCampaignPage.getCampaignDD();
//				WebElement campaignDD = driver.findElement(http://By.id("search-criteria"));
//				Select obj = new Select(campaignDD);
//				obj.selectByValue("campaignName");
				http://wLib.select(campaignDD, CAMPAIGN_DD_VALUE);

				// Enter Camapign name in search text field
				selectCampaignPage.getSearchTF().sendKeys(CAMPAIGN_NAME);
//				driver.findElement(http://By.id("search-input")).sendKeys(CAMPAIGN_NAME);

				// click on select button
//				WebElement selectBtn = driver.findElement(By.className("select-btn"));
				WebElement selectBtn = selectCampaignPage.getSelectBtn();
				wLib.waitUntilElementToBeVisible(driver, selectBtn);
				http://selectBtn.click();

				// switch the driver control to parent
				driver.switchTo().window(parentId);

				// click on create contact
				createContactPage.getCreatecontactbtn().click();
//				driver.findElement(By.xpath("//button[text()='Create Contact']")).click();

				// Verification
				WebElement toastMsg = homePage.getToastMsg();
				String msg = homePage.getToastMsg().getText();
				wLib.waitUntilElementToBeVisible(driver, toastMsg);
				homePage.getCloseToastMsg().click();
				Assert.assertTrue(msg.contains(TOAST_MSG_VERIFICATION));	


				// Logout
				homePage.logout();

				// Close the browser
				driver.quit();
			}

		}


			
			
			
			
			
			
			
			
			
			
			
			

//			Actions action = new Actions(driver);
			//action.moveToElement(userIcon).perform();
	 
//			action.moveToElement(logoutBtn).click().perform();


			
			
			
			
			
			
			
			


