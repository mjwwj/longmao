package com.opengroup.longmao.gwcommon.tools.result;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpRequestApi {
	
	//记录访问日志
	private static Logger logger = LogManager.getLogger(HttpRequestApi.class.getName());

	
	/**
	 * 通过post方式获取数据
	 * @authors liunan<liunan@zjyushi.com>
	 * @time 2015年6月17日 上午11:37:59
	 * @version 0.1
	 * @since 0.1
	 * @param formparams
	 * @param uri
	 * @return
	 */
	public static Map<String,Object> httpPost(Map<String,String> paramsMap,String url,String methodName){
		//封装参数
		NameValuePair[] formparams = {};
		if(null!=paramsMap&&paramsMap.size()>0){
			formparams = new NameValuePair[paramsMap.size()];
			int i = 0;
			for (String key : paramsMap.keySet()) {  
				formparams[i] = new NameValuePair(key,paramsMap.get(key));
				i++;
	        } 
		}
		//创建连接
		HttpClient httpclient = new HttpClient();
		HttpConnectionManagerParams managerParams = httpclient.
				getHttpConnectionManager().getParams(); 
		//设置连接超时时间(单位毫秒) 
		managerParams.setConnectionTimeout(30000); 
		//设置读数据超时(响应超时时间)时间(单位毫秒) 
		managerParams.setSoTimeout(60000); 
		//创建参数 
		Map<String,Object> retMap = new HashMap<String, Object>();
		PostMethod postMethod = null;
		try {
			//post方式发送请求
			postMethod = new PostMethod(url); 
			//如果不设置编码，那么httpclient就会使用默认的iso8859,服务器端就无法得到正确的中文编码
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
			postMethod.setRequestBody(formparams);
			postMethod.setRequestHeader(CommConstant.METHODNAME, methodName);
			//发送请求
			int statusCode = httpclient.executeMethod(postMethod); 
			//String strResponse = postMethod.getResponseBodyAsString();
		    //HTTP头没有指定contentLength，或者是contentLength大于上限（默认是1M）。
			///如果能确定返回结果的大小对程序没有显著影响，可以用上面的方式,这个WARN就可以忽略，
			//可以在日志的配置中把HttpClient的日志级别调到ERROR，不让它报出来。
			//对于返回结果很大或无法预知的情况，就需要使用InputStream getResponseBodyAsStream()，
			//避免byte[] getResponseBody()可能带来的内存的耗尽问题。
			if (statusCode == HttpStatus.SC_OK) {
				InputStream inputStream = postMethod.getResponseBodyAsStream();  
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  
				StringBuffer stringBuffer = new StringBuffer();  
				String str= "";  
				while((str = br.readLine()) != null){  
					stringBuffer.append(str);  
				}  
				retMap.put("strResponse", null==stringBuffer?"":stringBuffer.toString());
				retMap.put("resultCode", CommConstant.GWSSUCC);
			}else{
				logger.info("访问资源不存在，或者服务器异常！");
				retMap.put("resultCode", CommConstant.GWS1001);
			}
		}catch (Exception e) {
			logger.info("服务器拒绝连接，或者通讯超时！",e);
			retMap.put("resultCode", CommConstant.GWS1002);
		} finally {
			//关闭连接
			postMethod.releaseConnection();
		}
		return retMap;
	}
	
	/**
	 * httpGet请求
	 * @param url
	 * @return
	 */
	public static Map<String,Object> httpGet(String url){
		Map<String,Object> retMap = new HashMap<String, Object>();
		HttpClient httpClient = new HttpClient(); 
		HttpConnectionManagerParams managerParams = httpClient.
				getHttpConnectionManager().getParams(); 
		//设置连接超时时间(单位毫秒) 
		managerParams.setConnectionTimeout(30000); 
		//设置读数据超时(响应超时时间)时间(单位毫秒) 
		managerParams.setSoTimeout(60000); 
		GetMethod getMethod = new GetMethod(url); //输入网址
		try {
			int statusCode = httpClient.executeMethod(getMethod);  //按下回车运行，得到返回码
			if (statusCode != HttpStatus.SC_OK) {
				logger.info("访问资源不存在，或者服务器异常！");
				retMap.put("resultCode", CommConstant.GWS1001);
			}else{
				//读取内容 
				InputStream inputStream = getMethod.getResponseBodyAsStream();  
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  
				StringBuffer stringBuffer = new StringBuffer();  
				String str= "";  
				while((str = br.readLine()) != null){  
					stringBuffer .append(str );  
				}  
				retMap.put("strResponse", null==stringBuffer?"":stringBuffer.toString());
				retMap.put("resultCode", CommConstant.GWSSUCC);
			}
		}catch (Exception e) {
			logger.info("服务器拒绝连接，或者通讯超时！",e);
			retMap.put("resultCode", CommConstant.GWS1002);
		}finally{
			getMethod.releaseConnection();
		}
		return retMap;
	}
	
}
