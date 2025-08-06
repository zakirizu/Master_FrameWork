package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

public LoginPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}


@FindBy(xpath = "")
private WebElement userName;


@FindBy(xpath="")
private WebElement passWord;


}
