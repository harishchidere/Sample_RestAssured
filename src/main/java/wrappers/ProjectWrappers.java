package wrappers;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class ProjectWrappers extends GenericWrappers {

	public String testcasename, testDesc;

	@BeforeSuite
	public void beforeSuite() {
		startReport();
		System.out.println("Suite executed");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("test executed");
	}

	@BeforeMethod
	public void beforeMethod() {
		startTest(testcasename, testDesc);
		System.out.println("method executed");

	}

	@AfterSuite
	public void afterSuite() {
		reports.flush();
	}

}
