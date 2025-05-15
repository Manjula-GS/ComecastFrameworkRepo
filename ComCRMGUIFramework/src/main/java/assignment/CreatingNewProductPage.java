package assignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewProductPage {
	
	WebDriver driver;
	public CreatingNewProductPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "productname")
	private WebElement productNameEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	public WebElement getProductNameEdt() {
		return productNameEdt;
	}
	public WebElement getSavebtn() {
		return savebtn;
	}
	
	public void createProduct(String productName)
	{
		productNameEdt.sendKeys(productName);
		savebtn.click();
	}
}
