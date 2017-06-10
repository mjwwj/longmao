package com.opengroup.longmao.gwcommon.configuration.qiniu;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.configuration.properties.QiNiuConfig;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;




@Configuration
@EnableAutoConfiguration
public class QiniuUploadConfig {
	
	@Autowired
	private QiNiuConfig qiNiuConfig;

//	private final static Auth auth = Auth.create(accessKey, secretKey);	
	public Auth getUpToken(){
		//密钥配置
		  Auth auth = Auth.create(qiNiuConfig.getQiniuAccessKey(), qiNiuConfig.getQiniuSecretKey());
	      return auth;
	  }
	
	/**
	 * 普通上传用的token
	 * @param bucket 空间名
	 * @param response
	 */
	public String token() throws Exception{
		GwsLogger.info("获取token入参bucketName:" + qiNiuConfig.getQiniuBucketName());
		String upToken = getUpToken().uploadToken(qiNiuConfig.getQiniuBucketName());
		return upToken;
	}
	
	/**
	 * 获取指定空间的域名
	 * @param bucket(image,file,video)
	 * @param response
	 */
	public String domainUrl()  throws Exception{
		return qiNiuConfig.getQiniuDomainName();
	}
	
	/**
	 * 获取文件下载路径
	 * @param bucket 存放空间
	 * @param key	存放文件名
	 * @return
	 */
	public  String downloadUrl(String key) throws Exception{
		String path=domainUrl()+key;
		GwsLogger.info("获取文件下载路径入参path:" + path + "validTime:" + 3600);
		String downloadRUL = getUpToken().privateDownloadUrl(path,3600);
		return downloadRUL;
	}
	
	/**
	 * 将文件上传到七牛(字节流形式上传)
	 * @param data 字节流
	 * @param key	文件名
	 * @param bucket	文件存储空间
	 * @return
	 * @throws Exception
	 */
	public StringMap uploadFile(byte[] data,String key) throws Exception{
		GwsLogger.info("将文件上传到七牛(字节流形式上传)入参data:" + data.toString() + "key:" + key);
		UploadManager uploadManager = new UploadManager();
		Response res =uploadManager.put(data, key, token());
		return res.jsonToMap();
		
	}
	
	/**
	 * 将文件上传到七牛（文件形式上传）
	 * @param file 文件
	 * @param key	文件名
	 * @param bucket	文件存储空间
	 * @return
	 * @throws Exception
	 */
	public StringMap uploadFile(File file,String key) throws Exception{
		GwsLogger.info("将文件上传到七牛(文件形式上传)入参file:" + file.toString() + "key:" + key );
		UploadManager uploadManager = new UploadManager();
		Response res =uploadManager.put(file, key, token());
		return res.jsonToMap();
		
	}
	
	/**
	 * 将七牛上的文件删除
	 * @param key	文件名
	 * @param bucket	文件存储空间
	 * @throws Exception
	 */
	public void deleteFile(String key) throws Exception{
		//实例化一个BucketManager对象
	    BucketManager bucketManager = new BucketManager(getUpToken());
        //调用delete方法移动文件
	    GwsLogger.info("将七牛上的文件删除入参key:" + key);
        bucketManager.delete(qiNiuConfig.getQiniuBucketName(), key);
	}
}
