package com.dongnaoedu.vip.shiro.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class TestInfo  implements Serializable{
	private long id;
	private String name;
	private String phoneNumber;
	private long timeStamp;
	private long setDateTimeStamp;
	private String dateStr;
	private long comps;
	private long whoSet;
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
	private String time;
	public String getTime() {
		return time;
	}
	public void setTime() {
		time = df.format(timeStamp*1000);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumbe) {
		this.phoneNumber = phoneNumbe;
	}

	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public long getComps() {
		return comps;
	}
	public void setComps(long comps) {
		this.comps = comps;
	}
	public long getWhoSet() {
		return whoSet;
	}
	public void setWhoSet(long whoSet) {
		this.whoSet = whoSet;
	}
	public long getSetDateTimeStamp() {
		return setDateTimeStamp;
	}
	public void setSetDateTimeStamp(long setDateTimeStamp) {
		this.setDateTimeStamp = setDateTimeStamp;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
		
}
