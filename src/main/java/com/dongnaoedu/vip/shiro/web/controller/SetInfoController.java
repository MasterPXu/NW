package com.dongnaoedu.vip.shiro.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dongnaoedu.vip.shiro.entity.TestInfo;
import com.dongnaoedu.vip.shiro.entity.User;
import com.dongnaoedu.vip.shiro.service.TestService;
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
	      //提取前端传来的具体参数
	      String jsons = (String)param.get("json");
	      JSONArray jsonArray = JSONArray.fromObject(jsons);
	      if(jsonArray.size() != 0) {
	    	  for(int i=0;i<jsonArray.size();i++) {
		    	  TestInfo testInfo = new TestInfo();
		    	  long date = 0;
		    	  JSONObject myjObject = jsonArray.getJSONObject(i);
		    	  String name = (String)myjObject.get("name");
		    	  String phone = (String)myjObject.get("phone");
		    	  String dateStr = (String)myjObject.get("date");
		    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  		Date dateTemp = new Date();
			  		try {   
			  			dateTemp = sdf.parse(dateStr); 
			            date = dateTemp.getTime()/1000;
			          } catch (Exception e) {   
			              e.printStackTrace();   
			          }  
		    	  testInfo.setName(name);
		    	  testInfo.setPhoneNumber(phone);
		    	  testInfo.setTimeStamp(date);
		    	  testInfo.setComps(currentUser.getId());
		    	  testInfo.setWhoSet(currentUser.getId());
		    	  testService.setTestInfo(testInfo);
		      }
		      return "1";
	      }else {
	    	  return "error";
	      }
	    }
}
