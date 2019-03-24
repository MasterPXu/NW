package com.dongnaoedu.vip.shiro.utils.getphone;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dongnaoedu.vip.shiro.service.TestService;
import com.dongnaoedu.vip.shiro.utils.ts.Calendar2UnixStamp;

public class GetPhoneNum {
	
	@Autowired
	TestService testService;
	
	public ArrayList<String> getPhoneNum(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 12);
		calendar.set(Calendar.MINUTE, 34);
		calendar.set(Calendar.SECOND, 23);
		long timestamp = Calendar2UnixStamp.calendar2UnixTimeStamp(calendar);
		List<String> list= testService.getBeSetPhoneNumber(timestamp);
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.addAll(list);
		return arrayList;
	}
}
