package pOM;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objrepo.utility.CreateNewOrganizationPage;
import com.comcast.crm.objrepo.utility.HomePage;
import com.comcast.crm.objrepo.utility.LoginPage;
import com.comcast.crm.objrepo.utility.OrganizationInfoPage;
import com.comcast.crm.objrepo.utility.OrganizationPage;

public class DeleteOrgTest {
	
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
		String Org = elib.getDataFromExcel("org", 10, 2)+jlib.getRandomNumber();
				
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
		
		LoginPage lp=new LoginPage(driver);
		lp.logintoApp(Username, Password);
				
		//Step 2: Navigation to Organization Module
		HomePage hp=new HomePage(driver);
		hp.getOrglink().click();
				
		//Step 3: Click on "Create Organization" Button
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
				
		//Step 4: Enter all the details and create New Organization
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createOrg(Org);
		
		//Verify Header message expected result 
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actOrg = oip.getHeaderMsg().getText();
		Assert.assertEquals(Org, actOrg);
		
		/*
		 * //go back to organization page hp.getOrglink().click();
		 * 
		 * //search for organization op.getSeachTextFld().sendKeys(Org);
		 * wlib.select(op.getSearchDropDown(), "Organization Name");
		 * op.getSearchBtn().click();
		 * 
		 * //In dynamic web table select delete Org
		 * driver.findElement(By.xpath("//a[.='"+Org+"']/../../td[8]/a[.='del']")).click
		 * ();
		 * 
		 * //logout hp.logout();
		 */
				 			
		driver.quit();
		
	}


}
