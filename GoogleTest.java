

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

//**Logs a user into Gmail and sends a Message.**
//
//Arguments:
//	1.Google Username/Email
//  2.Password
//  3.Email of recipient
//  4.Message Subject
//  5.Message

import java.util.*;
	
	public class GoogleTest {
		public static void waitForLoad(RemoteWebDriver driver) {
	        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	            }
	        };
	        WebDriverWait wait = new WebDriverWait(driver, 120);
	        wait.until(pageLoadCondition);
	    }
		 public static WebElement explicitWaitid(RemoteWebDriver driver, String id) {
			waitForLoad(driver);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
			return element;
		}
			
		//Wait for xpath
	    public static WebElement explicitWaitxpath(RemoteWebDriver driver, String xpath) {
	    	waitForLoad(driver);
	        WebDriverWait wait = new WebDriverWait(driver, 120);
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	        return element;
	    }

	    public static RemoteWebDriver setBrowserDriver() {
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
	    			RemoteWebDriver driver = new ChromeDriver(); 
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
			        
			String signIn = "//*[@id='gb_70']";
			     
	        String passLoc = "//input[@type='password']";
	        String next = "//div[@id='identifierNext']";
	        String next2 = "//div[@id='passwordNext']";
			        //
	        String enter = "//input[@id='identifierId']";
	        String gmail = "//*[@data-pid='23']"; 
	        String compose = "//div[@class='T-I J-J5-Ji T-I-KE L3']";
	        String to = "//*[@id=':o4']";
	        String subject = "//input[@id=':nm']";
	        String message = "//div[@id=':or']";
	        String send = "//div[@class='T-I J-J5-Ji aoO T-I-atl L3']";

			RemoteWebDriver driver = setBrowserDriver();
			  
			driver.get("https://google.com");
			       
		    WebElement elm = explicitWaitxpath(driver,signIn);
		    elm.click();
		    System.out.println("waiting to enter username");
		    Thread.sleep(3000);
		    elm = explicitWaitxpath(driver,enter);
			elm.sendKeys(args[0]);
		    elm = explicitWaitxpath(driver,next);
	        elm.click();  
	        elm = explicitWaitxpath(driver,passLoc);
		    elm.sendKeys(args[1]);
		    elm = explicitWaitxpath(driver,next2);
		    elm.click();
		    elm = explicitWaitxpath(driver,gmail);
		    elm.click();
		    elm = explicitWaitxpath(driver,compose);
		    elm.click();
		    elm = explicitWaitxpath(driver,to);
		    elm.click();
			elm.sendKeys(args[2]);
			elm.sendKeys(Keys.ENTER);
		    elm = explicitWaitxpath(driver,subject);
		    elm.click();
			elm.sendKeys(args[3]);
			elm = explicitWaitxpath(driver,message);
		    elm.click();
			elm.sendKeys(args[4]);
		    elm = explicitWaitxpath(driver,send);
		    elm.click();
		    Thread.sleep(10000);
			        
			driver.quit();
			     
			 }
	}
