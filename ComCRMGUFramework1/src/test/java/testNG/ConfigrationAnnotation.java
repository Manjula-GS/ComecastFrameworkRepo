package testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ConfigrationAnnotation 
{	
	@BeforeSuite
	public void BS()
	{
		System.out.println("Execute Before Suite");
	}
	@BeforeClass
	public void BC()
	{
		System.out.println("Execute Before Class");
	}
	@BeforeMethod
	public void BM()
	{
		System.out.println("Execute Before Method");
	}
	@Test
	public void createContact() 
	{
		System.out.println("Execute Create Contact");
	}
	@Test
	public void createContactWithPhNo() 
	{
		System.out.println("Execute Create Contact With Phone Number");
	}
	
	@AfterMethod
	public void AM()
	{
		System.out.println("Execute After Method");
	}
	@AfterClass
	public void AC()
	{
		System.out.println("Execute After Class");
	}
	@AfterSuite
	public void AS()
	{
		System.out.println("Execute After Suite");
	}
	
}
