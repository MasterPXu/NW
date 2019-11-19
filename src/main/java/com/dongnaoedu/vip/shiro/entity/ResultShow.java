package com.dongnaoedu.vip.shiro.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ResultShow implements Serializable {
	private long id;
	private String taskInfo;
	private String taskResult;
	private String phoneNumber;
	private long replyTime;
	private String replyName;
	private String compsName;
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
	private String time;
	
	
	public String getTime() {
		return time;
	}
	public void setTime() {
		if(replyTime != 0) {
			this.time = df.format(replyTime*1000);
		}else {
			this.time = "缺岗状态";
		}		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTaskInfo() {
		return taskInfo;
	}
	public void setTaskInfo(String taskInfo) {
		this.taskInfo = taskInfo;
	}
	public String getTaskResult() {
		return taskResult;
	}
	public void setTaskResult(String taskResult) {
		this.taskResult = taskResult;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public long getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(long replyTime) {
		this.replyTime = replyTime;
	}
	public String getReplyName() {
		return replyName;
	}
	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}
	public String getCompsName() {
		return compsName;
	}
	public void setCompsName(String compsName) {
		this.compsName = compsName;
	}
	
}
