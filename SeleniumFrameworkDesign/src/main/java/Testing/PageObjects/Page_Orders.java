package Testing.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Testing.AbstractComponent.AbstractComponents;

public class Page_Orders extends AbstractComponents {

	WebDriver driver;

	public Page_Orders(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//th")
	List<WebElement> order_list_orderIds;

	@FindBy(css = "h1[class='ng-star-inserted']")
	WebElement order_text_yourOrders;

	
	public Boolean validateOrder(String strOrderId) {
		
		waitForWebElementToAppear(order_text_yourOrders);
		Boolean match = order_list_orderIds.stream().anyMatch(s -> s.getText().equalsIgnoreCase(strOrderId));
		return match;
		
	}

}
