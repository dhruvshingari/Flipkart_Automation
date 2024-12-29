package com.nagarro.ExitTest.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nagarro.ExitTest.pages.FlightsPage;
import com.nagarro.ExitTest.pages.FlightsSearchPage;
import com.nagarro.ExitTest.pages.HomePage;
import com.nagarro.ExitTest.utility.BaseTest;

public class Test_FlightsPage extends BaseTest {

	@Test(priority = 0, enabled = true)
	public void test_searchOneWayFlight() {
		logger.info("Stating test case: OneWayFlight");
		test = extentReports.createTest("Stating test case: OneWayFlight");

		try {

			HomePage homePage = new HomePage(driver);

			logger.info("navigating to flight page");
			test.info("navigating to flight page");

			homePage.clickTravelPageLink();

			FlightsPage flightsPage = new FlightsPage(driver);

			flightsPage.selectOneWay();

			logger.info("searching for one way flight");
			test.info("searching for one way flight");

			flightsPage.selectDepartCity(configReader.getProperty("depart.city"));
			flightsPage.selectArrivalCity(configReader.getProperty("arrival.city"));

			flightsPage.defaultDepartDate().click();

			flightsPage.clickSearchButton();

			FlightsSearchPage flightsSearchPage = new FlightsSearchPage(driver);

			String departureCity = flightsSearchPage.flightLocation().get(0).getText();
			String arrivalCity = flightsSearchPage.flightLocation().get(1).getText();

			Assert.assertEquals(departureCity.toLowerCase(), configReader.getProperty("depart.city").toLowerCase(),
					"Departure city not matched");
			Assert.assertEquals(arrivalCity.toLowerCase(), configReader.getProperty("arrival.city").toLowerCase(),
					"Arrival city not matched");

			test.pass("Test passed: Desired result is visible");
			logger.info("Test passed: Desired result is visible");

		} catch (Exception e) {
			test.fail("Test failed: Desired result not visible");
			logger.error("Test failed: Desired result not visible", e);
		}
	}

	@Test(priority = 1, enabled = true)
	public void test_searchRoundtripFlight() {

		logger.info("Stating test case: RoundTripFlight");
		test = extentReports.createTest("Stating test case: RoundtripFlight");

		try {

			HomePage homePage = new HomePage(driver);

			logger.info("navigating to flight page");
			test.info("navigating to flight page");

			homePage.clickTravelPageLink();

			FlightsPage flightsPage = new FlightsPage(driver);

			flightsPage.selectRoundTrip();

			logger.info("searching for round trip flight");
			test.info("searching for round trip flight");

			flightsPage.selectDepartCity(configReader.getProperty("depart.city"));
			flightsPage.selectArrivalCity(configReader.getProperty("arrival.city"));

			flightsPage.defaultDepartDate().click();
			flightsPage.defaultArrivalDate().click();

			flightsPage.clickSearchButton();

			FlightsSearchPage flightsSearchPage = new FlightsSearchPage(driver);

			String departureCity1 = flightsSearchPage.flightLocation().get(0).getText();
			String arrivalCity1 = flightsSearchPage.flightLocation().get(1).getText();

			String departureCity2 = flightsSearchPage.flightLocation().get(2).getText();
			String arrivalCity2 = flightsSearchPage.flightLocation().get(3).getText();

			Assert.assertEquals(departureCity1.toLowerCase(), configReader.getProperty("depart.city").toLowerCase(),
					"Departure city not matched");
			Assert.assertEquals(arrivalCity1.toLowerCase(), configReader.getProperty("arrival.city").toLowerCase(),
					"Arrival city not matched");

			Assert.assertEquals(departureCity2.toLowerCase(), configReader.getProperty("arrival.city").toLowerCase(),
					"Departure city not matched");
			Assert.assertEquals(arrivalCity2.toLowerCase(), configReader.getProperty("depart.city").toLowerCase(),
					"Arrival city not matched");

			test.pass("Test passed: Desired result is visible");
			logger.info("Test passed: Desired result is visible");

		} catch (Exception e) {
			test.fail("Test failed: Desired result not visible");
			logger.error("Test failed: Desired result not visible", e);
		}

	}
}
