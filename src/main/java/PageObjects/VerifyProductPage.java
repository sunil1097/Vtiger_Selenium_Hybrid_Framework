package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerifyProductPage {
	WebDriver driver;
	
	public VerifyProductPage(WebDriver driver) {
		this.driver=driver;
		//initialization the Page Fac
		PageFactory.initElements(driver, this);	
	}
	@FindBy(id="dtlview_Product Name")
	private WebElement productText;
	
	public WebElement getProductText() {
		return productText;
	}
	public String getProductNameText() {
		return productText.getText();
	}

}
