package Testing.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Testing.PageObjects.Page_Home;
import Testing.PageObjects.Page_Login;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String strURL = "https://www.rahulshettyacademy.com/client";
		String strUsername = "mld@gmail.com";
		String strPassword = "1qa@WS3ed$RF";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(strURL);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		// Step Name: Step 1
		// Description: Login using correct credentials
		// Expected Results: Able to login, able to see the home page.

		// Login Page
		//WebElement login_input_userEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
		//WebElement login_input_userPassword = driver.findElement(By.xpath("//input[@id='userPassword']"));
		//WebElement login_btn_login = driver.findElement(By.xpath("//input[@id='login']"));

		//login_input_userEmail.sendKeys(strUsername);
		//login_input_userPassword.sendKeys(strPassword);
		//login_btn_login.click();
		
		Page_Login loginPage = new Page_Login(driver);
		loginPage.LoginApplication(strUsername, strPassword);
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mb-3')]")));

		// Step Name: Step 2
		// Description: Add to cart item.
		// Expected Results: Able to add to cart item.

		// Home Page
		//List<WebElement> home_list_products = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));

		String strProductName = "ZARA coat 3";
		String strCountry_short = "ind";
		String strCountry_long = "india";

		//WebElement home_product = home_list_products.stream()
		//		.filter(s -> s.findElement(By.xpath("//h5/b")).getText().equalsIgnoreCase(strProductName)).findFirst()
		//		.orElse(null);

		//home_product.findElement(By.xpath("//div[contains(@class,'card-body')] //button[last()]")).click();

		//wait.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath("//div[@id='toast-container']"))));
		Page_Home homePage = new Page_Home(driver);
		homePage.addProductToCart(strProductName);
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		// Step Name: Step 3
		// Description: Validate cart items and checkout.
		// Expected Results: Able validate cart items and checkout successfully.
		
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@class='cartSection'] //h3"))));
		
		List<WebElement> cart_list_products = driver.findElements(By.xpath("//div[@class='cartSection'] //h3"));
				
		Assert.assertTrue(cart_list_products.stream().anyMatch(s -> s.getText().equalsIgnoreCase(strProductName)));
		
		driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
		
		// Step Name: Step 4
		// Description: Place order.
		// Expected Results: Able to place order successfully.
		
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys(strCountry_short);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//section[contains(@class,'ta-results')]"))));
		
		List<WebElement> checkout_list_countries = driver.findElements(By.xpath("//section/button"));
			
		for(WebElement country : checkout_list_countries) {
			
			if(country.getText().equalsIgnoreCase(strCountry_long)) {
				
				Actions a = new Actions(driver);
				a.moveToElement(country).click().build().perform();
				break;
			}
			
		}		
				
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
						
		driver.quit();

	}

}
