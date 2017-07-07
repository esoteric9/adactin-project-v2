/**
 * File Name: BasicPage.java<br>
 * Created: Feb 4, 2017
 */
package com.amunteanu.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * BasicTest class is the base for all page objects
 * The constructor initializes all elements
 *
 * @author Munteanu, Alex
 * @version 1.0.0
 * @since 1.0
 */
public class BasicPage extends Core
{

	public BasicPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
}
