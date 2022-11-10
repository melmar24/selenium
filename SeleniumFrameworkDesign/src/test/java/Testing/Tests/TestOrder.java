package Testing.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import Testing.PageObjects.Page_Cart;
import Testing.PageObjects.Page_Checkout;
import Testing.PageObjects.Page_Home;
import Testing.PageObjects.Page_Orders;
import Testing.TestComponents.BaseTest;

public class TestOrder extends BaseTest {

	@Test(dataProvider="getData")
	public void SubmitOrder(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub

		// Initialize Properties
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/Testing/Resources/GlobalData.properties");
		prop.load(fis);

		// Get Properties
		String strUsername = input.get("username");
		String strPassword = input.get("password");
		String strProductName = input.get("productName");
		String strCountry_short = prop.getProperty("country_short");
		String strCountry_long = prop.getProperty("country_long");
		
		// Step Name: Step 1
		// Description: Login using correct credentials
		// Expected Results: Able to login, able to see the home page.
		Page_Home homePage = loginPage.LoginApplication(strUsername, strPassword);

		// Step Name: Step 2
		// Description: Add to cart item.
		// Expected Results: Able to add to cart item.
		homePage.addProductToCart(strProductName);
		Page_Cart cartPage = homePage.goToCart();

		// Step Name: Step 3
		// Description: Validate cart items and checkout.
		// Expected Results: Able validate cart items and checkout successfully.
		Boolean match = cartPage.validateProductInCart(strProductName);
		Assert.assertTrue(match);
		Page_Checkout checkoutPage = cartPage.goToCheckout();

		// Step Name: Step 4
		// Description: Place order.
		// Expected Results: Able to place order successfully.
		checkoutPage.populateCountryField(strCountry_short);
		checkoutPage.selectCountryFromList(strCountry_long);
		checkoutPage.placeOrder();
		Assert.assertEquals(checkoutPage.getConfirmationMessage(), "THANKYOU FOR THE ORDER.");

	}

	@Test(dependsOnMethods= {"SubmitOrder"})
	public void ValidateOrder() throws IOException {

		// Initialize Properties
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/Testing/Resources/GlobalData.properties");
		prop.load(fis);

		// Get Properties
		String strUsername = prop.getProperty("username");
		String strPassword = prop.getProperty("password");		
		String strOrderId = prop.getProperty("orderId");

		// Step Name: Step 1
		// Description: Login using correct credentials
		// Expected Results: Able to login, able to see the home page.
		Page_Home homePage = loginPage.LoginApplication(strUsername, strPassword);
		
		// Step Name: Step 2
		// Description: Validate orderId in Order page.
		// Expected Results: orderId in Order page is present.
		Page_Orders ordersPage = homePage.goToOrders();
		Boolean match = ordersPage.validateOrder(strOrderId);
		Assert.assertTrue(match);
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToHasmap(System.getProperty("user.dir") + "/src/main/java/Testing/Resources/PurchaseOrders.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}}; 
		
	}
	
}
