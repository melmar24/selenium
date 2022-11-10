package Testing.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Testing.PageObjects.Page_Login;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public Page_Login loginPage;

	@Test
	public WebDriver InitializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/Testing/Resources/GlobalData.properties");
		prop.load(fis);

		String strBrowserName = prop.getProperty("browser");
		String strURL = prop.getProperty("url");

		if (strBrowserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			
		}

		driver.manage().window().maximize();
		driver.get(strURL);

		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToHasmap(String filePath) throws IOException {

		// Read json file

		String jsonString = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonString,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String getScreenshot(String strTestCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "/reports/" + strTestCaseName + ".png");
		FileUtils.copyFile(source, file);
		
		return System.getProperty("user.dir") + "/reports/" + strTestCaseName + ".png";
		
	}
	
	@BeforeMethod
	public Page_Login LaunchApplication() throws IOException {

		driver = InitializeDriver();
		loginPage = new Page_Login(driver);
		return loginPage;

	}

	@AfterMethod
	public void QuitApplication() {

		driver.quit();

	}

}
