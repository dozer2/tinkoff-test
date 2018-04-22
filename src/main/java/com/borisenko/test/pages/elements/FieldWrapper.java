package com.borisenko.test.pages.elements;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

/**
 * Created by maxim on 21.04.18.
 */
public class FieldWrapper extends Element{

    @FindBy(css = "[data-qa-file='UIFormRowError']")
    @CacheLookup
    Element error;

    @FindBy(css = "[data-qa-node='input']")
    @CacheLookup
    Element input;

    @FindBy(css = ".ui-input__label_placeholder-text")
    @CacheLookup
    Element caption;



    public FieldWrapper(WebElement webElement) {
        super(webElement);
    }

    public String getErrorText()
    {
        return error.getText();
    }

    public void setText(String text)
    {
        input.clear();
        input.sendKeys(text);
    }

    public String getCaptionText() {

            return caption.getText();
    }

    @Override
    public void clear() {
        input.sendKeys(Keys.chord(Keys.HOME, Keys.SHIFT,Keys.END,Keys.DELETE));
    }
}
