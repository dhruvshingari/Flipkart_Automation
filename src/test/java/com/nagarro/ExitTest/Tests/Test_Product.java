package com.nagarro.ExitTest.Tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nagarro.ExitTest.pages.HomePage;
import com.nagarro.ExitTest.pages.ProductPage;
import com.nagarro.ExitTest.pages.SearchPage;
import com.nagarro.ExitTest.utility.BaseTest;
import com.nagarro.ExitTest.utility.SeleniumHelper;

public class Test_Product extends BaseTest {

	@Test(priority = 0, enabled = true)
	public void sortProductByPriceLowToHigh() {

		logger.info("Starting test case: sortProductByPriceLowToHigh");
		test = extentReports.createTest("Stating test case: sortProductByPriceLowToHigh");

		HomePage homePage = new HomePage(driver);

		logger.info("searching for product");
		test.info("search for products");

		homePage.search("mobiles");

		test.info("Trying to sort product by price low to high");
		logger.info("Trying to sort product by price low to high");
		try {

			SearchPage searchPage = new SearchPage(driver);
			searchPage.clickLowToHighPriceButton();

			test.info("Verifying the result");
			logger.info("Verifying the result");

			List<Integer> list_product_price = searchPage.sortProductsPriceList();

			if (list_product_price == null) {
				logger.warn("Product prices list is null");
				throw new Exception("Product prices list is null");
			}

			List<Integer> sorted_list_product_price = new ArrayList<>(list_product_price);

			Collections.sort(sorted_list_product_price);

			Assert.assertEquals(list_product_price, sorted_list_product_price, "Product not sorted by price");
			test.pass("Sorted by price low to high");
			logger.info("Sorted by price low to high");

		} catch (Exception e) {
			test.fail("Test case failed: Product not sorted by price");
			logger.error("Test case failed: Product not sorted by price", e);

		}
	}

	@Test(priority = 1, enabled = true)
	public void verify_addToWishlistRequiresLogin() {

		test = extentReports.createTest("Starting test case: addToCart");
		logger.info("Starting test case: addToCart");

		HomePage homePage = new HomePage(driver);

		try {
			test.info("searching for product");
			logger.info("searching for product");
			homePage.search("pixel 9");

			SearchPage searchPage = new SearchPage(driver);

			searchPage.clickWishlistButton();

			boolean buttonDisplayed = searchPage.OTPButton().isDisplayed();

			Assert.assertTrue(buttonDisplayed, "Request OTP Button not displayed");

			searchPage.clickCrossButton();

			test.pass("Test case passes: addToWishlistRequiresLogin");
			logger.info("Test case passes: addToWishlistRequiresLogin");
		} catch (Exception e) {
			test.fail("Test case failed: addToWishlistRequiresLogin");
			logger.error("Test case failed: addToWishlistRequiresLogin", e);

		}
	}

	@Test(priority = 2, enabled = true)
	public void addToCart() {
		logger.info("Starting test case: addToCart");
		test = extentReports.createTest("Starting test case: addToCart");

		HomePage homePage = new HomePage(driver);

		try {
			test.info("searching for product");
			logger.info("searching for product");
			homePage.search("pixel 9");

			SearchPage searchPage = new SearchPage(driver);

			test.info("navigating to product page");
			logger.info("navigating to product page");

			searchPage.clickFirstProduct();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));

			// Get all window handles
			ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

			// Switch to the new tab
			driver.switchTo().window(tabs.get(1));

			ProductPage productPage = new ProductPage(driver);

			productPage.clickAddToCart();

			SeleniumHelper.waitUntilTitleChanges(driver, configReader.getProperty("cartPage.title"));
			Assert.assertTrue(driver.getTitle().contains("Shopping Cart"));
			Assert.assertTrue(productPage.totalProductInCart() > 0);

			test.pass("Test case passes: Product added to cart");
			logger.info("Test case passes: Product added to cart");
		} catch (Exception e) {
			test.fail("Test case failed: Product not added to cart");
			logger.error("Test case failed: Product not added to cart", e);
		}
	}
}
