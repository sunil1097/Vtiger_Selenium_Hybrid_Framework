package com.crm.vtiger.utility;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	WebDriver driver;

	public WebDriverUtility(WebDriver driver) {
		this.driver = driver;
	}

	public void maxWindow() {
		driver.manage().window().maximize();
	}

	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	public void hover(WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	// select by visible text
	public void selectByVisibleText(WebElement dropdownElement, String visibleText) {
		Select orgDD = new Select(dropdownElement);
		orgDD.selectByVisibleText(visibleText);

	}

	// select by value
	public void selectByValue(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}

	// select by index
	public void selectByIndex(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	public void acceptAlert() {
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
		Alert ale = driver.switchTo().alert();
		ale.accept();
	}
	// Switch to new window

	public void switchToNewWindow(String originalWindow) {

		// wait for another window to open

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

	}

	public void switchToOriginalWindow(String originalWindow) {
		driver.switchTo().window(originalWindow);
	}

}
