package com.wooliesx.test.steps;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class CalenderHomeScreenStep extends AbstractSteps {
	public void openCalenderWithMonthView() {
		calenderHomeScreen.isAppOpened();
		calenderHomeScreen.clickHamburger();

	}
}
