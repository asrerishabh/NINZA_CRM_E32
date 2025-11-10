package practice;

	import java.io.FileInputStream;
	import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

	public class fetchTheDataFromPropertiesfileExecusion {
		
		public static void main(String[] args) throws IOException, InterruptedException {
			FileInputStream fis = new FileInputStream("C:\\Users\\ADMIN\\OneDrive\\Documents\\key_value.properties");
			Properties prop = new Properties();
			prop.load(fis);
			
			String browser = prop.getProperty("browser");
			String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		System.out.println(browser);
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		
		// launch 
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);
         
		WebDriver driver= null;
	//	WebDriverManager.edgedriver().setup(); 
		if(browser.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver(settings);
		else if(browser.equalsIgnoreCase("edge"))
			driver= new EdgeDriver();
		else if(browser.equalsIgnoreCase("safari"))
			driver= new SafariDriver();
		
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//login
		
		driver.get(url);
		 WebElement usernameTextfield = driver.findElement(By.id("username"));
		 usernameTextfield.sendKeys("username");
		 
		 WebElement passwordTextField = driver.findElement(By.id("inputPassword"));
		 passwordTextField.sendKeys("password");
		 
		 WebElement signinButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
		 signinButton.click();
		
		 Thread.sleep(5000);
		 
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
		WebElement usericon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions action = new Actions(driver);
		action.moveToElement(usericon).click().perform();
		
		WebElement logutbtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
		action.moveToElement(logutbtn).click().perform();
		
		
		driver.quit();
		
		
	
}
}