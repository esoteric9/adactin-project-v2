package com.amunteanu.adactin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.amunteanu.helpers.AutoBasics;
import com.amunteanu.helpers.BasicTest;

public class AdactinTest extends BasicTest
{
	private String username = "amunteanu";
	private String password = "sqasolution";
	private String firstName = "Alex";
	private String lastName = "Munteanu";
	private String address = "999 John Doe Dr, Daly City, CA, 94132";
	private String creditCardNr = "9999999999999999";
	private String cvvNr = "333";

	LocalDateTime currentTime = LocalDateTime.now();
	LocalDate currentDate = currentTime.toLocalDate();

	public AdactinTest()
	{
		super("http://www.adactin.com/HotelApp");
	}

	@Test()
	public void TC101()
	{
		/** 
		 * Purpose: To verify valid login Details
		 */
		getLogger().info("Adactin login test");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Hotel Reservation System");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		takeScreenshot("TC-101");
	}

	@Test()
	public void TC102()
	{
		/** 
		 * Purpose: To verify whether the check-in date field accepts a later date than check-out date
		 */
		getLogger().info("Adactin later checkout date test");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Hotel Reservation System");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		Select select = new Select(getDriver().findElement(By.id("location")));
		select.selectByValue("Sydney");
		select = new Select(getDriver().findElement(By.id("hotels")));
		select.selectByValue("Hotel Creek");
		select = new Select(getDriver().findElement(By.id("room_type")));
		select.selectByValue("Standard");
		select = new Select(getDriver().findElement(By.id("room_nos")));
		select.selectByValue("1");
		getDriver().findElement(By.id("datepick_in")).clear();
		LocalDate checkin = this.currentDate.plusDays(7);
		getDriver().findElement(By.id("datepick_in")).sendKeys(AutoBasics.convertDateToDayMonthYearFormat(checkin));
		getDriver().findElement(By.id("datepick_out")).clear();
		LocalDate checkout = this.currentDate.plusDays(5);
		getDriver().findElement(By.id("datepick_out")).sendKeys(AutoBasics.convertDateToDayMonthYearFormat(checkout));
		getDriver().findElement(By.id("Submit")).click();
		Assert.assertEquals(getDriver().findElement(By.id("checkin_span")).getText(),
				"Check-In Date shall be before than Check-Out Date");
		Assert.assertEquals(getDriver().findElement(By.id("checkout_span")).getText(),
				"Check-Out Date shall be after than Check-In Date");
		takeScreenshot("TC-102");
	}

	@Test()
	public void TC103()
	{
		/** 
		 * Purpose: To verify if error is reported if check-out date field is in the past
		 * Note: Currently fails, is a bug
		 */
		getLogger().info("Adactin dates in the past test");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Hotel Reservation System");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		Select select = new Select(getDriver().findElement(By.id("location")));
		select.selectByValue("Sydney");
		select = new Select(getDriver().findElement(By.id("hotels")));
		select.selectByValue("Hotel Creek");
		select = new Select(getDriver().findElement(By.id("room_type")));
		select.selectByValue("Standard");
		select = new Select(getDriver().findElement(By.id("room_nos")));
		select.selectByValue("1");
		getDriver().findElement(By.id("datepick_in")).clear();
		LocalDate checkin = this.currentDate.minusDays(5);
		getDriver().findElement(By.id("datepick_in")).sendKeys(AutoBasics.convertDateToDayMonthYearFormat(checkin));
		getDriver().findElement(By.id("datepick_out")).clear();
		LocalDate checkout = this.currentDate.minusDays(3);
		getDriver().findElement(By.id("datepick_out")).sendKeys(AutoBasics.convertDateToDayMonthYearFormat(checkout));
		getDriver().findElement(By.id("Submit")).click();
		Assert.assertEquals(getDriver().findElement(By.id("checkin_span")).getText(), "Enter Valid Dates");
		Assert.assertEquals(getDriver().findElement(By.id("checkout_span")).getText(), "Enter Valid Dates");
		takeScreenshot("TC-103");
	}

	@Test()
	public void TC104()
	{
		/** 
		 * Purpose: To verify whether locations in Select Hotel page are displayed according to the location selected on Search Hotel page
		 */
		getLogger().info("Adactin hotel location test");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Hotel Reservation System");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		Select select = new Select(getDriver().findElement(By.id("location")));
		select.selectByValue("Sydney");
		select = new Select(getDriver().findElement(By.id("hotels")));
		select.selectByValue("Hotel Creek");
		select = new Select(getDriver().findElement(By.id("room_type")));
		select.selectByValue("Standard");
		select = new Select(getDriver().findElement(By.id("room_nos")));
		select.selectByValue("1");
		getDriver().findElement(By.id("datepick_in")).clear();
		getDriver().findElement(By.id("datepick_in"))
				.sendKeys(AutoBasics.convertDateToDayMonthYearFormat(this.currentDate));
		getDriver().findElement(By.id("datepick_out")).clear();
		LocalDate checkout = this.currentDate.plus(1, ChronoUnit.DAYS);
		getDriver().findElement(By.id("datepick_out")).sendKeys(AutoBasics.convertDateToDayMonthYearFormat(checkout));
		getDriver().findElement(By.id("Submit")).click();
		WebElement location = (new WebDriverWait(getDriver(), 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("location_0")));
		Assert.assertEquals(location.getAttribute("value"), "Sydney");
		takeScreenshot("TC-104");
	}

	@Test()
	public void TC105()
	{
		/** 
		 * Purpose: To verify whether check-in date and check-out date are displayed on Select Hotel page according to the dates selected on Search Hotel page
		 */
		getLogger().info("Adactin check-in and check-out dates same as selected test");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Hotel Reservation System");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		Select select = new Select(getDriver().findElement(By.id("location")));
		select.selectByValue("Sydney");
		select = new Select(getDriver().findElement(By.id("hotels")));
		select.selectByValue("Hotel Creek");
		select = new Select(getDriver().findElement(By.id("room_type")));
		select.selectByValue("Standard");
		select = new Select(getDriver().findElement(By.id("room_nos")));
		select.selectByValue("1");
		getDriver().findElement(By.id("datepick_in")).clear();
		getDriver().findElement(By.id("datepick_in"))
				.sendKeys(AutoBasics.convertDateToDayMonthYearFormat(this.currentDate));
		getDriver().findElement(By.id("datepick_out")).clear();
		LocalDate checkout = this.currentDate.plusDays(1);
		getDriver().findElement(By.id("datepick_out")).sendKeys(AutoBasics.convertDateToDayMonthYearFormat(checkout));
		getDriver().findElement(By.id("Submit")).click();
		Assert.assertEquals(getDriver().findElement(By.id("arr_date_0")).getAttribute("value"),
				AutoBasics.convertDateToDayMonthYearFormat(this.currentDate));
		Assert.assertEquals(getDriver().findElement(By.id("dep_date_0")).getAttribute("value"),
				AutoBasics.convertDateToDayMonthYearFormat(checkout));
		takeScreenshot("TC-105");
	}

	@Test()
	public void TC106()
	{
		/** 
		 * Purpose: To verify whether number of rooms on Select Hotel page is the same as the nr. of rooms selected on Search Hotel page
		 */
		getLogger().info("Adactin nr of rooms same as selected test");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Hotel Reservation System");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		Select select = new Select(getDriver().findElement(By.id("location")));
		select.selectByValue("Sydney");
		select = new Select(getDriver().findElement(By.id("hotels")));
		select.selectByValue("Hotel Creek");
		select = new Select(getDriver().findElement(By.id("room_type")));
		select.selectByValue("Standard");
		select = new Select(getDriver().findElement(By.id("room_nos")));
		select.selectByValue("3");
		getDriver().findElement(By.id("datepick_in")).clear();
		getDriver().findElement(By.id("datepick_in"))
				.sendKeys(AutoBasics.convertDateToDayMonthYearFormat(this.currentDate));
		getDriver().findElement(By.id("datepick_out")).clear();
		LocalDate checkout = this.currentDate.plusDays(1);
		getDriver().findElement(By.id("datepick_out")).sendKeys(AutoBasics.convertDateToDayMonthYearFormat(checkout));
		getDriver().findElement(By.id("Submit")).click();
		String[] stringArray = getDriver().findElement(By.id("rooms_0")).getAttribute("value").split(" ");
		Assert.assertEquals(stringArray[0], "3");
		takeScreenshot("TC-106");
	}

	@Test()
	public void TC107()
	{
		/** 
		 * Purpose: To verify whether room type on Select Hotel page is the same as room type selected on Search Hotel page
		 */
		getLogger().info("Adactin room type same as selected test");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Hotel Reservation System");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		Select select = new Select(getDriver().findElement(By.id("location")));
		select.selectByValue("Sydney");
		select = new Select(getDriver().findElement(By.id("hotels")));
		select.selectByValue("Hotel Creek");
		select = new Select(getDriver().findElement(By.id("room_type")));
		select.selectByValue("Deluxe");
		select = new Select(getDriver().findElement(By.id("room_nos")));
		select.selectByValue("3");
		getDriver().findElement(By.id("datepick_in")).clear();
		getDriver().findElement(By.id("datepick_in"))
				.sendKeys(AutoBasics.convertDateToDayMonthYearFormat(this.currentDate));
		getDriver().findElement(By.id("datepick_out")).clear();
		LocalDate checkout = this.currentDate.plusDays(1);
		getDriver().findElement(By.id("datepick_out")).sendKeys(AutoBasics.convertDateToDayMonthYearFormat(checkout));
		getDriver().findElement(By.id("Submit")).click();
		Assert.assertEquals(getDriver().findElement(By.id("room_type_0")).getAttribute("value"), "Deluxe");
		takeScreenshot("TC-107");
	}

	@Test()
	public void TC108()
	{
		/** 
		 * Purpose: To verify whether the total price is calculated as "price per night * nr od nights * nr of rooms"
		 */
		getLogger().info("Adactin total price test");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Hotel Reservation System");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		Select select = new Select(getDriver().findElement(By.id("location")));
		select.selectByValue("Sydney");
		select = new Select(getDriver().findElement(By.id("hotels")));
		select.selectByValue("Hotel Creek");
		select = new Select(getDriver().findElement(By.id("room_type")));
		select.selectByValue("Standard");
		select = new Select(getDriver().findElement(By.id("room_nos")));
		select.selectByValue("2");
		getDriver().findElement(By.id("Submit")).click();
		(new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("radiobutton_0")))
				.click();
		getDriver().findElement(By.id("continue")).click();
		Assert.assertEquals(getDriver().findElement(By.id("total_price_dis")).getAttribute("value").split(" ")[2],
				"135");
		takeScreenshot("TC-108");
	}

	@Test()
	public void TC109()
	{
		/** 
		 * Purpose: To verify that pressing the Logout button after completing booking logs the user out of the application
		 */
		getLogger().info("Adactin logout after booking test");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Select select = new Select(getDriver().findElement(By.id("location")));
		select.selectByValue("Sydney");
		select = new Select(getDriver().findElement(By.id("hotels")));
		select.selectByValue("Hotel Creek");
		select = new Select(getDriver().findElement(By.id("room_type")));
		select.selectByValue("Standard");
		select = new Select(getDriver().findElement(By.id("room_nos")));
		select.selectByValue("2");
		getDriver().findElement(By.id("Submit")).click();
		(new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("radiobutton_0")))
				.click();
		getDriver().findElement(By.id("continue")).click();
		getDriver().findElement(By.id("first_name")).sendKeys(firstName);
		getDriver().findElement(By.id("last_name")).sendKeys(lastName);
		getDriver().findElement(By.id("address")).sendKeys(address);
		getDriver().findElement(By.id("cc_num")).sendKeys(creditCardNr);
		select = new Select(getDriver().findElement(By.id("cc_type")));
		select.selectByValue("VISA");
		select = new Select(getDriver().findElement(By.id("cc_exp_month")));
		select.selectByValue("1");
		select = new Select(getDriver().findElement(By.id("cc_exp_year")));
		select.selectByValue("2022");
		getDriver().findElement(By.id("cc_cvv")).sendKeys(cvvNr);
		getDriver().findElement(By.id("book_now")).click();
		WebElement logout = (new WebDriverWait(getDriver(), 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("logout")));
		AutoBasics.checkPageIsReady(getDriver());
		logout.click();
		Assert.assertEquals(
				getDriver().findElement(By.xpath("html/body/table[2]/tbody/tr/td[1]/table/tbody/tr/td")).getText(),
				"You have successfully logged out. Click here to login again");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Logout");
		takeScreenshot("TC-109");
	}

	@Test()
	public void TC110()
	{
		/** 
		 * Purpose: To verify whether the final price is calculated as "price per night * nr od nights * nr of rooms + GST"
		 */
		getLogger().info("Adactin final price test");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Hotel Reservation System");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		Select select = new Select(getDriver().findElement(By.id("location")));
		select.selectByValue("Sydney");
		select = new Select(getDriver().findElement(By.id("hotels")));
		select.selectByValue("Hotel Creek");
		select = new Select(getDriver().findElement(By.id("room_type")));
		select.selectByValue("Standard");
		select = new Select(getDriver().findElement(By.id("room_nos")));
		select.selectByValue("2");
		getDriver().findElement(By.id("Submit")).click();
		(new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("radiobutton_0")))
				.click();
		getDriver().findElement(By.id("continue")).click();
		Assert.assertEquals(getDriver().findElement(By.id("final_price_dis")).getAttribute("value").split(" ")[2],
				"148.5");
		takeScreenshot("TC-110");
	}

	@Test()
	public void TC111()
	{
		/** 
		 * Purpose: To check that the hotel name, location, room type, total days, price per night are the same on the 
		 * booking confirmation page as they were selected on the previous screen.
		 */
		getLogger().info("Adactin booking confirmation info test");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Hotel Reservation System");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		Select select = new Select(getDriver().findElement(By.id("location")));
		select.selectByValue("Sydney");
		select = new Select(getDriver().findElement(By.id("hotels")));
		select.selectByValue("Hotel Creek");
		select = new Select(getDriver().findElement(By.id("room_type")));
		select.selectByValue("Standard");
		select = new Select(getDriver().findElement(By.id("room_nos")));
		select.selectByValue("2");
		getDriver().findElement(By.id("Submit")).click();
		(new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("radiobutton_0")))
				.click();
		getDriver().findElement(By.id("continue")).click();
		Assert.assertEquals(getDriver().findElement(By.id("hotel_name_dis")).getAttribute("value"), "Hotel Creek");
		Assert.assertEquals(getDriver().findElement(By.id("location_dis")).getAttribute("value"), "Sydney");
		Assert.assertEquals(getDriver().findElement(By.id("room_type_dis")).getAttribute("value"), "Standard");
		Assert.assertEquals(getDriver().findElement(By.id("room_num_dis")).getAttribute("value"), "2 Room(s)");
		Assert.assertEquals(getDriver().findElement(By.id("total_days_dis")).getAttribute("value"), "1 Day(s)");
		Assert.assertEquals(getDriver().findElement(By.id("price_night_dis")).getAttribute("value"), "AUD $ 125");
		takeScreenshot("TC-111");
	}

	@Test()
	public void TC114()
	{
		/** 
		 * Purpose: To verify that an order number is generated in booking confirmation page
		 */
		getLogger().info("Adactin booking confirmation number test");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Hotel Reservation System");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		Select select = new Select(getDriver().findElement(By.id("location")));
		select.selectByValue("Sydney");
		select = new Select(getDriver().findElement(By.id("hotels")));
		select.selectByValue("Hotel Creek");
		select = new Select(getDriver().findElement(By.id("room_type")));
		select.selectByValue("Standard");
		select = new Select(getDriver().findElement(By.id("room_nos")));
		select.selectByValue("2");
		getDriver().findElement(By.id("Submit")).click();
		(new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("radiobutton_0")))
				.click();
		getDriver().findElement(By.id("continue")).click();
		getDriver().findElement(By.id("first_name")).sendKeys(firstName);
		getDriver().findElement(By.id("last_name")).sendKeys(lastName);
		getDriver().findElement(By.id("address")).sendKeys(address);
		getDriver().findElement(By.id("cc_num")).sendKeys(creditCardNr);
		select = new Select(getDriver().findElement(By.id("cc_type")));
		select.selectByValue("VISA");
		select = new Select(getDriver().findElement(By.id("cc_exp_month")));
		select.selectByValue("1");
		select = new Select(getDriver().findElement(By.id("cc_exp_year")));
		select.selectByValue("2022");
		getDriver().findElement(By.id("cc_cvv")).sendKeys(cvvNr);
		getDriver().findElement(By.id("book_now")).click();
		String orderNr = (new WebDriverWait(getDriver(), 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("order_no"))).getAttribute("value");
		Assert.assertNotNull(orderNr);
		Assert.assertNotEquals(orderNr, "");
		Assert.assertNotEquals(orderNr, " ");
		takeScreenshot("TC-114");
	}

	@Test()
	public void TC115()
	{
		/** 
		 * Purpose: To verify whether the booked itinerary details are not editable
		 * Note: fails, is a bug
		 */
		getLogger().info("Adactin itinerary details not editable test");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Hotel Reservation System");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		Select select = new Select(getDriver().findElement(By.id("location")));
		select.selectByValue("Sydney");
		select = new Select(getDriver().findElement(By.id("hotels")));
		select.selectByValue("Hotel Creek");
		select = new Select(getDriver().findElement(By.id("room_type")));
		select.selectByValue("Standard");
		select = new Select(getDriver().findElement(By.id("room_nos")));
		select.selectByValue("2");
		getDriver().findElement(By.id("Submit")).click();
		(new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("radiobutton_0")))
				.click();
		getDriver().findElement(By.id("continue")).click();
		getDriver().findElement(By.id("first_name")).sendKeys(firstName);
		getDriver().findElement(By.id("last_name")).sendKeys(lastName);
		getDriver().findElement(By.id("address")).sendKeys(address);
		getDriver().findElement(By.id("cc_num")).sendKeys(creditCardNr);
		select = new Select(getDriver().findElement(By.id("cc_type")));
		select.selectByValue("VISA");
		select = new Select(getDriver().findElement(By.id("cc_exp_month")));
		select.selectByValue("1");
		select = new Select(getDriver().findElement(By.id("cc_exp_year")));
		select.selectByValue("2022");
		getDriver().findElement(By.id("cc_cvv")).sendKeys(cvvNr);
		getDriver().findElement(By.id("book_now")).click();
		WebElement itinerary = (new WebDriverWait(getDriver(), 15))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("my_itinerary")));
		AutoBasics.checkPageIsReady(getDriver());
		itinerary.click();
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input"))
				.isEnabled(), false);
		takeScreenshot("TC-115");
	}

	@Test()
	public void TC116()
	{
		/** 
		 * Purpose: To check whether the booked itinerary reflects the correct information in line with the booking
		 */
		getLogger().info("Adactin bookcing confirmation number test");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Hotel Reservation System");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		Select select = new Select(getDriver().findElement(By.id("location")));
		select.selectByValue("Sydney");
		select = new Select(getDriver().findElement(By.id("hotels")));
		select.selectByValue("Hotel Creek");
		select = new Select(getDriver().findElement(By.id("room_type")));
		select.selectByValue("Standard");
		select = new Select(getDriver().findElement(By.id("room_nos")));
		select.selectByValue("2");
		getDriver().findElement(By.id("Submit")).click();
		(new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("radiobutton_0")))
				.click();
		getDriver().findElement(By.id("continue")).click();
		getDriver().findElement(By.id("first_name")).sendKeys(firstName);
		getDriver().findElement(By.id("last_name")).sendKeys(lastName);
		getDriver().findElement(By.id("address")).sendKeys(address);
		getDriver().findElement(By.id("cc_num")).sendKeys(creditCardNr);
		select = new Select(getDriver().findElement(By.id("cc_type")));
		select.selectByValue("VISA");
		select = new Select(getDriver().findElement(By.id("cc_exp_month")));
		select.selectByValue("1");
		select = new Select(getDriver().findElement(By.id("cc_exp_year")));
		select.selectByValue("2022");
		getDriver().findElement(By.id("cc_cvv")).sendKeys(cvvNr);
		getDriver().findElement(By.id("book_now")).click();
		String orderNr = (new WebDriverWait(getDriver(), 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("order_no"))).getAttribute("value");
		AutoBasics.checkPageIsReady(getDriver());
		(new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("my_itinerary")))
				.click();
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[last()]/td[2]/input"))
				.getAttribute("value"), orderNr);
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[last()]/td[4]/input"))
				.getAttribute("value"), "Hotel Creek");
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[last()]/td[5]/input"))
				.getAttribute("value"), "Sydney");
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[last()]/td[6]/input"))
				.getAttribute("value"), "2 Rooms");
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[last()]/td[7]/input"))
				.getAttribute("value"), firstName);
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[last()]/td[8]/input"))
				.getAttribute("value"), lastName);
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[last()]/td[9]/input"))
				.getAttribute("value"), AutoBasics.convertDateToDayMonthYearFormat(currentDate));
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[last()]/td[10]/input"))
				.getAttribute("value"), AutoBasics.convertDateToDayMonthYearFormat(currentDate.plusDays(1)));
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[last()]/td[11]/input"))
				.getAttribute("value"), "1 Days");
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[last()]/td[12]/input"))
				.getAttribute("value"), "Standard");
		takeScreenshot("TC-116");
	}

	@Test()
	public void TC117()
	{
		/** 
		 * Purpose: To check whether "search order id" is working and displaying the relevant details
		 */
		getLogger().info("Adactin login test");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Hotel Reservation System");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		getDriver().findElement(By.xpath("html/body/table[2]/tbody/tr[1]/td[2]/a[2]")).click();
		getDriver().findElement(By.id("order_id_text")).sendKeys("I44BLST8LT");
		getDriver().findElement(By.id("search_hotel_id")).click();
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input"))
				.getAttribute("value"), "I44BLST8LT");
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[4]/input"))
				.getAttribute("value"), "Hotel Creek");
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[5]/input"))
				.getAttribute("value"), "Sydney");
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[6]/input"))
				.getAttribute("value"), "2 Rooms");
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[7]/input"))
				.getAttribute("value"), firstName);
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[8]/input"))
				.getAttribute("value"), lastName);
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[9]/input"))
				.getAttribute("value"), "30/06/2017");
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[10]/input"))
				.getAttribute("value"), "01/07/2017");
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[11]/input"))
				.getAttribute("value"), "1 Days");
		Assert.assertEquals(getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[12]/input"))
				.getAttribute("value"), "Standard");
		takeScreenshot("TC-117");
	}

	@Test()
	public void TC119()
	{
		/** 
		 * Purpose: To verify that the order gets cancelled after click on Cancel order button
		 */
		getLogger().info("Adactin cancel order test");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Hotel Reservation System");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		Select select = new Select(getDriver().findElement(By.id("location")));
		select.selectByValue("Sydney");
		select = new Select(getDriver().findElement(By.id("hotels")));
		select.selectByValue("Hotel Creek");
		select = new Select(getDriver().findElement(By.id("room_type")));
		select.selectByValue("Standard");
		select = new Select(getDriver().findElement(By.id("room_nos")));
		select.selectByValue("2");
		getDriver().findElement(By.id("Submit")).click();
		(new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("radiobutton_0")))
				.click();
		getDriver().findElement(By.id("continue")).click();
		getDriver().findElement(By.id("first_name")).sendKeys(firstName);
		getDriver().findElement(By.id("last_name")).sendKeys(lastName);
		getDriver().findElement(By.id("address")).sendKeys(address);
		getDriver().findElement(By.id("cc_num")).sendKeys(creditCardNr);
		select = new Select(getDriver().findElement(By.id("cc_type")));
		select.selectByValue("VISA");
		select = new Select(getDriver().findElement(By.id("cc_exp_month")));
		select.selectByValue("1");
		select = new Select(getDriver().findElement(By.id("cc_exp_year")));
		select.selectByValue("2022");
		getDriver().findElement(By.id("cc_cvv")).sendKeys(cvvNr);
		getDriver().findElement(By.id("book_now")).click();
		String orderNr = (new WebDriverWait(getDriver(), 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("order_no"))).getAttribute("value");
		AutoBasics.checkPageIsReady(getDriver());
		(new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("my_itinerary")))
				.click();
		getDriver().findElement(By.id("order_id_text")).sendKeys(orderNr);
		getDriver().findElement(By.id("search_hotel_id")).click();
		getDriver()
				.findElement(By
						.xpath("html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]/input"))
				.click();
		getDriver().switchTo().alert().accept();
		Assert.assertEquals(getDriver().findElement(By.id("search_result_error")).getText().trim(),
				"The booking has been cancelled.");
		getDriver().findElement(By.id("order_id_text")).sendKeys(orderNr);
		getDriver().findElement(By.id("search_hotel_id")).click();
		Assert.assertEquals(getDriver().findElement(By.id("search_result_error")).getText().trim(),
				"0 result(s) found. Show all");
		takeScreenshot("TC-119");
	}

	@Test()
	public void TC120()
	{
		/** 
		 * Purpose: To verify valid login Details
		 */
		getLogger().info("Adactin login test");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Hotel Reservation System");
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys(username);
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.id("login")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		getDriver().findElement(By.xpath("html/body/table[2]/tbody/tr[1]/td[2]/a[2]")).click();
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Select Hotel");
		Assert.assertEquals(getDriver()
				.findElement(By.xpath(".//*[@id='booked_form']/table/tbody/tr[1]/td/table/tbody/tr/td[1]")).getText(),
				"Booked Itinerary");
		getDriver().findElement(By.xpath("html/body/table[2]/tbody/tr[1]/td[2]/a[3]")).click();
		Assert.assertEquals(getDriver()
				.findElement(By.xpath(".//*[@id='change_password_form']/table/tbody/tr[1]/td/strong")).getText(),
				"Change Password");
		getDriver().findElement(By.xpath("html/body/table[2]/tbody/tr[1]/td[2]/a[1]")).click();
		Assert.assertEquals(getDriver().findElement(By.xpath(".//*[@id='search_form']/table/tbody/tr[1]/td")).getText(),
				"Search Hotel (Fields marked with Red asterix (*) are mandatory)");
		Assert.assertEquals(getDriver().getTitle(), "AdactIn.com - Search Hotel");
		takeScreenshot("TC-120");
	}

}
