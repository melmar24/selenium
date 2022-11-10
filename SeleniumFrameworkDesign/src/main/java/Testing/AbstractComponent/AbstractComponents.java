package Testing.AbstractComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver)  {
		
		this.driver = driver;
		
	}
	
	public void waitForElementToAppear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForWebElementToAppear(WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public void waitForElementToDisappear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
		
	}
	
	public void waitForWebElementToDisappear(WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
		
	}
	
	public void scrollAndFind(WebElement element) {
		
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		
	}
	
	public void updatePropertyFile(String strKey, String strValue) throws IOException {
		
		//Initialize Properties
		Properties prop = new Properties();
		FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/Testing/Resources/GlobalData.properties");
		prop.load(in);
		in.close();
		
		FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + "/src/main/java/Testing/Resources/GlobalData.properties");
		prop.setProperty(strKey, strValue);
		prop.store(out, null);
		out.close();
		
	}
	
}
