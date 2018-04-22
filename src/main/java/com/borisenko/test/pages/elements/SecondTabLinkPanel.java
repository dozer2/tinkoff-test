package com.borisenko.test.pages.elements;

import com.borisenko.test.exceptions.TPSException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by maxim on 21.04.18.
 */

public class SecondTabLinkPanel extends Element{


    public static final String PAYMENTS = "Платежи";


    @FindBy(css = "[data-qa-file=SecondMenu]")
    @CacheLookup
    private List<Item> items;

    public SecondTabLinkPanel(WebElement webElement) {
        super(webElement);
    }


    public void clickToLink(String linkName)
    {
        items.stream()
                .filter(items->items.getText().compareTo(linkName)==0)
                .findFirst()
                .orElseThrow(()->new TPSException("Can't found link "+linkName))
                .click();

    }

}
