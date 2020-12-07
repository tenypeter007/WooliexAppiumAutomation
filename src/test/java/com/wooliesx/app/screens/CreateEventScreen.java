package com.wooliesx.app.screens;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;


@Component
@Scope("cucumber-glue")
public class CreateEventScreen extends AbstractScreen {

	@AndroidFindBy(id = "com.google.android.calendar:id/floating_action_button")

	private WebElement addFloatingButton;
	@AndroidFindBy(xpath = "//*[@text='Does not repeat']")

	private WebElement repeatEventButton;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[6]")

	private WebElement customDateSelector;

	@AndroidFindBy(id = "com.google.android.calendar:id/interval")
	private WebElement intervalTextBox;

	@AndroidFindBy(id = "com.google.android.calendar:id/frequency")
	private WebElement frequencySelect;

	@AndroidFindBy(xpath = "//*[@text='week']")
	private WebElement selectWeek;

	@AndroidFindBy(accessibility = "Wednesday")
	private WebElement wed;

	@AndroidFindBy(accessibility = "Friday")
	private WebElement fri;

	@AndroidFindBy(id = "com.google.android.calendar:id/right_button")
	private WebElement doneButton;

	@AndroidFindBy(id = "com.google.android.calendar:id/title")
	private WebElement eventTitle;

	@AndroidFindBy(xpath = "//*[contains(@content-desc, 'Start time')]")
	private WebElement selectTimeButton;

	@AndroidFindBy(id = "android:id/am_label")
	private WebElement amLabel;

	@AndroidFindBy(id = "android:id/pm_label")
	private WebElement pmLabel;

	@AndroidFindBy(xpath = "//*[@text='OK']")
	private WebElement okButton;

	@AndroidFindBy(xpath = "//*[@text='Add people']")
	private WebElement addPeople;

	@AndroidFindBy(id = "com.google.android.calendar:id/search_text")
	private WebElement searchPeopleTextBox;

	@AndroidFindBy(id = "com.google.android.calendar:id/right_button")
	private WebElement doneButtonInAddPeople;

	@AndroidFindBy(id = "com.google.android.calendar:id/save")
	private WebElement saveButton;

	@AndroidFindBy(xpath = "//*[contains(@text, \"Don't send\")]")
	private WebElement dontSendButton;

	@AndroidFindBy(xpath = "//*[@text='OK']")
	private List<WebElement> googleMeetOKButton;

	@AndroidFindBy(accessibility = "More options")
	private WebElement deleteMoreOption;

	@AndroidFindBy(xpath = "//*[@text='Delete']")
	private WebElement moreOptionDelete;

	@AndroidFindBy(xpath = "//*[@text='This and following events']")
	private WebElement followingEventButton;

	@AndroidFindBy(xpath = "//*[@text='Delete']")
	private WebElement deleteButton;

	@AndroidFindBy(xpath = "//*[contains(@text, \"Don't send\")]")
	private WebElement deleteDontSendButton;

	WebDriverWait wait = new WebDriverWait(driver, 5);

	@Autowired
	public CreateEventScreen(AppiumDriver<? extends MobileElement> driver) {
		super(driver);

	}

	public void clickRepeatEventButton() {

		wait.until(ExpectedConditions.visibilityOf(eventTitle));
		driver.hideKeyboard();
		repeatEventButton.click();
	}

	public void selectCustomDateOption() {
		customDateSelector.click();
	}

	public void selectWeek() {
		frequencySelect.click();
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(selectWeek)));

		selectWeek.click();
	}

	public void selectWedAndFri() {
		wait.until(ExpectedConditions.visibilityOf(wed));
		wed.click();
		fri.click();
	}

	public void clickDone() {
		doneButton.click();
	}

	public void inputTitle(String title) {
		eventTitle.sendKeys(title);
	}

	public void deleteEvent(String title) {
		String datePickElementString = "//*[contains(@content-desc, '" + title + "')]";
		List<MobileElement> datePickElementList = (List<MobileElement>) driver
				.findElements(MobileBy.xpath(datePickElementString));

	
		if (!datePickElementList.isEmpty()) {
			datePickElementList.get(0).click();
			(new WebDriverWait(driver, 30))
					.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(deleteMoreOption)));
			deleteMoreOption.click();
			moreOptionDelete.click();
			followingEventButton.click();
			deleteButton.click();
			deleteDontSendButton.click();
			(new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOf(addFloatingButton));

		}

	}

	public void setTime(String hours, String mins) {

		selectTimeButton.click();

		if (Integer.parseInt(hours) > 12)
			pmLabel.click();
		else
			amLabel.click();

		if (Integer.parseInt(hours) > 12)
			hours = String.valueOf((Integer.parseInt(hours) - 12));
		driver.findElement(MobileBy.AccessibilityId(hours.trim())).click();
		driver.findElement(MobileBy.AccessibilityId(mins.trim())).click();

		okButton.click();
	}

	public void clickAddPeopleButton() {
		addPeople.click();
	}

	public void addPeople(String name) {
		searchPeopleTextBox.sendKeys(name);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
	}

	public void clickDoneButtonInAddPeople() {
		doneButtonInAddPeople.click();
	}

	public void saveMeeting() {
	
		try {
			if (!googleMeetOKButton.isEmpty()) {
				googleMeetOKButton.get(0).click();
			}
		} catch (Exception e) {
			
		}
		saveButton.click();
		dontSendButton.click();
	}
	
	public AppiumDriver<? extends MobileElement> getDriver()
	{
		return driver;
	}

}
