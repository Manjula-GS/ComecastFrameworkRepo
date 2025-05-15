package com.comcast.crm.objrepo.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);		
	}
	
	@FindBy(name="accountname")
	private WebElement orgNameTxtFld;
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	@FindBy(name="industry")
	private WebElement indusDD;
	
	public WebElement getIndusDD() {
		return indusDD;
	}
	public WebElement getOrgNameTxtFld() {
		return orgNameTxtFld;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrg(String orgName)
	{
		orgNameTxtFld.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrg(String orgName, String industry)
	{
		orgNameTxtFld.sendKeys(orgName);
		Select sel=new Select(indusDD);
		sel.selectByVisibleText(industry);
		saveBtn.click();
	}
}
