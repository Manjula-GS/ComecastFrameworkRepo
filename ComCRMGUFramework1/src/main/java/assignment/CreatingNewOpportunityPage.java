package assignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewOpportunityPage {
	WebDriver driver;
	public CreatingNewOpportunityPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "potentialname")
	private WebElement OpportunityNameEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	@FindBy(xpath = "//input[@id='related_to']/following-sibling::img")
	private WebElement selectOrgbtn;
	
	public WebElement getOpportunityNameEdt() {
		return OpportunityNameEdt;
	}
	public WebElement getSavebtn() {
		return savebtn;
	}
	public WebElement getSelectOrgbtn() {
		return selectOrgbtn;
	}
}
