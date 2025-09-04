package tests;

import java.io.IOException;
import java.security.Key;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.CreateDriverObject;
import base.keyWords;
import pages.LoginPage;
import utils.PropertyFileReader;

public class LoginTest {

	static WebDriver driver ;
	LoginPage login;
	keyWords keys;


	
	@BeforeMethod
	public void launchBrowser() throws IOException {
		driver = CreateDriverObject.getDriver();
		driver.get(PropertyFileReader.getPropertyValue("url"));
		login = new LoginPage(driver);
		 keys = new keyWords(driver);
		///login.getLogintoApplicaiton();		
	}
	
	
	@Test
	public void MyFirstTestCase() throws InterruptedException {
		keys.enterValues(login.getUserName(), "zakir_qa");
		keys.enterValues(login.getPassWord(), "asdf@123");
		Thread.sleep(8000);
		driver.close();		
	}
	

	
	
	
	
}
