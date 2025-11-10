package practice;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class WorkingWithConfiguration {
  @Test
  public void test1() {
	  System.out.println("test1");
  }
  @Test
  public void test2() {
	  System.out.println("test2");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("beforemethod");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("after Method");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("before Class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("After class");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("before Test");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("After method");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("Before suite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("After suit");
  }
  

}
