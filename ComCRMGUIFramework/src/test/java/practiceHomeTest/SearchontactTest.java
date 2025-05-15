package practiceHomeTest;
/**
 * test class for contact module
 * @author MANJULA G S
 * 
 */
import org.testng.annotations.Test;

import com.comcast.crm.objrepo.utility.LoginPage;

import baseGenerics.BaseClass;

public class SearchontactTest extends BaseClass{
	/**
	 * Scenario : login===>navigateContact===>craeteContact===>verify
	 */
	@Test
	public void searchContactTest()
	{
		/*step1: login to App*/
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
	}
}
