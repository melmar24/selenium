package Testing.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import Testing.PageObjects.Page_Cart;
import Testing.PageObjects.Page_Checkout;
import Testing.PageObjects.Page_Home;
import Testing.PageObjects.Page_Login;
import Testing.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{

	Page_Login loginPage;
	Page_Home homePage;
	Page_Cart cartPage;
	Page_Checkout checkoutPage;
	
	@Given("I landed on the ecommerce home page")
	public void I_landed_on_the_ecommerce_home_page() throws IOException{
		
		loginPage = LaunchApplication();
		
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String strUsername, String strPassword) {
		
		homePage = loginPage.LoginApplication(strUsername, strPassword);

	}
	
	@When("^I add the product (.+) to Cart$")
	public void I_add_the_product_to_Cart(String strProductName) {
		
		homePage.addProductToCart(strProductName);
		cartPage = homePage.goToCart();
		
	}
	
	@When("^Checkout product (.+) and submit the order$")
	public void Checkout_product_and_submit_the_order(String strProductName) throws IOException {
	
		String strCountry_short = "Ind";
		String strCountry_long = "India";
		
		Boolean match = cartPage.validateProductInCart(strProductName);
		Assert.assertTrue(match);
		checkoutPage = cartPage.goToCheckout();
		checkoutPage.populateCountryField(strCountry_short);
		checkoutPage.selectCountryFromList(strCountry_long);
		checkoutPage.placeOrder();
		
	}	
	
	
	@Then("I verify the success message {string}")
	public void I_verify_the_success_message(String strMessage) throws IOException {
		
		strMessage = "THANKYOU FOR THE ORDER.";
		Assert.assertEquals(checkoutPage.getConfirmationMessage(), strMessage);
		QuitApplication();
	}
	
}
