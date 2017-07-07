/**
 * File Name: Core.java<br>
 * Created: Feb 11, 2017
 */
package com.amunteanu.helpers;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Core class is the base for both tests and page objects
 * <p>
 * Contains the WebDriver and Logger objects and the baseURL
 * <p>
 * Contains the getDriver() and setDriver() methods which give access to the WebDriver object<br>
 * Contains the getbaseURL() and setbaseURL() methods which give access to the baseURL variable<br>
 * Contains the getLogger() and setLogger() methods which give access to the Logger object<br>
 * Contains the takeScreenshot() method that allows taking a screenshot of the web page<br>
 * Contains methods for retrieving and writing properties to a config file
 *
 * @author Munteanu, Alex
 * @version 1.0.0
 * @since 1.0
 */
public class Core
{
	private String baseURL;

	private WebDriver driver;

	private Logger logger;

	public Core()
	{
		this.logger = Logger.getLogger(Core.class);
	}

	public Core(WebDriver driver)
	{
		this.driver = driver;
		this.logger = Logger.getLogger(Core.class);
	}

	/**
	 * Purpose: Gets the WebDriver object
	 * Return the WebDriver object
	 */
	public WebDriver getDriver()
	{
		return this.driver;
	}

	/**
	 * Purpose: Sets the WebDriver object
	 */
	protected void setDriver(WebDriver driver)
	{
		this.driver = driver;
	}

	/**
	 * Purpose: Gets the base url
	 * Return the base url as a String
	 */
	public String getBaseURL()
	{
		return baseURL;
	}

	/**
	 * Purpose: Sets the base url
	 */
	protected void setBaseURL(String baseURL)
	{
		this.baseURL = baseURL;
	}

	/**
	 * Purpose: Gets the Logger object
	 * Return the Logger object
	 */
	public Logger getLogger()
	{
		return this.logger;
	}

	/**
	 * Purpose: Sets the Logger object
	 */
	protected void setLogger(Logger logger)
	{
		this.logger = logger;
	}

	/**
	 * Purpose: Gets a property and converts it to an int (uses the default config file)
	 * Return the property value converted to an int
	 */
	public int getInt(String name) throws IOException
	{
		return AutoBasics.getInt(name);
	}

	/**
	 * Purpose: Get a property with the specified key from the default config file 
	 * Return the property value as a String
	 */
	public String getProp(String name) throws IOException
	{
		return AutoBasics.getProp(name);
	}

	/**
	 * Purpose: Writes a property with the specified key to the default config file
	 * Return true if writing is successful
	 */
	public void addProp(String key, String value)
	{
		AutoBasics.addProp(key, value);
	}

	/**
	 * Purpose: Checks if a WebElement is present on the page
	 * Return false if the WebElement is not present on the page
	 */
	public boolean isElementPresent(By by)
	{
		return AutoBasics.isElementPresent(getDriver(), by);
	}

	/**
	 * Purpose: Takes a screenshot of the page currently displayed by the WebDriver object.
	 * Screenshots are stored in the screenshots folder.
	 * Return true if the screenshot was successfully created.
	 */
	public boolean takeScreenshot(String name)
	{
		return AutoBasics.takeScreenshot(getDriver(), name);
	}
}
