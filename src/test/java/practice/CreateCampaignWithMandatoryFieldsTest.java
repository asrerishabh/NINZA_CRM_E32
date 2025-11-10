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
import objectReposatiory.HomePage;
import objectReposatiory.Loginpage;

public class CreateCampaignWithMandatoryFieldsTest {
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
	
	
	/* driver.get("http://49.249.28.218:8098/");
	 WebElement usernameTextfield = driver.findElement(By.id("username"));
	 usernameTextfield.sendKeys("rmgyantra");
	 
	 WebElement passwordTextField = driver.findElement(By.id("inputPassword"));
	 passwordTextField.sendKeys("rmgy@9999");
	 
	 WebElement signinButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
	 signinButton.click();
	
	 Thread.sleep(5000);
	 WebDriverUtility wLib  = new WebDriverUtility();
	*/ 
 // create campaign
	// driver.switchTo().alert().accept();
	 WebElement createbutton = driver.findElement(By.xpath("//span[text()='Create Campaign']"));
	 createbutton.click();
	 
    //div[@class='user-icon']
	WebElement campaignName = driver.findElement(By.name("campaignName"));
	campaignName.sendKeys("firstmavaenP");
	 
	WebElement targetSize = driver.findElement(By.name("targetSize"));
	targetSize.clear();
	targetSize.sendKeys("5");
	
	WebElement createCampaignButton = driver.findElement(By.xpath("//button[text()='Create Campaign']"));
	createCampaignButton.click();
	
	//verification
	//toast msg
	WebElement toast = driver.findElement(By.xpath("//div[@role='alert']"));
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOf(toast));
	if(toast.getText().contains("Successfully Added"))
		System.out.println("campaign created");
	else
		System.out.println("campaign not created");
	driver.findElement(By.xpath("//button[@aria-label='close']")).click();
	//logout
	/*WebElement usericon = driver.findElement(By.xpath("//div[@class='user-icon']"));
	Actions action = new Actions(driver);
	action.moveToElement(usericon).click().perform();
	
	WebElement logutbtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
	action.moveToElement(logutbtn).click().perform();
	*/
	// Logout
	
	HomePage homepage = new HomePage(driver);
	homepage.logout();
	
	
	
		/*		WebElement userIcon = driver.findElement(By.className("user-icon"));
		        wLib.mouseHoverOnWebElement(driver,userIcon);
				
				WebElement logoutBtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
			    wLib.clickOnWebDriverElement(driver,logoutBtn);
		*/		
	             
	
	driver.quit();
	
	
	
	
	
	
	
	
	
	 
}
}
