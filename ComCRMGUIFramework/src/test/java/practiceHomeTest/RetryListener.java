package practiceHomeTest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryListener {
	@Test(retryAnalyzer = com.comcast.crm.listnerUtility.RetryListenerImp.class)
	public void activateSim()
	{
		System.out.println("execute createInvoiceTest");
		Assert.assertEquals("", "Login");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}

}
