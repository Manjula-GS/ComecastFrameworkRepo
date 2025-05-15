package practiceHomeTest;

import java.lang.reflect.Method;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class ReporterLog {
	@Test
	public void homePageTest(Method mtd)
	{
		Reporter.log(mtd.getName()+" Test Start", true);
		Reporter.log("Step_1", true);
		Reporter.log("Step_2", true);
		Reporter.log("Step_3", true);
		Reporter.log("Step_4", true);
		Reporter.log(mtd.getName()+" Test End", true);
	}
	@Test
	public void verifyLogoHomePageTest(Method mtd)
	{
		Reporter.log(mtd.getName()+" Test Start", true);
		Reporter.log("Step_1", true);
		Reporter.log("Step_2", true);
		Reporter.log("Step_3", true);
		Reporter.log("Step_4", true);
		Reporter.log(mtd.getName()+" Test End", true);
		
	}

}
