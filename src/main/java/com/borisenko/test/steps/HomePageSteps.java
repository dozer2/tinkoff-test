package com.borisenko.test.steps;

import com.borisenko.test.pages.HomePage;
import com.borisenko.test.pages.PaymenstPage;
import com.borisenko.test.pages.elements.SecondTabLinkPanel;
import com.borisenko.test.util.PropertyLoader;

/**
 * Created by maxim on 21.04.18.
 */
public class HomePageSteps extends BaseSteps<HomePageSteps>{

    HomePage homePage= new HomePage();


    public HomePageSteps openHomePage()
    {
        driverManager.getWebDriver().get(PropertyLoader.loadProperty("site.url") );
        refreshPage();
        return this;
    }

    public PaymentsPageSteps openPaymentsPage()
    {
        homePage.clickToLinkOnSecondTabPanel(SecondTabLinkPanel.PAYMENTS);
        PaymenstPage paymenstPage= new PaymenstPage();
        driverManager.initPage(paymenstPage);
        return new PaymentsPageSteps().setPaymenstPage(paymenstPage);
    }

    @Override
    public HomePageSteps refreshPage() {
        driverManager.initPage(homePage);
        return this;
    }
}
