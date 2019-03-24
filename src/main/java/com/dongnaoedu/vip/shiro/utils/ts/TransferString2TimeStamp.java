package com.dongnaoedu.vip.shiro.utils.ts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransferString2TimeStamp {
	public static long transfer(String timeString) {
		Date date = new Date(); 
		DateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			date = sdFormat.parse(timeString);
			System.out.println(date.toString()); 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return date.getTime();
	}
	
	public static void main(String[] args) {
		String timeStr = "2010/05/04 12:34:23";
		long a = transfer(timeStr)/1000;
		System.out.println(a);
	}
}
