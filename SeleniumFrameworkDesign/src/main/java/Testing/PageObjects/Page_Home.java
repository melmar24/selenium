package Testing.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Testing.AbstractComponent.AbstractComponents;

public class Page_Home extends AbstractComponents{

	WebDriver driver;
	
	public Page_Home(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath = "//div[@class='card-body']")
	List<WebElement> home_list_products;
	
	@FindBy(xpath = "//div[@id='toast-container']")
	WebElement object_toast_container;
	
	@FindBy(css = ".ng-animating")
	WebElement object_loading;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement home_btn_cart;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement home_btn_orders;
	
	By home_list_products_by = By.xpath("//div[contains(@class,'mb-3')]");
	By home_btn_addToCart_by = By.xpath("//div[contains(@class,'card-body')] //button[last()]");
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(home_list_products_by);
		return home_list_products;
	
	}
	
	public WebElement getProductByName(String strProductName) {
		
		WebElement product = getProductList().stream()
				.filter(s -> s.findElement(By.xpath("//h5/b")).getText().equalsIgnoreCase(strProductName)).findFirst()
				.orElse(null);
		return product;
		
	}
	
	public void addProductToCart(String strProductName) {
		
		WebElement product = getProductByName(strProductName);
		product.findElement(home_btn_addToCart_by).click();
		waitForWebElementToDisappear(object_toast_container);

	}
	
	public Page_Cart goToCart() {
		
		home_btn_cart.click();
		Page_Cart cartPage = new Page_Cart(driver);
		return cartPage;
	}
	
	public Page_Orders goToOrders() {
		
		waitForElementToAppear(home_list_products_by);
		home_btn_orders.click();
		Page_Orders ordersPage = new Page_Orders(driver);
		return ordersPage;
	}
	
}
