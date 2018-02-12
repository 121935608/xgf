package com.xingrongjinfu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertyUtils {
	private Properties config=null;
	/**
	 * 加载properties文件
	 */
	public PropertyUtils(String path){
		try {
			//写法一:PropertyUtils.class.getClassLoader().getResourceAsStream("DB.properties");
			//写法二:this.getClass().getResourceAsStream("/DB.properties");
			ClassLoader cll=PropertyUtils.class.getClassLoader();
			InputStream in =this.getClass().getResourceAsStream(path);
			config = new Properties();
			config.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取key对应的值
	 */
	public String getValue(String key){
		return config.getProperty(key);
	}
	/**
	 * 获取所有键值对
	 */
	public Map<String,String> getAllValue(){
		Map<String,String> map=new HashMap<String,String>();
		Set<Object> set=config.keySet();
		Iterator<Object> it=set.iterator();
		while(it.hasNext()){
			String key=(String) it.next();
			String value=config.getProperty(key);
			map.put(key, value);
		}
		return map;
	}
	public static void main(String[] args) {
		PropertyUtils pu=new PropertyUtils("DB.properties");
		pu.getValue("asdf");
	}

}
