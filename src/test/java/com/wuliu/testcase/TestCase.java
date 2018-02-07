package com.wuliu.testcase;

import org.testng.annotations.Test;
public class TestCase {
	@Test
	public void test() {
		MainFunction testCase = new MainFunction();
		testCase.analysisExcelMainSheet();
	}
}
