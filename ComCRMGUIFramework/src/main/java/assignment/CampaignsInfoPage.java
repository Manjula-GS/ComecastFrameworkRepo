package assignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsInfoPage {
	
	WebDriver driver;
	public CampaignsInfoPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headertext;
	
	@FindBy(xpath  = "//input[@title='Edit [Alt+E]']")
	private WebElement editBtn;
	
	@FindBy(xpath = "(//td[@class='dvtCellInfo'])[8]")
	private WebElement endDatetxt;

	public WebElement getHeadertext() {
		return headertext;
	}
	public WebElement getEditBtn() {
		return editBtn;
	}
	public WebElement getendDatetxt() {
		return endDatetxt;
	}
	
}
