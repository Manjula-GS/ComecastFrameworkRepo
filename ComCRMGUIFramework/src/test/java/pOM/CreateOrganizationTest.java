package pOM;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objrepo.utility.CreateNewOrganizationPage;
import com.comcast.crm.objrepo.utility.HomePage;
import com.comcast.crm.objrepo.utility.OrganizationInfoPage;
import com.comcast.crm.objrepo.utility.OrganizationPage;

public class CreateOrganizationTest {
	private String orgName;

	@Test
	public void createOrg() throws IOException, InterruptedException
	{
		FileUtility flib=new FileUtility();
		WebDriverUtility wlib=new WebDriverUtility();
			
		//read common data from properties file
		String Browser=flib.getDataFromPropertiesFile("browser");
		String Url=flib.getDataFromPropertiesFile("url");
		String Username=flib.getDataFromPropertiesFile("usn");
		String Password=flib.getDataFromPropertiesFile("psw");
			
		//read test script data from excel file	
				
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
		HomePage hp=new HomePage(driver);
		hp.getOrglink().click();
		
		//Step 3: Click on "Create Organization" Button
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
		Thread.sleep(2000);
		//Step 4: Enter all the details and create New Organization
		CreateNewOrganizationPage cnop= new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName);
				
		//verify Header orgName info Expected result 
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		wlib.verification(actOrgName, orgName);
				
		//Step 5: Logout
		hp.logout();
		 			
		driver.quit();
		
	}

}
