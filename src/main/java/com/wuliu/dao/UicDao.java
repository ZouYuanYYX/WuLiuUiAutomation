package com.wuliu.dao;

/**
 * 与数据库对接的dao，UicDao表示操作Uic库
 * @author joy
 * @data 2018年01月15日
 */
public interface UicDao {
	/**
	 * 获取下单用户的userid
	 * @param cellphone
	 * @return
	 */
	String getUserId(String cellphone);
}
