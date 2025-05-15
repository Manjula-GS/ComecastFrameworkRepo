package practiceHomeTest;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePageSampleTest {
	@Test
	public void homePageTest(Method mtd)
	{
		System.out.println(mtd.getName()+" Test Start");
		SoftAssert saObj=new SoftAssert();
		System.out.println("Step_1");
		System.out.println("Step_2");
		Assert.assertEquals("Home", "HomePage");
		System.out.println("Step_3");
		saObj.assertEquals("Home-CRM", "Home-Cr");
		System.out.println("Step_4");
		saObj.assertAll();
		System.out.println(mtd.getName()+" Test End");
	}
	@Test
	public void verifyLogoHomePageTest(Method mtd)
	{
		System.out.println(mtd.getName()+" Test Start");
		SoftAssert saObj=new SoftAssert();
		System.out.println("Step_1");
		System.out.println("Step_2");
		saObj.assertTrue(true);
		System.out.println("Step_3");
		System.out.println("Step_4");
		saObj.assertAll();
		System.out.println(mtd.getName()+" Test End");
		
	}

}
