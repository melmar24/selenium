package Testing.PageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Testing.AbstractComponent.AbstractComponents;

public class Page_Checkout extends AbstractComponents{

	WebDriver driver;
	
	public Page_Checkout(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
		
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement checkout_input_county;
	
	@FindBy(xpath = "//section/button")
	List<WebElement> checkout_list_countries;
	
	@FindBy(xpath = "(//a[normalize-space()='Place Order'])[1]")
	WebElement checkout_btn_placeOrder;
	
	@FindBy(xpath = "//section[contains(@class,'ta-results')]")
	WebElement checkout_section_results;
	
	@FindBy(xpath = "//h1[@class='hero-primary']")
	WebElement checkout_txt_confirmation;
	
	@FindBy(css = "label[class='ng-star-inserted']")
	WebElement checkout_txt_orderId;
	
	By checkout_section_results_by = By.xpath("//section[contains(@class,'ta-results')]");
	By checkout_txt_confirmation_by = By.xpath("//h1[@class='hero-primary']");
	
	public void populateCountryField(String strCountry_short) {
		
		checkout_input_county.sendKeys(strCountry_short);
		waitForElementToAppear(checkout_section_results_by);
		
	}
	
	public void selectCountryFromList(String strCountry_long) {
		
		for(WebElement country : checkout_list_countries) {
			
			if(country.getText().equalsIgnoreCase(strCountry_long)) {
				
				Actions a = new Actions(driver);
				a.moveToElement(country).click().build().perform();
				waitForWebElementToDisappear(checkout_section_results);
				break;
			}
			
		}	
		
	}
	
	public void placeOrder() {
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", checkout_btn_placeOrder);

		
	}
	
	public String getConfirmationMessage() throws IOException{
		
		waitForElementToAppear(checkout_txt_confirmation_by);
		
		String[] arrOrderId =  checkout_txt_orderId.getText().split(" ");
		updatePropertyFile("orderId", arrOrderId[1]);
		
		return checkout_txt_confirmation.getText();
		
	}

}
