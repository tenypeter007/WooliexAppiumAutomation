package com.wooliesx.test.steps;

import com.wooliesx.app.screens.*;
import com.wooliesx.test.utils.AppUtils;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractSteps {
	@Autowired
	public AppUtils utils;

	@Autowired
	public CalenderHomeScreen calenderHomeScreen;
	@Autowired
	public MonthHomeScreen monthHomeScreen;

	@Autowired
	public CreateEventScreen createEventScreen;

}
