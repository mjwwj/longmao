package com.opengroup.longmao.gwcommon.service;

import java.util.Map;

public interface FileUploadService {
	/**
	 * 【文件上传到七牛】
	 * @param file
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> uploadToQiniu(byte[] file,String fileName) throws Exception;
}
