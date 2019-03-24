package com.dongnaoedu.vip.shiro.utils.ts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 
 * @author Pei_XU
 * 将calendar转换为Unix的timeStamp
 */
public class Calendar2UnixStamp {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static long calendar2UnixTimeStamp(Calendar calendar) {
		Date timeString = calendar.getTime();
		long unixTimestamp = timeString.getTime()/1000;
		return unixTimestamp;
	}
}
