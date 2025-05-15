package com.comcast.crm.objrepo.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@class='searchBox']")
	private WebElement searchTextField;
	@FindBy(xpath="//input[@class='searchBtn']")
	private WebElement searchbtn;
	@FindBy(linkText="Organizations")
	private WebElement Orglink;
	@FindBy(linkText="Contacts")
	private WebElement contactlink;
	@FindBy(linkText="Campaigns")
	private WebElement campaignlink;
	@FindBy(linkText="More")
	private WebElement morelink;
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;
	
	public WebElement getAdminImg() {
		return adminImg;
	}
	public WebElement getSignOutLink() {
		return signOutLink;
	}
	public WebElement getCampaignlink() {
		return campaignlink;
	}
	public WebDriver getDriver() {
		return driver;
	}
	public WebElement getOrglink() {
		return Orglink;
	}
	public WebElement getContactlink() {
		return contactlink;
	}
	public WebElement getSearchTextField() {
		return searchTextField;
	}
	public WebElement getSearchbtn() {
		return searchbtn;
	}
	public WebElement getLink() {
		return Orglink;
	}
	public WebElement getMorelink() {
		return morelink;
	}
	
	public void navigateToCampaignPage()
	{
		Actions act=new Actions(driver);
		act.moveToElement(morelink).perform();
		
		morelink.click();
		campaignlink.click();
	}
	
	public void logout()
	{
		Actions act=new Actions(driver);
		act.moveToElement(adminImg).perform();
		signOutLink.click();
	}
}
