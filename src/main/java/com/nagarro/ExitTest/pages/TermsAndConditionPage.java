package com.nagarro.ExitTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class TermsAndConditionPage {
	
	@FindBy(xpath = "//strong[contains(text(),'Flipkart Terms of Use')]")
	private WebElement pageHeading;
	
	WebDriver driver;

	public TermsAndConditionPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public WebDriver termsAndConditionPageTitle() {
		return this.driver;
	}
	
	public WebElement pageHeadingVisible() {
		return pageHeading;
	}
	
}
