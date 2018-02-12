package com.xingrongjinfu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UuidUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	public static String get20UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "").substring(0,20);
		return uuid;
	}

	public static String get10UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "").substring(0,10);
		return uuid;
	}
	public static String getNo(String prefix){
		Date day=new Date();
		SimpleDateFormat df = new SimpleDateFormat("YYMMddHHmmss");
		int random = (int) ((Math.random()*9+1)*100);
		return prefix+df.format(day)+random;
	}
}

