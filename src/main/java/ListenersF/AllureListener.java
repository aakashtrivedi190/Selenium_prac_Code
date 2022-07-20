package ListenersF;



import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import BaseClass.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import java.io.ByteArrayInputStream;
import java.io.File;

import org.apache.commons.io.FileUtils;


public class AllureListener extends BaseTest implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	 
	
	
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	
		
	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println("I am in onStart method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", BaseTest.getDriver());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am in onFinish method " + iTestContext.getName());
	
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
	}
    
	@Override
	public void onTestSuccess(ITestResult iTestResult)  {
		System.out.println("I am in onTestSucccess method " + getTestMethodName(iTestResult) + " passed");
		WebDriver driver = BaseTest.getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));

			try {
				ByteArrayInputStream fileAsByte = new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(saveScreenShot(getTestMethodName(iTestResult), driver))));
				Allure.addAttachment(getTestMethodName(iTestResult), fileAsByte);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		saveTextLog(getTestMethodName(iTestResult) + " passed and screenshot taken!");	
	}

	



	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
			
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
		
		
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");	
	}

	
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}
	

}