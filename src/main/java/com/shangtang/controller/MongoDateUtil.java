package com.shangtang.controller;

import java.util.Date;

public class MongoDateUtil {

	public static String getHexString(Date time) {
		return String.format("%08X", time.getTime() / 1000) + "0000000000000000";
	}
}
