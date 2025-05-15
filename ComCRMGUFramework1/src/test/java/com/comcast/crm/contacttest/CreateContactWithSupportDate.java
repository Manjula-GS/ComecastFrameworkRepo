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

public class CreateContactWithSupportDate {
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
		String LastName = elib.getDataFromExcel("contact", 4, 2)+jlib.getRandomNumber();
		
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
		driver.findElement(By.linkText("Contacts")).click();
				
		//Step 3: Click on "Create Organization" Button				
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
		//Step 4: Enter all the details and create New Organization		
		String StartDate = jlib.getSystemDateYYYYMMDD();
		String EndDate = jlib.getRequiredDateYYYYMMDD(30);
		
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(StartDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(EndDate);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify Date info expected result 		
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		wlib.verification(actStartDate,StartDate);
			
		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		wlib.verification(actEndDate,EndDate); 
		
		//Step 5: Logout
		 wlib.mouseMoveOnElement(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
         driver.findElement(By.linkText("Sign Out")).click();
		 			
		driver.quit();
		
	}


}
