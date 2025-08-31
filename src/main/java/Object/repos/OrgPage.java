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
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// click on create + button  to create Org
	@FindBy(xpath="//img[@title='Create Organization...']")
	private WebElement createAOrg;
	public WebElement getCreateOrg() {
		return createAOrg;
	}
	// click on Org name input
	@FindBy(name="accountname")
	private WebElement orgName;
	public WebElement getOrgName() {
		return orgName;
	}
	// the next is save button
	
	@FindBy(xpath="//input[@type='button']")
	private WebElement saveOrg;
	public WebElement getSaveOrg() {
		return saveOrg;
	}
	// edit the org
	
	// seach the org name on seach field
	
	@FindBy(name="search_text")
	private WebElement searchIcon;
	public WebElement getClickOnSearchIcon() {
	return searchIcon;
	}
	
	@FindBy(name="Edit")
	private WebElement editOrgName;
	public WebElement getEditOrgName() {
		return editOrgName;
	}
	//then delete the  Org 
	@FindBy(name="Delete")
	private WebElement deleteOrg;
	public WebElement getDeleteTheOrg() {
		return deleteOrg;
	}
	
	// Edit Org
	// search 
	@FindBy(name ="search_text")
	private WebElement searchTheOrgName;
	public WebElement getTheOrgName() {
		return searchTheOrgName;
	}
	//select by org name 
	@FindBy(id="bas_searchfield")
	private WebElement orgDropDown;
	public WebElement getSearchOrgName() {
		return orgDropDown;
	}
	// click on search now button 
	
	@FindBy(name="submit")
	private WebElement clickSearchBtn;
	public WebElement getClickOnSearchBtn() {
		return clickSearchBtn;
	}
	// click on Org Row from Org details page 
	@FindBy(linkText="edit")
	private WebElement clickOnOrgDetailPage;
	public WebElement  getClickOnOrgEditBtn() {
		return clickOnOrgDetailPage;
	}
	
	// delete the exiting org name 
	@FindBy(linkText="del")
	private WebElement delLink;
	public WebElement getDelLink() {
		return delLink;
	}
	
	//BUSINESS UTILITY :1 Create Organization
	public void createOrganization(String OrgNameText) {
		createAOrg.click();
		orgName.sendKeys(OrgNameText);
		saveOrg.click();
	}
	//BUSINESS UTILITY :2 Edit existing Organization
	public void editExistOrg(String accountname) {
		getTheOrgName().clear();
		getTheOrgName().sendKeys(accountname);
		util = new WebDriverUtility(driver);
		util.selectByVisibleText(orgDropDown, "Organization Name");
		getClickOnSearchBtn().click();
		getClickOnOrgEditBtn().click();
	}
	
	// inner edit 
	public void editTheInternalOrgPage(String updatedOrgName) {
		getOrgName().clear();
		getOrgName().sendKeys(updatedOrgName);
	}
	//BUSINESS UTILITY :3 Delete the existing  Organization
	public void deleteExistOrg(String accountname ) {
		getTheOrgName().clear();
		getTheOrgName().sendKeys(accountname);
		util = new WebDriverUtility(driver);
		util.selectByVisibleText(orgDropDown, "Organization Name");
		getClickOnSearchBtn().click();
		getDelLink().click();
		util.acceptAlert();

	}


	

}
