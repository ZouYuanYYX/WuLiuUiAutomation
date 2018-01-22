package com.wuliu.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.wuliu.dao.CarrierDao;
import com.wuliu.dao.GoodsOwnerDao;
import com.wuliu.dao.UicDao;
import com.wuliu.utils.LogUtils;
import com.wuliu.utils.SqlSessionFactoryUtil;

public class GetDataFromMySql {

	private static String getUserIdFromMySql(String cellphone) {
		SqlSession sqlSession = null;
		String userId = null;
		try {
			SqlSessionFactory uicSqlSessionFactory = SqlSessionFactoryUtil.getUicSqlsessionFactory();
			sqlSession = uicSqlSessionFactory.openSession();
			UicDao uicUserIdDao = sqlSession.getMapper(UicDao.class);
			userId = uicUserIdDao.getUserId(cellphone);
			System.out.println("获取到的用户id为："+userId);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return userId;
	}
	
	private static Map<String,String> getUserTransIdFromSql(String userId) {
		SqlSession sqlSession = null;
		List<Map<String,String>> transIds = new ArrayList<Map<String,String>>();
		Map<String,String> transId = new HashMap();
		try {
			SqlSessionFactory goodsOwnerSqlSessionFactory = SqlSessionFactoryUtil.getGoodsOwnerSqlsessionFactory();
			sqlSession = goodsOwnerSqlSessionFactory.openSession();
			GoodsOwnerDao goodsOwnerDao = sqlSession.getMapper(GoodsOwnerDao.class);
			transIds = goodsOwnerDao.getUserAllTransId(userId);
			transId = transIds.get(transIds.size()-1);
			System.out.println("获取到用户的最新工单id为："+transId.get("id"));
			System.out.println("获取到用户的最新竞价单id为："+transId.get("auction_id"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return transId;
		
	}
	
	private static String getItemIdFromMySql(String auctionId) {
		SqlSession sqlSession = null;
		String itemId = null;
		try {
			SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getCarrierSqlsessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			CarrierDao carrierItemIdDao = sqlSession.getMapper(CarrierDao.class);
			itemId = carrierItemIdDao.getUserItemId(auctionId);
			System.out.println("获取到的用户最新托运单id为："+itemId);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return itemId;
	}
	//返回货主下单的工单id
	public static String userTransId(String cellphone) {
		String user_id = getUserIdFromMySql(cellphone);
    	String trans_id = getUserTransIdFromSql(user_id).get("id");
    	LogUtils.info("获取到的工单id为："+trans_id);
    	return trans_id;
	}
	
	//返回货主下单的竞价单id
	public static String userAuctionId(String cellphone) {
		String user_id = getUserIdFromMySql(cellphone);
	    String auction_id = getUserTransIdFromSql(user_id).get("auction_id");
	    LogUtils.info("获取到的竞价单id为："+auction_id);
	    return auction_id;
	}
	
	//返回货主下单的托运单id
	public static String userItemId(String cellphone) {
	    String auction_id = userAuctionId(cellphone);
		String item_id = getItemIdFromMySql(auction_id);
		LogUtils.info("获取到的托运单id为："+item_id);
		return item_id;
	}

}
