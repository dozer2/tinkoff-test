package com.borisenko.test.pages;


import com.borisenko.test.pages.elements.LoaderProgress;
import com.borisenko.test.pages.elements.SecondTabLinkPanel;
import org.openqa.selenium.support.FindBy;


/*
 * Abstract class representation of a BasePage in the UI. BasePage object pattern
 * 
 * @author Sebastiano Armeli-Battana
 */
@FindBy(css = ".application")
public  class BasePage {

	@FindBy(css = "[class^='SecondMenu__root']")
	SecondTabLinkPanel secondTabLinkPanel;

	LoaderProgress loaderProgress;


	public SecondTabLinkPanel getSecondTabLinkPanel() {
		return secondTabLinkPanel;
	}

	public LoaderProgress getLoaderProgress() {
		return loaderProgress;
	}

	public void clickToLinkOnSecondTabPanel(String link)
	{
		getSecondTabLinkPanel().clickToLink(link);
		getLoaderProgress().untilLoadPage();
	}
}
