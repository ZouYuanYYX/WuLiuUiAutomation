package com.wuliu.data.enumdata;
/**
 * 
 * @author joy
 *
 */
public enum Product {
	/**
	 * 测试产品，与与测试用例excel中Product列值对应
	 */
	SHIPPER("货主"),
	FREIGHTSTATION("货运站"),
	CARRIER("车主"),
	DRIVER("司机"),
	XIAOER("小二"),
	DIANSHANG("水泥电商"),
	OTHER("其他");
	private String name ;
	private Product(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}
