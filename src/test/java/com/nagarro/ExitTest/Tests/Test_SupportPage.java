package com.nagarro.ExitTest.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nagarro.ExitTest.pages.HomePage;
import com.nagarro.ExitTest.pages.SupportPage;
import com.nagarro.ExitTest.utility.BaseTest;

public class Test_SupportPage extends BaseTest {

	@Test(enabled = true)
	public void verifyHelpTopics() {
		logger.info("Starting test: VerifySupportPageTopics");
		test = extentReports.createTest("Starting test: VerifySupportPageTopics");

		supportPageIsVisible();
		deliveryRelatedTopicIsVisible();
		refundsRelatedTopicIsVisible();

		logger.info("Completed test: VerifySupportPageTopics");
	}

	private void supportPageIsVisible() {
		test.info("Verifying support page is visible or not.");
		logger.info("Verifying support page is visible or not.");

		try {
			new HomePage(driver).clickHelpLinks();
			new HomePage(driver).clickCustomerSupport();

			test.pass("Customer support button clicked");
			logger.info("Customer support button clicked");
		} catch (Exception e) {
			test.fail("Not able to click customer support button");
			logger.error("Not able to click customer support button", e);
		}
		try {
			boolean pageVisible = new SupportPage(driver).supportPageHeading().isDisplayed();

			Assert.assertTrue(pageVisible, "Support page is not visible");

			test.pass("Test case passed: Customer support page is visible");
			logger.info("Test case passed: Customer support page is visible");
		} catch (Exception e) {
			test.fail("Test case failed: Support page not visible");
			logger.error("Test case failed: Support page not visible", e);

		}
	}

	private void deliveryRelatedTopicIsVisible() {

		test.info("Verifying delivery topic is visible");
		logger.info("Verifying delivery topic is visible");

		try {
			new SupportPage(driver).deliveryHelpTopic().click();

			test.pass("Help topic delivery related clicked");
			logger.info("Help topic delivery related clicked");
		} catch (Exception e) {
			test.fail("Not able to click help topic delivery related");
			logger.error("Not able to click help topic delivery related", e);
		}

		try {
			String topicClicked = new SupportPage(driver).deliveryHelpTopic().getText();
			String topicVisible = new SupportPage(driver).topicVisible().getText();

			Assert.assertEquals(topicClicked, topicVisible, topicClicked + " topic not visible");

			test.pass("Help topic delivery related is visible");
			logger.info("Help topic delivery related is visible");
		} catch (Exception e) {
			test.fail("Help topic delivery related not visible");
			logger.error("Help topic delivery related not visible", e);

		}
	}

	private void refundsRelatedTopicIsVisible() {

		test.info("Verifying refund topic is visible");
		logger.info("Verifying refund topic is visible");

		try {
			new SupportPage(driver).refundHelpTopic().click();
			test.pass("Help topic refund related clicked");
			logger.info("Help topic refund related clicked");
		} catch (Exception e) {
			test.fail("Not able to click help topic refund related");
			logger.error("Not able to click help topic refund related", e);
		}

		try {
			String topicClicked = new SupportPage(driver).refundHelpTopic().getText();
			String topicVisible = new SupportPage(driver).topicVisible().getText();

			Assert.assertEquals(topicClicked, topicVisible, topicClicked + " topic not visible");

			test.pass("Help topic refund related is visible");
			logger.info("Help topic refund related is visible");
		} catch (Exception e) {
			test.fail("Help topic refund related not visible");
			logger.error("Help topic refund related not visible", e);
		}
	}
}
