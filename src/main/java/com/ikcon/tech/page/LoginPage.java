package com.ikcon.tech.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    
    private WebDriver driver;
    
    @FindBy(how = How.ID, using = "Email")
    public WebElement emailInput;
    
    @FindBy(how = How.ID, using = "Password")
    public WebElement passwordInput;
    
    @FindBy(how = How.XPATH, using = "//button[@class='button-1 login-button']")
    public WebElement SignInButton;
    
    //used for taking Login section screenshot
    @FindBy(how = How.XPATH, using = "//div[@class='returning-wrapper fieldset']")
    public WebElement loginSectionDiv;
    
    public LoginPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(this.driver, this);
    }

}
