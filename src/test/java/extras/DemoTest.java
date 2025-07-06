package extras;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class DemoTest {
	@Test(priority =1)
	public void main1() {
		Reporter.log("");
	
	}
	@Test(priority =5)
	public void main2() {
		Reporter.log("test not 3");
	}
	@Test(priority =4)
	public void main3() {
		System.out.println("Test 3");
	
	}
	@Test(priority =3)
	public void main4() {
		System.out.println("Test 3");
	
	}
	@Test(priority =2)
	public void main5() {
		System.out.println("Test 4");
	}

}
