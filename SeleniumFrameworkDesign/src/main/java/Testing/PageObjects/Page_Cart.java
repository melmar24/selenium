package Testing.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Testing.AbstractComponent.AbstractComponents;

public class Page_Cart extends AbstractComponents{

	WebDriver driver;
	
	public Page_Cart(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath = "//div[@class='cartSection'] //h3")
	List<WebElement> cart_list_products;

	@FindBy(xpath = "//button[normalize-space()='Checkout']")
	WebElement cart_btn_checkout;
	
	@FindBy(css = ".ng-animating")
	WebElement object_loading;
	
	@FindBy(xpath = "//div[@id='toast-container']")
	WebElement object_toast_container;
	
	public Boolean validateProductInCart(String strProductName) {
		
		waitForWebElementToDisappear(object_toast_container);
		Boolean result = cart_list_products.stream().anyMatch(s -> s.getText().equalsIgnoreCase(strProductName));
		
		return result;
		
	}
	
	public Page_Checkout goToCheckout() {
		
		scrollAndFind(cart_btn_checkout);
		cart_btn_checkout.click();
		Page_Checkout checkoutPage = new Page_Checkout(driver);
		return checkoutPage;
	}
	
}
