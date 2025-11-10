package ChangedScript;

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

import genericutilities.ExcelFileUtility;
import genericutilities.JavaUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectReposatiory.CampaignPage;
import objectReposatiory.CreateCampaignPage;
import objectReposatiory.CreateContactPage;
import objectReposatiory.HomePage;
import objectReposatiory.Loginpage;
import objectReposatiory.SelectCampaignPage;
import objectReposatiory.ContactPage;

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
			
		
				PropertyFileUtility plib=new PropertyFileUtility();
				String BROWSER =plib.readDataFromProperty("Browser");
				String URL =plib.readDataFromProperty("URL");
				String USERNAME =plib.readDataFromProperty("Username");
				String PASSWORD =plib.readDataFromProperty("password");
          
   	
				//read data from excel
				ExcelFileUtility eLib=new ExcelFileUtility();
				String Campaign;
				String CAMPAIGN_NAME=eLib.readDataFromExcel("Contacts",1,2);
				String TARGET_SIZE =eLib.readDataFromExcel("Contacts", 1, 3);
				String ORGANISATION = eLib.readDataFromExcel("Contacts", 1, 4);
				String TITLE = eLib.readDataFromExcel("Contacts", 1, 5);
				String CONTACT_NAME = eLib.readDataFromExcel("Contacts", 1, 6);

				String SELECT_CAMPAIGN_TITLE = eLib.readDataFromExcel("Contacts", 1, 8);
				String CAMPAIGN_DD_VALUE = eLib.readDataFromExcel("Contacts", 1, 9);
				String TOAST_MSG_VERIFICATION = eLib.readDataFromExcel("Contacts", 1, 10);
						
				WebDriverUtility wLib= new WebDriverUtility();
				JavaUtility jLib= new JavaUtility();
						
						//Launch the browser
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
				Loginpage loginpage= new Loginpage(driver);
				loginpage.loginToApp(URL, USERNAME, PASSWORD);
			
				// Create Campaign
				CampaignPage campaignpage = new CampaignPage(driver);
	            campaignpage.getAddCreateCampaignBtn().click();
		
	            CreateCampaignPage createcampaignpage = new CreateCampaignPage(driver);
	               createcampaignpage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
	               createcampaignpage.getTargetSizeTF().clear();
	               createcampaignpage.getTargetSizeTF().sendKeys(TARGET_SIZE);
	               createcampaignpage.getCreateCampaignBtn().click();
	               HomePage homepage= new HomePage(driver);
	               homepage.getCloseToastMsg().click();
			 //click on contact
           homepage.getContactsLink().click();
           
			// click on +create contact
			ContactPage contactpage= new ContactPage(driver);
			contactpage.getAddCreateContactbtn().click();

			// enter the manadatory fields
		CreateContactPage createcontactpage = new CreateContactPage(driver);
		createcontactpage.getOrganisationTF().sendKeys(ORGANISATION);
		createcontactpage.getTitleTF().sendKeys(TITLE);
		createcontactpage.getcontactNametf().sendKeys(CONTACT_NAME);
		createcontactpage.getMobileTF().sendKeys("9"+jLib.generateNineDigitNumber());
		
	//click on plus button

		createcontactpage.getPlusbtn().click();
		
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
			 wLib.switchDriverControlOnTitle(driver,"select Camapign");
			 wLib.switchDriverControlOnTitle(driver,"SELECT_CAMPAIGN_TITLE ");
			 
			// select campaignName from drop down
			 SelectCampaignPage selectcampaignpage = new SelectCampaignPage(driver);
			WebElement campaignDD =selectcampaignpage.getCampaignDD();
			 
		WebDriverUtility wlib  = new WebDriverUtility();
		
		//	 Select obj= new Select(campaignDD);
			// obj.selectByValue("campaignName");
           wLib.select(campaignDD, CAMPAIGN_DD_VALUE);			

			// Enter Camapign name in search text field
           selectcampaignpage.getSearchTF().sendKeys(CAMPAIGN_NAME);
			
           // click on select button
			WebElement selectbtn =driver.findElement(By.className("select-btn"));
             wLib.waitUntilElementToBeVisible(driver, selectbtn);
             selectbtn.click();
			
			// switch the driver control to parent
			driver.switchTo().window(parentwid);
			
			
			// click on create contact
			driver.findElement(By.xpath("//button[text()='Create Contact']")).click();


			// Verification
			WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
          wLib.waitUntilElementToBeVisible(driver, toastMsg);
			
          //hardAssert
  		Assert.assertTrue(toastMsg.getText().contains(TOAST_MSG_VERIFICATION));

          
          /*if (toastMsg.getText().contains(TOAST_MSG_VERIFICATION))
				System.out.println("Contact Created");
			else
				System.out.println("Contact Not Created"); */
           
			// Logout
			
			HomePage homepage1= new HomePage(driver);
			homepage1.logout();
             
			
			
		  // Close the browser
			driver.quit();
		}
			
			
			
			
			
			
			
			
			
			
			
			
			
			

//			Actions action = new Actions(driver);
			//action.moveToElement(userIcon).perform();
	 
//			action.moveToElement(logoutBtn).click().perform();
}

			
			
			
			
			
			
			
			


