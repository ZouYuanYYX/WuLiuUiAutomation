package com.wuliu.operation.failedretry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
/**
 * 用例重跑监听器，用例失败自动重跑功能
 * @author joy
 *
 */
public class RetryTestCaseListener  implements IAnnotationTransformer {
	
	public void transform(ITestAnnotation iTestAnnotation, Class testClass, Constructor testConstructor, Method testMethod) {
		IRetryAnalyzer retry = iTestAnnotation.getRetryAnalyzer();
		if ( retry == null) {
			iTestAnnotation.setRetryAnalyzer(RetryTestCase.class);
		}
		
	}

}
