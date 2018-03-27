package com.wuliu.operation.failedretry;

import java.util.Iterator;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
/**
 * testNG执行case 失败后 ，testNG Listener会捕获执行失败
 * @author joy
 *
 */
public class TestListener extends TestListenerAdapter{
	@Override  
	public void onTestFailure(ITestResult tr) {  
	        super.onTestFailure(tr);  
	        //logger.info(tr.getName() + " Failure");  
	}  
	  
	@Override  
	public void onTestSkipped(ITestResult tr) {  
	        super.onTestSkipped(tr);  
	       // logger.info(tr.getName() + " Skipped");  
	}  
	  
	@Override  
	public void onTestSuccess(ITestResult tr) {  
	        super.onTestSuccess(tr);  
	        //logger.info(tr.getName() + " Success");  
	}  
	  
	@Override  
	public void onTestStart(ITestResult tr) {  
	        super.onTestStart(tr);  
	       // logger.info(tr.getName() + " Start");  
	}  
	  
	@Override  
	public void onFinish(ITestContext testContext) {  
	        super.onFinish(testContext);  
	        Iterator<ITestResult> listOfFailedTests = testContext.getFailedTests().getAllResults().iterator();  
	        while (listOfFailedTests.hasNext()) {  
	        ITestResult failedTest = (ITestResult) listOfFailedTests.next();  
	        ITestNGMethod method = failedTest.getMethod();  
	            if (testContext.getFailedTests().getResults(method).size() > 1) {  
	                    listOfFailedTests.remove();  
	                    }   
	            else {  
	                    if (testContext.getPassedTests().getResults(method).size() > 0) {  
	                    listOfFailedTests.remove();  
	            }  
	    }  
	 }  
	}  
	
}
