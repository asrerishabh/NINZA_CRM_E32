package objectReposatiory;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	public class SelectCampaignPage {

		WebDriver driver;

		public SelectCampaignPage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(id = "search-criteria")
		private WebElement campaignDD;
		
		@FindBy(id = "search-input")
		private WebElement searchTF;
		
		@FindBy(className = "select-btn")
		private WebElement selectBtn;

		public WebElement getCampaignDD() {
			return campaignDD;
		}

		public WebElement getSearchTF() {
			return searchTF;
		}

		public WebElement getSelectBtn() {
			return selectBtn;
		}
		
		
	}

