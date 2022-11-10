package Testing.ChromeDevTools;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MobileEmulatorTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// Parameters
		String strDriver = "webdriver.chrome.driver";
		String strDriverPath = "C:/Users/melmar.dimalaluan/Desktop/QualIT/Selenium Drivers/chromedriver.exe";
		String strURL = "https://rahulshettyacademy.com/angularAppdemo";

		// Initialize
		System.setProperty(strDriver, strDriverPath);
		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		
		devTools.createSession();
	
		//Send command to CDP methods that will invoke and get access to chrome dev tools
		
		//devTools.send(Emulation.setDeviceMetricsOverride(600, 1000, 50, true, Optional.empty(), Optional.empty(),
				//Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));

		
		driver.get(strURL);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//span[@class='navbar-toggler-icon']")).click();
		
	}

}
