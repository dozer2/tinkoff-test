package com.borisenko.test.pages;

import com.borisenko.test.util.selenium.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.io.IOException;

/*
 * Base class for all the test classes
 * 
 * @author Sebastiano Armeli-Battana
 */

public class TestBase {
	private static final String SCREENSHOT_FOLDER = "target/screenshots/";
	private static final String SCREENSHOT_FORMAT = ".png";

	private WebDriver webDriver= DriverManager.getInstance().getWebDriver();



	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

  @AfterMethod
  public void setScreenshot(ITestResult result) {
    if (!result.isSuccess()) {
      try {
        WebDriver returned = new Augmenter().augment(webDriver);
        if (returned != null) {
          File f = ((TakesScreenshot) returned).getScreenshotAs(OutputType.FILE);
          try {
            FileUtils.copyFile(f,
                new File(SCREENSHOT_FOLDER + result.getName() + SCREENSHOT_FORMAT));
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      } catch (ScreenshotException se) {
        se.printStackTrace();
      }
    }
  }
}
