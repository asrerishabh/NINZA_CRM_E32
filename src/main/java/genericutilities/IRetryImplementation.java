package genericutilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class IRetryImplementation implements IRetryAnalyzer{

	@Override
	public boolean retry(ITestResult result) {

		int count=1;
		int limitcount=5;
		
		if(count<=limitcount)
		{
			count++;
			return true;
		}
		
		return false;
	}

}
