package com.wuliu.dao;


/**
 * 与数据库对接的dao，CarrierDao表示操作carrier库
 * @author joy
 * @data 2018年01月15日
 */
public interface CarrierDao {
	/**
	 * 获取用户下单的托运单号
	 * @param auctionId
	 * @return
	 */
	String getUserItemId(String auctionId);
}
