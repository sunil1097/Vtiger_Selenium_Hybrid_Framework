package Parellel;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;


public class CrossBrowserTesting {
	WebDriver driver = null;
	@Parameters("browser")
	@BeforeClass
	public void openBro(String browser) throws InterruptedException {
		String BROWSER = browser;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		
		Thread.sleep(3000);
		driver.quit();
	}

}
