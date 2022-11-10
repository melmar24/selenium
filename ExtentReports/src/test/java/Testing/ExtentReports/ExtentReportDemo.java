package Testing.ExtentReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportDemo {

	ExtentReports extent;
	
	@BeforeTest
	public void config() {
		
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Reports");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "MM");
	}
	
	@Test
	public void initialDemo() {
		
		String strDriver = "webdriver.chrome.driver";
		String strDriverPath = "C:/Users/melmar.dimalaluan/Desktop/QualIT/Selenium Drivers/chromedriver.exe";
		String strURL = "https://www.amazon.com/";

		ExtentTest test = extent.createTest("Initial Demo");
		
		// Initialize
		System.setProperty(strDriver, strDriverPath);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(strURL);
		System.out.println(driver.getTitle());
	
		if(driver.getTitle().equalsIgnoreCase("Amazon")) {
			
			test.pass("PASS");
			
		}else {
			
			test.fail("FAIL");
			
		}
		
		extent.flush();
	}
	
}
