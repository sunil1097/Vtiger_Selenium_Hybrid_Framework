package Object.repos;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	
	// Declare WebDriver globally
	
	WebDriver driver;
		
	
	public LoginPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	// Declare of WebElement for userName
	@FindBy(name ="user_name")
	private WebElement un;
	
	// getter method to access the webElement 
	public WebElement getUn() {
		return un;
	}
	
	// for Password
	@FindBy(name ="user_password")
	private WebElement pwd;
	
	public WebElement getPwd() {
		return pwd;
	}
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	public WebElement getLoginBtn() {
		return loginBtn;
	}
	// business utility
	public void loginToApp() {
		getUn().sendKeys("admin");
		getPwd().sendKeys("admin");
		getLoginBtn().click();
	}
	

	

}
