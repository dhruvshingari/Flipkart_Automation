package com.nagarro.ExitTest.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nagarro.ExitTest.pages.HomePage;
import com.nagarro.ExitTest.pages.SearchPage;
import com.nagarro.ExitTest.pages.TermsAndConditionPage;
import com.nagarro.ExitTest.utility.BaseTest;
import com.nagarro.ExitTest.utility.DataFetcher;
import com.nagarro.ExitTest.utility.SeleniumHelper;

public class Test_Home extends BaseTest {

	@Test(priority = 0, enabled = true)
	public void test_HomePageIsVisible() {

		logger.info("Starting test case: verifyHomePageIsVisibleSuccessfully");
		test = extentReports.createTest("Stating test case");

		logger.info("Verifying if the home page is visible");
		test.info("Verifying home page is visible or not");

		try {
			int countAdShow = new HomePage(driver).countads();

			Assert.assertTrue(countAdShow > 0, "Home page not visible");
			Assert.assertEquals(driver.getTitle(), configReader.getProperty("homePage.title"));
			logger.info("Test passed: Home page is visible");
			test.pass("Test passed: Home page is visible");

		} catch (Exception e) {
			logger.error("Test failed: Home page is not visible", e);
			test.fail("Test failed: Home page is not visible");
		}
	}

	@Test(priority = 1,dataProvider = "searchData", dataProviderClass = DataFetcher.class, enabled = true,dependsOnMethods = { "test_HomePageIsVisible" })
	public void test_search(String input) {

		logger.info("Starting test case: searchForAProduct");
		test = extentReports.createTest("Stating test case: searchForAProduct");

		HomePage homePage = new HomePage(driver);

		logger.info("search functionality test");
		test.info("search functionality test");

		homePage.search(input);

		SearchPage searchPage = new SearchPage(driver);

		try {
			Assert.assertTrue(searchPage.productsListSize() > 0);
			logger.info("Test passed: Searched product is visible");
			test.pass("Test passed: Searched product is visible");

		} catch (Exception e) {
			logger.error("Test failed: Searched product is not visible", e);
			test.fail("Test failed: Searched product is not visible");

		}
	}

	@Test(priority = 2, dependsOnMethods = { "test_HomePageIsVisible" }, enabled = true)
	public void test_navigateToCartPage() {

		logger.info("Stating test case: navigateTocartPage");
		test = extentReports.createTest("Stating test case: navigateTocartPage");

		HomePage homePage = new HomePage(driver);

		logger.info("navigating to cart page");
		test.info("navigating to cart page");

		try {
			homePage.clickCartPageLink();
			SeleniumHelper.waitUntilTitleChanges(driver, configReader.getProperty("cartPage.title"));
			Assert.assertTrue(driver.getTitle().contains("Shopping Cart"));
			test.pass("Test passed: cart page is visible");
			logger.info("Test passed: cart page is visible");

		} catch (Exception e) {
			logger.error("Test failed: cart page is not visible", e);
			test.fail("Test failed: cart page is not visible");

		}
	}

	@Test(priority = 3, dependsOnMethods = { "test_HomePageIsVisible" }, enabled = true)
	public void test_navigateToSellerPage() {
		logger.info("Stating test case: navigateToSellerPage");
		test = extentReports.createTest("Stating test case: navigateToSellerPage");

		HomePage homePage = new HomePage(driver);

		logger.info("navigating to seller page");
		test.info("navigating to seller page");

		try {
			homePage.clickSellerPageLink();
			SeleniumHelper.waitUntilTitleChanges(driver, configReader.getProperty("sellerPage.title"));
			Assert.assertTrue(driver.getPageSource().contains("Sell Online with Flipkart"));
			test.pass("Test passed: seller page is visible");
			logger.info("Test passed: seller page is visible");

		} catch (Exception e) {
			logger.error("Test failed: seller page is not visible", e);
			test.fail("Test failed: seller page is not visible");

		}
	}

	@Test(priority = 4, dependsOnMethods = { "test_HomePageIsVisible" }, enabled = true)
	public void test_navigateToAppliancesPage() {

		logger.info("Stating test case: navigateToAppliancesPage");
		test = extentReports.createTest("Stating test case: navigateToAppliancesPage");

		HomePage homePage = new HomePage(driver);

		logger.info("navigating to appliances page");
		test.info("navigating to appliances page");
		try {
			boolean appliancespage = homePage.clickAppliancesPageLink().appliancesPageHeading().isDisplayed();

			Assert.assertTrue(appliancespage, "Appliances page is not visible");
			test.pass("Test passed: Appliances page is visible");
			logger.info("Test passed: Appliances page is visible");
		} catch (Exception e) {
			test.fail("Test failed: Appliances page is not visible");
			logger.info("Test failed: Appliances page is not visible", e);

		}

	}

	@Test(priority = 5, dependsOnMethods = { "test_HomePageIsVisible" }, enabled = true)
	public void test_navigateToFlightPage() {
		logger.info("Stating test case: navigateToFlightPage");
		test = extentReports.createTest("Stating test case: navigateToflightPage");

		HomePage homePage = new HomePage(driver);

		logger.info("navigating to flight page");
		test.info("navigating to flight page");

		try {
			homePage.clickTravelPageLink();
			;
			SeleniumHelper.waitUntilTitleChanges(driver, configReader.getProperty("flightsPage.title"));
			Assert.assertTrue(driver.getPageSource().contains("Flight bookings"));
			test.pass("Test passed: flights page is visible");
			logger.info("Test passed: flights page is visible");

		} catch (Exception e) {
			logger.error("Test failed: flights page is not visible", e);
			test.fail("Test failed: flights page is not visible");
		}
	}

	@Test(priority = 6, dependsOnMethods = { "test_HomePageIsVisible" }, enabled = true)
	public void test_navigateToTermsAndConditionsPage() {

		logger.info("Stating test case: navigateToTermsAndCondtionsPage");
		test = extentReports.createTest("Stating test case: navigateToTermsAndCondtionsPage");

		HomePage homePage = new HomePage(driver);
		homePage.clickTermsAndConditionsLink();

		logger.info("Verifying page is displayed or not");
		test.info("Verifying page is displayed or not");
		try {

			TermsAndConditionPage termsAndConditionPage = new TermsAndConditionPage(driver);
			String expectedTitle = configReader.getProperty("termsAndConditionPage.title");
			String actualTitle = driver.getTitle();

			Assert.assertTrue(termsAndConditionPage.pageHeadingVisible().isDisplayed());
			Assert.assertEquals(actualTitle, expectedTitle, "Title not matched");
			test.pass("Test case passed: Page is visible");
			logger.info("Test case passed: Page is visible");
		} catch (Exception e) {
			test.fail("Test case failed: Page not displayed");
			logger.error("Test case failed: Page not displayed", e);
		}
	}
}
