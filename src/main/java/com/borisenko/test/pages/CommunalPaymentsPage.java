package com.borisenko.test.pages;

import com.borisenko.test.pages.elements.Element;
import com.borisenko.test.pages.elements.FieldWrapper;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by maxim on 21.04.18.
 */
public class CommunalPaymentsPage extends BasePage{

    public static final String PAYER_CODE_LABEL="Код плательщика за ЖКУ в Москве";
    public static final String E_MAIL_LABEL="Введите e-mail";
    public static final String LOGIN_OR_PHONE_LABEL="Логин или телефон";
    public static final String ENTER_LOGIN_LABEL="Введите логин";
    public static final String ENTER_PHONE_LABEL="Номер телефона";
    public static final String PASSWORD_LABEL="Пароль";



    @FindBy(xpath = "//*[starts-with(@class,'UISearchProvider__wrapper')]/div/div//*[contains(@class,'ui-form__field') and count(descendant-or-self::*[@class='ui-input__label_placeholder-text'])>0 ]")
    @CacheLookup
    List<FieldWrapper> fields;

    @FindBy(css = "[class*='ui-button_failure']")
    Element submit;


    @FindBy(css = "[data-qa-file='PageTitleContainer']")
    Element title;

    public List<FieldWrapper> getFields() {
        return fields;
    }

    public Element getSubmit() {
        return submit;
    }

    public String getTitle()
    {
        return title.getText();
    }


}
