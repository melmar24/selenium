package Testing.Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Functions {

	public static void scrollAndFind(WebDriver driver, WebElement element) {

		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}

}
