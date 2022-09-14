package com.ikcon.tech.utils;

import org.openqa.selenium.WebDriver;

import com.ikcon.tech.page.PageObjectManager;


/**
 * The class manages the PageObjectManager(POM) class instance, so that other
 * classes will request the PageObjectManager instance though this class to
 * access the POM-locator classes from PageObjectManager. It also initializes
 * the WebDriverManager class which will return an instance of WebDriver which
 * will be kept in PICO container for the first time. The same driver instance
 * is binded to PageObjectManager instance, so can PageObjectManager can
 * delegate the driver to POM-locator classes. Request this class instance through
 * dependency injection from PICO container.
 */
public class TestContextInitilizer {

    public PageObjectManager pageObjectManager;
    public WebDriverManager webDriverManager;
    public WebDriver driver;

    public TestContextInitilizer() {
	webDriverManager = new WebDriverManager();
	this.driver = webDriverManager.InitializeSeliniumWebDriver();
	this.pageObjectManager = new PageObjectManager(this.driver);

    }

}
