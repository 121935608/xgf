package com.xingrongjinfu.utils;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JpushClientUtil {
	
	private final static String APP_KEY = "ced5eace6c76dc60d701cddb";
	   
	private final static String MASTER_SECRET = "f58c70ec7aedac19f3d374fd";
//	 public static final String TITLE = "星融金服";  
//     public static final String ALERT = "这是一个测试推送";  
//     public static final String MSG_CONTENT = "【星融金服】这是一个推送测试";  
//     public static final String ALIAS = "750BBL422D26";  
	   
	private static JPushClient jPushClient = new JPushClient(MASTER_SECRET,APP_KEY);

	public static int  pushMsg(String alias,String title,String msgContent){
	   JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());

		            int result = 0;
		            try {
		                //PushPayload pushPayload= JpushClientUtil.buildPushObject_all_registrationId_alertWithTitle(registrationId,notification_title,msg_title,msg_content,extrasparam);
		            	//别名推送
//		            	PushPayload pushPayload= JpushClientUtil.buildPushObject_android_and_iosByAlias(alias, title, msgContent);

                        //标签推送
                        PushPayload p = JpushClientUtil.buildPushObject_android_and_iosByTag(alias,title,msgContent);

		            	System.out.println(p);
		                PushResult pushResult=jPushClient.sendPush(p);
		                System.out.println(pushResult);
		                if(pushResult.getResponseCode()==200){
		                    result=1;
		                }
		            } catch (APIConnectionException e) {
		                e.printStackTrace();
		     
		            } catch (APIRequestException e) {
		                e.printStackTrace();
		            }
		     
		             return result;
		        }
	
		/**
	     * registrationId推送
	     */
	     private static PushPayload buildPushObject_all_registrationId_alertWithTitle(String registrationId,String notification_title, String msg_title, String msg_content, String extrasparam) {
		  
		         System.out.println("----------buildPushObject_all_all_alert");
		         //创建一个IosAlert对象，可指定APNs的alert、title等字段
		         return PushPayload.newBuilder()
		                 //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
		                 .setPlatform(Platform.all())
		                 //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
		                .setAudience(Audience.registrationId(registrationId))
		                 //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
		                 .setNotification(Notification.newBuilder()
		                         //指定当前推送的android通知
		                         .addPlatformNotification(AndroidNotification.newBuilder()
		 
		                                 .setAlert(notification_title)
		                                 .setTitle(notification_title)
		                                 //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
		                                 .addExtra("androidNotification extras key",extrasparam)
		  
		                                 .build())
		                         //指定当前推送的iOS通知
		                         .addPlatformNotification(IosNotification.newBuilder()
		                                 //传一个IosAlert对象，指定apns title、title、subtitle等
		                                 .setAlert(notification_title)
		                                .incrBadge(1)
		                                 .setSound("sound.caf")
		                                 .addExtra("iosNotification extras key",extrasparam)
		  
		                                 .build())
		                         .build())
		                 .setMessage(Message.newBuilder()
		                         .setMsgContent(msg_content)
		                         .setTitle(msg_title)
		                         .addExtra("message extras key",extrasparam)
		                         .build())
		                 .setOptions(Options.newBuilder()
		                         .setApnsProduction(false)
		                         .setSendno(1)
		                         .setTimeToLive(86400)
		                         .build())
		                 .build();
		  
	     }
	     
	     /**
	      * 标签推送
	      */
	     public static PushPayload buildPushObject_android_and_iosByTag(String tag,String title,String content) {
	         return PushPayload.newBuilder()
	                 .setPlatform(Platform.android_ios())
	                 .setAudience(Audience.tag(tag))
	                 .setNotification(Notification.newBuilder()
	                 		.setAlert(content)
	                 		.addPlatformNotification(AndroidNotification.newBuilder()
	                 				.setTitle(title).build())
	                 		.addPlatformNotification(IosNotification.newBuilder()
	                 				.incrBadge(1)
	                 				.addExtra(title, content).build())
	                 		.build())
	                 .build();
	     }
	     
	     /**
	      * 别名推送
	      */
	     public static PushPayload buildPushObject_android_and_iosByAlias(String alias,String title,String content) {
	         return PushPayload.newBuilder()
	                 .setPlatform(Platform.android_ios())
	                 .setAudience(Audience.alias(alias))
	                 .setNotification(Notification.newBuilder()
	                 		.setAlert(content)
	                 		.addPlatformNotification(AndroidNotification.newBuilder()
	                 				.setTitle(title).build())
	                 		.addPlatformNotification(IosNotification.newBuilder()
	                 				.incrBadge(1)
	                 				.addExtra(title, content).build())
	                 		.build())
	                 .build();
	     }
	     
	     
//	     public static void main(String[] args){
//	          int  count= pushMsg("1509761108","星融汇","标签测试推送");
//	          System.out.println(count);
//
//	       }


}
