package com.ikcon.tech.page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    
    public WebDriver driver;
    
    @FindBy(how = How.XPATH, using = "//ul[@class='top-menu notmobile']/li/a")
    public List<WebElement> productCategories;
    
    @FindBy(how = How.XPATH, using = "//ul[@class='sublist first-level']/li/a")
    public List<WebElement> subCategories;
    
    @FindBy(how = How.XPATH, using = "(//div/ul/li/a[@href='/computers'])[1]")
    public WebElement category;
    
    @FindBy(how = How.XPATH, using = "//ul[@class='sublist']/li")
    public List<WebElement> subCategory;
    
    @FindBy(how = How.LINK_TEXT, using = "Build your own computer")
    public WebElement buildComputerLink;
    
    @FindBy(how = How.ID, using = "product_attribute_1")
    public WebElement selectProcessor;
    
    @FindBy(how = How.XPATH, using = "//select[@id='product_attribute_1']/option")
    public List<WebElement> processorOptions;
    
    @FindBy(how = How.ID, using = "product_attribute_2")
    public WebElement selectRam;
    
    @FindBy(how = How.XPATH, using = "//select[@id='product_attribute_2']/option")
    public List<WebElement> ramOptions;
    
    @FindBy(how = How.XPATH, using = "//dd[@id='product_attribute_input_3']/ul/li")
    public List<WebElement> hddOptions;
    
    @FindBy(how = How.XPATH, using = "//input[@id='product_attribute_3_6']")
    public WebElement hddOption1;
    
    @FindBy(how = How.XPATH, using = "//input[@id='product_attribute_3_7']")
    public WebElement hddOption2;
    
    @FindBy(how = How.XPATH, using = "//dd[@id='product_attribute_input_4']/ul/li")
    public List<WebElement> osOptions;
    
    @FindBy(how = How.XPATH, using = "//input[@id='product_attribute_4_8']")
    public WebElement osOption1;
    
    @FindBy(how = How.XPATH, using = "//input[@id='product_attribute_4_9']")
    public WebElement osOption2;
    
    @FindBy(how = How.XPATH, using = "//dd[@id='product_attribute_input_5']")
    public List<WebElement> softwareOptions;
    
    @FindBy(how = How.XPATH, using = "//input[@id='product_attribute_5_10']")
    public WebElement softwareOption1;
    
    @FindBy(how = How.XPATH, using = "//input[@id='product_attribute_5_11']")
    public WebElement softwareOption2;
    
    @FindBy(how = How.XPATH, using = "//input[@id='product_attribute_5_12']")
    public WebElement softwareOption3;
    
    @FindBy(how = How.ID, using = "add-to-cart-button-1")
    public WebElement addToCartButton;
    
    @FindBy(how = How.XPATH, using = "//li[@id='topcartlink']")
    public WebElement shoppingCartBtn;
    
    @FindBy(how = How.XPATH, using = "(//div[@class='details'])[1]")
    public WebElement buildComputerSection;
    
    @FindBy(how = How.XPATH, using = "//div[@class='product-essential']")
    public WebElement buildComputerDetailsSection;
    
    public HomePage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(this.driver, this);
    }

}
