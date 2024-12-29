package com.nagarro.ExitTest.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nagarro.ExitTest.pages.HomePage;
import com.nagarro.ExitTest.pages.SellerPage;
import com.nagarro.ExitTest.utility.BaseTest;

public class Test_SellerPage extends BaseTest {

	@Test(enabled = true)
	public void test_navigateToSellerDashboard() {
		logger.info("Stating test case: navigateToSellerDashboard");
		test = extentReports.createTest("Stating test case: navigateToSellerDashboard");

		try {
			HomePage homePage = new HomePage(driver);

			logger.info("navigating to seller page");
			test.info("navigating to seller page");

			homePage.clickSellerPageLink();

			SellerPage sellerPage = new SellerPage(driver);
			sellerPage.goToSellerDashboard();

			Assert.assertEquals(driver.getTitle(), "Seller Dashboard");
			Assert.assertTrue(driver.getCurrentUrl().contains("accountCreation"));

			test.pass("Test pass :navigated to seller dashboard");
			logger.info("Test pass :navigated to seller dashboard");
		} catch (Exception e) {
			test.fail("test failed : seller dashboard not reached");
			logger.info("test failed : seller dashboard not reached", e);
		}

	}
}
