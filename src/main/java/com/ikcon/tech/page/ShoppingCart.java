package com.ikcon.tech.page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCart {
    
    private WebDriver driver;
    
    @FindBy(how = How.XPATH, using = "//a[@class='product-name']")
    public WebElement productName;
    
    @FindBy(how = How.ID, using = "shopping-cart-form")
    public WebElement shoppingCartSection;
    
    @FindBy(how = How.XPATH, using = "//button[@class='remove-btn']")
    public List<WebElement> removeProductBtn;
    
    @FindBy(how = How.XPATH, using = "//div[@class='no-data']")
    public WebElement cartEmptyValidator;
    
    @FindBy(how = How.XPATH, using = "//div[@class='edit-item']/a")
    public List<WebElement> editProductBtn;
    
    public ShoppingCart(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(this.driver, this);
    }

}
