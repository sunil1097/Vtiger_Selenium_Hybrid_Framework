package com.crm.vtiger.products;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.vtiger.pages.ProductPage;
import com.crm.vtiger.pages.VerifyProductPage;
import com.crm.vtiger.utility.BaseClass;
import com.crm.vtiger.utility.FileUtility;
import com.crm.vtiger.utility.JavaUtility;

@Listeners(com.crm.vtiger.listeners.ExtentReportListener.class)
public class ProductFlowTest extends BaseClass {
	String createdAProductName;
	String productName;
	String updatedProductName;
	ProductPage pp;
	VerifyProductPage vp;

	@Test(priority = 1, groups = { "smoke", "product" })
	public void createProductTest() throws IOException, InterruptedException {
		// getting data from Prop file by utility file
		FileUtility fUtil = new FileUtility();

		// getting data from excel by utility file
		String product = fUtil.getDataFromExcelFile("Product", 1, 0);
		// click on Product module
		hp.navigateToProduct();
		// click + for creating a product
		pp = new ProductPage(driver);
		pp.createProduct(product);
		// enter the product name
		createdAProductName = product + JavaUtility.getRandomNumber();
		pp.createProduct(createdAProductName);
		System.out.println("Title after product creation: " + driver.getTitle());
		// Verifying created product
		vp = new VerifyProductPage(driver);
		String nameOfProduct = vp.getProductNameText();
		boolean status = nameOfProduct.equals(createdAProductName);
		Assert.assertTrue(status);
		Reporter.log("successfully created a product ");

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
