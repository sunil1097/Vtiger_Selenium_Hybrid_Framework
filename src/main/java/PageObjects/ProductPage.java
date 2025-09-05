package PageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.vtiger.generic_utility.WebDriverUtility;


public class ProductPage {
	WebDriver driver;
	WebDriverUtility util;
	
	public ProductPage(WebDriver driver){
		this.driver=driver;
		this.util = new WebDriverUtility(driver);
		PageFactory.initElements(driver,this);
	}
	
	// click on create product button
	@FindBy(xpath="//img[@title='Create Product...']")
	private WebElement btnCreateProduct;

	// click on product name input 
	@FindBy(name="productname") 
	private WebElement txtProductField;

	
	// click on save button 
	@FindBy(xpath="//input[@type='submit']")
	private WebElement btnSaveProduct;

	
	// Edit the Product 
	
	// search productname in search field 
	@FindBy(name="search_text")
	private WebElement txtSearchProduct;
	
	// click on dropdown menu select ,select by product name 
	@FindBy(id ="bas_searchfield")
	private WebElement ddProductName;
		
	
	// click on search now button 
	
	@FindBy(name="submit")
	private WebElement btnSearchNow;

		
	// click on product row from product detail page 
	@FindBy(linkText ="edit")
	private WebElement lnkEditAction;
		
	
	// save button 
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement btnSaveEdit;

	// delete a product 
	
	@FindBy(linkText="del")
	private WebElement lnkDeleteAction;
	
	// Business Action : for creating a product
	
	public void createProduct(String productName) {
		btnCreateProduct.click();
		txtProductField.sendKeys(productName);
		btnSaveProduct.click();
	}
	// business Action   : for Editing Created Product 
	public void editTheSelectProductName(String createdAProductName,String updatedProductName) {
		txtSearchProduct.sendKeys(createdAProductName);
		util.selectByVisibleText(ddProductName,"Product Name");
		btnSearchNow.click();
		lnkEditAction.click();
		txtProductField.clear();
		txtProductField.sendKeys(updatedProductName);
		btnSaveEdit.click();
	}
	// Business Action : for Deleting Edited Product 
	public void clickDeleteAProduct(String productName) {
		txtSearchProduct.sendKeys(productName);
		util.selectByVisibleText(ddProductName,"Product Name");
		btnSearchNow.click();
		lnkDeleteAction.click();
		util.acceptAlert();
		
	}
}
