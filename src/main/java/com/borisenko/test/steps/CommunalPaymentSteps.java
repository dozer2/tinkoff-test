package com.borisenko.test.steps;

import com.borisenko.test.pages.CommunalPaymentsPage;
import com.borisenko.test.pages.PaymenstPage;
import com.borisenko.test.pages.RegionPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

/**
 * Created by maxim on 21.04.18.
 */
public class CommunalPaymentSteps extends BaseSteps<CommunalPaymentSteps> {

    public static final String MOSCOW_ZKU="ЖКУ-Москва";

    PaymenstPage communalPaymentPage;

    public CommunalPaymentSteps setCommunalPaymentPage(PaymenstPage communalPaymentPage) {
        this.communalPaymentPage = communalPaymentPage;
        return this;
    }

    public CommunalPaymentSteps checkActiveRegion(String expectedRegion)
    {
        assertThat(expectedRegion+" isn't  active", communalPaymentPage.getActiveRegion(), equalToIgnoringCase(expectedRegion));
        return this;
    }

    public RegionPageSteps openRegionPage()
    {
        communalPaymentPage.clickForOpenRegionPage();
        RegionPage regionPage= new RegionPage();
        driverManager.initPage(regionPage);
        return new RegionPageSteps().setRegionPage(regionPage).setParentSteps(this);
    }

    public MoscowZKUSteps openMoscowZKUPage()
    {
        communalPaymentPage.openMenuItemProvider(MOSCOW_ZKU);
        CommunalPaymentsPage communalPaymentPage= new CommunalPaymentsPage();
        driverManager.initPage(communalPaymentPage);
        return new MoscowZKUSteps().setCommunalPaymentsPage(communalPaymentPage);
    }

    public CommunalPaymentSteps  isExistsProvider(String providerName)
    {
        assertThat(providerName+" isn't found in list", communalPaymentPage.isExistsTextInSuggestList(providerName), is(true));
        return this;

    }
    public CommunalPaymentSteps  isNotExistsProvider(String providerName)
    {
        assertThat(providerName+"  found in list", communalPaymentPage.isNotExistsTextInSuggestList(providerName), is(true));
        return this;

    }

    public String getActiveRegionName()
    {
        return communalPaymentPage.getActiveRegion();
    }

    public CommunalPaymentSteps selectRegion(String activRegion)
    {
        return openRegionPage()
                .selectRegion(activRegion,CommunalPaymentSteps.class);

    }

    public CommunalPaymentSteps setTextInFastSearch(String textValue)
    {
     communalPaymentPage.setTextInSuggestSearch(textValue);
        return this;
    }
    public CommunalPaymentSteps checkPositionValueInFastSearch(String textValue, int positionText)
    {
        assertThat("Position "+textValue+" in fast search is not equals "+positionText,communalPaymentPage.getIndexTextInSuggestSearch(textValue),is(positionText));
        return this;
    }

    @Override
    public CommunalPaymentSteps refreshPage() {
        driverManager.initPage(communalPaymentPage);
        return this;
    }
}
