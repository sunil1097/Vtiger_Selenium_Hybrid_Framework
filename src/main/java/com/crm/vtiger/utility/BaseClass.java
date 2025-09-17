package com.crm.vtiger.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.crm.vtiger.pages.HomePage;
import com.crm.vtiger.pages.LoginPage;

public class BaseClass {

	public WebDriver driver;
	public HomePage hp;
	public WebDriverUtility wdUtil;
	public FileUtility fUtil = new FileUtility();
	public static WebDriver sdriver = null;

	@Parameters({ "browser", "os" })
	@BeforeClass(alwaysRun = true) // ✅ Always run, no groups
	public void setUp(@Optional("") String browser, @Optional("") String os) throws IOException {
		if (browser == null || browser.isEmpty()) {
			browser = fUtil.getDataFromPropFile("bro");
		}

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Browser not supported: " + browser);
		}

		sdriver = driver;
		wdUtil = new WebDriverUtility(driver);
		wdUtil.maxWindow();
		wdUtil.implicitWait();
	}

	@BeforeMethod(alwaysRun = true)
	public void login() throws IOException {
		String URL = fUtil.getDataFromPropFile("url");
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp();
		hp = new HomePage(driver, wdUtil);
	}

	@AfterMethod(alwaysRun = true)
	public void logOutFromVtiger() {
		if (hp != null) {
			hp.logOut();
		}
	}

	@AfterClass(alwaysRun = true) // ✅ Always run, no groups
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public static String captureScreen(String tname) {
		if (sdriver == null) {
			System.out.println("⚠ Screenshot skipped: WebDriver is null");
			return null;
		}

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		TakesScreenshot tks = (TakesScreenshot) sdriver;
		File sourceFile = tks.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + tname
				+ "_" + timeStamp + ".png";

		File targetFile = new File(targetFilePath);
		try {
			Files.copy(sourceFile.toPath(), targetFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return targetFilePath;
	}
}
