package com.comcast.crm.objrepo.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	WebDriver driver;
	public OrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(name="search_text")
	private WebElement seachTextFld;
	@FindBy(name="search_field")
	private WebElement searchDropDown;
	@FindBy(name="submit")
	private WebElement searchBtn;
	
	public WebElement getSearchBtn() {
		return searchBtn;
	}

	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement createNewOrgBtn;
	
	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}
	public WebElement getSeachTextFld() {
		return seachTextFld;
	}
	public WebElement getSearchDropDown() {
		return searchDropDown;
	}
	
}
