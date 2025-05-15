package extra4E2EScripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class Opportunities {
	@Test
	public void createOrg() throws IOException, InterruptedException
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
		String Org = elib.getDataFromExcel("org", 1, 2)+jlib.getRandomNumber();
		String Opp = elib.getDataFromExcel("e2e", 1, 2);
		String type = elib.getDataFromExcel("e2e", 1, 3);
		String leadSrc=elib.getDataFromExcel("e2e", 1, 4);
		
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
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(Org);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();		
		Thread.sleep(1000);
		driver.findElement(By.linkText("Opportunities")).click();
		driver.findElement(By.xpath("//img[@title='Create Opportunity...']")).click();
		driver.findElement(By.name("potentialname")).sendKeys(Opp);

		driver.findElement(By.xpath("//input[@name='related_to_display']/following-sibling::img")).click();
		
		wlib.switchNewTabOnUrl(driver, "module=Accounts");
		
		driver.findElement(By.name("search_text")).sendKeys(Org);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[.='"+Org+"']")).click();
		
		wlib.switchNewTabOnUrl(driver, "Potentials&action");
		
		Select sel2=new Select(driver.findElement(By.name("opportunity_type")));
		sel2.selectByVisibleText(type);
		Select sel=new Select(driver.findElement(By.name("leadsource")));
		sel.selectByVisibleText(leadSrc);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify the Industries and type 
	
		String actLeadSrc = driver.findElement(By.id("dtlview_Lead Source")).getText();
		wlib.verification(actLeadSrc,leadSrc);
		
		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		wlib.verification(actType,type);
				
		//Step 5: Logout	
		wlib.mouseMoveOnElement(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		driver.findElement(By.linkText("Sign Out")).click();
		 			
		driver.quit();
	}
}
