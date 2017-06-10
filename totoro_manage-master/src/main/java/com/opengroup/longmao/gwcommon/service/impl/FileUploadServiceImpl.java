package com.opengroup.longmao.gwcommon.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opengroup.longmao.gwcommon.configuration.qiniu.QiniuUploadConfig;
import com.opengroup.longmao.gwcommon.service.FileUploadService;
import com.qiniu.util.StringMap;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	@Autowired
	private QiniuUploadConfig qiniuUploadConfig;
	
	@Override
	public Map<String, Object> uploadToQiniu(byte[] file, String fileName) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		String uid = UUID.randomUUID().toString().replace("-", "");
		final String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		final String newFileName = uid + "." + suffix;
		try {
			StringMap qiniuResult = qiniuUploadConfig.uploadFile(file, newFileName);
			// 生成下载地址
			String url = qiniuUploadConfig.downloadUrl(String.valueOf(qiniuResult.get("key")));
			result.put("downurl", url);
		} catch (Exception e) {
			throw new Exception(e);
		}
		result.put("fileName", fileName);
		result.put("key", newFileName);
		return result;
	}

}
