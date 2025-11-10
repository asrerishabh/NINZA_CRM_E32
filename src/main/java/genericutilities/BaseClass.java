package genericutilities;

import org.testng.annotations.Test;

import objectReposatiory.HomePage;
import objectReposatiory.Loginpage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.module.Browser;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseClass {
	public static WebDriver driver = null;
    public   PropertyFileUtility pLib = new PropertyFileUtility();
    public ExcelFileUtility eLib= new ExcelFileUtility();
	public JavaUtility jLib= new JavaUtility();
	public WebDriverUtility wLib= new WebDriverUtility();
	public static WebDriver sdriver=null;
	
  @BeforeSuite(groups = {"smoke","regression"})
	  public void beforeSuite() {
	  System.out.println("Establish the data base connection");
	  }

  @BeforeTest(groups = {"smoke","regression"})
  public void beforeTest() {
	  System.out.println("PreCondition for Parallel execusion");
  }
@Parameters("BROWSER")
  @BeforeClass(groups = {"smoke","regression"})
 
public void beforeClass(String browser) throws IOException {
	String BROWSER = browser;
	System.out.println("Launch The Browser");
	 String Browser = pLib.readDataFromProperty("Browser");
	  //string BROWSER= System.getProperty("browser");
	    ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);

	
		if (BROWSER.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver(settings);
		else if (BROWSER.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		else if (BROWSER.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if (BROWSER.equalsIgnoreCase("safari"))
			driver = new SafariDriver();

		
		sdriver=driver;
		
		driver.manage().window().maximize();
         wLib.implicitWait(driver);
  }
  
  @BeforeMethod(groups = {"smoke","regression"})
  public void beforeMethod() throws IOException {
	  System.out.println("login");
	String URL =pLib.readDataFromProperty("URL");
		String USERNAME =pLib.readDataFromProperty("Username");
		String PASSWORD =pLib.readDataFromProperty("password");
  
	//  String URL =System.getProperty("url");
	  //String USERNAME =System.getProperty("username");
	  //String PASSWORD =System.getProperty("password");
 
		Loginpage loginpage= new Loginpage(driver);
		loginpage.loginToApp(URL, USERNAME, PASSWORD);
	
  }

  @AfterMethod(groups = {"smoke","regression"})
  public void afterMethod() {
	  System.out.println("logout");
	  HomePage homePage = new HomePage(driver);
	  homePage.logout();
  }


  @AfterClass(groups = {"smoke","regression"})
  public void afterClass() {System.out.println("close the Browser");
  }


  @AfterTest(groups = {"smoke","regression"})
  public void afterTest() {
	  System.out.println("Post_Condition for Parallel execusion");
  }

  

  @AfterSuite(groups = {"smoke","regression"})
  public void afterSuite() {
	  System.out.println("close data base connection");
  }

}
