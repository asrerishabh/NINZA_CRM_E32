package objectReposatiory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
WebDriver driver;
 public CreateContactPage(WebDriver driver)
 {
this.driver =driver;
PageFactory.initElements(driver,this);
 }
 
 @FindBy(name="organisation")
 private WebElement organisationNameTF;
 
 @FindBy(name="title")
 private WebElement titleTF;
 
 @FindBy(name="contactName")
 private WebElement contactNametf;
 
 @FindBy(name="mobile")
 private WebElement mobileTF;
 
 @FindBy(xpath="//*[name()=svg and @data-icon=plus']")
 private WebElement plusbtn;
 
 
 
 @FindBy(xpath="//button[text()='create contact']")
 private WebElement createcontactbtn;
 
public WebElement getOrganisationTF() {
	return organisationNameTF;
}
public WebElement getcontactNametf() {
	return contactNametf;
}

public WebElement getTitleTF() {
	return titleTF;
}

public WebElement getMobileTF() {
	return mobileTF;
}

public WebElement getPlusbtn() {
	return plusbtn;
}


public WebElement getCreatecontactbtn() {
	return createcontactbtn;
}
 
}
