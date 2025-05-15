package assignmentSysTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objrepo.utility.HomePage;
import com.comcast.crm.objrepo.utility.LoginPage;

import assignment.CampaignInfoEdtitingPage;
import assignment.CampaignsInfoPage;
import assignment.CampaignsPage;
import assignment.CreatingNewCampaignsPage;

public class CreateCompains {
	@Test
	public void run() throws IOException
	{
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility j = new JavaUtility();
		WebDriverUtility wd = new WebDriverUtility();
		// read common data from property file
		String browser = flib.getDataFromPropertiesFile("browser");
		String url = flib.getDataFromPropertiesFile("url");
		String username = flib.getDataFromPropertiesFile("username");
		String password = flib.getDataFromPropertiesFile("password");
				
		// read test script data from excel file
		String campaignsName = elib.getDataFromExcel("org", 10, 2)+j.getRandomNumber();
		String newcampaignsName = elib.getDataFromExcel("org", 10, 2)+j.getRandomNumber();
			
		WebDriver driver = null;
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();

		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();

		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		
		//Step 1: login to app
		driver.get(url);
		wd.waitForPageToLoad(driver);
		wd.maximizeWindow(driver);
		
		LoginPage lp = new LoginPage(driver);
		lp.logintoApp(username, password);
		HomePage h = new HomePage(driver);
		
		h.navigateToCampaignPage();
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCreateNewCampaignBtn().click();
		
		CreatingNewCampaignsPage cncp = new CreatingNewCampaignsPage(driver);
		cncp.CreateCampaign(campaignsName);
		
		CampaignsInfoPage cip = new CampaignsInfoPage(driver);
		
		String actHeaderInfo = cip.getHeadertext().getText();
		if (actHeaderInfo.contains(campaignsName)) {
			System.out.println(campaignsName + " is verified");
		} else {
			System.out.println(campaignsName + " is not verified");
		}
		
		//edit campaigns name and date
		cip.getEditBtn().click();
		CampaignInfoEdtitingPage cep = new CampaignInfoEdtitingPage(driver);
		cep.getCampaignnameEdt().clear();
		cep.getCampaignnameEdt().sendKeys(newcampaignsName);
		
		cep.getClosingdateEdt().clear();
		String closingDate = j.getRequiredDateYYYYMMDD(30);
		cep.getClosingdateEdt().sendKeys(closingDate);
		cep.getSaveBtn().click();
		
		//verify edited info and closing date
		String editedinfo = cip.getHeadertext().getText();
		if (editedinfo.contains(newcampaignsName)) {
			System.out.println(newcampaignsName + " is edited successfully");
		} else {
			System.out.println(newcampaignsName + " is not edited");
		}
		
		String actEndDate = cip.getendDatetxt().getText();
		System.out.println(actEndDate);
		if (actEndDate.trim().equals(closingDate)) {
			System.out.println(closingDate + " is verified");
		} else {
			System.out.println(closingDate + " is not verified");
		}
		driver.quit();
	}
}
