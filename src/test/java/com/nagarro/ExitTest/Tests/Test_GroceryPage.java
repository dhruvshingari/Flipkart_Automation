package com.nagarro.ExitTest.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nagarro.ExitTest.pages.GroceryPage;
import com.nagarro.ExitTest.pages.HomePage;
import com.nagarro.ExitTest.utility.BaseTest;
import com.nagarro.ExitTest.utility.DataFetcher;

public class Test_GroceryPage extends BaseTest {

	@Test(enabled = true,dataProvider = "groceryData", dataProviderClass = DataFetcher.class)
	public void test_searchGroceryItem(String input) {

		logger.info("Starting test case: SearchGroceryItem");
		test = extentReports.createTest("Stating test case: SearchGroceryItem");

		HomePage homePage = new HomePage(driver);

		test.info("navigating to grocery page");
		logger.info("navigating to grocery page");

		homePage.clickGroceryLink();
		GroceryPage groceryPage = new GroceryPage(driver);
		try {
			test.info("Verifying Grocery page is visible or not");
			logger.info("Verifying Grocery page is visible or not");

			Assert.assertTrue(groceryPage.isGroceryimagedisplayed(), "Grocery page is not displayed");
		} catch (Exception e) {
			test.fail("Test failed: Grocery page is not visible");
			logger.error("Test failed: Grocery page is not visible");
		}
		String pincode = configReader.getProperty("pincode");
		test.info("Entering pincode " + pincode);
		logger.info("Entering pincode " + pincode);

		try {
			groceryPage.enterPincode(pincode);
		} catch (Exception e) {
			test.fail("Not able to enter pincode");
			logger.error("Not able to enter pincode", e);
		}
		String item = input;

		test.info("Searching for item:");
		logger.info("Searching for item:");
		try {

			groceryPage.searchProduct(item);

			Assert.assertTrue(groceryPage.showingResult().isDisplayed());
			Assert.assertEquals(groceryPage.showingResult().getText(), item, "Searched Result not visible");

			test.pass("Test case passed: Searched product " + item + " is visible");
			logger.info("Test case passed: Searched product " + item + " is visible");

		}

		catch (Exception e) {
			test.fail("Searched product not visible");
			logger.error("Searched product not visible", e);
		}
	}

}
