package com.comcast.crm.listnerUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

import baseGenerics.BaseClass;

public class ListnerImpClass implements ITestListener, ISuiteListener {

	public ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;

	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");

		// Spark Report Configuration
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Si=uite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// Add Environment information and create test

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("Browser", "Chrome");
	}

	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println("==========>" + result.getMethod().getMethodName() + "<======Start=======");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"====>STARTED<====");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("==========>" + result.getMethod().getMethodName() + "<=====End========");
		test.log(Status.PASS, result.getMethod().getMethodName()+"====>COMPLETED<====");
	}

	public void onTestFailure(ITestResult result) {

		String testName = result.getMethod().getMethodName();
		TakesScreenshot TSS = (TakesScreenshot) BaseClass.sdriver;
		String filepath = TSS.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filepath, testName + "_" + time);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"====>FAILED<====");
	}

	public void onTestSkipped(ITestResult result) {
	}

	public void onFailedButWithinSuccessPercentage(ITestResult result) {

	}
	public void onStart(ITestContext context) {
		
	}
	public void onFinish(ITestContext context) {
		
	}

}
