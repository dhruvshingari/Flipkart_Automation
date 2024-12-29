package com.nagarro.ExitTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nagarro.ExitTest.utility.SeleniumHelper;

public class AppliancesPage {

	private WebDriver driver;

	public AppliancesPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(xpath = "//h1[@class = '_0FYq1K']")
	private WebElement appliancespageheading;

	public WebElement appliancesPageHeading() {
		SeleniumHelper.waitForElementToBeVisible(driver, appliancespageheading);
		return appliancespageheading;
	}

}
