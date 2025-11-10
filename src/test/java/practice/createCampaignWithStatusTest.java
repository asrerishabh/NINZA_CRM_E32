package practice;

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

import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectReposatiory.Loginpage;

public class createCampaignWithStatusTest {

	public static void main(String[] args) throws IOException {
/*
		// Read data from properties file
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
  
		//read from excel
	 FileInputStream fis1 = new FileInputStream("path of excel saved on local");
	 Workbook workbook= WorkbookFactory.create(fis1);
	  String CAMPAIGN_NAME =workbook.getSheet("campaign").getRow(4).getCell(2).getStringCellValue();
       String STATUS=workbook.getSheet("campaign").getRow(4).getCell(3).getStringCellValue();
        String TARGET_SIZE=workbook.getSheet("campaign").getRow(4).getCell(4).getStringCellValue();
	
        
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// Login
	/*	driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
*/
		Loginpage loginpage=  new Loginpage(driver);
		loginpage.loginToApp(URL, USERNAME, PASSWORD);
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

		// Logout
	/*	WebElement userIcon = driver.findElement(By.className("user-icon"));
		Actions action = new Actions(driver);
		action.moveToElement(userIcon).perform();
		WebElement logoutBtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
		action.moveToElement(logoutBtn).click().perform();
*/
		

		// Logout
		WebElement userIcon = driver.findElement(By.className("user-icon"));
        wLib.mouseHoverOnWebElement(driver,userIcon);
		
		WebElement logoutBtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
	    wLib.clickOnWebDriverElement(driver,logoutBtn);
		
		// Close the browser
		driver.quit();
	}


	

	}


