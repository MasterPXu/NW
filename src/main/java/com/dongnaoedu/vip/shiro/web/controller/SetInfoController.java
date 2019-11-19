package com.dongnaoedu.vip.shiro.web.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dongnaoedu.vip.shiro.entity.Phone;
import com.dongnaoedu.vip.shiro.entity.TestInfo;
import com.dongnaoedu.vip.shiro.entity.User;
import com.dongnaoedu.vip.shiro.service.TestService;
import com.dongnaoedu.vip.shiro.utils.String2List.String2List;
import com.dongnaoedu.vip.shiro.utils.ts.Calendar2UnixStamp;
import com.dongnaoedu.vip.shiro.utils.ts.TransferString2TimeStamp;
import com.dongnaoedu.vip.shiro.web.bind.annotation.CurrentUser;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/test")
public class SetInfoController {
	@Autowired
	TestService testService;
		
	 @RequestMapping(value="/doSave", method=RequestMethod.POST)
	    public @ResponseBody String emrSubmit(
	          @RequestParam Map<String,Object> param,@CurrentUser User currentUser
	    ){ 
		  System.err.println("setInfo has been invoke.....");
	      //提取前端传来的具体参数
	      String jsons = (String)param.get("json");
	      JSONArray jsonArray = JSONArray.fromObject(jsons);
	      if(jsonArray.size() != 0) {
	    	  for(int i=0;i<jsonArray.size();i++) {
		    	  TestInfo testInfo = new TestInfo();
		    	  JSONObject myjObject = jsonArray.getJSONObject(i);
		    	  String name = (String)myjObject.get("name");
		    	  String phone = (String)myjObject.get("phone");
		    	  String dateStr = (String)myjObject.get("date");
		    	  String times = (String)myjObject.get("time");
		    	  long date = TransferString2TimeStamp.transfer(dateStr + " 0:0:0");
		    	  testInfo.setName(name);
		    	  testInfo.setPhoneNumber(phone);
		    	  testInfo.setSetDateTimeStamp(date);
		    	  testInfo.setDateStr(times);
		    	  testInfo.setComps(currentUser.getOrganizationId());
		    	  testInfo.setWhoSet(currentUser.getId());
		    	  List<String> timesL = String2List.transfer(times);
		    	  if(timesL != null) {
			    	  for(String time : timesL) {
			    		  if(time.length() > 2) {
			    			  return "2";
			    		  }
			    		  TestInfo temp = new TestInfo();
			    		  temp = testInfo;
			    		  long stampPHour = TransferString2TimeStamp.transfer(dateStr + " " + time + ":0:0");
			    		  temp.setTimeStamp(stampPHour);
			    		  testService.setTestInfo(temp);
			    	  }
		    	  }else {
		    		  return "3";
		    	  }
		      }
		      return "1";
	      }else {
	    	  return "error";
	      }
	    }
	 
	 
	 
	 
	 public ArrayList<String> getPhoneNum(){
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			long timestamp = Calendar2UnixStamp.calendar2UnixTimeStamp(calendar);
			List<Phone> list = new ArrayList<>();
			try {
				 list= testService.getBeSetPhoneNumber(timestamp+3600);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ArrayList<String> arrayList = new ArrayList<>();
			if(list == null) {
				return null;
			}
			for(Phone phone : list) {
				arrayList.add(phone.getPhoneNumber());
			}
			return arrayList;
		}
}
