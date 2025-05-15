package com.comcast.crm.objrepo.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headertext;
	
	@FindBy(id = "dtlview_Last Name")
	private WebElement lastnametext;
	
	@FindBy(id = "mouseArea_Organization Name")
	private WebElement orgnametext;
	
	@FindBy(id = "dtlview_Support Start Date")
	private WebElement startDatetext;
	
	@FindBy(id = "dtlview_Support End Date")
	private WebElement endDatetext;
	

	WebDriver driver;
	public ContactInfoPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getLastnametext() {
		return lastnametext;
	}
	public WebElement getOrgnametext() {
		return orgnametext;
	}
	
	public WebElement getheadertext() {
		return headertext;
	}
	public WebElement getStartDatetext() {
		return startDatetext;
	}

	public WebElement getEndDatetext() {
		return endDatetext;
	}

}
