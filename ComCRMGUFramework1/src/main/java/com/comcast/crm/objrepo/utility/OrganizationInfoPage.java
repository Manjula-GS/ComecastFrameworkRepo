package com.comcast.crm.objrepo.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="dvHeaderText")
	private WebElement headerMsg;
	public WebDriver getDriver() {
		return driver;
	}
	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	

}
