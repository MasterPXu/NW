package com.dongnaoedu.vip.shiro.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.json.JSONException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.qcloudsms.SmsMobileStatusPuller;
import com.github.qcloudsms.SmsStatusPullCallbackResult;
import com.github.qcloudsms.SmsStatusPullReplyResult;
import com.github.qcloudsms.httpclient.HTTPException;


@Component("importOneTable")
public class MyJob implements Job {
	Logger logger = LoggerFactory.getLogger(MyJob.class);
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
//		System.out.println("开启了定时任务");
		// 短信应用SDK AppID
        int appid = 1400159174; // 1400开头

        // 短信应用SDK AppKey
        String appkey = "22307513bbb41b1e29b1cbbdb843b891";

        // 需要发送短信的手机号码
        String[] phoneNumbers = {"15013026913"};

        // 短信模板ID，需要在短信应用中申请
        // NOTE: 这里的模板ID`7839`只是一个示例，
        // 真实的模板ID需要在短信控制台中申请
        int templateId = 267523;

        // 签名
        // NOTE: 这里的签名"腾讯云"只是一个示例，
        // 真实的签名需要在短信控制台中申请，另外
        // 签名参数使用的是`签名内容`，而不是`签名ID`
        String smsSign = "";


        // 指定模板ID单发短信
//        try {
//            String[] params = {"5678","15"};
//            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
//            SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers,
//                templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
//            System.out.print(result);
//        } catch (HTTPException e) {
//            // HTTP响应码错误
//            e.printStackTrace();
//        } catch (JSONException e) {
//            // json解析错误
//            e.printStackTrace();
//        } catch (IOException e) {
//            // 网络IO错误
//            e.printStackTrace();
//        }

        // 拉取单个手机短信状态
        try {
            int beginTime = 1547706602;  // 开始时间(unix timestamp)
            int endTime = 1547769863;    // 结束时间(unix timestamp)
            int maxNum = 10;             // 单次拉取最大量
            SmsMobileStatusPuller mspuller = new SmsMobileStatusPuller(appid, appkey);

            // 拉取短信回执
            SmsStatusPullCallbackResult callbackResult = mspuller.pullCallback("86",
                phoneNumbers[0], beginTime, endTime, maxNum);
//            ArrayList<Callback> callbacks =  callbackResult.callbacks;
//            for(Callback callback : callbacks) {
//            	System.out.println(callback.user_receive_time);
//            }
//            System.out.println(callbackResult);
            logger.info(callbackResult+"");
            
            // 拉取回复
            SmsStatusPullReplyResult replyResult = mspuller.pullReply("86",
                phoneNumbers[0], beginTime, endTime, maxNum);
            logger.info(replyResult+"");
//            ArrayList<Reply> replies = replyResult.replys;
//            for(Reply reply : replies) {
//            	System.out.println(reply.text);
//            }
            
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
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

}
