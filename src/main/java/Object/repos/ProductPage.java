package Object.repos;


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
		PageFactory.initElements(driver,this);
	}
	
	// click on create product button
	@FindBy(xpath="//img[@title='Create Product...']")
	private WebElement createAPro;
	
	public WebElement getCreateProductBtn() {
		return createAPro;
	}
	// click on product name input 
	@FindBy(name="productname") 
	private WebElement productName;
	public WebElement getProductName() {
		return productName;
	}
	
	// click on save button 
	@FindBy(xpath="//input[@type='submit']")
	private WebElement SaveProductBtn;

	public WebElement getSaveProductBtn() {
		return SaveProductBtn;
		
	}
	
	// Edit the Product 
	
	// search productname in search field 
	@FindBy(name="search_text")
	private WebElement searchText;
	
	public WebElement getSearchbtn() {
		return searchText;
	}
	
	// click on dropdown menu select ,select by product name 
	@FindBy(id ="bas_searchfield")
	private WebElement orgDropDown;
		
	public WebElement getSearchDropDown() {
		return orgDropDown;
	}
	
	
	// click on search now button 
	
	@FindBy(name="submit")
	private WebElement clickSearchBtn;
	public WebElement getClickOnSearchBtn() {
		return clickSearchBtn;
	}
		
	// click on product row from product detail page 
	@FindBy(linkText ="edit")
	private WebElement clickOnProductDetailPage;
	public WebElement  getClickOnProductEditBtn() {
		return clickOnProductDetailPage;
	}
		
	
	// save button 
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	// delete a product 
	
	@FindBy(linkText="del")
	private WebElement delLink;
	public WebElement getDelLink() {
		return delLink;
	}
	
	// Business utility 0 : for creating a product
	
	public void createProduct(String createdAProductName) {
		getCreateProductBtn().click();
		getProductName().sendKeys(createdAProductName);
		getSaveProductBtn().click();
	}
	// business utility 1 : for searching and and selecting by product name to edit 
	public void editTheSelectProductName(String productName) {
		getSearchbtn().clear();
		getSearchbtn().sendKeys(productName);
		util= new WebDriverUtility(driver);
		getSearchDropDown().click();
		util.selectByVisibleText(orgDropDown,"Product Name");
		getClickOnSearchBtn().click();
		getClickOnProductEditBtn().click();
	}
	
	// edit the details of product page 
	// passed  String updatedProductName in the method so we can directly use in main script 
	
	public void editTheDetailsOfProductPage(String updatedProductName) {
		
		getProductName().clear();
		getProductName().sendKeys(updatedProductName);
		getSaveBtn().click();
	}
	
	public void clickDeleteAProduct(String productName) {
		getSearchbtn().clear();
		getSearchbtn().sendKeys(productName);
		util= new WebDriverUtility(driver);
		getSearchDropDown().click();
		util.selectByVisibleText(orgDropDown,"Product Name");
		getClickOnSearchBtn().click();
		getDelLink().click();
		util.handleAlertAccept();
		
	}

	//business utility 
			public void navigateToCreateProductbtn() {
				createAPro.click();	
			}
}
