package com.opengroup.longmao.gwcommon.configuration.apppush;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.IQueryResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.opengroup.longmao.gwcommon.configuration.properties.MessagePushConfig;

@Configuration
public class PushToAPP {    
    //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
    private static String appId = "ojAFIq3zst6DKmGcpgydA2";
    private static String appKey = "VwInXp3zaXAQ03L0FGCxX7";
    private static String masterSecret = "BrrUn4Mkt59pJNH7BC52b4";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";
	
	@Autowired
	private MessagePushConfig messagePushConfig;
	
	public static void main(String[] args) {
//		PushToAPP pta=new PushToAPP();
//		pta.pushToApp();
		IGtPush push = new IGtPush(host, appKey, appId);
		IQueryResult queryResult = push.getPushResultByGroupName(appId, "任务别名_toApp");

        Map<String, Object> res = (Map<String, Object>)queryResult.getResponse();
        String gt = res.get("GT").toString();
        String apns = res.get("APN").toString();
        System.out.println(res.get("groupName") + "|个推报表:"+gt+"|apns报表:"+apns);
	}

    public void pushToApp() {

//        IGtPush push = new IGtPush(messagePushConfig.getPushUrl(), messagePushConfig.getAppKey(), messagePushConfig.getMasterSecret());
    	IGtPush push = new IGtPush(host, appKey, appId);
    	NotificationTemplate template=null;
		try {
			template = linkTemplateDemo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        AppMessage message = new AppMessage();
        message.setData(template);

        message.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        //推送给App的目标用户需要满足的条件
        AppConditions cdt = new AppConditions(); 
        List<String> appIdList = new ArrayList<String>();
//        appIdList.add(messagePushConfig.getAppId());
        appIdList.add(appId);
        message.setAppIdList(appIdList);
        //手机类型
        List<String> phoneTypeList = new ArrayList<String>();
        //省份
        List<String> provinceList = new ArrayList<String>();
        //自定义tag
        List<String> tagList = new ArrayList<String>();

        cdt.addCondition(AppConditions.PHONE_TYPE, phoneTypeList);
        cdt.addCondition(AppConditions.REGION, provinceList);
        cdt.addCondition(AppConditions.TAG,tagList);
        message.setConditions(cdt); 

        IPushResult ret = push.pushMessageToApp(message,"任务别名_toApp");
        
        System.out.println(ret.getResponse().toString());
        
    }

    public NotificationTemplate linkTemplateDemo() throws Exception {
//        LinkTemplate template = new LinkTemplate();
//        NotyPopLoadTemplate  template = new NotyPopLoadTemplate ();
    	NotificationTemplate template = new NotificationTemplate ();
//        template.setAppId(messagePushConfig.getAppId());
//        template.setAppkey(messagePushConfig.getAppKey());
        template.setAppId(appId);
        template.setAppkey(appKey);
        
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        template.setTransmissionContent("2017-5到2017-5停服");
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("龙猫测试");
        style.setText("育体科技为你服务");
        // 配置通知栏图标
        //style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);
        // 设置弹框标题与内容
//        template.setPopTitle("弹框标题");
//        template.setPopContent("育体科技为你服务");
//        // 设置弹框显示的图片
//        template.setPopImage("");
//        template.setPopButton1("下载");
//        template.setPopButton2("取消");
//        // 设置下载标题
//        template.setLoadTitle("下载标题");
//        template.setLoadIcon("file://icon.png");
//        //设置下载地址        
//        template.setLoadUrl("http://www.baidu.com");
//        template.setUrl("http://www.baidu.com");
        return template;
    }


}
