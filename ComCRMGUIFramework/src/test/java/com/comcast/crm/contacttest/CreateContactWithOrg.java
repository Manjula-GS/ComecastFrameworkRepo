package com.comcast.crm.contacttest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrg {
	@Test
	public void createOrg() throws IOException
	{
		//create object for utilities
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		//read common data from properties file
		String Browser=flib.getDataFromPropertiesFile("browser");
		String Url=flib.getDataFromPropertiesFile("url");
		String Username=flib.getDataFromPropertiesFile("usn");
		String Password=flib.getDataFromPropertiesFile("psw");
		
		
		//read test script data from excel file
		String Org = elib.getDataFromExcel("contact", 7, 2)+jlib.getRandomNumber();
		String LastName = elib.getDataFromExcel("contact", 7, 3);
		
		WebDriver driver=null;
		
		if(Browser.equals("chrome"))
		{
			 driver = new ChromeDriver();
		}
		else if(Browser.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new EdgeDriver();
		}
		
		//Step 1: Login to application
		driver.manage().window().maximize();
		wlib.waitForPageToLoad(driver);
		driver.get(Url);
		
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();
				
		//Step 2: Navigation to Organization Module				
		driver.findElement(By.linkText("Organizations")).click();
				
		//Step 3: Click on "Create Organization" Button	
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
		//Step 4: Enter all the details and create New Organization
		driver.findElement(By.name("accountname")).sendKeys(Org);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify Header message expected result 
		String HeaderInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		wlib.verification(HeaderInfo,Org);
		
		//Step 5: Navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 6: Click on "Create Contact" Button				
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
		//Step 7: Enter all the details and create New Contact		
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		//Switch to Child window
		wlib.switchNewTabOnUrl(driver, "module=Accounts");

		driver.findElement(By.name("search_text")).sendKeys(Org);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[.='"+Org+"']")).click();
		
		//Switch to Parent window	
		wlib.switchNewTabOnUrl(driver, "Contacts&action");
			
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify Header message expected result 
		HeaderInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		wlib.verification(HeaderInfo,LastName);
	
		//verify Header orgName info Expected result
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		wlib.verification(actOrgName,Org);					
				
		//Step 8: Logout	
		 wlib.mouseMoveOnElement(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
         driver.findElement(By.linkText("Sign Out")).click();
		 			
		driver.quit();
		
	} 

}
