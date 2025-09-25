package com.crm.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunityDetailsPage {
	WebDriver driver;

	public OpportunityDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "dtlview_Opportunity Name")
	private WebElement oppNameText;

	@FindBy(xpath = "//a[@title='Organizations']")
	private WebElement orgNameText;

	// click to edit

	@FindBy(xpath = "//input[@type='button' and @name='Edit']")
	private WebElement editBtn;

	// edit the OppName
	@FindBy(xpath = "//input[@name='potentialname']")
	private WebElement textOppName;
	// edit the description
	@FindBy(xpath = "//textarea[@name='description']")
	private WebElement textAreaField;

	// save button after editing

	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;

	// click to delete
	@FindBy(xpath = "(//input[@value='Delete'])[1]")
	private WebElement deleteBtn;

	public String getOpportunityName() {
		return oppNameText.getText();
	}

	public String getOrgName() {
		return orgNameText.getText();
	}

	public void clickEdit() {
		editBtn.click();

	}

	public void editTheOppName(String newName) {
		textOppName.clear();
		textOppName.sendKeys(newName);
	}

	public void saveChanges() {
		saveBtn.click();
	}

	public void clickDelete() {
		deleteBtn.click();
		driver.switchTo().alert().accept();
	}

}
