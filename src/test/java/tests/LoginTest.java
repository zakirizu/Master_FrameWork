package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import base.CreateDriverObject;
import utils.PropertyFileReader;

public class LoginTest {

	static WebDriver driver ;
	
	@BeforeMethod
	public void launchBrowser() throws IOException {
		driver = CreateDriverObject.getDriver();
		driver.get(PropertyFileReader.getPropertyValue("url"));
	
		
		
		
	}
	
	
	
	
	
}
