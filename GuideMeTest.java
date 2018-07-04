
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.util.*;

//**Logs a user into GuideMe extension in Chrome.**
//
//**Download Chrome's GuideMe extension .crx file and place it in C:\WebDrivers
//Arguments:
//	1.Username/Email
//  2.Password

public class GuideMeTest{
	public static void waitForLoad(RemoteWebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(pageLoadCondition);
	}
	
	//Wait for xpath 
	public static WebElement xpathWait(RemoteWebDriver driver, String xpath) {
		waitForLoad(driver);
		WebDriverWait wait = new WebDriverWait(driver,120);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		return element;
	}
	
	 public static RemoteWebDriver setBrowserDriver(ChromeOptions options) {
			System.out.println("Enter 1 for chrome,  2 for Edge, 3 for firefox, 4 for Safari");
			boolean isMac = false;
			  if(!System.getenv("PATH").contains("\\")) {
				  isMac = true;
				  System.out.println("This is a mac operating systems");
			  }
			  else {
				  System.out.println("This is a windows operating systems");
			  }
			  
			Scanner scan = new Scanner(System.in);
			int browser = scan.nextInt();	
			scan.close();
			
			switch (browser) {
	    	case 1 :
	    			if(isMac) {
	    				 System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
	    			}
	    			else {
	    			  System.setProperty("webdriver.chrome.driver", "C:/WebDrivers/chromedriver.exe"); //for windows jar find the chromedriver  
	    			}
	    			RemoteWebDriver driver = new ChromeDriver(options); 
	        		 return driver;
	    	case 2:
	    		if(isMac) {
					 System.setProperty("webdriver.edge.driver", "/usr/local/bin/MicrosoftWebDriver"); 
				}
				else {
					System.setProperty("webdriver.edge.driver",  "C:/WebDrivers/MicrosoftWebDriver.exe"); 
				}
	    		driver = new EdgeDriver();
	   		 	return driver;
	    	case 3:
	    		if(isMac) {
					 System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
				}
				else {
					System.setProperty("webdriver.gecko.driver",  "C:/WebDrivers/geckodriver.exe"); 
				}
	    		driver = new FirefoxDriver();
	    		return driver;
	    	case 4:
	    		if(isMac) {
					 System.setProperty("webdriver.safari.driver", "/usr/local/bin/safaridriver");
					 driver = new SafariDriver();
					 return driver;
				}
	    		System.out.println("There is no Safari available on windows");
	    		break;
	    	case 5:
	    		System.out.println("There is no InternetExplorer option available ");
	    		break;
			}
			return null;
		}
	 
	 public static void main(String[] args) throws InterruptedException, IOException {
		 
		 ChromeOptions options = new ChromeOptions();
		 
		 //**Download GuideMe Chrome extension and place in C:/WebDrivers
		 options.addExtensions(new File("C:/WebDrivers/EdCast-GuideMe_v4.8.13.crx"));
		
		 RemoteWebDriver driver = setBrowserDriver(options);
		 driver.get("https://www.guideme.io/");
		 
		 String guideMe = "//*[@id='guideme-launch-button']";
		 String enterEmail = "//input[@name='email']";
		 String enterPassword ="//input[@name='password']";
		 String login = "//button[@ng-click='login()']";
				 
		 WebElement element = xpathWait(driver,guideMe);
		 element.click();
		 driver.switchTo().frame("guideme-app");
		 element = xpathWait(driver,enterEmail);
		 element.click();
		 element.sendKeys(args[0]);
		 element = xpathWait(driver,enterPassword);
		 element.click();
		 element.sendKeys(args[1]);
		 element = xpathWait(driver,login);
		 element.click();
		 Thread.sleep(10000);
	     driver.quit();
	 }
}


