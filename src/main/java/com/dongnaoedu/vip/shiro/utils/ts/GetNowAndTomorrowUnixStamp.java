package com.dongnaoedu.vip.shiro.utils.ts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.omg.CORBA.LongHolder;
/**
 * 
 * @author Pei_XU
 *	获取当天指定时间
 *	获取第二天指定时间
 *	用于拉取QCloud上的数据
 */
public class GetNowAndTomorrowUnixStamp {
	public long todayTimestamp;
	public long tomorrowTimestamp;
		
	public GetNowAndTomorrowUnixStamp() {
		//当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 15);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 00);
		this.todayTimestamp = Calendar2UnixStamp.calendar2UnixTimeStamp(calendar);
		
		//第二天时间
		Date currentDate = calendar.getTime();
		String toString = sdf.format(currentDate);
		try {
			calendar.setTime(sdf.parse(toString));
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			this.tomorrowTimestamp = Calendar2UnixStamp.calendar2UnixTimeStamp(calendar);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
