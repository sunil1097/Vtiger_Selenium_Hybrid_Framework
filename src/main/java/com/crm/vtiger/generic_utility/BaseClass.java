package com.crm.vtiger.generic_utility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import PageObjects.HomePage;
import PageObjects.LoginPage;

public class BaseClass {

	public WebDriver driver;
	public HomePage hp;	
	public WebDriverUtility wdUtil;
	public FileUtility fUtil = new FileUtility();
	public static WebDriver sdriver= null; 
	

	
	
	@BeforeClass(alwaysRun =true) 
	public void browserLaunch() throws IOException {
		//getting the data from prop file 
		String BROWSER =fUtil.getDataFromPropFile("bro");
		// cross browser set up 
		
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else {
			driver = new ChromeDriver();
		}
		sdriver= driver;
		wdUtil = new WebDriverUtility(driver);
		wdUtil.maxWindow();
		wdUtil.implicitWait();	
	}
	@BeforeMethod
	public void login() throws IOException {
		String URL = fUtil.getDataFromPropFile("url");
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp();
		hp= new HomePage(driver, wdUtil);
	}
			
	// log out from the crm 
	@AfterMethod(alwaysRun =true)
	public void logOutFromVtiger() {
		hp.logOut();
	}
	@AfterClass
	public void browserClosed() {
		driver.quit();
	}
	@AfterTest
	public void postCondition() {
		System.out.println("post conditions");
	}
	@AfterSuite
	public void dbclose() {
		System.out.println("DB close + report backup ");
	
	}
}
