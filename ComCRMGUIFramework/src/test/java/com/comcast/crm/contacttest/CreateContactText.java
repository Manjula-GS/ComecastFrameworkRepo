package com.comcast.crm.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.comcast.crm.objrepo.utility.CreateNewOrganizationPage;
import com.comcast.crm.objrepo.utility.HomePage;
import baseGenerics.BaseClass;

public class CreateContactText extends BaseClass {
	@Test
	public void createOrg() throws EncryptedDocumentException, IOException {
		// read test script data from excel file
		String LastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		// Step 2: Navigation to Contact Module
		driver.findElement(By.linkText("Contacts")).click();

		// Step 3: Click on "Create Contact" Button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step 4: Enter all the details and create New Contact
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify Phone number info expected result

		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		wlib.verification(actLastName, LastName);
	}

	@Test
	public void CreateContactWithSupportDate() throws EncryptedDocumentException, IOException {				
				
				//read test script data from excel file	
				String LastName = elib.getDataFromExcel("contact", 4, 2)+jlib.getRandomNumber();
						
				//Step 2: Navigation to Organization Module	
				HomePage hp=new HomePage(driver);
				hp.getContactlink().click();
									
				//Step 3: Click on "Create Organization" Button				
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
						
				//Step 4: Enter all the details and create New Organization	
				String StartDate = jlib.getSystemDateYYYYMMDD();
				String EndDate = jlib.getRequiredDateYYYYMMDD(30);
				
				driver.findElement(By.name("lastname")).sendKeys(LastName);
				driver.findElement(By.name("support_start_date")).clear();
				driver.findElement(By.name("support_start_date")).sendKeys(StartDate);
				
				driver.findElement(By.name("support_end_date")).clear();
				driver.findElement(By.name("support_end_date")).sendKeys(EndDate);
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//Verify Date info expected result 		
				String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
				wlib.verification(actStartDate,StartDate);
					
				String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
				wlib.verification(actEndDate,EndDate); 
	}

	@Test
	public void CreateContactWithOrg() throws EncryptedDocumentException, IOException {
		// read test script data from excel file
		String Org = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String LastName = elib.getDataFromExcel("contact", 7, 3) + jlib.getRandomNumber();

		// Step 2: Navigation to Organization Module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// Step 3: Click on "Create Organization" Button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

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
