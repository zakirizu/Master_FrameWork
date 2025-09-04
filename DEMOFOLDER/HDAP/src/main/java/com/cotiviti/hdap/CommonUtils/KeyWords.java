package com.cotiviti.hdap.CommonUtils;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cotiviti.hdap.CommonLibrary.ReadDataFromExcel;

public class KeyWords {
	HashMap<String, String> testData;
	static JavascriptExecutor js;
	public static WebDriver driver;
	static String highLigtElement = PropertiesFileReader.getUIProperty("highLightElement");
	public static Logger myLogger = LogManager.getLogger(KeyWords.class.getName());
	String Environment = PropertiesFileReader.getUIProperty("Env");
	String browserType = PropertiesFileReader.getUIProperty("browserType");

	@SuppressWarnings("static-access")
	public KeyWords(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
	}

	public void loginApplicaiton() throws InterruptedException {

		try {

			if (Environment.equalsIgnoreCase("QA")) {
				driver.get(com.cotiviti.hdap.CommonUtils.PropertiesFileReader.getUIProperty("QA_URL"));
				js.executeScript("document.body.style.zoom='75%'");
				sendKeys(UserName_txtBox, PropertiesFileReader.getUIProperty("QA_UserID"));
				sendKeys(passWord_txtBox, PropertiesFileReader.getUIProperty("QA_Pwd"));
				clickElement(getLogin_Button());
			} else if (Environment.equalsIgnoreCase("UAT")) {
				driver.get(com.cotiviti.hdap.CommonUtils.PropertiesFileReader.getUIProperty("UAT_URL"));
				js.executeScript("document.body.style.zoom='75%'");
				sendKeys(getUserName_txtBox(), PropertiesFileReader.getUIProperty("UAT_UserID"));
				sendKeys(passWord_txtBox, PropertiesFileReader.getUIProperty("UAT_Pwd"));
				clickElement(getLogin_Button());

			} else if (Environment.equalsIgnoreCase("PROD")) {
				driver.get(com.cotiviti.hdap.CommonUtils.PropertiesFileReader.getUIProperty("PROD_URL"));
				js.executeScript("document.body.style.zoom='75%'");
				sendKeys(getUserName_txtBox(), PropertiesFileReader.getUIProperty("PROD_UserID"));
				sendKeys(passWord_txtBox, PropertiesFileReader.getUIProperty("PROD_Pwd"));
				clickElement(getLogin_Button());
			}
			title.click();
//			js.executeScript("document.body.style.zoom='75%'");
			Robot robot = new Robot();
			for (int i = 0; i < 3; i++) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_SUBTRACT);
				Thread.sleep(1500);
			}

		}

		catch (Exception e) {
			myLogger.info("Error While Initializing the BrowserType : " + browserType + ". Environment:" + Environment);
			e.printStackTrace();
		}

	}

	// WebElemets

	@FindBy(xpath = "//input[@id='txtUserID']")
	public static WebElement UserName_txtBox;

	public static WebElement getUserName_txtBox() {
		return UserName_txtBox;
	}

	@FindBy(xpath = "//button[@id='sub']")
	public static WebElement Login_Button;

	public static WebElement getLogin_Button() {
		return Login_Button;
	}

	@FindBy(xpath = "//input[@id='txtPassword']")
	public static WebElement passWord_txtBox;

	public static WebElement getPassWord_txtBox() {
		return passWord_txtBox;
	}

	@FindBy(xpath = "//li[@title='Search']//span[text()='Search']")
	private WebElement searchButtonTab;

	public WebElement getSearchButtonTab() {
		return searchButtonTab;
	}

	@FindBy(xpath = "//img[@title='Cotiviti One Retrieval']")
	private WebElement title;

	public WebElement getTitleIcon() {
		return title;
	}

	// *

	public HashMap<String, String> getTestData(String TestCaseID) {
		testData = ReadDataFromExcel.getExcelData("createRG", TestCaseID);
		return testData;
	}

	/*
	 * @ Created Date : 12-June-2023
	 * 
	 * @ KEYWORD : sendKeys
	 * 
	 * @ Input Parameters : ele = WebElement of TextBox , Value = String value which
	 * you want to enter into the textBox
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To enter any String value into the text Box. First it
	 * will clear the value in the text box and enter data into it.
	 */
	public void sendKeys(WebElement ele, String value) {

		String title = null;
		try {
			title = driver.getTitle();
			if (highLigtElement.equalsIgnoreCase("yes")) {
				myLogger.info("Entering text:" + value + ". In textbox WebElement: " + ele + " On Page: " + title);
				js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')", ele);
				ele.clear();
				ele.sendKeys(value);
				myLogger.info("Entered text:" + value + ". In textbox WebElement: " + ele + " On Page: " + title);
			} else {
				myLogger.info("Entering text:" + value + ". In textbox WebElement: " + ele + " On Page: " + title);
				ele.clear();
				ele.sendKeys(value);
				myLogger.info("Entered text:" + value + ". In textbox WebElement: " + ele + " On Page: " + title);
			}
		} catch (Exception e) {
			myLogger.info("Exception occured while Entered text: " + value + " in textbox WebElement: " + ele
					+ " On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able to Enter Text in WebElement:" + ele + "On Page: " + title);
		}
	}

	/*
	 * @ Created Date : 12-June-2023
	 * 
	 * @ KEYWORD : SendKeys_Into_AutoSuggestiveTextBox
	 * 
	 * @ Input Parameters : ele = WebElement of TextBox , Value = String value which
	 * you want to enter into the textBox
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Enter Text In AutoSuggestive TextBox
	 */
	public void SendKeys_Into_AutoSuggestiveTextBox(WebElement ele, String value) {

		String title = null;
		try {
			title = driver.getTitle();
			if (highLigtElement.equalsIgnoreCase("yes")) {
				myLogger.info("Entering text:" + value + ". In textbox WebElement: " + ele + " On Page: " + title);
				js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')", ele);
				ele.clear();
				ele.sendKeys(value);
				// shortWait();
				Thread.sleep(5000);
				ele.sendKeys(Keys.ARROW_DOWN);
				// shortWait();
				Thread.sleep(5000);
				ele.sendKeys(Keys.ENTER);
				myLogger.info("Entered text:" + value + ". In textbox WebElement: " + ele + " On Page: " + title);
			} else {
				myLogger.info("Entering text:" + value + ". In textbox WebElement: " + ele + " On Page: " + title);
				ele.clear();
				ele.sendKeys(value);
				myLogger.info("Entered text:" + value + ". In textbox WebElement: " + ele + " On Page: " + title);
			}
		} catch (Exception e) {
			myLogger.info("Exception occured while Entered text: " + value + " in textbox WebElement: " + ele
					+ " On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able to Enter Text in WebElement:" + ele + "On Page: " + title);
		}
	}

	/*
	 * @ Created Date : 12-June-2023
	 * 
	 * @ KEYWORD : getTextOfWebElement
	 * 
	 * @ Input Parameters : ele = WebElement of which you want to get the Text
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : This will get the Text of the Target element.
	 */

	public static String getTextOfWebElement(WebElement ele) {
		String title = driver.getTitle();
		String text = null;
		try {

			if (!ele.isDisplayed()) {
				myLogger.info("Not able to find Element :" + ele + ". Refreshing the page");
				driver.navigate().refresh();
				mediumWait();
			}
			if (highLigtElement.equalsIgnoreCase("yes")) {
				myLogger.info("Getting Text WebElement :" + ele + " On Page: " + title);
				js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')", ele);
				text = ele.getText();
				myLogger.info("Text Of WebElement is :" + text + " On Page: " + title);
			} else {
				myLogger.info("Getting Text WebElement :" + ele + " On Page: " + title);
				text = ele.getText();
				myLogger.info("Text Of WebElement is :" + text + " On Page: " + title);
			}

		} catch (Exception e) {
			myLogger.info("Exception occured while Clicking on Element :" + ele + " On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able Click WebElement:" + ele + "On Page: " + title);

		}
		return text;

	}

	/*
	 * @ Created Date : 12-June-2023
	 * 
	 * @ KEYWORD : clickElement
	 * 
	 * @ Input Parameters : ele = WebElement of Button/Link/CheckBox
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Click on the target Element
	 */
	public void clickElement(WebElement ele) {
		String title = driver.getTitle();
		try {
			if (!ele.isDisplayed()) {
				myLogger.info("Not able to find Element :" + ele + ". Refreshing the page");
				driver.navigate().refresh();
				mediumWait();
			}
			if (highLigtElement.equalsIgnoreCase("yes")) {
				myLogger.info("Clicking on Element :" + ele + " On Page: " + title);
				js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')", ele);
				ele.click();
				myLogger.info("Clicked on Element :" + ele + " On Page: " + title);
			} else {
				myLogger.info("Clicking on Element :" + ele + " On Page: " + title);
				ele.click();
				myLogger.info("Clicked on Element :" + ele + " On Page: " + title);
			}

		} catch (Exception e) {
			myLogger.info("Exception occured while Clicking on Element :" + ele + " On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able Click WebElement:" + ele + "On Page: " + title);

		}

	}

	/*
	 * @ Created Date : 12-June-2023
	 * 
	 * @ KEYWORD : SelectByVisibleText
	 * 
	 * @ Input Parameters : ele = WebElement of DropDownList , Value = Visible Text
	 * value which you want to Select into the Drop Down List
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Select value in the DropDownList BY VISIBLE TEXT(Text
	 * Displyed on UI of the DDL).
	 */

	public void SelectByVisibleText(WebElement ele, String visibleText) {
		String title = driver.getTitle();
		try {
			if (highLigtElement.equalsIgnoreCase("yes")) {
				myLogger.info("Selecting Drop down list with VisbleText : " + visibleText + " in DropDownList :" + ele
						+ " On Page: " + title);
				js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')", ele);
				Select sc = new Select(ele);
				sc.selectByVisibleText(visibleText);
				myLogger.info(
						"Selected Drop down list selected with Visible Text of :" + visibleText + " On Page: " + title);
			} else {
				myLogger.info("Drop Down List Element is Visible :" + ele + " On Page: " + title);
				myLogger.info("Selecting drop down list VisbleText : " + visibleText + " in DropDownList :" + ele
						+ " On Page: " + title);
				Select sc = new Select(ele);
				sc.selectByVisibleText(visibleText);
				myLogger.info("Drop down list selected with Visible Text of :" + visibleText + " On Page: " + title);
			}

		} catch (Exception e) {
			myLogger.info("Exception Occured while selecting VisibleText : " + visibleText + " in DropDownList :" + ele
					+ " On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able to SelectByVisibleText WebElement:" + ele + "On Page: " + title);

		}

	}

	/*
	 * @ Created Date : 12-June-2023
	 * 
	 * @ KEYWORD : selectByValue
	 * 
	 * @ Input Parameters : ele = WebElement of DropDownList , Value = Value (in
	 * html tag )which you want to Select into the Drop Down List
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Select value in the DropDownList BY VALUE(VALUE
	 * attribute is present in the HTML tag of select Class).
	 */

	public void selectByValue(WebElement ele, String value) {
		String title = driver.getTitle();
		try {
			/*
			 * if(!ele.isDisplayed()) { myLogger.info("Not able to find Element :"+ele
			 * +". Refreshing the page"+" On Page title : "+title);
			 * driver.navigate().refresh(); mediumWait(); }
			 */
			if (highLigtElement.equalsIgnoreCase("yes")) {
				myLogger.info("Drop Down List Element is Visible :" + ele + " On Page: " + title);
				myLogger.info("Selecting drop down list value : " + value + " in DropDownList :" + ele + " On Page: "
						+ title);
				js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')", ele);
				Select sc = new Select(ele);
				sc.selectByValue(value);
				myLogger.info("Drop down list selected with Value of :" + value + " On Page: " + title);
			} else {
				myLogger.info("Drop Down List Element is Visible :" + ele + " On Page: " + title);
				myLogger.info("Selecting drop down list value  : " + value + " in DropDownList :" + ele
						+ " On Page tile: " + title);
				Select sc = new Select(ele);
				sc.selectByValue(value);
				myLogger.info("Drop down list selected with Value of :" + value + " On Page: " + title);
			}

		} catch (Exception e) {
			myLogger.info("Exception Occured while selecting value : " + value + " in DropDownList :" + ele
					+ " On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able to SelectByValue for WebElement:" + ele + ". On Page title: " + title);
		}

	}

	/*
	 * @ Created Date : 12-June-2023
	 * 
	 * @ KEYWORD : selectByIndex
	 * 
	 * @ Input Parameters : ele = WebElement of DropDownList , index = Index of the
	 * list value which you want to select in DDL. Note Index starts from 0 zero
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Select value in the DropDownList BY Index (INDEX
	 * starts from 0 ).
	 */

	public static void selectByIndex(WebElement ele, int index) {
		String title = driver.getTitle();
		try {
			if (!ele.isDisplayed()) {
				myLogger.info("Not able to find Element :" + ele + ". Refreshing the page" + " On Page: " + title);
				driver.navigate().refresh();
				Thread.sleep(5000);
			}
			if (highLigtElement.equalsIgnoreCase("yes")) {
				myLogger.info("Drop Down List Element is Visible :" + ele);
				myLogger.info("Selecting Drop Down List with Index of : " + index + " in DropDownList :" + ele
						+ " On Page: " + title);
				js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')", ele);
				Select sc = new Select(ele);
				sc.selectByIndex(index);
				myLogger.info("Drop down list selected with Index of :" + index + " On Page: " + title);
			} else {
				myLogger.info("Drop Down List Element is Visible :" + ele + " On Page: " + title);
				myLogger.info("Selecting Drop Down List with Index of : " + index + " in DropDownListl :" + ele
						+ " On Page: " + title);
				Select sc = new Select(ele);
				sc.selectByIndex(index);
				myLogger.info("Drop down list selected with Index of :" + index + " On Page: " + title);
			}

		} catch (Exception e) {
			myLogger.info("Exception Occured while selecting Index : " + index + " in DropDownList :" + ele
					+ " On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able to SelectByIndex for WebElement:" + ele + ". On Page title: " + title);
		}

	}

	/*
	 * @ Created Date : 12-June-2023
	 * 
	 * @ KEYWORD : explicitWait_Until_ElementDisplayed
	 * 
	 * @ Input Parameters : ele = WebElement for which you want to wait explicitly
	 * until it is displayed
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Wait until the target Element is displayed. This is
	 * Defined in Seconds
	 * 
	 * @ Can Be Configured: ROW_MDM_Automation\Resources\propertyFile.properties
	 * (Property:explicitWait)
	 */
	public void explicitWait_Until_ElementDisplayed(WebElement ele) {
		String title = driver.getTitle();
		try {
			String secString = com.cotiviti.hdap.CommonUtils.PropertiesFileReader.getUIProperty("explicitWait");
			int sec = Integer.parseInt(secString);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
			wait.until(ExpectedConditions.visibilityOf(ele));

			if (ele.isDisplayed()) {
				myLogger.info("Element is Visible :" + ele + " On Page: " + title);
				js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')", ele);
			}

			else {
				myLogger.info("Element is Not Visible :" + ele + " On Page: " + title);
				Assert.assertTrue(false, "WebElement is Not Visible even after ExplicitWait:" + ele
						+ ". On Page title: " + title + " .");
			}

		} catch (Exception e) {
			myLogger.info(
					"Exception occured while Checking if Element is Displayed or NOt:" + ele + " On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false,
					"WebElement is Not Visible with Xpath:" + ele + ". On Page title: " + title + " .");

		}

	}

	/*
	 * @ Created Date : 12-June-2023
	 * 
	 * @ KEYWORD : explicitWait_UntilElement_NOT_Displayed
	 * 
	 * @ Input Parameters : ele = WebElement for which you want to wait explicitly
	 * until it is displayed
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Wait until the target Element is NOT Displayed. This
	 * is Defined in Seconds
	 * 
	 * @ Can Be Configured: ROW_MDM_Automation\Resources\propertyFile.properties
	 * (Property:explicitWait)
	 */
	public static void explicitWait_UntilElement_NOT_Displayed(WebElement ele) {
		String title = driver.getTitle();
		try {
			String secString = com.cotiviti.hdap.CommonUtils.PropertiesFileReader.getUIProperty("explicitWait");
			int sec = Integer.parseInt(secString);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
			wait.until(ExpectedConditions.invisibilityOf(ele));

			if (!ele.isDisplayed()) {

				myLogger.info("Element is Not Visible :" + ele + " On Page: " + title);

			}

			else if (ele.isDisplayed()) {

				myLogger.info("Element is Visible :" + ele + " On Page: " + title);
				js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')", ele);
				Assert.assertTrue(false, "WebElement is Visible:" + ele + ". On Page title: " + title + " .");
			}

		} catch (Exception e) {
			myLogger.info(
					"Exception occured while Checking if Element is Displayed or NOt:" + ele + " On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false, "WebElement is Visible with Xpath:" + ele + ". On Page title: " + title + " .");

		}

	}

	/*
	 * @ Created Date : 13-June-2023
	 * 
	 * @ KEYWORD : Element_Is_NOT_Displayed
	 * 
	 * @ Input Parameters : ele = WebElement for which you want to wait explicitly
	 * until it is displayed
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Wait until the target Element is NOT Displayed. This
	 * is Defined in MilliSeconds
	 * 
	 * @ Can Be Configured: ROW_MDM_Automation\Resources\propertyFile.properties
	 * (Property: implicitWait)
	 */

	public static void Element_Is_NOT_Displayed(WebElement ele) {
		String title = driver.getTitle();
		try {
			if (!ele.isDisplayed()) {
				myLogger.info("Element is Not Visible :" + ele + " On Page: " + title);
			}

			else if (ele.isDisplayed()) {
				myLogger.info("Element is Visible :" + ele + " On Page: " + title);
				js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')", ele);
				Assert.assertTrue(false, "WebElement is Visible:" + ele + ". On Page title: " + title + " .");
			}
		}

		catch (Exception e) {
			myLogger.info(
					"Exception occured while Checking if Element is Displayed or NOt:" + ele + " On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false, "WebElement is Visible with Xpath:" + ele + ". On Page title: " + title + " .");

		}

	}

	/*
	 * @ Created Date : 13-June-2023
	 * 
	 * @ KEYWORD : Element_Is_Displayed
	 * 
	 * @ Input Parameters : ele = WebElement for which you want to wait explicitly
	 * until it is displayed
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Wait until the target Element is NOT Displayed. This
	 * is Defined in MilliSeconds
	 * 
	 * @ Can Be Configured: ROW_MDM_Automation\Resources\propertyFile.properties
	 * (Property: implicitWait)
	 */
	public void Element_Is_Displayed(WebElement ele) {
		String title = driver.getTitle();
		try {

			if (!ele.isDisplayed()) {
				myLogger.info("Not able to find Element :" + ele + ". Refreshing the page");
				driver.navigate().refresh();
				shortWait();
			}
			if (highLigtElement.equalsIgnoreCase("yes")) {

				js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')", ele);
				myLogger.info("Element is Visible :" + ele + " On Page: " + title);
			} else {
				myLogger.info("Element is Visible :" + ele + " On Page: " + title);
			}

		} catch (Exception e) {
			myLogger.info(
					"Exception occured while Checking if Element is Displayed or NOt:" + ele + " On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false,
					"WebElement is Not Visible with Xpath:" + ele + ". On Page title: " + title + " .");

		}

	}

	/*
	 * @ Created Date : 12-June-2023
	 * 
	 * @ KEYWORD : switchToDefaultContent
	 * 
	 * @ Input Parameters : ele = WebElement of DropDownList , Value = Visible Text
	 * value which you want to Select into the Drop Down List
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Select value in the DropDownList BY VISIBLE TEXT(Text
	 * Displyed on UI of the DDL).
	 */
	public void switchToDefaultContent() {
		String title = null;
		title = driver.getTitle();
		try {
			myLogger.info("Switching to Default Contend From Frame On Page: " + title);
			driver.switchTo().defaultContent();
			myLogger.info("Switched to Default Contend From Frame On Page: " + title);
		} catch (Exception e) {
			myLogger.info("Switching to Default Contend From Frame On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able to Swithcto Default Content On Page title: " + title);
		}
	}

	/*
	 * @ Created Date : 12-June-2023
	 * 
	 * @ KEYWORD : switchFrameById
	 * 
	 * @ Input Parameters : ele = id of the Frame
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Switch Frame By Using ID of the Frame
	 */
	public static void switchFrameById(int id) {
		String title = null;
		String frameTitle = null;
		title = driver.getTitle();
		try {
			myLogger.info("Switching to Frame By Index:" + id + " On Page: " + title);
			driver.switchTo().frame(id);
			frameTitle = driver.getTitle();
			myLogger.info("Switched to Frame By Index:" + id + " Frame title: " + frameTitle);
		} catch (Exception e) {
			myLogger.info("Exception occured Switching to Frame By Index:" + id);
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able to Swithcto Frame By ID: " + id + ". On Page title: " + title);
		}
	}

	/*
	 * @ Created Date : 13-June-2023
	 * 
	 * @ KEYWORD : switchFrameByWebElement
	 * 
	 * @ Input Parameters : ele = WebElement of the Frame
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Switch Frame By Using WebElement of the Frame
	 */
	public void switchFrameByWebElement(WebElement ele) {
		String title = null;
		title = driver.getTitle();

		try {
			myLogger.info("Switching to Frame By WebElement: " + ele + " On Page: " + title);
			driver.switchTo().frame(ele);
			myLogger.info("Switched to Frame By WebElement:" + ele + " Frame title: " + title);
		}

		catch (Exception e) {
			myLogger.info("Exception occured Switching to Frame By WebElement:" + ele);
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able to Swithc to Frame By WebElement: " + ele + ". On Page title: " + title);
		}
	}

	/*
	 * @ Created Date : 13-June-2023
	 * 
	 * @ KEYWORD : switchFrameByNameorID
	 * 
	 * @ Input Parameters : ele = Name or ID of the Frame
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Switch Frame By Using Frame Name/ID
	 */
	public static void switchFrameByNameorID(String nameORid) {

		String title = null;
		title = driver.getTitle();
		try {
			myLogger.info("Switching to Frame By nameORid:" + nameORid + " On Page: " + title);
			driver.switchTo().frame(nameORid);
			myLogger.info("Switched to Frame By nameORid:" + nameORid + " Frame title: " + title);
		} catch (Exception e) {
			myLogger.info("Exception occured Switching to Frame By name   ORid:" + nameORid);
			e.printStackTrace();
			Assert.assertTrue(false,
					"Not Able to Swithcto Frame By NameorID: " + nameORid + ". On Page title: " + title);
		}
	}

	/*
	 * @ Created Date : 12-June-2023
	 * 
	 * @ KEYWORD : shortWait
	 * 
	 * @ Input Parameters : None
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Wait time defined for shortWait property value.
	 * 
	 * @ Can Be Configured: ROW_MDM_Automation\Resources\propertyFile.properties
	 * (Property: shortWait)
	 */

	public void shortWait() throws InterruptedException {
		String x = com.cotiviti.hdap.CommonUtils.PropertiesFileReader.getUIProperty("shortWait");
		int i = Integer.parseInt(x);
		myLogger.info("Short Wait Start---. Waiting for Seconds: " + i);
		Thread.sleep(i);
		myLogger.info("Short Wait End---. Waited for Seconds: " + i);
	}

	/*
	 * @ Created Date : 12-June-2023
	 * 
	 * @ KEYWORD : mediumWait
	 * 
	 * @ Input Parameters : None
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Wait time defined for mediumWait property value.
	 * 
	 * @ Can Be Configured: ROW_MDM_Automation\Resources\propertyFile.properties
	 * (Property: mediumWait)
	 */
	public static void mediumWait() throws InterruptedException {
		String x = com.cotiviti.hdap.CommonUtils.PropertiesFileReader.getUIProperty("mediumWait");
		int i = Integer.parseInt(x);
		myLogger.info("Medium Wait Start---. Waiting for Seconds: " + i);
		Thread.sleep(i);
		myLogger.info("Medium Wait End---. Waited for Seconds: " + i);
	}

	/*
	 * @ Created Date : 12-June-2023
	 * 
	 * @ KEYWORD : longWait
	 * 
	 * @ Input Parameters : None
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Wait time defined for longWait property value.
	 * 
	 * @ Can Be Configured: ROW_MDM_Automation\Resources\propertyFile.properties
	 * (Property: longWait)
	 */
	public static void longWait() throws InterruptedException {
		String x = com.cotiviti.hdap.CommonUtils.PropertiesFileReader.getUIProperty("longWait");
		int i = Integer.parseInt(x);
		myLogger.info("- Long Wait Start---.Waiting for Seconds: " + i);
		Thread.sleep(i);
		myLogger.info("- Long Wait End---.Waited for Seconds: " + i);
	}

	/*
	 * @ Created Date : 12-June-2023
	 * 
	 * @ KEYWORD : explicitWait
	 * 
	 * @ Input Parameters : None
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Wait time defined for explicitWait property value.
	 * 
	 * @ Can Be Configured: ROW_MDM_Automation\Resources\propertyFile.properties
	 * (Property: explicitWait)
	 */
	public static void explicitWait() throws InterruptedException {
		String x = com.cotiviti.hdap.CommonUtils.PropertiesFileReader.getUIProperty("longWait");
		int i = Integer.parseInt(x);
		myLogger.info("Long Wait Start---.Waiting for Seconds: " + i);
		Thread.sleep(i);
		myLogger.info("Long Wait End---.Waited for Seconds: " + i);
	}

	/*
	 * @ Created Date : 13-June-2023
	 * 
	 * @ KEYWORD : rightClick
	 * 
	 * @ Input Parameters : ele = WebElement
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Perform RightClick on the Target WebElement
	 */

	public static void rightClick(WebElement ele) {

		Actions ac = new Actions(driver);
		String title = null;
		try {
			title = driver.getTitle();
			if (!ele.isDisplayed()) {
				myLogger.info("Not able to find Element :" + ele + ". Refreshing the page");
				driver.navigate().refresh();
				Thread.sleep(5000);
			}
			if (highLigtElement.equalsIgnoreCase("yes")) {
				myLogger.info("Performing RightClick on Element :" + ele + " On Page: " + title);
				js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')", ele);
				ac.contextClick(ele).build().perform();
				myLogger.info("Performed RightClick on Element :" + ele + " On Page: " + title);
			} else {
				myLogger.info("Performing RightClick on Element :" + ele + " On Page: " + title);
				ac.contextClick(ele).build().perform();
				myLogger.info("Performing RightClick on Element :" + ele + " On Page: " + title);
			}
		}

		catch (Exception e) {
			myLogger.info("Exception occured while Performing RightClick on Element :" + ele + " On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able to rightClick on WebElement:" + ele + ". On Page title: " + title);
		}

	}

	/*
	 * @ Created Date : 14-June-2023
	 * 
	 * @ KEYWORD : doubleClick
	 * 
	 * @ Input Parameters : ele = WebElement
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Perform Double Click on the Target WebElement
	 */

	public void doubleClick(WebElement ele) {
		Actions ac = new Actions(driver);
		String title = null;
		try {
			title = driver.getTitle();
			if (!ele.isDisplayed()) {
				myLogger.info("Not able to find Element :" + ele + ". Refreshing the page");
				driver.navigate().refresh();
				shortWait();
			}
			if (highLigtElement.equalsIgnoreCase("yes")) {
				myLogger.info("Performing RightClick on Element :" + ele + " On Page: " + title);
				js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')", ele);
				ac.doubleClick(ele).build().perform();
				myLogger.info("Performed RightClick on Element :" + ele + " On Page: " + title);
			} else {
				myLogger.info("Performing RightClick on Element :" + ele + " On Page: " + title);
				ac.doubleClick(ele).build().perform();
				myLogger.info("Performing RightClick on Element :" + ele + " On Page: " + title);
			}

		} catch (Exception e) {
			myLogger.info("Exception occured while Performing RightClick on Element :" + ele + " On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able to doubleClick on WebElement:" + ele + ". On Page title: " + title);
		}

	}

	/*
	 * @ Created Date : 14-June-2023
	 * 
	 * @ KEYWORD : moveToWebElement
	 * 
	 * @ Input Parameters : ele = WebElement
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Move to the Target WebElement
	 */

	public void moveToWebElement(WebElement ele) {
		Actions ac = new Actions(driver);
		String title = null;
		try {
			title = driver.getTitle();
			/*
			 * if(!ele.isDisplayed()) { myLogger.info("Not able to find Element :"+ele
			 * +". Refreshing the page"); driver.navigate().refresh(); shortWait(); }
			 */
			if (highLigtElement.equalsIgnoreCase("yes")) {
				myLogger.info("Moving to the Element :" + ele + " On Page: " + title);
				js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')", ele);
				ac.moveToElement(ele).build().perform();
				myLogger.info("Moving to the Element :" + ele + " On Page: " + title);
			} else {
				myLogger.info("Moving to the Element :" + ele + " On Page: " + title);
				ac.moveToElement(ele).build().perform();
				myLogger.info("Moving to the Element :" + ele + " On Page: " + title);
			}

		} catch (Exception e) {
			myLogger.info("Exception occured while Moving to Element :" + ele + " On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able to Move to WebElement:" + ele + ". On Page title: " + title);
		}

	}

	/*
	 * @ Created Date : 14-June-2023
	 * 
	 * @ KEYWORD : dragAndDrop
	 * 
	 * @ Input Parameters : ele = WebElement1, ele = WebElement2
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Move to the Target WebElement
	 */
	public void dragAndDrop() {
		WebElement From = null;
		WebElement To = null;
		Actions ac = new Actions(driver);
		String title = null;
		try {
			title = driver.getTitle();
			if (!(From.isDisplayed() && To.isDisplayed())) {
				myLogger.info("Not able to find WebElement Refreshing the page");
				driver.navigate().refresh();
				shortWait();
			}
			if (highLigtElement.equalsIgnoreCase("yes")) {
				myLogger.info("Moving Element:" + From + " To :" + To + "On Page: " + title);
				js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')", From);
				ac.clickAndHold(From).moveToElement(To).release(To).build().perform();
				myLogger.info("Moved Element:" + From + " To :" + To + "On Page: " + title);
			} else {
				myLogger.info("Moving Element:" + From + " To :" + To + "On Page: " + title);
				ac.clickAndHold(From).moveToElement(To).release(To).build().perform();
				myLogger.info("Movied Element:" + From + " To :" + To + "On Page: " + title);
			}

		} catch (Exception e) {
			myLogger.info("Exception While Moving Element:" + From + " To :" + To + "On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able to perform Drag&Drop on Page with Title: " + title);
		}

	}

	/*
	 * @ Created Date : 12-June-2023
	 * 
	 * @ KEYWORD : SwitchToWindow
	 * 
	 * @ Input Parameters :
	 * 
	 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
	 * 
	 * @ Developer ID : 163054
	 * 
	 * @ Can Be Used For : To Switch to Window That is Opened
	 */

	public static void SwitchToWindow() throws InterruptedException {

		try {
			String parentWindow = driver.getWindowHandle();
			String childWindowTilte = null;
			myLogger.info("Parent Window title: " + driver.getTitle());
			Set<String> handles = driver.getWindowHandles();
			Iterator<String> ite = handles.iterator();
			while (ite.hasNext()) {
				String childWindow = ite.next();
				if (!parentWindow.equals(childWindow)) {
					myLogger.info("Switching to the Child Window");
					driver.switchTo().window(childWindow);
					driver.manage().window().maximize();
					driver.navigate().refresh();
					mediumWait();
					childWindowTilte = driver.getTitle();
					myLogger.info("Switched to the Child Window title: " + childWindowTilte);
				}
			}
		} catch (Exception e) {
			myLogger.info("Exception While Swithing Wndows");
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able to Switch to Windows.");
		}

	}
	/*
	 * @Auther : Dinesh
	 * 
	 */

	public void scrollToElement(WebElement Element) throws InterruptedException {

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", Element);
		} catch (Exception e) {
			myLogger.info("Exception While Scrolling to Element");
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able to Scroll to Element.");
		}

	}

	/*
	 * @Auther : Dinesh
	 * 
	 */
	public void jsClickElement(WebElement ele) {
		String title = driver.getTitle();
		try {
			if (!ele.isDisplayed()) {
				myLogger.info("Not able to find Element :" + ele + ". Refreshing the page");
				driver.navigate().refresh();
				mediumWait();
			}
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);
			myLogger.info("Clicked on Element :" + ele + " On Page: " + title);

		} catch (Exception e) {
			myLogger.info("Exception occured while Clicking on Element :" + ele + " On Page: " + title);
			e.printStackTrace();
			Assert.assertTrue(false, "Not Able Click WebElement:" + ele + "On Page: " + title);

		}

	}

	/*
	 * @Auther : Dinesh
	 */

	public String currentDateTime() {
		String currentDate = "";
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			currentDate = formatter.format(date);
			System.out.println("Current Date and Time: " + currentDate);
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return currentDate;
	}

}
/*
 * @ Created Date : 12-June-2023
 * 
 * @ KEYWORD : sendKeys
 * 
 * @ Input Parameters : ele = WebElement of TextBox , Value = String value which
 * you want to enter into the textBox
 * 
 * @ Developer Name : Zakir Hussain Shaik(Hussain, Shaik)
 * 
 * @ Developer ID : 163054
 * 
 * @ Can Be Used For : To enter any String value into the text Box. First it
 * will clear the value in the text box and enter data into it.
 */
/*
 * public static void clickElementWithVisibleText(String eleText) {
 * 
 * String title=null; try { title = driver.getTitle();
 * 
 * if(highLigtElement.equalsIgnoreCase("yes")) {
 * myLogger.info("Clicking on Element with Text"+eleText +"On Page: "+title);
 * js.
 * executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;')"
 * , eleText);
 * driver.findElement(By.xpath("//td[normalize-space(text())='"+eleText+"']/a"))
 * .click();
 * 
 * ele.clear(); ele.sendKeys(value);
 * myLogger.info("Entered text:"+value+". In textbox WebElement: "+ele
 * +" On Page: "+title); } else {
 * myLogger.info("Entering text:"+value+". In textbox WebElement: "+ele
 * +" On Page: "+title); ele.clear(); ele.sendKeys(value);
 * myLogger.info("Entered text:"+value+". In textbox WebElement: "+ele
 * +" On Page: "+title); } } catch(Exception e) {
 * myLogger.info("Exception occured while Entered text: "
 * +value+" in textbox WebElement: "+ele +" On Page: "+title);
 * e.printStackTrace(); Assert.assertTrue(false,
 * "Not Able to Enter Text in WebElement:"+ele +"On Page: "+title); } }
 */
