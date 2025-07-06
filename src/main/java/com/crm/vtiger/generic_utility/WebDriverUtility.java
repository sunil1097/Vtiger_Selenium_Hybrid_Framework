package com.crm.vtiger.generic_utility;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtility {
	WebDriver driver;
	
	public  WebDriverUtility(WebDriver driver) {
		this.driver=driver;
	}
	public  void maxWindow() {
		driver.manage().window().maximize();	
	}
	public  void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	
	public void hover(WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	public void selectByVisibleText(WebElement dropdownElement ,String visibleText) {
		Select orgDD = new Select(dropdownElement);
		orgDD.selectByVisibleText(visibleText); 
		}
	public void handleAlertAccept() {
		Alert ale = driver.switchTo().alert();
		ale.accept();
	}
	}
	
