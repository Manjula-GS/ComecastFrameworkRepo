package createContactAndOrg;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objrepo.utility.CreateNewOrganizationPage;
import com.comcast.crm.objrepo.utility.HomePage;
import com.comcast.crm.objrepo.utility.OrganizationInfoPage;
import com.comcast.crm.objrepo.utility.OrganizationPage;

import baseGenerics.BaseClass;
@Listeners(com.comcast.crm.listnerUtility.ListnerImpClass.class)
public class CreateOrgTest extends BaseClass{
	@Test(groups="smoke Test")
	public void createOrgTest() throws EncryptedDocumentException, IOException 
	{
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		
		//read testScript data from excel file
		String OrgName=elib.getDataFromExcel("org", 1, 2)+jlib.getRandomNumber();
		
		//Step 2: Navigate to organization module.		
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Org Page");
		HomePage hp=new HomePage(driver);
		hp.getOrglink().click();
		
		//Step 3: Click on "Create Organization" button.		
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create Org Page");
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
		
		//Step 4: enter all the details and create new organization
		UtilityClassObject.getTest().log(Status.INFO, "Create a new Org");
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createOrg(OrgName);
		
		UtilityClassObject.getTest().log(Status.INFO, OrgName+"====>Created new Org<====");
		
		//Step 5: verify Header Message Expected result.
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		Assert.assertEquals(true, actOrgName.contains(OrgName));
		
		/*
		 * //Step 6: logout hp.logout();
		 * 
		 * driver.quit();
		 */
	}
	/*
	 * @Test public void createOrgWithIndustry() {
	 * ListnerImpClass.test.log(Status.INFO, "Navigate to Org Page");
	 * System.out.println("Execute Create Org With Industry and Verify"); }
	 */
	@Test
	public void CreateOrgWithPhoneNo() throws IOException
	{				
		//read test script data from excel file	
		UtilityClassObject.getTest().log(Status.INFO, "Read Data ferom excel");
		String Org = elib.getDataFromExcel("org", 7, 2)+jlib.getRandomNumber();
		String PhNo = elib.getDataFromExcel("org", 7, 3);
				
		//Step 2: Navigation to Organization Module
		UtilityClassObject.getTest().log(Status.INFO, "Create a New Org");
		HomePage hp=new HomePage(driver);
		hp.getOrglink().click();
				
		//Step 3: Click on "Create Organization" Button
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to creater Org Page");
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
				
		//Step 4: Enter all the details and create New Organization
		UtilityClassObject.getTest().log(Status.INFO, "Create new Org");
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createOrg(Org, PhNo);
		
		//Verify Phone number info expected result 
		UtilityClassObject.getTest().log(Status.INFO, "===>Verify the phone number<===");
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		Assert.assertEquals(true, actOrgName.contains(Org));
	}
	
	@Test
	public void CreateOrgWithIndustries() throws IOException
	{
		//read test script data from excel file	
		UtilityClassObject.getTest().log(Status.INFO, "Read Data From excel");
		String Org = elib.getDataFromExcel("org", 4, 2)+jlib.getRandomNumber();
		String Industry = elib.getDataFromExcel("org", 4, 3);
		String type = elib.getDataFromExcel("org", 4, 4);
		
		//Step 2: Navigation to Organization Module			
		UtilityClassObject.getTest().log(Status.INFO, "Create new Org");
		HomePage hp=new HomePage(driver);
		hp.getOrglink().click();
				
		//Step 3: Click on "Create Organization" Button				
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
				
		//Step 4: Enter all the details and create New Organization
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		driver.findElement(By.name("accountname")).sendKeys(Org);
		Select sel=new Select(driver.findElement(By.name("industry")));
		sel.selectByVisibleText(Industry);
		Select sel2=new Select(driver.findElement(By.name("accounttype")));
		sel2.selectByVisibleText(type);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify the Industries and type 	
		String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
		wlib.verification(actIndustries,Industry);
		
		
		String actType = driver.findElement(By.id("mouseArea_Type")).getText();
		wlib.verification(actType,type);
		
				
		//Step 5: Logout	
		wlib.mouseMoveOnElement(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		 driver.findElement(By.linkText("Sign Out")).click();
	}
}
