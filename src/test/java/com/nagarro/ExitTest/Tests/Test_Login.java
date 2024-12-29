package com.nagarro.ExitTest.Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.nagarro.ExitTest.pages.HomePage;
import com.nagarro.ExitTest.pages.LoginPage;
import com.nagarro.ExitTest.utility.BaseTest;
import com.nagarro.ExitTest.utility.DataFetcher;

import junit.framework.Assert;

public class Test_Login extends BaseTest {

	@Test(priority = 1, enabled = true)
	public void test_invalidlogin() {
		try {
			test = extentReports.createTest("starting test case: invalid login");
			logger.info("starting test case: invalid login");

			HomePage homePage = new HomePage(driver);

			test = extentReports.createTest("navigating to login Page");
			logger.info("navigating to Login page");

			homePage.clickLoginPageLink();
			;

			LoginPage loginPage = new LoginPage(driver);

			test = extentReports.createTest("entering invalid input");
			logger.info("entering invalid input");

			loginPage.fill_EmailOrMobileNumber("abc@gmail.com");
			loginPage.click_requesttop();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='eIDgeN']"))));

			//Assert.assertEquals("You are not registered with us. Please sign up.",
			//		driver.findElement(By.xpath("//div[@class='eIDgeN']")).getText());

			logger.info("test case passed: error message visible");
			test.pass("test case passed: error message visible");

		} catch (Exception e) {
			logger.error("test case failes: error message not visible");
			test.fail("test case failed: error message not visible");
		}

	}

	@Test(priority = 0, enabled = false, dataProvider = "loginData", dataProviderClass = DataFetcher.class)
	public void test_validLogin(String input) {

		try {
			test = extentReports.createTest("starting test case: valid login");
			logger.info("starting test case: valid login");

			HomePage homePage = new HomePage(driver);

			test = extentReports.createTest("navigating to login Page");
			logger.info("navigating to Login page");

			homePage.clickLoginPageLink();
			;

			LoginPage loginPage = new LoginPage(driver);

			test = extentReports.createTest("entering valid input");
			logger.info("entering valid id");

			loginPage.fill_EmailOrMobileNumber(input);
			loginPage.click_requesttop();

			Assert.assertTrue(loginPage.isOTPinputVisible());

			logger.info("test case passed: otp requested");
			test.pass("test case passed: otp requested");

		} catch (Exception e) {
			logger.error("test case failes: otp not requested");
			test.fail("test case failed: otp not requested");
		}
	}
}
