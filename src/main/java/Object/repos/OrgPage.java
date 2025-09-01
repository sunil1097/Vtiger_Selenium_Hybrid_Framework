package Object.repos;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.vtiger.generic_utility.WebDriverUtility;

public class OrgPage {
	WebDriver driver;
	WebDriverUtility util;

	public OrgPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// click on create + button to create Org
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement btnCreateOrg;

	// click on Org name input
	@FindBy(name = "accountname")
	private WebElement txtOrgNameInput;

	// the next is save button

	@FindBy(xpath = "//input[@type='button']")
	private WebElement btnSaveOrg;

	// edit outside locators the org

	// seach the org name on seach field

	@FindBy(name = "search_text")
	private WebElement txtSearchOrg;

	// select by org name
	@FindBy(id = "bas_searchfield")
	private WebElement ddOrgName;

	// click on search now button

	@FindBy(name = "submit")
	private WebElement btnSearchNow;

	@FindBy(linkText = "edit")
	private WebElement lnkEditAction;

	// then delete the Org
	@FindBy(name = "Delete")
	private WebElement deleteOrg;

	public WebElement getDeleteTheOrg() {
		return deleteOrg;
	}

	// delete a product

	@FindBy(linkText = "del")
	private WebElement lnkDeleteAction;

	// BUSINESS Action :1 Create Organization
	public void createOrganization(String OrgName) {
		btnCreateOrg.click();
		txtOrgNameInput.sendKeys(OrgName);
		btnSaveOrg.click();
	}

	// BUSINESS Action :2 Edit existing Organization
	public void editExistOrg(String OrgName, String updatedOrgName) {
		txtSearchOrg.sendKeys(OrgName);
		util = new WebDriverUtility(driver);
		util.selectByVisibleText(ddOrgName, "Organization Name");
		btnSearchNow.click();
		lnkEditAction.click();
		txtOrgNameInput.clear();
		txtOrgNameInput.sendKeys(updatedOrgName);
	}

	// BUSINESS Action :3 Delete the existing Organization
	public void deleteExistOrg(String OrgName) {
		txtSearchOrg.sendKeys(OrgName);
		util = new WebDriverUtility(driver);
		util.selectByVisibleText(ddOrgName, "Organization Name");
		btnSearchNow.click();
		lnkDeleteAction.click();
		util.acceptAlert();

	}

}
