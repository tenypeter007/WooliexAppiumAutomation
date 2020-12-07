package com.wooliesx.app.screens;

import java.util.List;
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
public class EventVerificationScreen extends AbstractScreen {

	@AndroidFindBy(id = "com.google.android.calendar:id/title")

	private WebElement eventTitle;

	@AndroidFindBy(id = "com.google.android.calendar:id/time")

	private WebElement eventTime;

	@AndroidFindBy(id = "com.google.android.calendar:id/recurrence")

	private WebElement eventRecurrance;

	@Autowired
	public EventVerificationScreen(AppiumDriver<? extends MobileElement> driver) {
		super(driver);
	}

	public boolean isTitleAsExpected(String title) {
		boolean flag = false;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String datePickElementString = "//*[contains(@content-desc, '" + title + "')]";
		List<MobileElement> datePickElementList = (List<MobileElement>) driver
				.findElements(MobileBy.xpath(datePickElementString));

		if (!datePickElementList.isEmpty()) {
			datePickElementList.get(0).click();

		}

		if (eventTitle.getText().trim().equals(title))
			flag = true;

		return flag;
	}

	public boolean isTimeAsExpected(String hrs, String mins) {
		boolean flag = false;
		String timeText = eventTime.getText().trim();
		boolean period = true;
		int timeTo12Hrs = Integer.parseInt(hrs.trim());
		if (timeTo12Hrs > 12) {
			timeTo12Hrs = timeTo12Hrs - 12;
			period = false;
		}
		if (timeText.contains(Integer.toString(timeTo12Hrs) + ":" + Integer.parseInt(mins))) {
			if (period == false && timeText.contains("pm")) {
				flag = true;
			} else if (period == true && timeText.contains("am")) {
				flag = true;
			}
		}

		return flag;
	}

	public boolean isFrequencyAsExpected() {
		boolean flag = false;

		if (eventRecurrance.getText().trim().equals("Repeats weekly on Mon, Wed, Fri")) {
			flag = true;
		}

		return flag;
	}

}
