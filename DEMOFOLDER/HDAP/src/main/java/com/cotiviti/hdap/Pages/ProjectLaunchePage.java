package com.cotiviti.hdap.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.Scenario;

public class ProjectLaunchePage {
	// Template Start
	public WebDriver driver;

	public ProjectLaunchePage(WebDriver driver, Scenario s) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// Template End//ZR

	// Current Launch = CL
	// Upcoming Launch = UL

	@FindBy(xpath = "//a[text()='QA Retrieval']")
	private WebElement QA_Retrieval_btn;

	@FindBy(xpath = "//a[text()='QA Retrieval']")
	private WebElement SwitchRole_btn;

	@FindBy(xpath = "//span[text()='HDAP:Admin']")
	private WebElement HDAP_Admin_btn;

	@FindBy(xpath = "//span[text()='Project Launches']")
	private WebElement Project_Launches_tab;

	@FindBy(xpath = "//h3[text()='Current Launch']")
	private WebElement CL_Click;

	@FindBy(xpath = "//h3[text()='Upcoming Launch']")
	private WebElement UL_Click;

	@FindBy(xpath = "//button[text()='Launch All Ready Projects']")
	private WebElement LaunchAllReadyProjects_btn;

	@FindBy(xpath = "//a[text()='Launch Selected Projects']")
	private WebElement LauchSelectedProjects_btn;

	@FindBy(xpath = "//input[@id='19549b81']")
	private WebElement CL_Keyword_txtbox;

	@FindBy(xpath = "//select[@id='323b550a']")
	private WebElement CL_Client_ddl;

	@FindBy(xpath = "//select[@id='ed4ef394']")
	private WebElement CL_Status_ddl;

	@FindBy(xpath = "//button[@name='ProjectLaunchSearch_D_ProjectLaunch_pa255774315139766pz_12']")
	private WebElement CL_Clear_btn;

	@FindBy(xpath = "//button[@name='ProjectLaunchSearch_D_ProjectLaunch_pa255774180837705pz_13']")
	private WebElement CL_Search_btn;

	@FindBy(xpath = "//button[@name='pyTableToolbar_GridMetadata_1729927243671_13']")
	private WebElement CL_DefaultView_btn;

	@FindBy(xpath = "//input[@id='b4995eaa']")
	private WebElement CL_SaveViewName_txtbox;

	@FindBy(xpath = "//input[@id='5bc17ad4']")
	private WebElement CL_SaveView_checkbox;

	@FindBy(xpath = "//button[@name='pzEditPersonalizedView_EditPersonalizePage_16']")
	private WebElement CL_SaveView_Save_btn;

	@FindBy(xpath = "//button[@name='pyTableToolbar_GridMetadata_1729927243671_14']")
	private WebElement CL_Keyboard_btn;

	@FindBy(xpath = "//a[@name='ProjectLaunchSearch_D_ProjectLaunch_pa255774180837705pz_25']")
	private WebElement CL_SelectAll_click;

	@FindBy(xpath = "//a[@name='ProjectLaunchSearch_D_ProjectLaunch_pa255774180837705pz_26']")
	private WebElement CL_DeselectAll_click;

	@FindBy(xpath = "//button[@name='pyTableToolbar_GridMetadata_1730015255943_8']")
	private WebElement CL_Fields_btn;

	@FindBy(xpath = "//table[@uniqueid='1730016160744']//th[@aria-label='ID']//a[@aria-label='ID Actions']")
	private WebElement CL_ID_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730016160744']//th[@aria-label='Patient']//a[@aria-label='Patient Actions']")
	private WebElement CL_Patient_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730016160744']//th[@aria-label='DOB']//a[@aria-label='DOB Actions']")
	private WebElement CL_DOB_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730016160744']//th[@aria-label='Retrieval Provider']//a[@aria-label='Retrieval Provider Actions']")
	private WebElement CL_RetrievalProvider_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730016160744']//th[@aria-label='Provider Address']//a[@aria-label='Provider Address Actions']")
	private WebElement CL_ProviderAddress_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730016160744']//th[@aria-label='DOS']//a[@aria-label='DOS Actions']")
	private WebElement CL_DOS_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730016160744']//th[@aria-label='Client']//a[@aria-label='Client Actions']")
	private WebElement CL_Client_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730016160744']//th[@aria-label='Project Type']//a[@aria-label='Project Type Actions']")
	private WebElement CL_ProjectType_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730016160744']//th[@aria-label='Status']//a[@aria-label='Status Actions']")
	private WebElement CL_Status_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730016160744']//th[@aria-label='Request Group']//a[@aria-label='Request Group Actions']")
	private WebElement CL_RequestGroup_KebabMenu;

	@FindBy(xpath = "//input[@id='0172f1ae']")
	private WebElement UL_Keyword_txtbox;

	@FindBy(xpath = "//select[@id='4c35521f']")
	private WebElement UL_Client_ddl;

	@FindBy(xpath = "//select[@id='7c63ab43']")
	private WebElement UL_Status_ddl;

	@FindBy(xpath = "//button[@name='ProjectLaunchSearch_D_ProjectLaunch_pa255774315139766pz_12']")
	private WebElement UL_Clear_btn;

	@FindBy(xpath = "//button[@name='ProjectLaunchSearch_D_ProjectLaunch_pa255774315139766pz_13']")
	private WebElement UL_Search_btn;

	@FindBy(xpath = "//button[@name='pyTableToolbar_GridMetadata_1729951272437_13']")
	private WebElement DefaultView_btn;

	@FindBy(xpath = "//input[@name='$PEditPersonalizePage$ppyViewLabelInput']")
	private WebElement UL_SaveViewName_txtbox;

	@FindBy(xpath = "//input[@id='5bc17ad4']")
	private WebElement UL_SaveView_checkbox;

	@FindBy(xpath = "//button[@name='pzEditPersonalizedView_EditPersonalizePage_16']")
	private WebElement UL_SaveView_Save_btn;

	@FindBy(xpath = "//button[@name='pyTableToolbar_GridMetadata_1729951272437_14']")
	private WebElement UL_Keyboard_btn;

	@FindBy(xpath = "//a[@name='ProjectLaunchSearch_D_ProjectLaunch_pa255774315139766pz_25']")
	private WebElement UL_SelectAll_click;

	@FindBy(xpath = "//a[@name='ProjectLaunchSearch_D_ProjectLaunch_pa255774315139766pz_26']")
	private WebElement UL_DeselectAll_click;

	@FindBy(xpath = "//table[@uniqueid='1730018641326']//th[@aria-label='ID']//a[@aria-label='ID Actions']")
	private WebElement UL_ID_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730018641326']//th[@aria-label='Patient']//a[@aria-label='Patient Actions']")
	private WebElement UL_Patient_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730018641326']//th[@aria-label='DOB']//a[@aria-label='DOB Actions']")
	private WebElement UL_DOB_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730018641326']//th[@aria-label='Retrieval Provider']//a[@aria-label='Retrieval Provider Actions']")
	private WebElement UL_RetrievalProvider_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730018641326']//th[@aria-label='Provider Address']//a[@aria-label='Provider Address Actions']")
	private WebElement UL_ProviderAddress_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730018641326']//th[@aria-label='DOS']//a[@aria-label='DOS Actions']")
	private WebElement UL_DOS_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730018641326']//th[@aria-label='Client']//a[@aria-label='Client Actions']")
	private WebElement UL_Client_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730018641326']//th[@aria-label='Project Type']//a[@aria-label='Project Type Actions']")
	private WebElement UL_ProjectType_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730018641326']//th[@aria-label='Status']//a[@aria-label='Status Actions']")
	private WebElement UL_Status_KebabMenu;

	@FindBy(xpath = "//table[@uniqueid='1730018641326']//th[@aria-label='Request Group']//a[@aria-label='Request Group Actions']")
	private WebElement UL_RequestGroup_KebabMenu;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getQA_Retrieval_btn() {
		return QA_Retrieval_btn;
	}

	public WebElement getSwitchRole_btn() {
		return SwitchRole_btn;
	}

	public WebElement getHDAP_Admin_btn() {
		return HDAP_Admin_btn;
	}

	public WebElement getProject_Launches_tab() {
		return Project_Launches_tab;
	}

	public WebElement getCL_Click() {
		return CL_Click;
	}

	public WebElement getUL_Click() {
		return UL_Click;
	}

	public WebElement getLaunchAllReadyProjects_btn() {
		return LaunchAllReadyProjects_btn;
	}

	public WebElement getLauchSelectedProjects_btn() {
		return LauchSelectedProjects_btn;
	}

	public WebElement getCL_Keyword_txtbox() {
		return CL_Keyword_txtbox;
	}

	public WebElement getCL_Client_ddl() {
		return CL_Client_ddl;
	}

	public WebElement getCL_Status_ddl() {
		return CL_Status_ddl;
	}

	public WebElement getCL_Clear_btn() {
		return CL_Clear_btn;
	}

	public WebElement getCL_Search_btn() {
		return CL_Search_btn;
	}

	public WebElement getCL_DefaultView_btn() {
		return CL_DefaultView_btn;
	}

	public WebElement getCL_SaveViewName_txtbox() {
		return CL_SaveViewName_txtbox;
	}

	public WebElement getCL_SaveView_checkbox() {
		return CL_SaveView_checkbox;
	}

	public WebElement getCL_SaveView_Save_btn() {
		return CL_SaveView_Save_btn;
	}

	public WebElement getCL_Keyboard_btn() {
		return CL_Keyboard_btn;
	}

	public WebElement getCL_SelectAll_click() {
		return CL_SelectAll_click;
	}

	public WebElement getCL_DeselectAll_click() {
		return CL_DeselectAll_click;
	}

	public WebElement getCL_Fields_btn() {
		return CL_Fields_btn;
	}

	public WebElement getCL_ID_KebabMenu() {
		return CL_ID_KebabMenu;
	}

	public WebElement getCL_Patient_KebabMenu() {
		return CL_Patient_KebabMenu;
	}

	public WebElement getCL_DOB_KebabMenu() {
		return CL_DOB_KebabMenu;
	}

	public WebElement getCL_RetrievalProvider_KebabMenu() {
		return CL_RetrievalProvider_KebabMenu;
	}

	public WebElement getCL_ProviderAddress_KebabMenu() {
		return CL_ProviderAddress_KebabMenu;
	}

	public WebElement getCL_DOS_KebabMenu() {
		return CL_DOS_KebabMenu;
	}

	public WebElement getCL_Client_KebabMenu() {
		return CL_Client_KebabMenu;
	}

	public WebElement getCL_ProjectType_KebabMenu() {
		return CL_ProjectType_KebabMenu;
	}

	public WebElement getCL_Status_KebabMenu() {
		return CL_Status_KebabMenu;
	}

	public WebElement getCL_RequestGroup_KebabMenu() {
		return CL_RequestGroup_KebabMenu;
	}

	public WebElement getUL_Keyword_txtbox() {
		return UL_Keyword_txtbox;
	}

	public WebElement getUL_Client_ddl() {
		return UL_Client_ddl;
	}

	public WebElement getUL_Status_ddl() {
		return UL_Status_ddl;
	}

	public WebElement getUL_Clear_btn() {
		return UL_Clear_btn;
	}

	public WebElement getUL_Search_btn() {
		return UL_Search_btn;
	}

	public WebElement getDefaultView_btn() {
		return DefaultView_btn;
	}

	public WebElement getUL_SaveViewName_txtbox() {
		return UL_SaveViewName_txtbox;
	}

	public WebElement getUL_SaveView_checkbox() {
		return UL_SaveView_checkbox;
	}

	public WebElement getUL_SaveView_Save_btn() {
		return UL_SaveView_Save_btn;
	}

	public WebElement getUL_Keyboard_btn() {
		return UL_Keyboard_btn;
	}

	public WebElement getUL_SelectAll_click() {
		return UL_SelectAll_click;
	}

	public WebElement getUL_DeselectAll_click() {
		return UL_DeselectAll_click;
	}

	public WebElement getUL_ID_KebabMenu() {
		return UL_ID_KebabMenu;
	}

	public WebElement getUL_Patient_KebabMenu() {
		return UL_Patient_KebabMenu;
	}

	public WebElement getUL_DOB_KebabMenu() {
		return UL_DOB_KebabMenu;
	}

	public WebElement getUL_RetrievalProvider_KebabMenu() {
		return UL_RetrievalProvider_KebabMenu;
	}

	public WebElement getUL_ProviderAddress_KebabMenu() {
		return UL_ProviderAddress_KebabMenu;
	}

	public WebElement getUL_DOS_KebabMenu() {
		return UL_DOS_KebabMenu;
	}

	public WebElement getUL_Client_KebabMenu() {
		return UL_Client_KebabMenu;
	}

	public WebElement getUL_ProjectType_KebabMenu() {
		return UL_ProjectType_KebabMenu;
	}

	public WebElement getUL_Status_KebabMenu() {
		return UL_Status_KebabMenu;
	}

	public WebElement getUL_RequestGroup_KebabMenu() {
		return UL_RequestGroup_KebabMenu;
	}

}
