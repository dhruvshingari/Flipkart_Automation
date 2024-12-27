package com.nagarro.ExitTest.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.nagarro.ExitTest.config.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;
	protected static ConfigReader configReader;
	protected static ExtentReports extentReports;
	protected ExtentTest test;
	private static final String path = "src/test/resources/application.properties";
	protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

	@BeforeSuite
	public void setupExtentReports() {
		ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(spark);
	}

	@BeforeMethod
	public void setUp() {
		// Setup WebDriverManager and initialize WebDriver
		logger.info("Setting up for test class: " + this.getClass().getSimpleName());
		configReader = new ConfigReader(path);
		String browser = configReader.getProperty("browser");
		boolean isHeadless = Boolean.parseBoolean(configReader.getProperty("headless"));

		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions c_options = new ChromeOptions();
			if (isHeadless) {
				c_options.addArguments("--headless");
			}
			driver = new ChromeDriver(c_options);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions f_options = new FirefoxOptions();
			if (isHeadless) {
				f_options.addArguments("--headless");
			}
			driver = new FirefoxDriver(f_options);
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions e_options = new EdgeOptions();
			if (isHeadless) {
				e_options.addArguments("--headless");
			}
			driver = new EdgeDriver(e_options);
			break;
		default:
			throw new IllegalArgumentException("Browser \"" + browser + "\" not supported.");
		}
		driver.manage().window().maximize();
		// Navigate to the login page (you can replace this URL with any starting URL)
		String url = configReader.getProperty("application.url");
		driver.get(url);
		long waitTime = Long.parseLong(configReader.getProperty("global.wait.time"));
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		// Close the browser after each test
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Failed: " + result.getName());
			test.log(Status.FAIL, result.getThrowable());

			// Take screenshot and attach it to the report
			String screenshotPath = takeScreenshot(result.getName());
			if (new File(screenshotPath).exists()) { // Ensure file exists before attaching
				test.addScreenCaptureFromPath(screenshotPath);
			} else {
				System.out.println("Screenshot not found at path: " + screenshotPath);
			}
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Passed: " + result.getName());
		}

		if (driver != null) {
			driver.quit();
		}
	}

	public String takeScreenshot(String screenshotName) {
		String path = "test-output/screenshots/" + screenshotName + ".png";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			Files.createDirectories(Paths.get("test-output/screenshots"));
			Files.copy(src.toPath(), Paths.get(path));
			System.out.println("Screenshot saved at: " + path); // Log screenshot path for verification
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to save screenshot to " + path);
		}
		return path;
	}

	@AfterSuite
	public void tearDownExtentReports() {
		extentReports.flush();
	}

}
