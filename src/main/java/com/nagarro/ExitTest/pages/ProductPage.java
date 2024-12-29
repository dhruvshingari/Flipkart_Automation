package com.nagarro.ExitTest.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nagarro.ExitTest.utility.SeleniumHelper;

public class ProductPage {

	WebDriver driver;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='VU-ZEz']")
	private WebElement productName;

	@FindBy(xpath = "//button[@class='QqFHMw cNEU5Q J9Kkbj _7Pd1Fp']")
	private WebElement addToCart;

	@FindBy(xpath = "//div[@class = '_8X-K8p']")
	private List<WebElement> productincart;

	public void clickAddToCart() {

		SeleniumHelper.presenceOfElementLocatedBy(driver, By.xpath("//button[@class='QqFHMw cNEU5Q J9Kkbj _7Pd1Fp']"));
		addToCart.click();
	}

	public int totalProductInCart() {
		return productincart.size();
	}

	public String getProductName() {
		SeleniumHelper.stalenessOf(driver, productName);
		return productName.getText();
	}
}
