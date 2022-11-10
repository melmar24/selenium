package Testing.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Testing.AbstractComponent.AbstractComponents;

public class Page_Login extends AbstractComponents{

	WebDriver driver;

	public Page_Login(WebDriver driver)  {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement login_input_userEmail;

	@FindBy(xpath = "//input[@id='userPassword']")
	WebElement login_input_userPassword;

	@FindBy(xpath = "//input[@id='login']")
	WebElement login_btn_login;
	
	@FindBy(xpath = "//div[contains(@class,'toast-error')")
	WebElement login_error_msg;
	
	public Page_Home LoginApplication(String strUsername, String strPassword) {
		
		login_input_userEmail.sendKeys(strUsername);
		login_input_userPassword.sendKeys(strPassword);
		login_btn_login.click();
		Page_Home homePage = new Page_Home(driver);
		return homePage;
	}

	
}
