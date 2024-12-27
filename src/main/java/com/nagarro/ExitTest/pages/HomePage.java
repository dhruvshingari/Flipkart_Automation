package com.nagarro.ExitTest.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div._8S67Ib")
	private List<WebElement> adshow;

	@FindBy(xpath = "//input[@type='text']")
	private WebElement searchBox;

	@FindBy(xpath = "//a[@title='login']")
	private WebElement loginButton;

	@FindBy(linkText = "Cart")
	private WebElement cartButton;
	
	@FindBy(xpath = "//span[contains(text(),'Flight Bookings')]")
	private WebElement travelbutton;

	@FindBy(xpath = "//a[@title='Become a Seller']")
	private WebElement sellerButton;
	
	@FindBy(xpath = "//span[contains(text(),'Grocery')]")
	private WebElement grocerybutton;
	
	@FindBy(xpath = "//span[contains(text(),'Appliances')]")
	private WebElement appliancesbutton;
	
	@FindBy(xpath = "//a[@class='HlWMPX' and @aria-label='Terms Of Use']")
	private WebElement termsandconditionslink;

	public void search(String input) {
		searchBox.sendKeys(input);
		searchBox.submit();
	}

	public void navigateToCartPage() {
		cartButton.click();
	}

	public void navigateToSellerPage() {
		sellerButton.click();
	}
	
	public AppliancesPage navigateToAppliancesPage() {
		appliancesbutton.click();
		return new AppliancesPage(driver);
	}
	
	public void navigateToLoginPage() {
		loginButton.click();
	}
	
	public void navigateToTravelPage() {
		travelbutton.click();
	}
	
	public int countads() {
		return adshow.size();
	}
}
