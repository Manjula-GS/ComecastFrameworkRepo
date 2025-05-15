package practiceHomeTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class AttachScreenShotInExtentReport {
	
	public ExtentSparkReporter spark;
	public static ExtentReports report;
	public ExtentTest test;
	
	@BeforeSuite
		public void configBS()
		{
			//Spark Report Configuration
			spark=new ExtentSparkReporter("./AdvanceReport/report.html");
			spark.config().setDocumentTitle("CRM Test Suite Results");
			spark.config().setReportName("CRM Report");
			spark.config().setTheme(Theme.STANDARD );
			
			//Add Environment information and create test
			
			report=new ExtentReports();
			report.attachReporter(spark);
			report.setSystemInfo("OS", "Windows-11");
			report.setSystemInfo("Browser", "Chrome");				
		}
	@AfterSuite
	public void configAS() {
		report.flush();		
	}
	
	
	@Test
	public void createContactTest()
	{
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888/");
		
		TakesScreenshot TSS=(TakesScreenshot)driver;
		String filepath = TSS.getScreenshotAs(OutputType.BASE64);
		
		ExtentTest test = report.createTest("createContactTest");		
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "Navigate to contact page");
		test.log(Status.INFO, "create contact ");
		if ("HDFCc".equals("HDFC")) {
			test.log(Status.PASS,"contact is created");
		}
		else {
			test.addScreenCaptureFromBase64String(filepath, "ErrorFile");
		}
		driver.close();
		
	}
}
