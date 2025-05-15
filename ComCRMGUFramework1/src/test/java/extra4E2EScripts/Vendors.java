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

	public class Vendors {
		
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
			String Vendor = elib.getDataFromExcel("e2e", 13, 2)+jlib.getRandomNumber();
			
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
			
			driver.manage().window().maximize();
			wlib.waitForPageToLoad(driver);
			driver.get(Url);

			driver.findElement(By.name("user_name")).sendKeys(Username);
			driver.findElement(By.name("user_password")).sendKeys(Password);
			driver.findElement(By.id("submitButton")).click();
			
			driver.findElement(By.linkText("More")).click();
			driver.findElement(By.linkText("Vendors")).click();
			driver.findElement(By.xpath("//img[@title='Create Vendor...']")).click();
			driver.findElement(By.name("vendorname")).sendKeys(Vendor);	
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			String actVendor = driver.findElement(By.id("dtlview_Vendor Name")).getText();
			wlib.verification(actVendor,Vendor);
			
			driver.findElement(By.linkText("Vendors")).click();
			driver.findElement(By.name("search_text")).sendKeys(Vendor);
			
			Select sel=new Select(driver.findElement(By.name("bas_searchfield")));
			sel.selectByVisibleText(Vendor);
			
			driver.findElement(By.name("submit")).click();
				
			//Step 5: Logout	
			wlib.mouseMoveOnElement(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
			driver.findElement(By.linkText("Sign Out")).click();
			 			
			driver.quit();
		}
	}




