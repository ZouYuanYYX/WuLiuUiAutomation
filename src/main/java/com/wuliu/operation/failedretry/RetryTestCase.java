package com.wuliu.operation.failedretry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.wuliu.utils.LogUtils;
/**
 * RetryAnalyzer实现接口IRetryAnalyzer的方法，重复执行失败用例
 * @author joy
 *
 */
public class RetryTestCase implements IRetryAnalyzer{
	int retryCount = 1;
	int maxRetryCount = 2;
	@Override
	public boolean retry(ITestResult result) {
		String msg = "执行测试用例："+result.getName()+"第"+retryCount+"失败";
		System.out.println("msg"+"RunCount=" + (retryCount + 1));
		LogUtils.info(msg);
		if(retryCount <= maxRetryCount) {
			retryCount ++;
			return true;
		}
		return false;
	}
	
}
