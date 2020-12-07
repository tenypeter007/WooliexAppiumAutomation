package com.wooliesx.test.steps;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class MonthViewHomeScreenStep extends AbstractSteps {
	public void navigateToEventScreen() {
		monthHomeScreen.clickFloatingButton();
		monthHomeScreen.clickEventButton();

	}
}
