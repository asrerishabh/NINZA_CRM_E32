package CampaignTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class newTest {
@Test(priority=-200,enabled= false)// this testcase will skip
	public void productCreation()
       {
		Reporter.log("Product creation",true);	
		}



@Test(priority=-200,invocationCount =5,threadPoolSize=3)
public void deleteProduct() throws InterruptedException {
	Reporter.log("deleteProduct",true);
	//Reporter.log("Product creation",true);	
	//WebDriver driver= new ChromeDriver();
//	Thread.sleep(2000);
	//driver.close();
}
@Test(dependsOnMethods="productCreation")
	public void updateProduct()
	{
		Reporter.log("updateProduct",true);
	}

}
