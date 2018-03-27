package com.wuliu.data.enumdata;
/**
 * 
 * @author joy
 *
 */

public enum TestDevices {
	/**
	 * 测试设备枚举类型，web表示测试pc端，app表示测试手机端
	 * 与测试用例excel中testDevices列值对应
	 */
	WEB("web",1),APP("app",2);
	private String name ;
    private int index ;
	private TestDevices(String name, int index) {
		this.name = name;
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
