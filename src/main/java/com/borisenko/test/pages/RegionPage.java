package com.borisenko.test.pages;

import com.borisenko.test.exceptions.TPSException;
import com.borisenko.test.pages.elements.Element;
import com.borisenko.test.pages.elements.Item;
import com.borisenko.test.util.conditions.IsNotExistElement;
import com.borisenko.test.util.selenium.DriverManager;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

/**
 * Created by maxim on 21.04.18.
 */
public class RegionPage extends BasePage {

    @FindBy(css = "[data-qa-file='UIFadingSlideMotion']")
    Element page;

    @FindBy(css = "[data-qa-file='UILink'] [class='ui-link__text']")
    List<Item> regionsList;


    private Item getRegion(String regionName) {
        return regionsList.stream()
                .filter(items -> items.getText().compareTo(regionName) == 0)
                .findFirst()
                .orElseThrow(() -> new TPSException("Can't found region " + regionName));
    }

    public void selectRegion(String regionName)
    {
        getRegion(regionName).click();
        DriverManager.getInstance().checkUntil(new IsNotExistElement(page), Duration.ofSeconds(10));
    }
}
