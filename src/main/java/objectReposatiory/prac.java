package objectReposatiory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class prac {
	WebDriver driver;
 public prac( WebDriver driver) {
	 PageFactory.initElements(driver, this);
	 this.driver=driver;
 }
 @FindBy(name="campaignNAme")
 private WebElement CampaignNAmeTf;
 
 
 @FindBy(name="targetsize") private WebElement targetSizeTf;



public WebElement getCampaignNAmeTf() {
	return CampaignNAmeTf;
}


public WebElement getTargetSizeTf() {
	return targetSizeTf;
}
 
}
