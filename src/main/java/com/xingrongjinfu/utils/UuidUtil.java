package com.xingrongjinfu.utils;

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
}

