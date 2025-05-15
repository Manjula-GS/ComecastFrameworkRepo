package testNG;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class AmazonDataProvider {
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String BrandName, String ProductName) 
	{		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in");
		//driver.navigate().refresh();
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(BrandName,Keys.ENTER);
		
		//capture product info				
		String x="//span[.='"+ProductName+"']/../../../../div[3]/div[1]/div/div[1]/div[2]/div[1]/a/span/span[2]/span[2]";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println(price);	
		driver.quit();
	}
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		ExcelUtility elib=new ExcelUtility();
		int rcount = elib.getRowCount("product");
		Object[][] obj=new Object[rcount][2];
		
		for (int i=0; i<rcount; i++)
		{
		obj[i][0]=elib.getDataFromExcel("product", i+1, 0);
		obj[i][1]=elib.getDataFromExcel("product", i+1, 1);
		}
		return obj;
	}

}
