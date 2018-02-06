package com.wuliu.data;

import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

/**
 * 解析json数据
 * @author PC
 * @date 2017年12月19日
 */

public class GetJsonData {
	
	public static String getOnlyOneMemberJsonData (String jsonMessage,String member) {
        String jsonStr = null;
        JSONObject jsonObject = JSONObject.fromObject(jsonMessage);  
        //直接将json值取出，不经过map也不经过数组（只适用于只有一个json键值对的场景）
        jsonStr = jsonObject.getString(member);
        return jsonStr;
    }
    public static String getMultipleMemberJsonData (String jsonMessage,String member) {
        String jsonStr = null;
        JSONObject jsonObject = JSONObject.fromObject(jsonMessage);  
        @SuppressWarnings("unchecked")
		Map<String, Object> mapJson = JSONObject.fromObject(jsonObject);     
        for(Entry<String,Object> entry : mapJson.entrySet()){
            String key = entry.getKey();
            System.out.println("KEY:"+entry.getKey()+"  -->  Value:"+entry.getValue()+"\n");  
            if (member.equals(key)) {
                jsonStr = (String) entry.getValue();
                break;
            }        
      }
        return jsonStr;
    }
}
