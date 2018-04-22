package com.borisenko.test.pages.elements;

import com.borisenko.test.util.conditions.IsNotExistElement;
import com.borisenko.test.util.selenium.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

/**
 * Created by maxim on 21.04.18.
 */
public class LoaderProgress extends Element {

    @FindBy(css = "[data-qa-file=LoaderProgress]")
    Element loader;

    public LoaderProgress(WebElement webElement) {
        super(webElement);
    }

    public void untilLoadPage()
    {
        DriverManager.getInstance().checkUntil(new IsNotExistElement(this), Duration.ofSeconds(10));
    }
}
