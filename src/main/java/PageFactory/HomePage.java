package PageFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

public class HomePage {
	

		public static final Logger log = Logger.getLogger(HomePage.class.getName());
		
		public static String link="https://practicetestautomation.com/practice-test-login/";	
		public static String title="Test Login | Practice Test Automation";
				
		   WebDriver driver;
			  

		public HomePage(WebDriver driver2) {
			// TODO Auto-generated constructor stub
				this.driver =driver2;	
				PageFactory.initElements(driver, this);
		}

		
		
			
	    @FindBy(id="username")
		WebElement email;
	    
	    public  WebElement emailId() {
			return email;
		}
		
		@FindBy(id="password")
		WebElement password;
		
		public WebElement password() {
			return password;
		}
		
		@FindBy(xpath="//input[@id='continue']")
		WebElement contBtn;
		
		public WebElement continu() {
			return contBtn;
		}
		
		
		@FindBy(xpath="//span[text()='Hello, Sign in']")
		WebElement si;
		
		public WebElement openSignInPage() {
			
			return si;
		}
		
		@FindBy(id="submit")
		WebElement sBtn;
		
		public WebElement Submit() {
			return sBtn;
		}
		
		@Step("Enter the {0} and {1} after that click on Submit button")
		public String LogIn(String email_id, String Password) {
			email.sendKeys(email_id);
			password.sendKeys(Password);
			sBtn.click();
			return "Success";
		}
		
		
		@Step("getting the title of the page")
		public String getLogInPageTitle() {
			return driver.getTitle();
		}
		
		@Step("Verify the URL")
		public String getPageUrl() {
			return driver.getCurrentUrl();
		}
		
		
	}

