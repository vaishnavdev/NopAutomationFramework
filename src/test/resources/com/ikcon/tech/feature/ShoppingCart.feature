Feature: Verifying the functionality of Shopping Cart

Background: Login to application
Given User is on nopCommerce Landing Page and take screenshot
When User enters the Username "vaishnavkannan05@gmail.com",Password "Race@race1",take screenshot and SignIn
Then User will be loggedin,navigated to homepage and take screenshot

Scenario Outline: Build a computer product and add it to shopping cart with required details
Given User is on nopCommerce home page and take screenshot
When User chooses a category<Category> and subcategory<SubCategory> of products to be displyed and take screenshot
Then User will build a computer by choosing required details <PROCESSOR>,<RAM>,<HDD>,<OS>,<SOFTWARE> and take screenshot
And Add the product to cart and take screenshot

Examples:
|Category |SubCategory|            PROCESSOR          |RAM |  HDD   |     OS        |    SOFTWARE     |
|Computers|  Desktops |2.2 GHz Intel Pentium Dual-Core|2 GB|320 GB 6|Vista Premium 9|Acrobat Reader 11|

Scenario: Open the shopping cart and verify the product Build
Given User is on nopCommerce home page and take screenshot
When User opens the shopping cart
Then User should be displayed with the product that is added to shopping cart and take screenshot

Scenario Outline: Update the product built in shopping cart
Given User is on nopCommerce home page and take screenshot
When User opens the shopping cart and take screenshot
And Edit the existing Built product details with new details<PROCESSOR>,<RAM>,<HDD>,<OS>,<SOFTWARE> and take screenshot
Then User adds the updated product to shopping cart
And Updated built product details should be displayed in the shopping cart and take screenshot

Examples:
|            PROCESSOR          |RAM|  HDD   |     OS     |     SOFTWARE     |
|2.5 GHz Intel Pentium Dual-Core|4GB|400 GB 7|Vista Home 8|Total Commander 12|

Scenario: Remove the product from shopping cart
Given User is on nopCommerce home page and take screenshot
When User opens the shopping cart, product will be displayed and take screenshot
Then User will remove the product
And the shopping cart becomes empty and take screenshot






