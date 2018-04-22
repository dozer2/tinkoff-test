package com.borisenko.test.pages.elements;

import com.borisenko.test.util.conditions.IsNotExistElement;
import com.borisenko.test.util.conditions.WaitPageLoad;
import com.borisenko.test.util.selenium.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

/**
 * Created by maxim on 21.04.18.
 */
public class Item extends Element {

    public Item(WebElement webElement) {
        super(webElement);
    }

    @Override
    public void click() {
        DriverManager.getInstance().checkUntil(ExpectedConditions.not(new IsNotExistElement(this)), Duration.ofSeconds(10));
        super.click();
        DriverManager.getInstance().checkUntil(new WaitPageLoad(), Duration.ofSeconds(30));
    }
}
