package com.nagarro.ExitTest.Tests;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nagarro.ExitTest.pages.HomePage;
import com.nagarro.ExitTest.utility.BaseTest;
import com.nagarro.ExitTest.utility.SeleniumHelper;

public class Test_GroupCompanies extends BaseTest {

	@Test(priority = 0, enabled = true)
	public void test_first_myntra() {
		logger.info("Stating test case: navigateToMyntraPage");
		test = extentReports.createTest("Stating test case: navigateToMyntraPage");

		HomePage homePage = new HomePage(driver);

		logger.info("navigating to myntra website");
		test.info("navigating to myntra website");

		try {
			homePage.clickMyntraLink();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			SeleniumHelper.waitUntilTitleContains(driver, "Myntra");

			Assert.assertTrue(driver.getTitle().contains("Myntra"));
			Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.myntra.com/"));
			test.pass("Test passed: navigated to myntra");
			logger.info("Test passed: navigated to myntra");

		} catch (Exception e) {
			logger.error("Test failed: navigation to myntra unsuccessful", e);
			test.fail("Test failed: navigation to myntra unsuccessful");
		}
	}

	@Test(priority = 1, enabled = true)
	public void test_second_cleartrip() {
		logger.info("Stating test case: navigateToCleartripPage");
		test = extentReports.createTest("Stating test case: navigateToCleartripPage");

		HomePage homePage = new HomePage(driver);

		logger.info("navigating to cleartrip website");
		test.info("navigating to cleartrip website");

		try {
			homePage.clickCleatripLink();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			Assert.assertTrue(driver.getTitle().contains("Cleartrip"));
			Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.cleartrip.com/"));
			test.pass("Test passed: navigated to cleartrip");
			logger.info("Test passed: navigated to cleartrip");

		} catch (Exception e) {
			logger.error("Test failed: navigation to cleartrip unsuccessful", e);
			test.fail("Test failed: navigation to cleartrip unsuccessful");
		}
	}

	@Test(priority = 2, enabled = true)
	public void test_third_shopsy() {
		logger.info("Stating test case: navigateToShopsyPage");
		test = extentReports.createTest("Stating test case: navigateToShopsyPage");

		HomePage homePage = new HomePage(driver);

		logger.info("navigating to shopsy website");
		test.info("navigating to shopsy website");

		try {
			homePage.clickShopsyLink();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			SeleniumHelper.waitUntilTitleContains(driver, "Shopsy");

			Assert.assertTrue(driver.getTitle().contains("Shopsy"));
			Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.shopsy.in/"));
			test.pass("Test passed: navigated to shopsy");
			logger.info("Test passed: navigated to shopsy");

		} catch (Exception e) {
			logger.error("Test failed: navigation to shopsy unsuccessful", e);
			test.fail("Test failed: navigation to shopsy unsuccessful");
		}
	}
}
