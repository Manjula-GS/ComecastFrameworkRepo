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

	public class Products {
		
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
			String Product = elib.getDataFromExcel("e2e", 4, 2);
			String Procat = elib.getDataFromExcel("e2e", 13, 4);
			String Acc=elib.getDataFromExcel("e2e", 13, 5);
			
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
			driver.findElement(By.linkText("Vendors")).click();;
			driver.findElement(By.xpath("//img[@title='Create Vendor...']")).click();
			driver.findElement(By.name("vendorname")).sendKeys(Vendor);	
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			String actVendor = driver.findElement(By.id("dtlview_Vendor Name")).getText();
			wlib.verification(actVendor,Vendor);
		
			driver.findElement(By.linkText("Products")).click();
			driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
			driver.findElement(By.name("productname")).sendKeys(Product);
			
			Select sel=new Select(driver.findElement(By.name("productcategory")));
			sel.selectByVisibleText(Procat);
			
			String StartDate = jlib.getSystemDateYYYYMMDD();
			String EndDate = jlib.getRequiredDateYYYYMMDD(30);
			//driver.findElement(By.name("sales_start_date")).clear();
			driver.findElement(By.name("sales_start_date")).sendKeys(StartDate);
			
			//driver.findElement(By.name("sales_end_date")).clear();
			driver.findElement(By.name("sales_end_date")).sendKeys(EndDate);

			driver.findElement(By.xpath("//input[@name='vendor_name']/following-sibling::img")).click();
			
			wlib.switchNewTabOnUrl(driver, "module=Vendors");
			
			driver.findElement(By.name("search_text")).sendKeys(Vendor);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[.='"+Vendor+"']")).click();
			
			wlib.switchNewTabOnUrl(driver, "Products&action");

			
			Select sel1=new Select(driver.findElement(By.name("glacct")));
			sel1.selectByVisibleText(Acc);
			
			String StartDate1 = jlib.getSystemDateYYYYMMDD();
			String EndDate1 = jlib.getRequiredDateYYYYMMDD(30);
			//driver.findElement(By.id("jscal_field_start_date")).clear();
			driver.findElement(By.id("jscal_field_start_date")).sendKeys(StartDate1);
			
			//driver.findElement(By.id("sales_end_date")).clear();
			driver.findElement(By.id("jscal_field_expiry_date")).sendKeys(EndDate1);
			
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			//Verify the Industries and type 
		
			String actprodCategory = driver.findElement(By.id("dtlview_Product Category")).getText();
			wlib.verification(actprodCategory,Procat);
			
				
			//Step 5: Logout	
			wlib.mouseMoveOnElement(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
			driver.findElement(By.linkText("Sign Out")).click();
			 			
			driver.quit();
		}
	}



