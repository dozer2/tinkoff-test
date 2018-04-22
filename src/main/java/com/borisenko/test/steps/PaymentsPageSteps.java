package com.borisenko.test.steps;

import com.borisenko.test.pages.PaymenstPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;



/**
 * Created by maxim on 21.04.18.
 */
public class PaymentsPageSteps extends BaseSteps<PaymentsPageSteps>{

    public static final String COMNNUNAL_PAYMENT="ЖКХ";
    PaymenstPage paymenstPage;


    public PaymentsPageSteps setPaymenstPage(PaymenstPage paymenstPage) {
        this.paymenstPage = paymenstPage;
        return this;
    }

    public CommunalPaymentSteps openCommunalPaymentsPage()
    {
        paymenstPage.openMenuItemProvider(COMNNUNAL_PAYMENT);
        refreshPage();
        return new CommunalPaymentSteps().setCommunalPaymentPage(paymenstPage);
    }

    public PaymentsPageSteps setTextInFastSearch(String textValue)
    {
        paymenstPage.setTextInSuggestSearch(textValue);
        return this;
    }

    public void selectTexInSuggestMenu(String textValue)
    {
        Integer index =paymenstPage.getIndexTextInSuggestSearch(textValue);
        assertThat(textValue+" not found in suggest ", index,greaterThanOrEqualTo(0));
        paymenstPage.selectInSuggestSearch(index);
    }


    @Override
    public PaymentsPageSteps refreshPage() {
        driverManager.initPage(paymenstPage);
        return this;
    }
}
