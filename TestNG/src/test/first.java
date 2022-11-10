package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class first {

	//@Parameters({ "username", "password" })
	@Test(dataProvider="getData")
	public void Login(String username, String password) {

		System.out.println(username + " logged in first.");
		System.out.println("Using this password: " + password);
	}

	@Test
	public void EnterDetails() {

		System.out.println("Details entered in first.");

	}

	@Test
	public void Logoff() {

		System.out.println("Logged off in first.");

	}

	@BeforeMethod
	public void RunEveryTest() {

		System.out.println("Run every test.");

	}

	@AfterSuite
	public void RunEverySuite() {

		System.out.println("Run every suite.");

	}
	
	@DataProvider
	public Object[][] getData() {
		
		Object[][] data = new Object[3][2];
		data[0][0] ="user1";
		data[0][1] ="password1";
		data[1][0] ="user2";
		data[1][1] ="password2";
		data[2][0] ="user3";
		data[2][1] ="password3";
		
		return data;
	}
}
