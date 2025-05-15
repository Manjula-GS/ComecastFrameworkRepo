package assignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InvoiceInfoPage {
	
	WebDriver driver;
	public InvoiceInfoPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "lvtHeaderText")
	private WebElement headertext;
	
	public WebElement getHeadertext() {
		return headertext;
	}
}
