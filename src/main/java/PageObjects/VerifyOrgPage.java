package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerifyOrgPage {
	WebDriver driver;
	
	public VerifyOrgPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="dtlview_Organization Name")
	private WebElement OrgNameVerify;
	public String getOrgNameVerify() {
		return OrgNameVerify.getText();
	}

}
