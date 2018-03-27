package com.wuliu.data.enumdata;

/**
 * 
 * @author joy
 *
 */
public enum ExcelHeader {
	/**
	 * 与测试用例excel的表头信息值对应
	 */
	SUITECASEID("suiteCaseId"),TESTCASEID("testCaseId"),TESTDEVICES("testDevices"),
	PRODUCT("product"),FUNCTION("function"),OPERATION("operation"),
	KEYWORDS("keyWords"),KEYWORDSFUNCTION("keyWordsFunction"),VALUE1("value1"),
	VALUE2("value2"),VALUE3("value3"),VALUE4("value4"),
	ELEMENTLOCATION1("elementLocation1"),ELEMENTLOCATION2("elementLocation2"),RESULTS("results");
	private String name ;
	private ExcelHeader(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
