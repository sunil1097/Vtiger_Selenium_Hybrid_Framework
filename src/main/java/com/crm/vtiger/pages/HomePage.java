package com.crm.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.vtiger.utility.WebDriverUtility;

public class HomePage {
	WebDriver driver;
	WebDriverUtility wdUtil;
	
	public HomePage(WebDriver driver,WebDriverUtility wdUtil){
		this.driver=driver;
		this.wdUtil=wdUtil;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(xpath="//a[contains(@href, 'module=Home&action=index')]")
	// then make it private
	private WebElement clickOnHome;
	
	public WebElement getHomePage() {
		return clickOnHome;
	}
	// navigate to product page 
	 @FindBy(linkText="Products")
	 private WebElement clickOnProducts;

	 @FindBy(xpath ="//img[@src='themes/softed/images/user.PNG']")
	 private WebElement hoverOverProfile;
	 
	 @FindBy(linkText="Sign Out")
	 private WebElement logOut;
	 
	
	 // navigate to Org page 
	 @FindBy(linkText="Organizations")
	 private WebElement clickOnOrg;
	 
	 public WebElement getOrgPage() {
		 return clickOnOrg;
	 }
	 // business utility 
	 
	 public void navigateToHome() {
		 clickOnHome.click();
	 }
	 
	 public void  navigateToProduct() {
		 clickOnProducts.click();
	 }
	 public void logOut() {
		
		 wdUtil.hover(hoverOverProfile);
		 logOut.click();
		 
	 }
	 // nav to org page 
	 public void navigateToOrg() {
		 clickOnOrg.click();
	 }
	 
	 
	 
	 
	

}
