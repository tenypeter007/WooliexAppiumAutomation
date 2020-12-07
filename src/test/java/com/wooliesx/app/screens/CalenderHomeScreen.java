package com.wooliesx.app.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class CalenderHomeScreen extends AbstractScreen {

    @AndroidFindBy(accessibility = "Show Calendar List and Settings drawer")

    private WebElement hamburgerMenu;
    

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Month']")

    private WebElement monthViewButton;
    
    @AndroidFindBy(id  = "com.google.android.calendar:id/next_arrow")
    private WebElement nextButton;
    
    @AndroidFindBy(id  = "com.google.android.calendar:id/oobe_done_button")
    private WebElement gotItButton;
    
   

    

    @Autowired
    public CalenderHomeScreen(AppiumDriver<? extends MobileElement> driver) {
        super(driver);
    }

    public boolean isAppOpened() {
       boolean flag= false;
       if (hamburgerMenu.isDisplayed())
    	   flag= true;
       return flag;
    }
    
    public void clickHamburger()
    {
    	hamburgerMenu.click();
    }
    
    public void openMonthView()
    {
    	monthViewButton.click();
    }
    
    public void navigateToHmeScreen()
    {
    	nextButton.click();
    	nextButton.click();
    	gotItButton.click();
    }
}
