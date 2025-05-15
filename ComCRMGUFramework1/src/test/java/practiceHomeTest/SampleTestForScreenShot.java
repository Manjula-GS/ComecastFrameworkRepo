package practiceHomeTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SampleTestForScreenShot {
	@Test
	public void amazonTest() throws IOException
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.get("https://www.amazon.in");
		
		//Step-1 : create an object to EventFiring WebDriver
		 TakesScreenshot ss= (TakesScreenshot) driver;
		 
		//Step-2 : Use getScreenShotAs() to get file type of screenshot
		 File src = ss.getScreenshotAs(OutputType.FILE);
		 
		//Step-3 : store screenshot on local driver
		 FileUtils.copyFile(src, new File("./screenshot/test.png"));
		 
	}
}
