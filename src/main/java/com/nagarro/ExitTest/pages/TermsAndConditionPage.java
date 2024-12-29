package com.nagarro.ExitTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TermsAndConditionPage {

	@FindBy(id = "flipkart-terms-of-use")
	private WebElement pageHeading;

	WebDriver driver;

	public TermsAndConditionPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public WebElement pageHeadingVisible() {
		return pageHeading;
	}

}
