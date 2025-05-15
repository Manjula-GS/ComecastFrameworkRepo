package com.comcast.crm.generic.webdriverutility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
//	private WebDriver driver;
//	public WebDriverUtility(WebDriver driver) 
//	{
//        this.driver = driver;
//    }
	
	// Implicit Wait
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	// Explicit Wait element present
	public void waitForElementPresent(WebDriver driver, WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));		
	}
	
	// Explicit Wait until element visibility
	public void waitForElementVisible(WebDriver driver, WebElement element, int timeout) 
	{
       WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
          wait.until(ExpectedConditions.visibilityOf(element));
    }
	
	// Explicit Wait until element to be click
	public void waitForElementClickable(WebDriver driver, WebElement element, int timeout) 
	{
       WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
          wait.until(ExpectedConditions.elementToBeClickable(element));
    }
	
	// Explicit Wait until presence of element
	public void waitForElementPresence(WebDriver driver, WebElement element, int timeout) 
	{
       WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
          wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
    }
	
	public void waitForTitleContains(WebDriver driver, String title) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.titleContains(title));
	}
	
	public void waitForURLContains(WebDriver driver, String url) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.titleContains(url));
	}
	
	// Switch to New window using Url.
	public void switchNewTabOnUrl(WebDriver driver, String partialUrl)
	{
		Set<String> whs = driver.getWindowHandles();
		Iterator<String> it = whs.iterator();
		while(it.hasNext())
		{
			String winID = it.next();
			driver.switchTo().window(winID);
		}
		String actUrl = driver.getCurrentUrl();
		if(actUrl.contains("partialUrl"))
		{
			return;
		}		
	}
	
	// Switch to New window using title.
	public void switchNewTabOnTitle(WebDriver driver, String partialTitle)
	{
		Set<String> whs = driver.getWindowHandles();
		Iterator<String> it = whs.iterator();
		while(it.hasNext())
		{
			String winID = it.next();
			driver.switchTo().window(winID);
		}
		String actUrl = driver.getTitle();
		if(actUrl.contains("partialTitle"))
		{
			return;
		}		
	}
	
	public void switchToParentWindow(WebDriver driver, String parentWindowHandle) 
	{
	    driver.switchTo().window(parentWindowHandle);
	}
	
	// Switch to Frame using index.
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);		
	}
	
	// Switch to Frame using ID.
	public void switchToFrame(WebDriver driver,String NameID)
	{
		driver.switchTo().frame(NameID);
	}
	
	// Switch to Frame element address.
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	// To Accept the Alert PopUp.
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	// To reject the Alert PopUp.
	public void switchToAlertAndDismiss(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	// To select Element By VisibleText
	public void select(WebElement element, String text)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);				
	}
	
	// To select Element By Index
	public void select(WebElement element, int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	// To Mouse hover on Element
	public void mouseMoveOnElement(WebDriver driver, WebElement element)
	{
		Actions act= new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	// To DoubleClick on Element
	public void doubleClick(WebDriver deiver, WebElement element)
	{
		Actions act=new Actions(deiver);
		act.doubleClick(element).perform();
	}
	
	// To RightClick on Element
	public void dragAndDrop(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.dragAndDrop(element, element).perform();
	}
	
	// To Click and Hold the Element
	public void clickAndHold(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.clickAndHold(element).perform();
	}
	
	// To RightClick on Element
	public void rightClick(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	
	// To Minimize
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	// To Maximize
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	// Scroll to element
	public void scrollToElement(WebDriver driver, WebElement element) 
	{
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
	
	public void scrollactions(WebDriver driver,int x, int y)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy("+x+","+y+")");
	}
	
	public void scrolldowntoaParticularElement(WebDriver driver, WebElement element)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView()",element);
	}

	public void scrollBottom(WebDriver driver)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void scrollUp(WebDriver driver)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
	}
	
	 // Take screenshot
    public void takeScreenshot(WebDriver driver, String filePath) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(filePath));
    }
    
    public void verification(String actual, String expected)
    {
    	if(actual.trim().contains(expected.trim()))
		{
			System.out.println(expected+" Information is Vrified==PASS");
		}
		else
		{
			System.out.println(expected+" Information is Not Vrified==FAIL");
		}
    }
    

}
