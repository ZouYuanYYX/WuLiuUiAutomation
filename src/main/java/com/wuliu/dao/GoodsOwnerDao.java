package com.wuliu.dao;

import java.util.List;
import java.util.Map;
/**
 * 与数据库对接的dao，GoodsOwnerDao表示操作GoodsOwner库
 * @author joy
 * @data 2018年01月15日
 */
public interface GoodsOwnerDao {
	
	/**
	 * 将获取到的工单id、竞价单id、水泥电商id存入map，Map<字段名称,字段值>
	 * @param userId
	 * @return
	 */
	List<Map<String,String>> getUserAllTransId(String userId);
	
}
