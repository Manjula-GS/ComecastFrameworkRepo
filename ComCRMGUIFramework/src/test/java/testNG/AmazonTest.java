package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class AmazonTest {

	@Test
	public void demo()
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in");
	}
}
