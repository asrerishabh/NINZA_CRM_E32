package practice;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericutilities.WebDriverUtility;
import objectReposatiory.CampaignPage;
import objectReposatiory.CreateCampaignPage;
import objectReposatiory.HomePage;
import objectReposatiory.Loginpage;

public class changedCreateCampaignWithMandatoryFieldsTest {
public static void main(String[] args) throws InterruptedException {
// launch 
	ChromeOptions settings = new ChromeOptions();
	Map<String, Object> prefs = new HashMap<>();
	prefs.put("profile.password_manager_leak_detection", false);
	settings.setExperimentalOption("prefs", prefs);
	
	WebDriver driver= new ChromeDriver(settings);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	//login
	Loginpage loginpage =new Loginpage(driver);
	loginpage.loginToApp(URL,USERNAME,PASSWORD);
	
	
 // create campaign
	CampaignPage campaignpage = new CampaignPage(driver);
    campaignpage.getAddCreateCampaignBtn().click();

    CreateCampaignPage createcampaignpage = new CreateCampaignPage(driver);
    createcampaignpage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
    createcampaignpage.getTargetSizeTF().clear();
    createcampaignpage.getTargetSizeTF().sendKeys(TARGET_SIZE);
    createcampaignpage.getCreateCampaignBtn().click();
		
	
	WebElement createCampaignButton = driver.findElement(By.xpath("//button[text()='Create Campaign']"));
	createCampaignButton.click();
	
	//verification
	//toast msg
	WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
	
	wib.waitUntilElementToBeVisible(driver,toastMsg);
	if(toastMsg.getText().contains("Successfully Added"))
		System.out.println("campaign created");
	else
		System.out.println("campaign not created");
    homepage.getCloseToastMsg().click()
	
	// Logout
	
	HomePage homepage = new HomePage(driver);
	homepage.logout();
	
	
	          
	
	driver.quit();
	
	
	
	
	
	
	
	
	
	 
}
}
