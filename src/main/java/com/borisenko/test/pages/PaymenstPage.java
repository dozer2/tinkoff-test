package com.borisenko.test.pages;

import com.borisenko.test.exceptions.TPSException;
import com.borisenko.test.pages.elements.Item;
import com.borisenko.test.pages.elements.search.SearchSuggested;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by maxim on 21.04.18.
 */
public class PaymenstPage extends BasePage {


    @FindBy(css = "[data-qa-file='SearchSuggested']")
    SearchSuggested searchSuggest;

    @FindBy(css = "[class^='PaymentsCatalogHeader__regionSelect_']")
    @CacheLookup
    Item region;

    @FindBy(css = "[data-qa-file='UIMenuItemProvider']")
    @CacheLookup
    private List<Item> menuItemProvider;


    public void clickForOpenRegionPage()
    {
       region.click();
    }

    public String getActiveRegion()
    {
        return region.getText();
    }

    private Item getItemByName(String providerName) {
        return menuItemProvider.stream()
                .filter(items -> items.getText().compareTo(providerName) == 0)
                .findFirst()
                .orElseThrow(() -> new TPSException("Can't found link " + providerName));
    }

    public void openMenuItemProvider(String providerName)
    {
        getItemByName(providerName).click();
        getLoaderProgress().untilLoadPage();
    }

    public int getIndexTextInSuggestSearch(String text)
    {
        return searchSuggest.getTextIndexInSuggest(text);
    }

    public void setTextInSuggestSearch(String text)
    {
        searchSuggest.setText(text);
    }

    public void selectInSuggestSearch(int index)
    {
        searchSuggest.selectSuggestItemByIndex(index);
        getLoaderProgress().untilLoadPage();
    }
    public Boolean isExistsTextInSuggestList(String text)
    {
        return searchSuggest.isExistsTextInSuggestList(text);
    }

    public Boolean isNotExistsTextInSuggestList(String text) {
        return !searchSuggest.isExistsTextInSuggestList(text);
    }
}
