@tag
Feature: Validate order in ecommerce orders page
	
	Background: 
		Given I landed on the ecommerce home page

  @tag2
  Scenario Outline: Positive test of validating the order in Orders page
    Given Logged in with username <username> and password <password>
    When I validate the orderId <orderId> in Orders page
    Then I verify the orderId is present in the page

    Examples: 
      |username 			|password 			|
      |mld@gmail.com	|1qa@WS3ed$RF   |