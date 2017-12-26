package com.xingrongjinfu.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil {

	public static Map parseJsonToMap(String str) {
		Map maps = (Map) JSON.parse(str);
		Map paramsMap = (Map) maps.get("params");
		return paramsMap;
	}

	public static String parseMapToJson(Map map) {
		Map result = new HashMap();
		result.put("status", "200");
		result.put("result", map);
		JSONObject json = new JSONObject(result);
		return json.toJSONString();
	}

	public static String parseObjectToJson(Object o) {
		Map result = new HashMap();
		JSONObject json = new JSONObject(result);
		return json.toJSONString();
	}

	//work for repay
	public static String parseMapToJson2(Map map) {
		Map result = new HashMap();
		result.put("param", map);
		JSONObject json = new JSONObject(result);
		return json.toJSONString();
	}
	
	public static String parseMapToJson3(Map map) {
		JSONObject json = new JSONObject(map);
		return json.toJSONString();
	}
	
	//将json字符串转为map
	public static Map parseJsonToMap2(String str) {
		Map maps = (Map) JSON.parse(str);
		return maps;
	}
	
	
	public static void main(String[] args) {
		String jsonStr = "{params:{phoneNum:18668566326}}";
		Map paramsMap = JsonUtil.parseJsonToMap2(jsonStr);

		System.out.println(paramsMap.get("phoneNum"));
		for (Object map : paramsMap.entrySet()) {
			System.out.println(((Map.Entry) map).getKey() + "  "
					+ ((Map.Entry) map).getValue());
		}

		Map<String, Object> reqMap=new HashMap<String, Object>();
		reqMap.put("card_id","0000");
		reqMap.put("money",500);
		
		System.out.println("test:"+JsonUtil.parseMapToJson3(reqMap));
		
	}
}