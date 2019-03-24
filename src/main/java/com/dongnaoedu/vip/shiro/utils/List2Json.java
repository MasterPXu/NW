package com.dongnaoedu.vip.shiro.utils;

import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

import com.dongnaoedu.vip.shiro.entity.ResultShow;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class List2Json {
	public static JSONArray List2Json(List<ResultShow> list) {
        JSONArray json = new JSONArray();
        for(ResultShow rs : list){
            JSONObject jo = new JSONObject();
            jo.put("id", rs.getId());
            jo.put("phoneNumber", rs.getPhoneNumber());
            jo.put("taskInfo", rs.getTaskInfo());
            jo.put("taskResult", rs.getTaskResult());
            jo.put("replyTime", rs.getReplyTime());
            jo.put("replyName", rs.getReplyName());
            jo.put("compsName", rs.getCompsName());
            jo.put("time", rs.getTime());
            json.add(jo);
        }
        return json;
	}
}
