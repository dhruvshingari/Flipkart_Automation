package com.nagarro.ExitTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@class='r4vIwl BV+Dqf']")
	private WebElement inputBox;

	@FindBy(xpath = "//button[@class='QqFHMw twnTnD _7Pd1Fp']")
	private WebElement requestotp;

	@FindBy(xpath = "//input[@class='r4vIwl IX3CMV']")
	private WebElement OTPinput;

	public void fill_EmailOrMobileNumber(String input) {
		inputBox.sendKeys(input);
	}

	public void click_requesttop() {
		requestotp.click();
	}

	public boolean isOTPinputVisible() {
		return OTPinput.isDisplayed();
	}
}
