package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class keyWords {
	WebDriver driver;
	
	public keyWords(WebDriver driver) {		
		this.driver = driver;
	}
	
	public void enterValues(WebElement ele, String value) {
		ele.sendKeys(value);		
	}
	
	

}
