/**
 * File Name: AutoBasics.java<br>
 * Created: Feb 4, 2017
 */
package com.amunteanu.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * AutoBasics is a utility class that contains a variety of methods that can be useful in automation scripts
 *
 * @author Munteanu, Alex
 * @version 1.0.0
 * @since 1.0
 */
public class AutoBasics
{

	private static final String DEFAULT_CONFIG_FILE_NAME = "config.properties";

	/**
	 * Purpose: Takes a screenshot of the page currently displayed by the WebDriver object.
	 * Screenshots are stored in the screenshots folder.
	 * Return true if the screenshot was successfully created.
	 */
	public static boolean takeScreenshot(WebDriver driver, String fileName)
	{
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try
		{
			FileUtils.copyFile(srcFile, new File("screenshots/" + fileName + ".png"));
		} catch (IOException e)
		{
			System.out.println("Screenshot was not saved correctly: " + fileName + ".png");
			return false;
		}
		return true;
	}

	/**
	 * Purpose: Checks if a WebElement is present on the page
	 * Return false if the WebElement is not present on the page
	 */
	public static boolean isElementPresent(WebDriver driver, By by)
	{
		try
		{
			driver.findElement(by);
		} catch (NoSuchElementException e)
		{
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}

	/**
	 * Purpose: Get properties from the specified file and store them in a Properties object
	 * Return the Properties object
	 */
	public static Properties readProps(String fileName)
	{
		Properties prop = new Properties();
		try
		{
			InputStream input = new FileInputStream("src/main/resources/" + fileName);
			prop.load(input);
		} catch (FileNotFoundException e)
		{
			System.err.println("File was not found.");
		} catch (IOException e)
		{
			System.err.println("Error when reading file.");
		}
		return prop;
	}

	/**
	 * Purpose: Writes properties to a specified file
	 * Return true if writing is successful
	 */
	public static boolean writeProps(Properties prop, String fileName)
	{
		boolean success;
		try
		{
			OutputStream output = new FileOutputStream("src/main/resources/" + fileName);
			prop.store(output, "Properties Added");
			success = true;
		} catch (FileNotFoundException e)
		{
			System.err.println("File was not found.");
			return false;
		} catch (IOException e)
		{
			System.err.println("Error when writting to file.");
			return false;
		}
		return success;
	}

	/**
	 * Purpose: Get a property from the specified file with the specified key
	 * Return the property value as a String
	 */
	public static String getProp(String fileName, String key) throws IOException
	{
		Properties prop = new Properties();
		InputStream input = new FileInputStream("src/main/resources/" + fileName);
		prop.load(input);
		return prop.getProperty(key);
	}

	/**
	 * Purpose: Get a property with the specified key from the default config file 
	 * Return the property value as a String
	 */
	public static String getProp(String key) throws IOException
	{
		return getProp(DEFAULT_CONFIG_FILE_NAME, key);
	}

	/**
	 * Purpose: Gets a property from the specified file with the specified key and converts it to an int
	 * Return the property value converted to an int
	 */
	public static int getInt(String fileName, String key) throws IOException
	{
		String input = getProp(fileName, key);
		int result = 0;
		try
		{
			result = Integer.parseInt(input);
		} catch (NumberFormatException e)
		{
			System.err.println("Error when converting to int");
		}
		return result;
	}

	/**
	 * Purpose: Gets a property and converts it to an int (uses the default config file)
	 * Return the property value converted to an int
	 */
	public static int getInt(String key) throws IOException
	{
		return getInt(DEFAULT_CONFIG_FILE_NAME, key);
	}

	/**
	 * Purpose: Writes a property with the specified key to a specified file 
	 * Return true if writing is successful
	 */
	public static void addProp(String key, String value, String fileName)
	{
		Properties prop = readProps(fileName);
		prop.setProperty(key, value);
		writeProps(prop, fileName);
	}

	/**
	 * Purpose: Writes a property with the specified key to the default config file
	 * Return true if writing is successful
	 */
	public static void addProp(String key, String value)
	{
		addProp(key, value, DEFAULT_CONFIG_FILE_NAME);
	}

	/** 
	 * Purpose: Convert LocalDate object to mm/dd/yyyy format
	 */
	public static String convertDateToMonthDayYearFormat(LocalDate date)
	{
		String[] datePieces = date.toString().split("-");
		String newDate = datePieces[1] + "/" + datePieces[2] + "/" + datePieces[0];
		return newDate;
	}

	/** 
	 * Purpose: Convert LocalDate object to dd/mm/yyyy format
	 */
	public static String convertDateToDayMonthYearFormat(LocalDate date)
	{
		String[] datePieces = date.toString().split("-");
		String newDate = datePieces[2] + "/" + datePieces[1] + "/" + datePieces[0];
		return newDate;
	}

	/** 
	 * Purpose: Checks if the Javascript has been loaded on the page. 
	 * Keeps checking every second for 25 seconds if it has not. 
	 * Exits when Javascript is loaded or after 25 seconds.
	 */
	public static void checkPageIsReady(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (js.executeScript("return document.readyState").toString().equals("complete"))
		{
			return;
		}
		for (int i = 0; i < 25; i++)
		{
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
			}
			if (js.executeScript("return document.readyState").toString().equals("complete"))
			{
				break;
			}
		}
	}
}
