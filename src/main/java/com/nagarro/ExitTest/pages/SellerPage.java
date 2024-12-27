package com.nagarro.ExitTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nagarro.ExitTest.utility.SeleniumHelper;


public class SellerPage {

	@FindBy(xpath = "//img[contains(@class,'styles__HeaderImage-sc-155ljwi-1')]")
	private WebElement flipkartsellerlogo;
	
	@FindBy(xpath = "//button[contains(@class,'jSCZCD')]")
	private WebElement startsellingbutton;

	WebDriver driver;

	public SellerPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public void sellerLogo() {
		SeleniumHelper.waitForElementToBeVisible(driver, flipkartsellerlogo);
		flipkartsellerlogo.click();
	}
	
	public void startSellingButton() {
		 startsellingbutton.click();;
	}
	
	public void goToSellerDashboard() {
		startsellingbutton.click();
	}
}
