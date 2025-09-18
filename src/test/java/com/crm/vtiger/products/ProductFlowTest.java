package com.crm.vtiger.products;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.vtiger.pages.ProductPage;
import com.crm.vtiger.pages.VerifyProductPage;
import com.crm.vtiger.utility.BaseClass;
import com.crm.vtiger.utility.JavaUtility;

@Listeners(com.crm.vtiger.listeners.ExtentReportListener.class)
public class ProductFlowTest extends BaseClass {
	String createdAProductName;
	String productName;
	String updatedProductName;
	ProductPage pp;
	VerifyProductPage vp;

	@Test(priority = 1, groups = { "smoke",
			"product" }, dataProvider = "productData", dataProviderClass = com.crm.vtiger.dataProvider.ProductDataProvider.class)
	public void createProductTest(String ProductNameFromExcel) throws IOException, InterruptedException {
		if (hp == null) {
			throw new IllegalStateException("HomePage not initialized. Login might have failed.");
		}
		hp.navigateToProduct();

		pp = new ProductPage(driver);

		createdAProductName = ProductNameFromExcel + JavaUtility.getRandomNumber();
		pp.createProduct(createdAProductName);
		System.out.println("Title after product creation: " + driver.getTitle());
		// Verifying created product
		vp = new VerifyProductPage(driver);
		String nameOfProduct = vp.getProductNameText();
		Assert.assertTrue(nameOfProduct.equals(createdAProductName));
		Reporter.log("successfully created a product " + createdAProductName, true);

	}
	// Edit a product

	@Test(priority = 2, dependsOnMethods = "createProductTest", groups = { "sanity", "functional", "product" })
	public void editExistingProductTest() {
		hp.navigateToProduct();
		pp = new ProductPage(driver);
		String updatedProductName = "sunil" + JavaUtility.getRandomNumber();
		pp.editTheSelectProductName(createdAProductName, updatedProductName);

		Reporter.log("successfully edited ", true);
		// so now delete method can access and delete
		createdAProductName = updatedProductName;
	}

	// delete a product
	@Test(priority = 3, dependsOnMethods = "editExistingProductTest", groups = { "regression", "product" })
	public void deleteExistingProductTest() {
		hp.navigateToProduct();
		pp = new ProductPage(driver);
		pp.clickDeleteAProduct(createdAProductName);
		Reporter.log("successfully deleted a product", true);
	}

}
