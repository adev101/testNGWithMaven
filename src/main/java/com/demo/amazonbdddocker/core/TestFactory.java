package com.demo.amazonbdddocker.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.demo.amazonbdddocker.utilities.TestCapture;
import com.demo.amazonbdddocker.utilities.TestConfig;

public class TestFactory {
	static String path= null, mode=null;
	public static WebDriver driver;
	static String url= null, urlA=null;
	static String currURL=null;
	static HttpURLConnection huc = null;
    static int respCode = 200;
    static int brokenUrlCount=0;
	
	public static void getURL(String browser) throws IOException {
		
		mode=TestConfig.getConfigDetails().get("mode");
		
		if(mode.contentEquals("local")) {
			
			path=System.getProperty("user.dir");
			
			switch (browser)
			{
			case "chrome": 
				System.setProperty("webdriver.chrome.driver", path+"//src//main//resources//drivers//chromedriver.exe");
				driver=new ChromeDriver();
				break;
			case "IE": 
				System.setProperty("webdriver.edge.driver", path+"//src//main//resources//drivers//IEDriverServer.exe");
				driver= new InternetExplorerDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", path+"//src//main//resources//drivers//geckodriver.exe");
				driver= new FirefoxDriver();
				break;
			default:
				System.out.println("Wrong Browser");
				break;
			}
		}
		else if (mode.contentEquals("remote")) {
			DesiredCapabilities dr=DesiredCapabilities.chrome();
			dr.setBrowserName(browser);
			dr.setPlatform(Platform.LINUX);
			dr.setVersion("83.0.4103.61");
			
			String hub=TestConfig.getConfigDetails().get("hub");
			
			System.setProperty("webdriver.chrome.driver", path+"//src//main//resources//drivers//chromedriver.exe");
			driver=new RemoteWebDriver(new URL(hub),dr);
			
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		url=TestConfig.getConfigDetails().get("baseURL");
		driver.get(url);
		driver.manage().window().maximize();
		
		
		
	}
	
	
	public static void webElementClick(WebElement element) throws TestException {
		if (!element.isEnabled()) {
			throw new TestException(element.getText()+ " is not clicked as it is disabled");
		}
		
		try {
			element.click();
		} catch (Exception e) {
			TestCapture.captureScreenshot(driver, element.getText());
			e.printStackTrace();
		}	
	}
	
	public static void clickElementWithWait (WebElement element) {
		//WebDriverWait wait = new WebDriverWait (driver, 15);
		try {
			//wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void elementSendKeys(WebElement element, String text) throws TestException {
		if (!element.isEnabled()) {
			throw new TestException(element.getText()+ " is disabled");	
		}
		
		try {
			element.sendKeys(text);
		} catch (Exception e) {
			TestCapture.captureScreenshot(driver, element.getText());
		}	
	}
	
	public static void elementSendKeys(WebElement element, Keys key) throws TestException {
		if (!element.isEnabled()) {
			throw new TestException(element.getText()+ " is disabled");	
		}
		
		try {
			element.sendKeys(key);
		} catch (Exception e) {
			TestCapture.captureScreenshot(driver, element.getText());
		}	
	}
	
	public static String searchTextOnPage(String text) {
		if(driver.getPageSource().contains(text)) {
		    System.out.println("Login Successful");
		    return "Login Success";
		}
		else {
			System.out.println("Login not successful");
			return "Login Not Success";
		}
	}
	
	public static String checkURL() {
		currURL= driver.getCurrentUrl();	
		return currURL;
	}
	
	public static void navigateToHome() {
		driver.get(url);
	}
	
	public static void hoverOnElement(WebElement element) {
		try {
			Actions act= new Actions(driver);
			act.moveToElement(element).build().perform();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String checkElementText(WebElement element) {
		String text=null;
		
			try {
				text=element.getText();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return text;
	}
	
	
	
	
	public static void checkBrokenLinks(List<WebElement> element) {
		
		try {
			Iterator<WebElement> it= element.iterator();
			while(it.hasNext()){
			    
			    urlA = it.next().getAttribute("href");
			    
			    //System.out.println(urlA);
			
			    if(urlA == null || urlA.isEmpty() || urlA.startsWith("javascript")){
			    	//System.out.println("URL is either not configured for anchor tag or it is empty");
			        continue;
			    }
			
			    huc = (HttpURLConnection)(new URL(urlA).openConnection());
			    
			    huc.setRequestMethod("HEAD");
			    
			    huc.connect();
			    
			    respCode = huc.getResponseCode();
			    
			    if(respCode == 400 ||respCode ==401 || respCode ==500){
			    	brokenUrlCount++;
			        //System.out.println(urlA+" is a broken link");
			    }
			}	
		    System.out.println("Total Broken Links: "+ brokenUrlCount);
		    
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
