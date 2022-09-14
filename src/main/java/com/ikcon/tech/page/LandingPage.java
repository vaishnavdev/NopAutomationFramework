package com.ikcon.tech.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    
    public WebDriver driver;
    
    @FindBy(how = How.CLASS_NAME, using = "ico-login")
    public WebElement loginButton;
    
    public LandingPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(this.driver, this);
    }

}
