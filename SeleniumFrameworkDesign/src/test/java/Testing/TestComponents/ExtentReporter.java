package Testing.TestComponents;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Testing.AbstractComponent.AbstractComponents;

public class ExtentReporter extends AbstractComponents{
	
	public ExtentReporter(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Reports");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "MM");
		
		return extent;
		
	}
	
	

}
