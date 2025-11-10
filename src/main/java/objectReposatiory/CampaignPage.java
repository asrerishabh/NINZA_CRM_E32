package objectReposatiory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage {
	WebDriver driver;
 public CampaignPage(WebDriver driver)
 {
	 PageFactory.initElements(driver, this);
 }
 
 @FindBy(xpath="//span[text()='create campaign']")
 private WebElement addCreateCampaignBtn;
 

public WebElement getAddCreateCampaignBtn() {
	return addCreateCampaignBtn;
}
 
}
