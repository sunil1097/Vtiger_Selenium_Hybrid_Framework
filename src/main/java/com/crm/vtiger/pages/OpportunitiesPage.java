package com.crm.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.vtiger.utility.WebDriverUtility;

public class OpportunitiesPage {
	WebDriver driver;
	WebDriverUtility wdutil;

	public OpportunitiesPage(WebDriver driver) {
		this.driver = driver;
		wdutil = new WebDriverUtility(driver);
		PageFactory.initElements(driver, this);
	}

	// click on + icon
	@FindBy(xpath = "//img[@title='Create Opportunity...']")
	private WebElement plusIcon;

	// click on Opportunity name
	@FindBy(xpath = "//input[@name='potentialname']")
	private WebElement inputOpportunityName;

	// select from drop down (organisation)
	@FindBy(id = "related_to_type")
	private WebElement OrgDropDown;

	// then click on right side if dropdown for opening a new frame window
	@FindBy(xpath = "//input[@id='related_to_display']/following-sibling::img[@title='Select']")
	private WebElement selectOrg;

	// inside pop up search org name
	@FindBy(xpath = "//input[@id='search_txt']")
	private WebElement orgSearchInput;

	// inside pop up , select Org name
	@FindBy(xpath = "//select[@name='search_field']")
	private WebElement OrgDropDownInsidePopUp;

	// click on search result of Org pop
	@FindBy(xpath = "//a[@id='1']")
	private WebElement linkOrgSearch;
	// click search button
	@FindBy(xpath = "//input[@type='button']")
	private WebElement searchBtn;

	// click on save button
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	// action method
	public void createOpportunities(String OpportunityName, String OrgNameFromExcel) {
		plusIcon.click();
		inputOpportunityName.sendKeys(OpportunityName);
		wdutil.selectByValue(OrgDropDown, "Accounts");
		String originalWindow = driver.getWindowHandle();
		selectOrg.click();
		wdutil.switchToNewWindow(originalWindow);
		// now in pop
		orgSearchInput.sendKeys(OrgNameFromExcel);
		wdutil.selectByValue(OrgDropDownInsidePopUp, "accountname");
		searchBtn.click();
		linkOrgSearch.click();
		wdutil.switchToOriginalWindow(originalWindow);
		saveBtn.click();

	}

}
