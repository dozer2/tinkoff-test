package com.borisenko.test.util.selenium;

import com.borisenko.test.pages.BasePage;
import com.borisenko.test.util.Browser;
import com.borisenko.test.util.PropertyLoader;
import com.borisenko.test.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Created by maxim on 20.04.18.
 */
public class DriverManager {

    protected WebDriver webDriver;

    private static DriverManager ourInstance  ;

    public static DriverManager getInstance() {
        if (ourInstance == null) {
            synchronized (DriverManager.class) {
                if (ourInstance == null) {
                    ourInstance = new DriverManager();
                }
            }
        }
        return ourInstance;
    }

    private DriverManager() {
        String gridHubUrl = PropertyLoader.loadProperty("grid2.hub");
        Browser browser = new Browser();
        browser.setName(PropertyLoader.loadProperty("browser.name"));
        browser.setVersion("");
        browser.setPlatform("");
        String username = PropertyLoader.loadProperty("user.username");
        String password = PropertyLoader.loadProperty("user.password");
        webDriver = WebDriverFactory.getInstance( browser, username,
                password);
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }


    public <T extends BasePage> void initPage(BasePage page)
    {
        PageFactory.initElements(new CustomFieldDecorator(webDriver), page);
    }

    public void checkUntil(ExpectedCondition<Boolean> conditions,Duration wait)
    {
        Wait<WebDriver> waiting = new FluentWait<>(webDriver)
                .withTimeout(wait)
                .pollingEvery( Duration.ofMillis(500));
        waiting.until(conditions);
    }

    public void checkVisibleElement(ExpectedCondition<WebElement> conditions,Duration wait)
    {
        Wait<WebDriver> waiting = new FluentWait<>(webDriver)
                .withTimeout(wait)
                .pollingEvery( Duration.ofMillis(500));
        waiting.until(conditions);
    }

}
