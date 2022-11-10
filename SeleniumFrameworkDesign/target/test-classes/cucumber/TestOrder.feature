@tag
Feature: Purchase order from ecommerce website
  I want to use this template for my feature file
	
	Background: 
		Given I landed on the ecommerce home page

  @tag2
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <username> and password <password>
    When I add the product <productName> to Cart
    And Checkout product <productName> and submit the order
    Then I verify the success message "THANKYOU FOR THE ORDER."

    Examples: 
      |username 			|password 			|productName |
      |mld@gmail.com	|1qa@WS3ed$RF   |ZARA Coat 3 |