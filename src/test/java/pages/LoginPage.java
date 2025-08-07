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

public void getLogintoApplicaiton() {
	userName.sendKeys("qa_zakir");
	passWord.sendKeys("asdf@123");
}
//Test



public WebElement getUserName() {
	return userName;
}

public WebElement getPassWord() {
	return passWord;
}



@FindBy(xpath = "//input[@id='txtUserID']")
private WebElement userName;


@FindBy(xpath = "//input[@id='txtPassword']")
private WebElement passWord;


}
