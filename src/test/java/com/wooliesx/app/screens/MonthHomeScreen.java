package com.wooliesx.app.screens;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

@Component
@Scope("cucumber-glue")
public class MonthHomeScreen extends AbstractScreen {

	@AndroidFindBy(id = "com.google.android.calendar:id/floating_action_button")

	private WebElement addFloatingButton;

	@AndroidFindBy(accessibility = "Create new event and more")
	private WebElement addEventButton;

	@Autowired
	public MonthHomeScreen(AppiumDriver<? extends MobileElement> driver) {
		super(driver);
	}

	public void clickFloatingButton() {
		addFloatingButton.click();
	}

	public void clickEventButton() {
		addEventButton.click();
	}

	public String getDeviceCurrentDate() {
		return driver.getDeviceTime();
	}

	public void clickNextMonday(LocalDate ld) {
		Date date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
		String month = new SimpleDateFormat("MMMM", Locale.ENGLISH).format(date);
		String day = new SimpleDateFormat("dd", Locale.ENGLISH).format(date);

		String datePickElementString = "//*[contains(@content-desc, '" + dayOfWeek + " " + day + " " + month + "')]";

		driver.findElement(MobileBy.xpath(datePickElementString)).click();
	}
}
