package Selenium.ho.framework;


import java.io.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.*;
import io.qameta.allure.*;

import PageFactory.HomePage;
import dataManager.ReadExcelDataFile;
import BaseClass.BaseTest;



//@Listeners(Listener.ListenerTest.class)
@Listeners({ListenersF.AllureListener.class})
public class HomePageTest extends BaseTest {
	
	public static final Logger log = Logger.getLogger(HomePageTest.class.getName());
	ReadExcelDataFile readData = new ReadExcelDataFile(System.getProperty("user.dir") + "/src/main/java/dataManager/loginData-2.xlsx");
	
	
	HomePage homepage;
    int i;
    String email_id="";
    String password="";
    
	@BeforeTest
	public void settingUp() throws Exception {
		
		init(); //Browser and URL setup   
		ByteArrayInputStream fileAsByte = new ByteArrayInputStream(FileUtils.readFileToByteArray(new File("ScreenShots/2022_07_15_05_00_21verifyPageURL.png")));
		Allure.addAttachment("SS", fileAsByte);
		homepage = new HomePage(driver);
		
	}
	
	
	
	@Test(priority=1, description="verify the page title")
	@Severity(SeverityLevel.NORMAL)
	@Description("Here we are verifyting the page title")
	public void verifyPageTitle() throws Exception {
		
		String title=homepage.getLogInPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, HomePage.title);
		ByteArrayInputStream fileAsByte = new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(saveScreenShot("Verify page Title", driver))));
		Allure.addAttachment("Verify Page Title", fileAsByte);
		
	}
	
	

	@Test(priority=2, description="verify the page URL")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Here we are verifyting the page URL")
	public void verifyPageURL() throws Exception {
		
		String url=homepage.getPageUrl();
		System.out.println(url);
		Assert.assertEquals(url, HomePage.link);
		ByteArrayInputStream fileAsByte = new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(saveScreenShot("Verify page URL", driver))));
		Allure.addAttachment("page URL", fileAsByte);
		
	}
	
	
	
	
	@Test(priority=3, description="Verify the Login page with Valid credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Here we are verifyting the Log in page functionality")
	public void loginTest1_RightPassword() throws Exception {
	    
	
	 
		
		    
			email_id=readData.getCellData("login", 1, 2);
			log.info(email_id);
			password=readData.getCellData("login", 2, 2);
			log.info(password);
				
			String text =homepage.LogIn(email_id, password);
			log.info("Email id and Password is entered");
			
			Assert.assertEquals("Success", text);
		    ByteArrayInputStream fileAsByte = new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(saveScreenShot("Verify LogIn Test", driver))));
			Allure.addAttachment("LogIn Test", fileAsByte);
		    
	} 

	@Attachment
	 public void takeScreenshot(WebDriver webDriver) throws IOException {
		  File screenshotAs = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		  Allure.addAttachment("Screenshot", FileUtils.openInputStream(screenshotAs));
		 }

    



	@AfterTest
    public void endTest() {
    	driver.close();	
    }
}

