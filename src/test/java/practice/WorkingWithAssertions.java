package practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WorkingWithAssertions {       //
 @Test
	public void ti() {
	 
	 // hard assert
		System.out.println("start");
	    Assert.assertEquals("hdfc","hhdc");---. hard assert(static method ,here we dont create object and assertall method is not necessary)
    	Assert. assertTrue("hdfc.equals("hfdc"));
    	Assert.assertNotEquals("hdfc", "hfdc");
    	Assert.assertEquals("hdfc", "hfdc");
		System.out.println("end");  
		
		String s="kavya";
		Assert.assertNull(s);
	
		String s= null;
		Assert.assertNotNull(s);
		//softAssert
		SoftAssert soft = new SoftAssert();
		soft.assertEquals("hdfc","hfdc");//---> soft assert non static method so we  create object and assertall method.
		System.out.println("end");
		
	    soft.assertNotEquals("hdfc", "hfdc");//--> assertNotEquals()
		soft.assertTrue("hdfc.equals("hfdc"));
	    soft.assertAll();
	}
}
