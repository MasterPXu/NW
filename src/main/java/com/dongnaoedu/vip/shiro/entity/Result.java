package com.dongnaoedu.vip.shiro.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class Result implements Serializable {
	private long id;
	private String taskInfo;
	private String taskResult;
	private String phoneNumber;
	private long replyTime;
	private long sendTime;
	
	public long getSendTime() {
		return sendTime;
	}
	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
	public String time;
	
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
		time = df.format(replyTime*1000);
		this.replyTime = replyTime;
	}

	
}
