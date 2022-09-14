package com.ikcon.tech.page;

import org.openqa.selenium.WebDriver;

/**
 * This class receives the Driver instance from TestContextInitializer class and
 * is responsible to provide Driver instance to all the PageObjectLocator
 * classes. (HomePage, LandingPage). If you need an instance of this class, 
 * request it through TestContextInitailizer instance.
 *
 */
public class PageObjectManager {

    private WebDriver driver;

    public PageObjectManager(WebDriver driver) {
	this.driver = driver;
    }

    /**
     * @return LandingPage instance with driver instance, to perform activities on
     *         actual html elements of LandingPage using this instance,this instance
     *         will be used by StepDefinition class to use the operations performed
     *         by the class.
     */
    public LandingPage getLandingPageInstance() {
	LandingPage landingPage = new LandingPage(driver);
	return landingPage;
    }

    /**
     * @return HomePage instance with driver instance, to perform activities on
     *         actual html elements of HomePage using this instance, this instance
     *         will be used by StepDefinition class to use the operations performed
     *         by the class.
     */
    public HomePage getHomePageInstance() {
	HomePage homePage = new HomePage(driver);
	return homePage;
    }
    
    /**
     * @return LoginPage instance with driver instance, to perform activities on
     *         actual html elements of HomePage using this instance, this instance
     *         will be used by StepDefinition class to use the operations performed
     *         by the class.
     */
    public LoginPage getLoginPageInstance() {
	LoginPage loginPage = new LoginPage(driver);
	return loginPage;
    }
    
    /**
     * @return ShoppingCartPage instance with driver instance, to perform activities on
     *         actual html elements of HomePage using this instance, this instance
     *         will be used by StepDefinition class to use the operations performed
     *         by the class.
     */
    public ShoppingCart getShoppingCartInstance() {
	ShoppingCart shoppingCart = new ShoppingCart(driver);
	return shoppingCart;
    }
}
