package com.dongnaoedu.vip.shiro.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dongnaoedu.vip.shiro.entity.Result;
import com.dongnaoedu.vip.shiro.service.ResultService;
import com.dongnaoedu.vip.shiro.service.UserService;
import com.dongnaoedu.vip.shiro.utils.getphone.GetPhoneNum;
import com.dongnaoedu.vip.shiro.utils.rand.RandNum;
import com.dongnaoedu.vip.shiro.utils.ts.Calendar2UnixStamp;
import com.dongnaoedu.vip.shiro.utils.ts.GetNowAndTomorrowUnixStamp;
import com.github.qcloudsms.SmsMobileStatusPuller;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsStatusPullReplyResult;
import com.github.qcloudsms.SmsStatusPullReplyResult.Reply;
import com.github.qcloudsms.httpclient.HTTPException;


@Component("testJobComponent")
public class TestJob {
	Logger logger = LoggerFactory.getLogger(TestJob.class);
	MessageInfo messageInfo = new MessageInfo(); 		//缓存
	QueryInfo queryInfo = new QueryInfo();
	PhoneInfo phoneInfo = new PhoneInfo();
	
	String rand;
	// 短信应用SDK AppID
    int appid = 1400159174; // 1400开头

    // 短信应用SDK AppKey
    String appkey = "22307513bbb41b1e29b1cbbdb843b891";

    // 需要发送短信的手机号码
//    String[] phoneNumbers = {"15013026913"};
//    ArrayList<String> phoneNumbers = messageInfo.phoneNumbers;

    // 短信模板ID，需要在短信应用中申请
    // NOTE: 这里的模板ID`7839`只是一个示例，
    // 真实的模板ID需要在短信控制台中申请
    int templateId = 267523;

    // 签名
    // NOTE: 这里的签名"腾讯云"只是一个示例，
    // 真实的签名需要在短信控制台中申请，另外
    // 签名参数使用的是`签名内容`，而不是`签名ID`
    String smsSign = "";

    
    @Autowired
    private UserService userService;
    @Autowired
    private ResultService resultService;
    
    public class PhoneInfo{
        ArrayList<String> phoneNumbers;   //发送目标电话
		public ArrayList<String> getPhoneNumbers() {
			return phoneNumbers;
		}
		public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
			this.phoneNumbers = phoneNumbers;
		}
    }
    
	public class MessageInfo{
        ArrayList<String> params;		  //发送提示信息       0：随机验证码      1：多少分钟内有效
		public ArrayList<String> getParams() {
			return params;
		}
		public void setParams(ArrayList<String> params) {
			this.params = params;
		}
        
	}
	
	public class QueryInfo{
        int beginTime;
        int endTime;
		public int getBeginTime() {
			return beginTime;
		}
		public void setBeginTime(int beginTime) {
			this.beginTime = beginTime;
		}
		public int getEndTime() {
			return endTime;
		}
		public void setEndTime(int endTime) {
			this.endTime = endTime;
		}
        
	}
	
	//传入发送短信的详细信息,拉取时间设置，数据库操作，quartz任务，一天一次
	public void setInfo() {
		GetPhoneNum gphone = new GetPhoneNum();
		ArrayList<String> phoneNumbers = gphone.getPhoneNum();
		if(phoneNumbers == null) {
			phoneNumbers.add("15013026913");			
		}
		phoneInfo.setPhoneNumbers(phoneNumbers);
		
		//设置查询时间
		GetNowAndTomorrowUnixStamp ts = new GetNowAndTomorrowUnixStamp();
		int beginTime = (int) ts.todayTimestamp;
		int endTime = (int) ts.tomorrowTimestamp;
		
		queryInfo.setBeginTime(beginTime);
		queryInfo.setEndTime(endTime);
	}
	
	
	//将查询到的数据写入数据库，quartz任务
	public void write2DB() {
		
	}
	
	//一小时一次 同时将随机验证码存入数据库
	public void setParam() {
		if(messageInfo.params == null) {
			ArrayList<String> params = new ArrayList<String>();
			params.add("1234");
			params.add("15");
			messageInfo.setParams(params);
		}else{
			ArrayList<String> params = new ArrayList<String>();
			rand = RandNum.getRand()+"";
			params.add(rand);
			params.add("15");
			messageInfo.setParams(params);
			//查看是否需要插入数据库
	          if(phoneInfo.phoneNumbers.size() != 0 
	   			&& !phoneInfo.phoneNumbers.contains("15013026913")) {
	        	  for(int i = 0;i<phoneInfo.phoneNumbers.size();i++) {
		  				Result result = new Result();
		  				result.setTaskInfo(rand);
		  				result.setPhoneNumber(phoneInfo.phoneNumbers.get(i));
		  				result.setTaskResult("0");
		  				result.setReplyTime(0);
		  				resultService.createResult(result);    
	        	  }
	          }
		}
	}
	
		//一小时一次 更新replyTime 和拉取 reply
		public void updateParam() {
				List<Result> results = resultService.queryToUpdate(rand);
		        // 拉取单个手机短信状态
		        try {
		    		Calendar calendar = Calendar.getInstance();
		    		calendar.set(Calendar.MINUTE, 00);
		    		calendar.set(Calendar.SECOND, 00);
		            int endTime = (int) Calendar2UnixStamp.calendar2UnixTimeStamp(calendar);    // 结束时间(unix timestamp)
		            int beginTime = endTime-3600;  // 开始时间(unix timestamp)
		            int maxNum = 10;             // 单次拉取最大量
		            SmsMobileStatusPuller mspuller = new SmsMobileStatusPuller(appid, appkey);

		            // 拉取回复
		          if(phoneInfo.phoneNumbers.size() != 0 
				   			&& !phoneInfo.phoneNumbers.contains("15013026913")) {
		        	  for(int i = 0 ; i<phoneInfo.phoneNumbers.size();i++) {
		        		  SmsStatusPullReplyResult replyResult = mspuller.pullReply("86",
		        				  phoneInfo.phoneNumbers.get(i), beginTime, endTime, maxNum);
		        		  ArrayList<Reply> replies = replyResult.replys;
		        		  for(Reply reply : replies) {
		        			  if(results != null) {
		        				  for(Result result : results) {
			        				  if(result.getPhoneNumber() == reply.mobile) {
			        					  result.setReplyTime(reply.time);
			        					  result.setTaskResult(reply.text);
			        					  resultService.update(result);
			        				  }
			        			  }
		        			  }
		        		  }
		        	  }			        	  
		          }
		        } catch (HTTPException e) {
		            // HTTP响应码错误
		            e.printStackTrace();
		        } catch (JSONException e) {
		            // json解析错误
		            e.printStackTrace();
		        } catch (IOException e) {
		            // 网络IO错误
		            e.printStackTrace();
		        }
		        
				//查看是否需要插入数据库
		          if(phoneInfo.phoneNumbers.size() != 0 
		   			&& !phoneInfo.phoneNumbers.contains("15013026913")) {
		        	  for(int i = 0;i<phoneInfo.phoneNumbers.size();i++) {
			  				Result result = new Result();
			  				result.setTaskInfo("123");
			  				result.setPhoneNumber(phoneInfo.phoneNumbers.get(i));
			  				result.setTaskResult("0");
			  				result.setReplyTime(0);
			  				resultService.createResult(result);    
		        	  }
		          }
			
		}
		
    //定义是否
	//发送短信模块，quartz任务
    @Value("${crmbi.cronExpression.isDoTestJob}")
    private boolean isDoTestJob = false;
    public void execute(){       
//         指定模板ID单发短信
        try {
           if(phoneInfo.phoneNumbers.size() != 0 
    			&& messageInfo.params.size() != 0 
    			&& !phoneInfo.phoneNumbers.contains("15013026913")) {
        	   SmsMultiSender msender = new SmsMultiSender(appid, appkey);
               SmsMultiSenderResult result =  msender.sendWithParam("86", phoneInfo.phoneNumbers,
                   templateId, messageInfo.params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
               System.out.print(result);
           }
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
           // 网络IO错误
            e.printStackTrace();
       }
        
    }
}
