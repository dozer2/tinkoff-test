package com.borisenko.test.pages;

import com.borisenko.test.message.ErrorText;
import com.borisenko.test.steps.CommunalPaymentSteps;
import com.borisenko.test.steps.HomePageSteps;
import com.borisenko.test.steps.MoscowZKUSteps;
import org.testng.annotations.Test;

/**
 * Created by maxim on 21.04.18.
 */
public class PaymentsTest extends TestBase {

	HomePageSteps homePageSteps = new HomePageSteps();

	@Test
	public void fieldsMustBeHaveErrorMessageOnInvalidateValue() throws InterruptedException {
		 homePageSteps.openHomePage()
		         .openPaymentsPage()
				 .openCommunalPaymentsPage()
		         .selectRegion("г. Москва")
				 .openMoscowZKUPage()
				 .setValueInToField(CommunalPaymentsPage.E_MAIL_LABEL,"wrong email")
				 .ckickToCheckDebt()
				 .checkErrorMessageInToField(CommunalPaymentsPage.PAYER_CODE_LABEL, ErrorText.FIELD_IS_REQUIRED)
				 .checkErrorMessageInToField(CommunalPaymentsPage.LOGIN_OR_PHONE_LABEL, ErrorText.FIELD_IS_REQUIRED)
				 .checkErrorMessageInToField(CommunalPaymentsPage.E_MAIL_LABEL, ErrorText.ENTER_CORRECT_EMAIL)
				 .setValueInToField(CommunalPaymentsPage.PAYER_CODE_LABEL,"123")
				 .ckickToCheckDebt()
				 .checkErrorMessageInToField(CommunalPaymentsPage.PAYER_CODE_LABEL, ErrorText.WRONG_FIELD_VALUE)
				 .setValueInToField(CommunalPaymentsPage.LOGIN_OR_PHONE_LABEL,"test")
		 		 .refreshPage()
		 		 .clickToField(CommunalPaymentsPage.PASSWORD_LABEL)
		 		 .clickToField(CommunalPaymentsPage.ENTER_LOGIN_LABEL)
				 .checkErrorMessageInToField(CommunalPaymentsPage.PASSWORD_LABEL, ErrorText.FIELD_IS_REQUIRED)
				 .clearField(CommunalPaymentsPage.ENTER_LOGIN_LABEL)
				 .refreshPage()
				 .setValueInToField(CommunalPaymentsPage.LOGIN_OR_PHONE_LABEL,"7345")
				 .refreshPage()
				 .clickToField(CommunalPaymentsPage.E_MAIL_LABEL)
				 .checkErrorMessageInToField(CommunalPaymentsPage.ENTER_PHONE_LABEL, ErrorText.WRONG_COUNT_SYMBOL_NUMBER);
	}

	@Test
	public void shouldBeActiveRegionInMoscow() throws InterruptedException {
		String activeRegionName = "Москве";
		String selectRegionName = "г. Москва";

		CommunalPaymentSteps communalPaymentSteps = homePageSteps.openHomePage()
				.openPaymentsPage()
				.openCommunalPaymentsPage();
		if (communalPaymentSteps.getActiveRegionName().compareTo(activeRegionName) != 0) {
			communalPaymentSteps = communalPaymentSteps.selectRegion(selectRegionName);
		}
		communalPaymentSteps.checkActiveRegion(activeRegionName);
	}

	@Test
    public void shouldBeFirstMyFavoriteProviderInFastSearch() throws InterruptedException {
			String favoriteProvider=CommunalPaymentSteps.MOSCOW_ZKU;
            homePageSteps.openHomePage()
					.openPaymentsPage()
					.openCommunalPaymentsPage()
					.selectRegion("г. Москва")
					.setTextInFastSearch(favoriteProvider)
					.checkPositionValueInFastSearch(favoriteProvider,0);

	}

	@Test
	public void shouldBeValidSelectProviderInFastSearch() throws InterruptedException {
		MoscowZKUSteps moscowZKUSteps=homePageSteps.openHomePage()
				.openPaymentsPage()
				.openCommunalPaymentsPage()
				.selectRegion("г. Москва")
				.openMoscowZKUPage();
		String pageTitle=moscowZKUSteps.getPageTitle();
		moscowZKUSteps.openPaymentsPage()
				.setTextInFastSearch(CommunalPaymentSteps.MOSCOW_ZKU)
				.selectTexInSuggestMenu(CommunalPaymentSteps.MOSCOW_ZKU);
		MoscowZKUSteps.openMoscowZKUPage().checkPageTitle(pageTitle);

	}

	@Test
	public void shouldBeNotExistsProviderInListOnOtherRegion() throws InterruptedException {
		String oldRegion = "Москве";
		String newSelectRegionText = "г. Санкт-Петербург";
		String newRegion = "Санкт-Петербурге";
			homePageSteps.openHomePage()
				.openPaymentsPage()
				.openCommunalPaymentsPage()
				.selectRegion("г. Москва")
				.checkActiveRegion(oldRegion)
				.isExistsProvider(CommunalPaymentSteps.MOSCOW_ZKU)
				.openRegionPage()
				.selectRegion(newSelectRegionText,CommunalPaymentSteps.class)
				.checkActiveRegion(newRegion)
			    .isNotExistsProvider(CommunalPaymentSteps.MOSCOW_ZKU);
	}


}
