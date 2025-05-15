package assignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InvoicePage {

	WebDriver driver;
	public InvoicePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@title='Create Invoice...']")
	private WebElement CreateNewProductBtn;

	public WebElement getCreateNewInvoiceBtn() {
		return  CreateNewProductBtn;
	}
	
}
