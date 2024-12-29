package com.nagarro.ExitTest.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nagarro.ExitTest.utility.SeleniumHelper;

public class FlightsSearchPage {

	private WebDriver driver;

	public FlightsSearchPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(xpath = "//span[@class = 'PH2SxH']")
	private List<WebElement> location;

	@FindBy(xpath = "//div[@class='Do40aG']")
	private List<WebElement> bookButtonOneWay;

	@FindBy(xpath = "//div[@class='_7QayNj']")
	private WebElement bookButtonRoundTrip;

	public List<WebElement> flightLocation() {
		SeleniumHelper.waitForAllElementsToBeVisible(driver, location);
		return location;
	}

	public List<WebElement> clickBookButtonOneWay() {
		return bookButtonOneWay;
	}

	public WebElement clickBookButtonRoundTrip() {
		return bookButtonRoundTrip;
	}

}
