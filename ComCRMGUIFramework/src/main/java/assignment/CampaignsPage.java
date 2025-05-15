package assignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsPage {
	
	WebDriver driver;
	public CampaignsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@title='Create Campaign...']")
	private WebElement CreateNewCampaignBtn;

	public WebElement getCreateNewCampaignBtn() {
		return  CreateNewCampaignBtn;
	}
}
