package com.ikcon.tech.glue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.ikcon.tech.utils.TestContextInitilizer;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * methods of this class execute automatically based on the annotation provided,
 * for more, read the description of methods in this class
 */
public class Hooks {

    private WebDriver driver;
    private static Logger logger = LogManager.getLogger(Hooks.class);

    // get TestContextInitializer through DI, which is holding the the driver
    // instance
    public Hooks(TestContextInitilizer initializer) {
	this.driver = initializer.driver;
    }

    // execute before each scenario starts
    @Before
    public void beforeScenario() {
	logger.info("Before scenario executed");
	logger.info("Browser window maximized");
	// maximize the browser window before performing any operations on the page
	this.driver.manage().window().maximize();
    }

    // execute after each scenario
    @After
    public void afterScenario() {
	logger.info("After scenario executed");
	logger.info("Browser window closed");
	// close the browser
	this.driver.quit();
    }

    // execute after each failed step of a scenario
    @AfterStep
    public void generateFailedStepScreenshot(Scenario scenario) {
	byte[] fileContent = null;
	File screenshotFile = null;
	if (scenario.isFailed()) {
	    logger.info("Scenario Step failed");
	    logger.debug(scenario.getLine() + " is the failed step of the scenario");
	    screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    try {
		fileContent = FileUtils.readFileToByteArray(screenshotFile);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    scenario.attach(fileContent, "image/jpg", "failedStep");
	}

    }

}
