package base;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import utils.PropertyFileReader;


public class CreateDriverObject  {
	static Logger log = LogManager.getLogger(PropertyFileReader.class.getName());
	static WebDriver driver ;

	

	public static WebDriver getDriver() throws IOException {
	
		String browser = PropertyFileReader.getPropertyValue("browser");
		
		try {
			if(browser.equalsIgnoreCase("chrome"))
			{
				driver = new ChromeDriver();
				log.info("Browser Type Chrome Inititated");
			}
			else if(browser.equalsIgnoreCase("ie"))
			{
				driver = new EdgeDriver();
				log.info("Browser Type Edge Inititated");
			}
			else if(browser.equalsIgnoreCase("firefox"))
			{
				driver = new FirefoxDriver();
				log.info("Browser Type Firefox Inititated");
			}
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			
		}
		catch(Exception e) {
			
			
		}
		return driver;

	}

}
