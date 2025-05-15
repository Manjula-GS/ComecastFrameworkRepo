package createContactAndOrg;

/**
 * @author MANJULA G S
 */

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.objrepo.utility.ContactInfoPage;
import com.comcast.crm.objrepo.utility.ContactPage;
import com.comcast.crm.objrepo.utility.CreateNewOrganizationPage;
import com.comcast.crm.objrepo.utility.CreatingNewContactPage;
import com.comcast.crm.objrepo.utility.HomePage;
import com.comcast.crm.objrepo.utility.OrganizationPage;

import baseGenerics.BaseClass;

public class CreateContactTest extends BaseClass {
	@Test(groups="smoke test")
	public void createOrg() throws EncryptedDocumentException, IOException {
		// read test script data from excel file
		String LastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		// Step 2: Navigation to Contact Module
		HomePage hp=new HomePage(driver);
		hp.getContactlink().click();

		// Step 3: Click on "Create Contact" Button
		ContactPage cp=new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// Step 4: Enter all the details and create New Contact
		CreatingNewContactPage CNCP=new CreatingNewContactPage(driver);
		CNCP.createContact(LastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify Phone number info expected result
		String actHeader=cp.getHeaderMsg().getText();
		boolean status = actHeader.contains(LastName);
		Assert.assertEquals(status, true);

		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actLastName, LastName);
		soft.assertAll();
	}

	@Test(groups="regression Test")
	public void CreateContactWithSupportDate() throws EncryptedDocumentException, IOException {

		// read test script data from excel file
		String LastName = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();

		// Step 2: Navigation to contact Module
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();

		// Step 3: Click on "Create contact" Button
		ContactPage cp=new ContactPage(driver);
		cp.getCreateNewContactBtn().click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step 4: Enter all the details and create New Organization
		String StartDate = jlib.getSystemDateYYYYMMDD();
		String EndDate = jlib.getRequiredDateYYYYMMDD(30);
		
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		cncp.createContactwithSupporteDate(LastName, StartDate, EndDate);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify Date info expected result
		ContactInfoPage cip=new ContactInfoPage(driver);
		String actStartDate = cip.getStartDatetext().getText();
		Assert.assertEquals(actStartDate.trim(), EndDate);

		String actEndDate = cip.getEndDatetext().getText();
		Assert.assertEquals(actEndDate.trim(), EndDate);
	}

	@Test(groups="regression test")
	public void CreateContactWithOrg() throws EncryptedDocumentException, IOException {
		// read test script data from excel file
		String Org = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String LastName = elib.getDataFromExcel("contact", 7, 3) + jlib.getRandomNumber();

		// Step 2: Navigation to Organization Module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// Step 3: Click on "Create Organization" Button
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// Step 4: Enter all the details and create New Organization
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrg(Org);

		// Verify Header message expected result
		String HeaderInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		wlib.verification(HeaderInfo, Org);

		// Step 5: Navigate to contact module
		hp.getContactlink().click();

		// Step 6: Click on "Create Contact" Button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step 7: Enter all the details and create New Contact
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// Switch to Child window
		wlib.switchNewTabOnUrl(driver, "module=Accounts");

		driver.findElement(By.name("search_text")).sendKeys(Org);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[.='" + Org + "']")).click();

		// Switch to Parent window
		wlib.switchNewTabOnUrl(driver, "Contacts&action");

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify Header message expected result
		HeaderInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		wlib.verification(HeaderInfo, LastName);

		// verify Header orgName info Expected result
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		wlib.verification(actOrgName, Org);

		// Step 8: Logout
		wlib.mouseMoveOnElement(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		driver.findElement(By.linkText("Sign Out")).click();
	}
}
