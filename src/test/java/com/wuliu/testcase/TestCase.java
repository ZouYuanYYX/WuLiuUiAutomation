package com.wuliu.testcase;

import org.testng.annotations.Test;

import com.wuliu.MainFunction;
public class TestCase {
	@Test
	public void test() {
		MainFunction testCase = new MainFunction();
		testCase.analysisExcelMainSheet();
	}
}
