package test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class second {

	@Test
	public void Login() {
		
		System.out.println("Logged in second.");
		
	}
	
	@Test
	public void EnterDetails() {
		
		System.out.println("Details entered in second.");
		
	}
	
	@Test
	public void Logoff() {
		
		System.out.println("Logged off in second.");
		
	}
	
}
