package BaseClass;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import dataManager.ConfigReader;
import io.qameta.allure.Allure;



public class BaseTest {
	
		public static final Logger log = Logger.getLogger(BaseTest.class.getName());
		public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
		
		public WebDriver driver;
		String baseURL=ConfigReader.getValueFromPropertyFile("Url");
		String browser_type=ConfigReader.getValueFromPropertyFile("Browser");;

		
public WebDriver init() {
	
			selectBrowser(browser_type);
			getUrl(baseURL);
		    String log4jConfPath = "log4j.properties";
			PropertyConfigurator.configure(log4jConfPath);
			tdriver.set(driver);
			return getDriver();
			
}
		
public static synchronized WebDriver getDriver() {
			return tdriver.get();
}


public void selectBrowser(String browser_type) {
	
		if (browser_type.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "/Volumes/AT/chromedriver");
			driver=new ChromeDriver();
	     }
		
		else if (browser_type.equalsIgnoreCase("Firefox")){
			System.setProperty("webdriver.mozilla.driver", "/Volumes/AT/geckodriver");
			driver=new FirefoxDriver();
		}
		
		else {
			log.info("There is no match for the browser type");
		}
			
}
		
public void getUrl(String url) {
	
			log.info("Navigating to:"+url);
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}
		
		
		
public String saveScreenShot(String testCaseName, WebDriver driver) throws IOException {
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
	        SimpleDateFormat s=new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
			String d = s.format(new Date());
			String destinationFile =System.getProperty("usr.dir") + "ScreenShots/" +d+ testCaseName + ".png" ;
			FileUtils.copyFile(source, new File(destinationFile));
			return destinationFile;
		
}



		
		
		
}



