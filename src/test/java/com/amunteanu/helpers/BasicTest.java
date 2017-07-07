/**
 * File Name: BasicPage.java<br>
 * Created: Feb 4, 2017
 */
package com.amunteanu.helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * BasicTest class is the base for all tests<br>
 * Contains the setup methods for different web browsers that set up each run of the test 
 * and a tear down method that quits the driver
 *
 * @author Munteanu, Alex
 * @version 1.0.0
 * @since 1.0
 */
public class BasicTest extends Core
{

	public BasicTest(String baseURL)
	{
		super();
		setBaseURL(baseURL);
		setLogger(Logger.getLogger(BasicTest.class));
		getLogger().info("created BasicTest object through constructor");
	}

	/**
	 * Purpose: Sets up the Firefox driver and goes to the base URL
	 */
	@BeforeMethod(groups = "firefox")
	public void setupFirefox()
	{
		WebDriver driver = new FirefoxDriver();
		setDriver(driver);
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getDriver().get(getBaseURL());
	}

	/**
	 * Purpose: Sets up the Chrome driver and goes to the base URL
	 */
	@BeforeMethod(groups = "chrome")
	public void setupChrome()
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		setDriver(driver);
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getDriver().get(getBaseURL());
	}

	/**
	 * Purpose: Sets up the Internet Explorer driver and goes to the base URL
	 */
	@BeforeMethod(groups = "ie")
	public void setupIE()
	{
		System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		setDriver(driver);
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(getBaseURL());
	}

	/**
	 * Purpose: Quits the driver
	 */
	@AfterMethod
	public void tearDown()
	{
		getDriver().quit();
	}
}
