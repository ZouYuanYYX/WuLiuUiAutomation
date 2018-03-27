package com.wuliu.testcase;

import org.testng.annotations.Test;

import com.wuliu.MainFunction;
/**
 * 
 * @author joy
 *
 */
public class TestCase {
	@Test
	public void test() {
		MainFunction testCase = new MainFunction();
		testCase.analysisExcelMainSheet("testdata.xlsx","Suite");
	}
	
	@Test(enabled=false)
	public void testTwo() {
		MainFunction testCase = new MainFunction();
		testCase.analysisExcelMainSheet("testdata2.xlsx","Suite");
	}
}
