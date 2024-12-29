package com.nagarro.ExitTest.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

	@FindBy(xpath = "//a[@title='Login']")
	private WebElement loginButton;

	@FindBy(linkText = "Cart")
	private WebElement cartButton;

	@FindBy(xpath = "//span[contains(text(),'Flight Bookings')]")
	private WebElement travelbutton;

	@FindBy(xpath = "//a[@title='Become a Seller']")
	private WebElement sellerButton;

	@FindBy(xpath = "//a[@title = 'Dropdown with more help links']")
	private WebElement helplinks;

	@FindBy(xpath = "//a[@title = '24x7 Customer Care']")
	private WebElement customersupport;

	@FindBy(xpath = "//span[contains(text(),'Grocery')]")
	private WebElement grocerybutton;

	@FindBy(xpath = "//span[contains(text(),'Appliances')]")
	private WebElement appliancesbutton;

	@FindBy(xpath = "//a[@class='HlWMPX' and @aria-label='Terms Of Use']")
	private WebElement termsandconditionslink;

	@FindBy(linkText = "Myntra")
	private WebElement myntraButton;

	@FindBy(linkText = "Cleartrip")
	private WebElement cleartripButton;

	@FindBy(linkText = "Shopsy")
	private WebElement shopsyButton;

	public void search(String input) {
		searchBox.sendKeys(input);
		searchBox.submit();
	}

	public void clickCartPageLink() {
		cartButton.click();
	}

	public void clickSellerPageLink() {
		sellerButton.click();
	}

	public AppliancesPage clickAppliancesPageLink() {
		appliancesbutton.click();
		return new AppliancesPage(driver);
	}

	public void clickLoginPageLink() {
		loginButton.click();
	}

	public void clickTravelPageLink() {
		travelbutton.click();
	}

	public int countads() {
		return adshow.size();
	}

	public void clickTermsAndConditionsLink() {
		termsandconditionslink.click();
	}

	public void clickMyntraLink() {
		myntraButton.click();
	}

	public void clickCleatripLink() {
		cleartripButton.click();
	}

	public void clickShopsyLink() {
		shopsyButton.click();
	}

	public void clickHelpLinks() {
		helplinks.click();
	}

	public void clickCustomerSupport() {
		customersupport.click();
		;
	}

	public void clickGroceryLink() {
		grocerybutton.click();
	}
}
