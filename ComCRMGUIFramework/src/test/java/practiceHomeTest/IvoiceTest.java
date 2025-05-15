package practiceHomeTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import baseGenerics.BaseClass;

public class IvoiceTest extends BaseClass{
	@Test
	public void createInvoiceTest()
	{
		System.out.println("execute createInvoiceTest");
		
		System.out.println("Step-1");
		System.out.println("Step-2");
		String act = driver.getTitle();
		Assert.assertEquals(act, "Login");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
	@Test
	public void createInvoiceWithContTest()
	{
		System.out.println("execute createInvoiceWithContTest");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}

}
