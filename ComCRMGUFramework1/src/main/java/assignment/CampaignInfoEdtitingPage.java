package assignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignInfoEdtitingPage {
	
	WebDriver driver;
	public CampaignInfoEdtitingPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "campaignname")
	private WebElement campaignnameEdt;
	
	@FindBy(name = "closingdate")
	private WebElement closingdateEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public WebElement getCampaignnameEdt() {
		return campaignnameEdt;
	}
	public WebElement getClosingdateEdt() {
		return closingdateEdt;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}

}
