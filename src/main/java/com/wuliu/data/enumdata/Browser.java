package com.wuliu.data.enumdata;
/**
 * 
 * @author joy
 *
 */
public enum Browser {
	/**
	 * web端测试支持的浏览器
	 */
	IE("ie"),CHROME("chrome"),FIREFOX("firefox");
	private String name ;
	private Browser(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
