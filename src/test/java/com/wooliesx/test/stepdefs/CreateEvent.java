package com.wooliesx.test.stepdefs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.springframework.beans.factory.annotation.Autowired;

import com.wooliesx.app.screens.CalenderHomeScreen;
import com.wooliesx.app.screens.EventVerificationScreen;
import com.wooliesx.app.screens.MonthHomeScreen;
import com.wooliesx.test.steps.CreateEventStep;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateEvent {

	@Autowired
	private CalenderHomeScreen calenderHome;

	@Autowired
	private MonthHomeScreen monthHomeScreen;

	@Autowired
	private CreateEventStep createEventStep;

	@Autowired
	private EventVerificationScreen eventVerificationScreen;

	@Given("^I have launched the Calendar App$")
	public void openApp() {

		calenderHome.navigateToHmeScreen();
		org.junit.Assert.assertTrue(calenderHome.isAppOpened());
		calenderHome.clickHamburger();
		calenderHome.openMonthView();

	}

	@When("^It is not a non working day$")
	public void selectDate() throws ParseException {

		monthHomeScreen.clickFloatingButton();
		monthHomeScreen.clickEventButton();

	}

	@And("^I delete existing event with name \"(.*)\"$")
	public void deleteExistingEvent(String title) throws ParseException {
		String currentDeviceDate = monthHomeScreen.getDeviceCurrentDate();
		Date date1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(currentDeviceDate);

		LocalDate ld = LocalDate.of(date1.getYear() + 1900, date1.getMonth() + 1, date1.getDate());

		ld = ld.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

		monthHomeScreen.clickNextMonday(ld);
		createEventStep.deleteEvent(title);
	}

	@And("^Meeting is not repeated on successive days$")
	public void selectEventDays() {
		createEventStep.createRepeatWeeklyEvent();
	}

	@Then("^I want to book a meeting with the title \"(.*)\"$")
	public void createTitle(String title) {
		createEventStep.inputTitle(title);
	}

	@And("^Set Meeting duration as \"([^\"]*)\" and \"([^\"]*)\" in the evening$")
	public void setTime(String hours, String mins) {
		createEventStep.setTime(hours, mins);
	}

	@And("^I invite \"([^\"]*)\" of people$")
	public void addPeople(String number, DataTable table) {
		createEventStep.clickAddPeopleButton();
		List<List<String>> rows = table.asLists(String.class);
		for (int i = 0; i <= Integer.parseInt(number) - 1; i++) {

			createEventStep.addPeople(rows.get(i).get(0));

		}

		createEventStep.clickDoneButtonInAddPeople();

	}

	@And("^I save the meeting$")
	public void saveMeeting() {
		createEventStep.saveMeeting();
	}

	@Then("^I Check if the meeting is created as expected$")
	public void isMeetingAsExecpted(DataTable table) {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		for (int i = 0; i < rows.size(); i++) {

			Assert.assertTrue(eventVerificationScreen.isTitleAsExpected(rows.get(i).get("title")));
			Assert.assertTrue(
					eventVerificationScreen.isTimeAsExpected(rows.get(i).get("#hrs"), rows.get(i).get("#Minutes")));
			Assert.assertTrue(eventVerificationScreen.isFrequencyAsExpected());
		}

	}

	@After
	public void embedScreenshot(Scenario scenario) {

		try {

			if (scenario.isFailed()) {
				byte[] screenshot = ((TakesScreenshot) createEventStep.getDriver()).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			}
		} catch (WebDriverException wde) {
			System.err.println(wde.getMessage());
		} catch (ClassCastException cce) {
			cce.printStackTrace();
		}
		createEventStep.getDriver().quit();
	}

}
