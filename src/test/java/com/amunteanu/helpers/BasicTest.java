package com.amunteanu.helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BasicTest extends Core
{

	public BasicTest(String baseURL)
	{
		super();
		setBaseURL(baseURL);
		setLogger(Logger.getLogger(BasicTest.class));
		getLogger().info("created BasicTest object through constructor");
	}

	@BeforeMethod(groups = "firefox")
	public void setupFirefox()
	{
		WebDriver driver = new FirefoxDriver();
		setDriver(driver);
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().get(getBaseURL());
	}

	@BeforeMethod(groups = "chrome")
	public void setupChrome()
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		setDriver(driver);
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().get(getBaseURL());
	}

	@BeforeMethod(groups = "ie")
	public void setupIE()
	{
		System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		setDriver(driver);
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(getBaseURL());
	}

	@AfterMethod
	public void tearDown()
	{
		getDriver().quit();
	}
}
