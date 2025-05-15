package extra4E2EScripts;

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

public class Comments {
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
		String Leads = elib.getDataFromExcel("org", 1, 2)+jlib.getRandomNumber();
		String company = elib.getDataFromExcel("e2e", 1, 4);
		String Comment = elib.getDataFromExcel("e2e", 4, 3);
		
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
		
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(Leads);
		driver.findElement(By.name("company")).sendKeys(company);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();		
		
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("More")).click();
		driver.findElement(By.linkText("Comments")).click();
		driver.findElement(By.xpath("//img[@title='Create Comment...']")).click();
		driver.findElement(By.name("commentcontent")).sendKeys(Comment);

		driver.findElement(By.xpath("//input[@name='related_to_display']/following-sibling::img")).click();
		
		wlib.switchNewTabOnUrl(driver, "module=Leads");
		
		driver.findElement(By.name("search_text")).sendKeys(Leads);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[.='"+Leads+"']")).click();
		
		wlib.switchNewTabOnUrl(driver, "module=ModComments&action");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
		//Step 5: Logout	
		wlib.mouseMoveOnElement(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		driver.findElement(By.linkText("Sign Out")).click();
		 			
		driver.quit();
	}
}
