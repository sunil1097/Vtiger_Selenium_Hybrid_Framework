package com.crm.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunitiesPage {
	WebDriver driver;

	public OpportunitiesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// click on + icon
	@FindBy(xpath = "//img[@title='Create Opportunity...']")
	private WebElement plusIcon;

	// click on Opportunity name
	@FindBy(xpath = "//input[@name='potentialname']")
	private WebElement inputOpportunityName;

	// action method
	public void createOpportunities() {
		plusIcon.click();
		inputOpportunityName.sendKeys(null);

	}

}
