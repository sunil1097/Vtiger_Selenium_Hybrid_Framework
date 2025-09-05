package com.crm.vtiger.utility;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	public void acceptAlert() {
		new WebDriverWait (driver,Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
		Alert ale = driver.switchTo().alert();
		ale.accept();
	}
	}
	
