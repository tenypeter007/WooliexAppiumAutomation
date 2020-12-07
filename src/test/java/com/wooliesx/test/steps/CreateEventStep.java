package com.wooliesx.test.steps;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

@Component
@Scope("cucumber-glue")
public class CreateEventStep extends AbstractSteps {
	public void createRepeatWeeklyEvent() {
		createEventScreen.clickRepeatEventButton();
		createEventScreen.selectCustomDateOption();
		createEventScreen.selectWeek();
		createEventScreen.selectWedAndFri();
		createEventScreen.clickDone();

	}

	public void deleteEvent(String title) {
		createEventScreen.deleteEvent(title);
	}

	public void inputTitle(String title) {
		createEventScreen.inputTitle(title);

	}

	public void setTime(String hours, String mins) {
		createEventScreen.setTime(hours, mins);
	}

	public void clickAddPeopleButton() {
		createEventScreen.clickAddPeopleButton();

	}

	public void addPeople(String people) {
		createEventScreen.addPeople(people);
	}

	public void clickDoneButtonInAddPeople() {
		createEventScreen.clickDoneButtonInAddPeople();
	}

	public void saveMeeting() {
		createEventScreen.saveMeeting();
	}
	
	public AppiumDriver<? extends MobileElement> getDriver()
	{
		return createEventScreen.getDriver();
	}

	/*
	 * public boolean isMeetingAsExecpted() { boolean flag= false;
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
}
