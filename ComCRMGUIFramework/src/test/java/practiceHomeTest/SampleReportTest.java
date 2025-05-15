package practiceHomeTest;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseGenerics.BaseClass;

public class SampleReportTest extends BaseClass{
	
	public ExtentSparkReporter spark;
	public static ExtentReports report;
	public ExtentTest test;
	
	@BeforeSuite
		public void configBS()
		{
			//Spark Report Configuration
			spark=new ExtentSparkReporter("./AdvanceReport/report.html");
			spark.config().setDocumentTitle("CRM Test Si=uite Results");
			spark.config().setReportName("CRM Report");
			spark.config().setTheme(Theme.DARK);
			
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
		
		ExtentTest test = report.createTest("createContactTest");		
		
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "Navigate to contact page");
		test.log(Status.INFO, "create contact ");
		if ("HDFC".equals("HDFC")) {
			test.log(Status.PASS,"contact is created");
		}
		else {
			test.log(Status.FAIL,"contact is not created");
		}
	}
	@Test
	public void createContactWithOrg()
	{
		
		ExtentTest test = report.createTest("createContactWithOrg");		
		
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "Navigate to contact page");
		test.log(Status.INFO, "create contact ");
		if ("HDFC".equals("HDFC")) {
			test.log(Status.PASS,"contact is created");
		}
		else {
			test.log(Status.FAIL,"contact is not created");
		}
	}
	@Test
	public void createContactWithPhNo()
	{
		
		ExtentTest test = report.createTest("createContactWithPhNo");		
		
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "Navigate to contact page");
		test.log(Status.INFO, "create contact ");
		if ("HDFC".equals("HDFC")) {
			test.log(Status.PASS,"contact is created");
		}
		else {
			test.log(Status.FAIL,"contact is not created");
		}
	}

}
