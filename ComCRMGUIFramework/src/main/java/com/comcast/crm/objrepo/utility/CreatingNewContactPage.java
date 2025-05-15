package com.comcast.crm.objrepo.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	
	@FindBy(name = "lastname")
	private WebElement lastnameEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	@FindBy(name = "support_start_date")
	private WebElement startdateEdt;
	
	@FindBy(name = "support_end_date")
	private WebElement enddateEdt;
	
	public WebElement getLastnameEdt() {
		return lastnameEdt;
	}
	public void setLastnameEdt(WebElement lastnameEdt) {
		this.lastnameEdt = lastnameEdt;
	}
	public WebElement getStartdateEdt() {
		return startdateEdt;
	}
	
	public WebElement getEnddateEdt() {
		return enddateEdt;
	}

	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public WebElement getlastnameEdt() {
		return lastnameEdt;
	}
	public WebElement getSavebtn() {
		return savebtn;
	}
	
	public void createContact(String lastname)
	{
		getlastnameEdt().sendKeys(lastname);
		savebtn.click();
	}
	
	public void createContactwithSupporteDate(String lastname, String startdate, String enddate)
	{
		getlastnameEdt().sendKeys(lastname);
		getStartdateEdt().clear();
		getStartdateEdt().sendKeys(startdate);
		getEnddateEdt().clear();
		getEnddateEdt().sendKeys(enddate);
		savebtn.click();
	}

}
