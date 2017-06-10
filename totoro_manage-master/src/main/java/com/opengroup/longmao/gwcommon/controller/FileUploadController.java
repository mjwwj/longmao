package com.opengroup.longmao.gwcommon.controller;

import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.configuration.properties.QiNiuConfig;
import com.opengroup.longmao.gwcommon.service.FileUploadService;
import com.opengroup.longmao.gwcommon.tools.result.CommConstant;
import com.opengroup.longmao.gwcommon.tools.result.RetResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 【fileupload】 控制类
 *
 * @version 1.0
 * @author Yangst 2017年04月25日 上午11:12:07
 */
@RestController
@EnableSwagger2
@Api(value = "文件上传", tags = "file")
public class FileUploadController {

	@Autowired
	private FileUploadService fileUploadService;

	@Autowired
	private QiNiuConfig qiNiuConfig;

	/**
	 * 
	 * 【七牛上传工具类】
	 * 
	 * @author Hermit 2017年5月11日
	 * @param file
	 * @return
	 */
	@ApiOperation(value = "文件上传",notes="文件上传")
	@ApiImplicitParam(name ="file",value ="七牛上传file", required = true, dataType ="MultipartFile")
	@RequestMapping(value = "/fileupload",method =RequestMethod.POST)
	public RetResult fileupload(MultipartFile file){
		String code = CommConstant.GWSCOD0000;
		String message = CommConstant.GWSMSG0000;
		GwsLogger.info("文件上传开始:code={},message={},file={}",code,message,ToStringBuilder.reflectionToString(file));
		if (file == null) {   
				GwsLogger.error("file不合法:code={},message={},file={}", CommConstant.GWSCOD0003,CommConstant.GWSMSG0003, ToStringBuilder.reflectionToString(file));
				return RetResult.setRetDate(CommConstant.GWSCOD0003, CommConstant.GWSMSG0003, null);
        }
		if(file.getSize()>Long.valueOf(qiNiuConfig.getQiniuFileMaxSize())){   
			GwsLogger.error("file大小不合法:code={},message={},file.size={}", CommConstant.GWSCOD0003,CommConstant.GWSMSG0003, file.getSize());
			return RetResult.setRetDate(CommConstant.GWSCOD0003, CommConstant.GWSMSG0003, null);
       } 
		Map<String,Object> map = null;
		try {
			map= fileUploadService.uploadToQiniu(file.getBytes(), file.getOriginalFilename());
			if (map != null) {
			   GwsLogger.info("文件上传成功:code={},message={},",code,message);
			}else{
			   code = CommConstant.GWSCOD0001;
			   message = CommConstant.GWSMSG0001;
			   GwsLogger.info("文件上传失败:code={},message={},file={}",code,message,ToStringBuilder.reflectionToString(file));
			}
		} catch (Exception e) {
			code = CommConstant.GWSCOD0001;
			message = CommConstant.GWSMSG0001;
			GwsLogger.error("文件上传异常:tvFeedback={},e={}",ToStringBuilder.reflectionToString(file),e);
		}
		GwsLogger.info("文件上传结束:code={},message={}",code,message);
		return RetResult.setRetDate(code, message, map);
	}
}
