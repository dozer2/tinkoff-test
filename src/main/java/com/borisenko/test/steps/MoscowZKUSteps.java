package com.borisenko.test.steps;

import com.borisenko.test.exceptions.TPSException;
import com.borisenko.test.pages.CommunalPaymentsPage;
import com.borisenko.test.pages.PaymenstPage;
import com.borisenko.test.pages.elements.FieldWrapper;
import com.borisenko.test.pages.elements.SecondTabLinkPanel;
import com.borisenko.test.util.selenium.DriverManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

/**
 * Created by maxim on 21.04.18.
 */
public class MoscowZKUSteps extends BaseSteps<MoscowZKUSteps>{

    CommunalPaymentsPage communalPaymentsPage;

    public MoscowZKUSteps setCommunalPaymentsPage(CommunalPaymentsPage communalPaymentsPage) {
        this.communalPaymentsPage = communalPaymentsPage;
        return this;
    }

    public static MoscowZKUSteps openMoscowZKUPage()
    {
        CommunalPaymentsPage communalPaymentPage= new CommunalPaymentsPage();
        DriverManager.getInstance().initPage(communalPaymentPage);
        return new MoscowZKUSteps().setCommunalPaymentsPage(communalPaymentPage);
    }


    private FieldWrapper getFieldFromCaption(String caption)
    {
        return communalPaymentsPage.getFields().stream().parallel()
                .filter(field-> field.getCaptionText().compareTo(caption)==0)
                .findFirst()
                .orElseThrow(()->new TPSException("Can't found field with caption "+caption));
    }

    public MoscowZKUSteps setValueInToField (String caption,String value)
    {
        getFieldFromCaption(caption).setText(value);
        return this;
    }

    public MoscowZKUSteps checkErrorMessageInToField (String caption,String errorMessage)
    {
        String error=getFieldFromCaption(caption).getErrorText();
        assertThat(errorMessage +"is not equals "+error, error, equalToIgnoringCase(errorMessage));
        getFieldFromCaption(caption).getErrorText();
        return this;
    }

    public MoscowZKUSteps clickToField (String caption)
    {
        getFieldFromCaption(caption).click();
        return this;
    }

    public MoscowZKUSteps clearField (String caption)
    {

        getFieldFromCaption(caption).clear();

        return this;
    }

    public MoscowZKUSteps ckickToCheckDebt ()
    {
        communalPaymentsPage.getSubmit().click();
        return this;
    }

    public String getPageTitle()
    {
        return communalPaymentsPage.getTitle();
    }

    public MoscowZKUSteps checkPageTitle(String pageTitle)
    {
        assertThat(pageTitle +"is not equals "+getPageTitle(), getPageTitle(), equalToIgnoringCase(pageTitle));
        return this;
    }

    public PaymentsPageSteps openPaymentsPage()
    {
        communalPaymentsPage.clickToLinkOnSecondTabPanel(SecondTabLinkPanel.PAYMENTS);
        PaymenstPage paymenstPage= new PaymenstPage();
        driverManager.initPage(paymenstPage);
        return new PaymentsPageSteps().setPaymenstPage(paymenstPage);
    }


    @Override
    public MoscowZKUSteps refreshPage() {
        driverManager.initPage(communalPaymentsPage);
        return this;

    }


}
