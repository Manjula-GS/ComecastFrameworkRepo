package com.comcast.crm.orgtest;

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

public class CreateOrgWithPhoneNo {
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
		String Org = elib.getDataFromExcel("org", 7, 2)+jlib.getRandomNumber();
		String PhNo = elib.getDataFromExcel("org", 7, 3);
				
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
		driver.findElement(By.id("phone")).sendKeys(PhNo);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify Phone number info expected result 
		
		String actPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
		wlib.verification(actPhoneNumber, PhNo);
				
		//Step 5: Logout
	
		wlib.mouseMoveOnElement(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
        driver.findElement(By.linkText("Sign Out")).click();
		 			
		driver.quit();
		
	}

}
