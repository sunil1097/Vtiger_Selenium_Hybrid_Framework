package extras;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginSauceDemo {
	@Test(dataProvider ="getData")
	
	public void login(String un,String pwd) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys(un);
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.id("login-button")).click();
		String actualName =driver.findElement(By.xpath("//span[@class='title']")).getText();
		
		Assert.assertEquals(actualName, "Products", "successfully get the text ");
		System.out.println("login passed for this user :"+ un);
//		String expectedName = "Products";
//		Boolean status = actualName.equals(expectedName);
//		Assert.assertTrue(status);
//		System.out.println("it should be executed ");
		driver.quit();
		
	}
	@DataProvider
	public Object [][] getData(){
		Object[][] users = new Object[3][2];
		
		users[0][0]="performance_glitch_user";
		users[0][1]= "secret_sauce";
		
		users[1][0]="problem_user";
		users[1][1]= "secret_sauce";

		users[2][0]="standard_user";
		users[2][1]= "secret_sauce";
		return  users;
	}
	}

