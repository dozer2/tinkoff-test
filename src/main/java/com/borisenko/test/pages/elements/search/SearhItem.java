package com.borisenko.test.pages.elements.search;

import com.borisenko.test.pages.elements.Element;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by maxim on 22.04.18.
 */
public class SearhItem extends Element {

   @FindBy(xpath = "./div/div/div[1]")
   Element mainText;

   @FindBy(xpath = "./div/div/div[2]")
   Element subText;

    public SearhItem(WebElement webElement) {
        super(webElement);
    }

    public Element getMainText() {
        return mainText;
    }

    public Element getSubText() {
        return subText;
    }
}
