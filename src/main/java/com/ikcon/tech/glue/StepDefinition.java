package com.ikcon.tech.glue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.ikcon.tech.page.HomePage;
import com.ikcon.tech.page.LandingPage;
import com.ikcon.tech.page.LoginPage;
import com.ikcon.tech.page.ShoppingCart;
import com.ikcon.tech.utils.TestContextInitilizer;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//for screenshots folder add timestamp with month and year
//format is - d-MMM-YY HH-mm, so that it will match with extent reports folder
// and while sending mail only particular build screenshots can be sent
public class StepDefinition {

    private static final Logger logger = LogManager.getLogger(StepDefinition.class);
    private WebDriver driver;
    private TestContextInitilizer initializer;
    private LoginPage loginPage;
    private HomePage homePage;
    private LandingPage landingPage;
    private ShoppingCart shoppingCart;
    private static final String projectPath = "C:\\ECLIPSE_WORKSPACE\\selenium_Testing\\NopCommerce-Automation\\Screenshots";

    public StepDefinition(TestContextInitilizer initializer) {
	this.initializer = initializer;
	this.driver = this.initializer.driver;
	this.landingPage = this.initializer.pageObjectManager.getLandingPageInstance();
	this.loginPage = this.initializer.pageObjectManager.getLoginPageInstance();
	this.homePage = this.initializer.pageObjectManager.getHomePageInstance();
	this.shoppingCart = this.initializer.pageObjectManager.getShoppingCartInstance();
    }

    @Given("User is on nopCommerce Landing Page and take screenshot")
    public void user_is_on_nop_commerce_landing_page() throws IOException {
	logger.info("---------------------------------------------------------------------");
	logger.info("USER IS ON NopCOMMERCE LANDING PAGE");
	// check the title of page
	String title = this.driver.getTitle();
	logger.debug("LANDING PAGE TITLE IS " + driver.getTitle() + " " + " AND LANDING PAGE URL IS "
		+ driver.getCurrentUrl());
	// take screenshot
	takePageScreenshot(driver, projectPath + "\\LoginPageScreenshots\\NOPC_LandingPage");
	logger.info("LANDING PAGE SCREENSHOT IS CAPTURED");
	// validate the title of page
	Assert.assertTrue(title.contains("nopCommerce demo"));
	logger.info("LANDING PAGE ASSERTION SUCCESSFULL");
    }

    @When("User enters the Username {string},Password {string},take screenshot and SignIn")
    public void user_enters_the_username_password_and_sign_in(String username, String password)
	    throws InterruptedException, IOException {
	logger.info("USER IS ABOUT TO ENTER LOGIN PAGE");
	// click on login button
	landingPage.loginButton.click();
	logger.info("LOGIN PAGE LINK IS CLICKED");
	logger.info("USER IS ON LOGIN PAGE");
	// enter username
	loginPage.emailInput.sendKeys(username);
	logger.debug("USER HAS ENTERED USERNAME " + username);
	// enter password
	loginPage.passwordInput.sendKeys(password);
	logger.info("USER HAS ENTERED PASSWORD " + password);
	// take screenshot
	takePageSectionScreenshot(loginPage.loginSectionDiv, projectPath + "\\LoginPageScreenshots\\NOPC_LoginPage");
	logger.info("SCREENSHOT IS CPATURED WITH LOGIN DETAILS");
	// click on Login
	loginPage.SignInButton.click();
	logger.info("SIGN IN BUTTON IS CLICKED");
    }

    @Then("User will be loggedin,navigated to homepage and take screenshot")
    public void user_will_be_loggedin_and_navigated_to_homepage() throws IOException {
	logger.info("USER IS LOGGED IN TO HOME PAGE");
	boolean flag = false;
	// get URL of the current page
	String url = driver.getCurrentUrl();
	logger.debug("HOME PAGE URL IS " + driver.getCurrentUrl());
	// take screenshot after reaching home page
	takePageScreenshot(this.driver, projectPath + "\\LoginPageScreenshots\\NOPC_HomePage");
	logger.info("SCREENSHOT OF HOME PAGE IS CAPTURED");
	InputStream is = new FileInputStream(
		"C:\\ECLIPSE_WORKSPACE\\selenium_Testing\\NopCommerce-Automation\\src\\test\\resources\\globalconfiguration.properties");
	Properties props = null;
	props = new Properties();
	props.load(is);
	String homePageUrl = props.getProperty("HomePageUrl");
	if (url.equals(homePageUrl)) {
	    flag = true;
	}
	Assert.assertEquals(flag, Boolean.TRUE);
	logger.info("HOME PAGE VALIDATION SUCCESSFULL");
    }

    @Given("User is on nopCommerce home page and take screenshot")
    public void user_is_on_nop_commerce_home_page() throws IOException, InterruptedException {
	logger.info("---------------------------------------------------------------------------");
	logger.info("USER IS ON NopCOMMERCE HOME PAGE");
	// get home page title
	String title = driver.getTitle();
	logger.debug(
		"HOME PAGE TITLE IS " + driver.getTitle() + " " + " AND HOME PAGE URL IS " + driver.getCurrentUrl());
	// take screenshot after reaching home page
	takePageScreenshot(this.driver, projectPath + "\\AddProductScreenshots\\AP_HomePage");
	logger.info("HOME PAGE SCREENSHOT CAPTURED");
	// validate home page title
	Assert.assertTrue(title.equals("nopCommerce demo store"));
	logger.info("HOME PAGE VALIDATION SUCCESSFULL");
    }

    @When("^User chooses a category(.+) and subcategory(.+) of products to be displyed and take screenshot$")
    public void user_chooses_a_category_and_subcategory_of_products_to_be_displyed(String category, String subcategory)
	    throws Throwable {
	logger.info("USER IS ABOUT TO BUILD A PRODUCT");
	List<WebElement> productCategories = homePage.productCategories;
	Actions action = new Actions(this.driver);
	for (WebElement singleCategory : productCategories) {
	    try {
		if (singleCategory.getText().equalsIgnoreCase(category)) {
		    action.moveToElement(singleCategory).build().perform();
		    logger.debug("USER HAS CHOOSEN THE CATEGORY AS " + category);
		}
	    } catch (Exception e) {
		e.getMessage();
	    }
	    List<WebElement> subcategories = homePage.subCategories;
	    for (WebElement singleSubCategory : subcategories) {
		try {
		    if (singleSubCategory.getText().equalsIgnoreCase(subcategory)) {
			// take screenshot after opening the categories section
			takePageScreenshot(driver, projectPath + "\\AddProductScreenshots\\AP_ComputersSection");
			logger.info("CATEGORY AND ITS SUBCATEGORY DETAILS SCREENSHOT HAS BEEN CAPTURED");
			singleSubCategory.click();
			logger.debug("USER HAS CHOOSEN A SUB-CATEGORY " + subcategory);
		    }
		} catch (Exception e) {
		    e.getMessage();
		}
	    }
	}
    }

    @Then("^User will build a computer by choosing required details (.+),(.+),(.+),(.+),(.+) and take screenshot$")
    public void user_will_build_a_computer_by_choosing_required_details_(String processor, String ram, String hdd,
	    String os, String software) throws IOException {
	// take screenshot before building the computer
	takePageSectionScreenshot(homePage.buildComputerSection,
		projectPath + "\\AddProductScreenshots\\AP_BuildComputer");
	logger.info("BUILD COMPUTER LINK SCREENSHOT HAS BEEN CAPTURED");
	// click on link to build you own computer
	homePage.buildComputerLink.click();
	logger.info("USER HAS REACHED TO BUILD COMPUTER SECTION");
	// choose required details to build computer
	// select Processor
	homePage.selectProcessor.click();
	List<WebElement> processorOptionsList = homePage.processorOptions;
	for (int i = 0; i < processorOptionsList.size(); i++) {
	    if (processorOptionsList.get(i).getText().contains(processor)) {
		processorOptionsList.get(i).click();
		logger.debug("USER HAS CHOOSEN A PROCESSOR " + processorOptionsList.get(i).getText());
		break;
	    }
	}
	// select RAM
	homePage.selectRam.click();
	List<WebElement> ramOptionsList = homePage.ramOptions;
	for (WebElement ramOption : ramOptionsList) {
	    if (ramOption.getText().equalsIgnoreCase(ram)) {
		ramOption.click();
		logger.debug("USER HAS CHOOSEN A RAM " + ram);
		break;
	    }
	}
	// select HDD
	List<WebElement> hddOptions = homePage.hddOptions;
	String[] hddData = hdd.split(" ");
	for (int i = 0; i < hddOptions.size(); i++) {
	    if (hddOptions.get(i).getText().contains(hddData[0] + " " + hddData[1])) {
		String value1 = homePage.hddOption1.getDomProperty("value");
		String value2 = homePage.hddOption2.getDomProperty("value");
		if (hddData[2].equals(value1)) {
		    homePage.hddOption1.click();
		    logger.debug("USER HAS CHOOSEN A HDD " + hddOptions.get(i).getText());

		} else if (hddData[2].equals(value2)) {
		    homePage.hddOption2.click();
		    logger.debug("USER HAS CHOOSEN A HDD " + hddOptions.get(i).getText());
		}
		break;
	    }
	}
	// select os
	List<WebElement> osOptions = homePage.osOptions;
	String[] osData = os.split(" ");
	for (WebElement osOption : osOptions) {
	    if (osOption.getText().contains(osData[0] + " " + osData[1])) {
		String value1 = homePage.osOption1.getDomProperty("value");
		String value2 = homePage.osOption2.getDomProperty("value");
		if (osData[2].equals(value1)) {
		    homePage.osOption1.click();
		    logger.debug("USER HAS CHOOSEN AN OS " + osOption.getText());
		} else if (osData[2].equals(value2)) {
		    homePage.osOption2.click();
		    logger.debug("USER HAS CHOOSEN AN OS " + osOption.getText());
		}
		break;
	    }
	}
	// select software
	List<WebElement> softwareOptions = homePage.softwareOptions;
	String[] softwareData = software.split(" ");
	String selectedSoftwareOption = null;
	for (WebElement softwareOption : softwareOptions) {
	    if (softwareOption.getText().contains(softwareData[0] + " " + softwareData[1])) {
		String value1 = homePage.softwareOption1.getDomProperty("value");
		String value2 = homePage.softwareOption2.getDomProperty("value");
		String value3 = homePage.softwareOption3.getDomProperty("value");
		if (softwareData[2].equals(value1)) {
		    // default value will be selected
		    selectedSoftwareOption = softwareOption.getText();
		    logger.debug("USER HAS CHOOSEN THE SOFTWARE " + softwareData[0] + softwareData[1]);
		} else if (softwareData[2].equals(value2)) {
		    homePage.softwareOption1.click();
		    homePage.softwareOption2.click();
		    selectedSoftwareOption = softwareOption.getText();
		    logger.debug("USER HAS CHOOSEN THE SOFTWARE " + softwareData[0] + softwareData[1]);
		} else if (softwareData[2].equals(value3)) {
		    homePage.softwareOption1.click();
		    homePage.softwareOption3.click();
		    selectedSoftwareOption = softwareOption.getText();
		    logger.debug("USER HAS CHOOSEN THE SOFTWARE " + softwareData[0] + softwareData[1]);
		} // elseif
	    } // if
	    break;
	} // for
	  // take screenshot after Building the product with all details
	takePageSectionScreenshot(homePage.buildComputerDetailsSection,
		projectPath + "\\AddProductScreenshots\\AP_BuildComputerDetails");
	logger.info("SCREENSHOT HAS BEEN CAPTURED WITH ALL THE DETAILS ENTERED BY THE USER FOR BUILDING THE COMPUTER");
	// validate the software value
	Assert.assertTrue(selectedSoftwareOption.contains(softwareData[0] + " " + softwareData[1]));
    }// method

    @Then("Add the product to cart and take screenshot")
    public void add_the_product_to_cart() throws InterruptedException, IOException {
	// click on Add to Cart Button
	homePage.addToCartButton.click();
	logger.info("ADD TO CART BUTTON IS CLICKED");
    }

    @When("^User opens the shopping cart$")
    public void user_opens_the_shopping_cart() throws Throwable {
	// open the shopping cart
	takePageSectionScreenshot(homePage.shoppingCartBtn,
		projectPath + "\\ValidateProductScreenshots\\VP_ShoppingCartLink");
	logger.info("SHOPPING CART LINK SCREENSHOT IS CAPTURED");
	homePage.shoppingCartBtn.click();
	logger.info("SHOPPING CART LINK IS CLICKED");
    }

    @Then("^User should be displayed with the product that is added to shopping cart and take screenshot$")
    public void user_should_be_displayed_with_the_product_that_is_added_to_shopping_cart() throws Throwable {
	// take screenshot of product details after opening the shopping cart
	takePageSectionScreenshot(shoppingCart.shoppingCartSection,
		projectPath + "\\ValidateProductScreenshots\\VP_ShoppingCartProductDetails");
	logger.info("SHOPPING CART PRODUCT DETAILS SCREENSHOT IS CAPTURED");
	// get the product details in shopping cart
	String productName = this.shoppingCart.productName.getText();
	// validate the shopping cart details
	Assert.assertEquals(productName, "Build your own computer");
	logger.info("PRODUCT DETAILS IN THE SHOPPING CART HAS BEEN VERIFIED");
    }

    @When("^User opens the shopping cart, product will be displayed and take screenshot$")
    public void user_opens_the_shopping_cart_product_will_be_displayed_and_take_screenshot() throws Throwable {
	takePageSectionScreenshot(homePage.shoppingCartBtn,
		projectPath + "\\RemoveProductScreenshots\\RP_ShoppingCartLink");
	// open the shopping cart
	homePage.shoppingCartBtn.click();
	logger.info("SHOPPING CART BUTTON IS CLICKED");
	// get the product details in shopping cart
	String productName = this.shoppingCart.productName.getText();
	// take screenshot of product details after opening the shopping cart
	takePageSectionScreenshot(shoppingCart.shoppingCartSection,
		projectPath + "\\RemoveProductScreenshots\\RP_ShoppingCartProductDetails");
	logger.info("SHOPPING CART PRODUCT DETAILS SCREENSHOT IS CAPTURED");
	// validate the shopping cart details
	Assert.assertEquals(productName, "Build your own computer");
	logger.info("PRODUCT DETAILS IN THE SHOPPING CART HAS BEEN VERIFIED");
    }

    @Then("^User will remove the product$")
    public void user_will_remove_the_product() throws Throwable {
	// remove the products from shopping cart
	List<WebElement> removeBtns = shoppingCart.removeProductBtn;
	for (int i = 0; i < removeBtns.size(); i++) {
	    removeBtns.get(i).click();
	    logger.info("PRODUCT IS REMOVED FROM SHOPPING CART");
	}
    }

    @And("^the shopping cart becomes empty and take screenshot$")
    public void the_shopping_cart_becomes_empty_and_take_screenshot() throws Throwable {
	// validate the shopping cart is empty or not
	String cartData = shoppingCart.cartEmptyValidator.getText();
	// take screenshot after removing the products from shopping cart
	takePageScreenshot(this.driver, projectPath + "\\RemoveProductScreenshots\\RP_ShoppingCartEmpty");
	Assert.assertEquals(cartData, "Your Shopping Cart is empty!");
    }

    // update Built product
    @When("^User opens the shopping cart and take screenshot$")
    public void user_opens_the_shopping_cart_and_take_screenshot() throws Throwable {
	// open the shopping cart
	homePage.shoppingCartBtn.click();
	// get the product details in shopping cart
	String productName = this.shoppingCart.productName.getText();
	// take screenshot of product details after opening the shopping cart
	takePageSectionScreenshot(shoppingCart.shoppingCartSection,
		projectPath + "\\UpdateProductScreenshots\\UP_BeforeUpdateShoppingCartProductDetails");
	// validate the shopping cart details
	Assert.assertEquals(productName, "Build your own computer");
    }

    @And("^Edit the existing Built product details with new details(.+),(.+),(.+),(.+),(.+) and take screenshot$")
    public void edit_the_existing_built_product_details_with_new_details_and_take_screenshot(String processor,
	    String ram, String hdd, String os, String software) throws Throwable {
	// click on edit link to edit the built computer
	List<WebElement> editBtns = shoppingCart.editProductBtn;
	for (int i = 0; i < editBtns.size(); i++) {
	    editBtns.get(i).click();
	    logger.info("USER HAS REACHED TO BUILD COMPUTER SECTION TO UPDATE THE PRODUCT DETAILS");
	    // choose required details to build computer
	    // select Processor
	    homePage.selectProcessor.click();
	    List<WebElement> processorOptionsList = homePage.processorOptions;
	    for (int j = 0; j < processorOptionsList.size(); j++) {
		if (processorOptionsList.get(j).getText().contains(processor)) {
		    processorOptionsList.get(j).click();
		    logger.debug("USER HAS UPDATED THE PROCESSOR TO " + processorOptionsList.get(j).getText());
		    break;
		}
	    }
	    // select RAM
	    homePage.selectRam.click();
	    List<WebElement> ramOptionsList = homePage.ramOptions;
	    for (WebElement ramOption : ramOptionsList) {
		if (ramOption.getText().contains(ram)) {
		    ramOption.click();
		    logger.debug("USER HAS UPDATED THE RAM TO" + ram);
		    break;
		}
	    }
	    // select HDD
	    List<WebElement> hddOptions = homePage.hddOptions;
	    String[] hddData = hdd.split(" ");
	    for (int k = 0; k < hddOptions.size(); k++) {
		if (hddOptions.get(k).getText().contains(hddData[0] + " s" + hddData[1])) {
		    String value1 = homePage.hddOption1.getDomProperty("value");
		    String value2 = homePage.hddOption2.getDomProperty("value");
		    if (hddData[2].equals(value1)) {
			homePage.hddOption1.click();
			logger.debug("USER HAS UPDATED THE HDD TO " + hddOptions.get(k).getText());

		    } else if (hddData[2].equals(value2)) {
			homePage.hddOption2.click();
			logger.debug("USER HAS UPDATED THE HDD TO " + hddOptions.get(k).getText());
		    }
		    break;
		}
	    }
	    // select os
	    List<WebElement> osOptions = homePage.osOptions;
	    String[] osData = os.split(" ");
	    for (WebElement osOption : osOptions) {
		if (osOption.getText().contains(osData[0] + " " + osData[1])) {
		    String value1 = homePage.osOption1.getDomProperty("value");
		    String value2 = homePage.osOption2.getDomProperty("value");
		    if (osData[2].equals(value1)) {
			homePage.osOption1.click();
			logger.debug("USER HAS UPDATED THE OS TO " + osOption.getText());
		    } else if (osData[2].equals(value2)) {
			homePage.osOption2.click();
			logger.debug("USER HAS UPDATED THE OS TO " + osOption.getText());
		    }
		    break;
		}
	    }
	    // select software
	    List<WebElement> softwareOptions = homePage.softwareOptions;
	    String[] softwareData = software.split(" ");
	    String selectedSoftwareOption = null;
	    for (WebElement softwareOption : softwareOptions) {
		if (softwareOption.getText().contains(softwareData[0] + " " + softwareData[1])) {
		    String value1 = homePage.softwareOption1.getDomProperty("value");
		    String value2 = homePage.softwareOption2.getDomProperty("value");
		    String value3 = homePage.softwareOption3.getDomProperty("value");
		    if (softwareData[2].equals(value1)) {
			// default value will be selected
			selectedSoftwareOption = softwareOption.getText();
			logger.debug("USER HAS UPDATED THE SOFTWARE TO " + softwareData[0] + softwareData[1]);
		    } else if (softwareData[2].equals(value2)) {
			homePage.softwareOption1.click();
			homePage.softwareOption2.click();
			selectedSoftwareOption = softwareOption.getText();
			logger.debug("USER HAS UPDATED THE SOFTWARE TO " + softwareData[0] + softwareData[1]);
		    } else if (softwareData[2].equals(value3)) {
			homePage.softwareOption2.click();
			homePage.softwareOption3.click();
			selectedSoftwareOption = softwareOption.getText();
			logger.debug("USER HAS UPDATED THE SOFTWARE TO " + softwareData[0] + softwareData[1]);
		    } // elseif
		} // if
		break;
	    } // for
	      // take screenshot after Building the product with all details
	    takePageSectionScreenshot(homePage.buildComputerDetailsSection,
		    projectPath + "\\UpdateProductScreenshots\\UP_BuildComputerDetails");
	    logger.info(
		    "SCREENSHOT HAS BEEN CAPTURED WITH ALL THE UPDATED DETAILS ENTERED BY THE USER FOR BUILDING THE COMPUTER");
	    // validate the software value
	    Assert.assertTrue(selectedSoftwareOption.contains(softwareData[0] + " " + softwareData[1]));
	}
    }

    @Then("^User adds the updated product to shopping cart$")
    public void user_adds_the_product_to_shopping_cart() throws Throwable {
	// click on Add to Cart Button
	homePage.addToCartButton.click();
	logger.info("UPDATE CART BUTTON IS CLICKED");
    }

    @And("^Updated built product details should be displayed in the shopping cart and take screenshot$")
    public void updated_built_product_details_should_be_displayed_in_the_shopping_cart_and_take_screenshot()
	    throws Throwable {
	// open the shopping cart
	homePage.shoppingCartBtn.click();
	logger.info("SHOPPING CART BUTTON IS CLICKED");
	// get the product details in shopping cart
	String productName = this.shoppingCart.productName.getText();
	// take screenshot of product details after opening the shopping cart
	takePageSectionScreenshot(shoppingCart.shoppingCartSection,
		projectPath + "\\UpdateProductScreenshots\\UP_ShoppingCartProductDetails");
	logger.info("SHOPPING CART PRODUCT DETAILS SCREENSHOT IS CAPTURED");
	// validate the shopping cart details
	Assert.assertEquals(productName, "Build your own computer");
	logger.info("PRODUCT DETAILS ARE VERIFIED");
    }

    /**
     * allows to take screenshot on Web page level
     * 
     * @param driver   - used to take screenshot in the page
     * @param fileName - name for the file
     * @throws IOException
     */
    public static void takePageScreenshot(WebDriver driver, String fileName) throws IOException {
	// Class<StepDefinition> clazz = StepDefinition.class;
	// Method method = clazz.getEnclosingMethod();
	logger.info("ENTERED INTO SCREENSHOT METHOD");
	TakesScreenshot ts = (TakesScreenshot) driver;
	File src = ts.getScreenshotAs(OutputType.FILE);
	// Calendar calendar = Calendar.getInstance();
	// Date d = calendar.getTime();
	// String[] dateData = d.toString().split(" ");
	// String[] date = dateData[3].split(":");
	// File target = new File(fileName + date[0] + "-" + date[1] + "-" + date[2] +
	// ".png");
	String time = currentDateTime();
	File target = new File(fileName + " " + time);
	FileUtils.copyFile(src, target);
    }

    /**
     * allows to take screenshot on particular section of Web page or specific
     * webelements level
     * 
     * @param webElement - the html element on which you need the screenshot
     * @param fileName   - name of the file
     * @throws IOException
     */
    public static void takePageSectionScreenshot(WebElement webElement, String fileName) throws IOException {
	// Class<StepDefinition> clazz = StepDefinition.class;
	// Method method = clazz.getEnclosingMethod();
	logger.info("ENTERED INTO SCREENSHOT METHOD");
	WebElement pageSection = webElement;
	File src = pageSection.getScreenshotAs(OutputType.FILE);
	String time = currentDateTime();
	File target = new File(fileName + " " + time);
	FileUtils.copyFile(src, target);
    }

    public static String currentDateTime() {
	Date now = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMM-YY HH-mm");
	String time = dateFormat.format(now);
	return time;
    }
}