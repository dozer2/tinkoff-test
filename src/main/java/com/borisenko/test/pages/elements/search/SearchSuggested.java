package com.borisenko.test.pages.elements.search;

import com.borisenko.test.pages.elements.Element;
import com.borisenko.test.util.conditions.IsNotExistElement;
import com.borisenko.test.util.selenium.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Created by maxim on 22.04.18.
 */
public class SearchSuggested extends Element {

    @FindBy(css = "[data-qa-file='SearchInput']")
    Element input;

    @FindBy(css = "[data-qa-file='SuggestBlock']")
    Element suggestCheck;

    @FindBy(xpath = "//*[@data-qa-file='SuggestBlock']/*/*[@data-qa-file='GridColumn']")
    List<SearhItem> suggestListItem;



    public SearchSuggested(WebElement webElement) {
        super(webElement);
    }

   public void setText(String text)
   {
       input.clear();
       input.sendKeys(text);
       DriverManager.getInstance().checkVisibleElement(ExpectedConditions.visibilityOf(suggestCheck.getWebElement()), Duration.ofSeconds(5));
   }

   public int getTextIndexInSuggest(String text)
   {
       OptionalInt indexText = IntStream.range(0, suggestListItem.size())
               .filter(i -> text.compareTo(suggestListItem.get(i).getMainText().getText())==0)
               .findFirst();
       return indexText.isPresent()?indexText.getAsInt():-1;
   }

   public void selectSuggestItemByIndex(int index)
   {
       suggestListItem.get(index).click();
       DriverManager.getInstance().checkUntil(new IsNotExistElement(suggestCheck), Duration.ofSeconds(5));
   }

   public Boolean isExistsTextInSuggestList(String text)
   {
       setText(text);
       return getTextIndexInSuggest(text)>-1;
   }

}
