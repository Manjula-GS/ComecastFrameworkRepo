package assignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewInvoicePage {
	
	WebDriver driver;
	public CreatingNewInvoicePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "subject")
	private WebElement subjectEdt;
	
	@FindBy(name = "bill_street")
	private WebElement billingedt;
	
	@FindBy(name = "ship_street")
	private WebElement shippingedt;
	
	@FindBy(name = "qty1")
	private WebElement qtyEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	public WebElement getSubjectEdt() {
		return subjectEdt;
	}

	public WebElement getBillingedt() {
		return billingedt;
	}

	public WebElement getShippingedt() {
		return shippingedt;
	}

	public WebElement getQtyEdt() {
		return qtyEdt;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}

}
