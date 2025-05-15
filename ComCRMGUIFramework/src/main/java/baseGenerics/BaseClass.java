package baseGenerics;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objrepo.utility.HomePage;
import com.comcast.crm.objrepo.utility.LoginPage;
public class BaseClass {
	public DataBaseUtility dblib=new DataBaseUtility();
	public FileUtility flib=new FileUtility();
	public ExcelUtility elib=new ExcelUtility();
	public JavaUtility jlib=new JavaUtility();
	public WebDriverUtility wlib=new WebDriverUtility();
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	
	@BeforeSuite(groups= {"SmokeTest", "reggression test"})
	public void configBS() throws SQLException
	{
		System.out.println("===Connect to DB, Report Config===");
		dblib.getDBConnection();	
	}
	
	//@Parameters("Browser")
	
	@BeforeClass(groups= {"SmokeTest","regressionTest"})
	public void configBC() throws IOException
	{
		System.out.println("==Launch the Browser==");
		//String Browser=browser;
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		
		if (BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new EdgeDriver();
		}
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
	}
	
	@BeforeMethod
	public void configBM() throws IOException
	{
		System.out.println("=Login=");
		String Url=flib.getDataFromPropertiesFile("url");
		String Username=flib.getDataFromPropertiesFile("usn");
		String Password=flib.getDataFromPropertiesFile("psw");
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(Url, Username, Password);
	}
	
	@AfterMethod
	public void configAM()
	{
		System.out.println("=Logout=");
		HomePage hp=new HomePage(driver);
		hp.logout();
				
	}
	@AfterClass
	public void configAC()
	{
		System.out.println("==Close the Browser==");
		driver.quit();
	}
	
	@AfterSuite
	public void configAS() throws SQLException
	{
		System.out.println("===Close DB, Report Backup===");
		dblib.closeDBConnection();
	}

}
