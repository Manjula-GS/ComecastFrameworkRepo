package com.comcast.crm.objrepo.utility;
/**
 * @author Manjula G S
 * contains login page elements and business libraries like login()
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

//Rule 1: create a separate java class

public class LoginPage extends WebDriverUtility{	
	//Rule 2: Object Creation
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
		@FindBy(name="user_name")
		private WebElement usnTextField;
		
		@FindBy(name="user_password")
		private WebElement pswTextField;
		
		@FindBy(id="submitButton")
		private WebElement loginBtn;
		
		//Rule 3: Object Initialization
		//Rule 4: Object Encapsulation
		public WebElement getUsnTextField() {
			return usnTextField;	
		}

		public WebElement getPswTextField() {
			return pswTextField;
		}

		public WebElement getLoginBtn() {
			return loginBtn;
		}
		/**
		 * login to application based on username, password url arguments
		 * @param url
		 * @param username
		 * @param password
		 */
		//Rule 5: Provide Action //Business Library
		public void loginToApp(String url, String username, String password)
		{
			waitForPageToLoad(driver);
			driver.get(url);
			usnTextField.sendKeys(username);
			pswTextField.sendKeys(password);
			loginBtn.click();
		}

		public void logintoApp(String username, String password) {
			usnTextField.sendKeys(username);
			pswTextField.sendKeys(password);
			loginBtn.click();			
		}

}
