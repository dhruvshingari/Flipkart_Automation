package com.nagarro.ExitTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nagarro.ExitTest.utility.SeleniumHelper;

public class SupportPage {

	WebDriver driver;

	public SupportPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(xpath = "//h1[@class = 'ogUXNW']")
	private WebElement supportpageheading;

	@FindBy(xpath = "//span[contains(text(),'Delivery related')]")
	private WebElement helptopic_delivery;

	@FindBy(xpath = "//span[contains(text(),'Refunds related')]")
	private WebElement helptopic_refund;

	@FindBy(xpath = "//div[@class = 'q7cqIL']")
	private WebElement topicselected;

	public WebElement supportPageHeading() {
		SeleniumHelper.waitForElementToBeVisible(driver, supportpageheading);
		return supportpageheading;
	}

	public WebElement deliveryHelpTopic() {
		SeleniumHelper.waitForElementToBeClickable(driver, helptopic_delivery);
		return helptopic_delivery;
	}

	public WebElement refundHelpTopic() {
		SeleniumHelper.waitForElementToBeClickable(driver, helptopic_refund);
		return helptopic_refund;
	}

	public WebElement topicVisible() {
		SeleniumHelper.waitForElementToBeVisible(driver, topicselected);
		return topicselected;
	}

}
