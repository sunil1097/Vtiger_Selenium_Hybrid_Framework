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

	// click to delete
	@FindBy(xpath = "(//input[@value='Delete'])[1]")
	private WebElement deleteBtn;

	public String getOpportunityName() {
		return oppNameText.getText();
	}

	public String getOrgName() {
		return oppNameText.getText();
	}

	public void clickEdit() {
		editBtn.click();
	}

	public void clickDelete() {
		deleteBtn.click();
	}

}
