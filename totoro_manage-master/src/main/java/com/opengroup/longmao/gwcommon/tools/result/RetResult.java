package com.opengroup.longmao.gwcommon.tools.result;

/**
 * 返回结果
 * @author Hermit
 *
 */
public class RetResult {
	//返回码
	private String code;
	//返回信息
	private String message;
	//返回数据
	private Object data;
	
	private Object extraData;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * 设置返回结果
	 * @param retResult
	 * @param code
	 * @param message
	 * @param object
	 * @return
	 */
	public static RetResult setRetDate(String code,String message,Object object){
		RetResult retResult = new RetResult();
		retResult.setCode(code);
		retResult.setMessage(message);
		retResult.setData(null==object?"":object);
		return retResult;
	}
	
	public static RetResult setRetDate(String code,String message,Object object, Object extraObject){
		RetResult retResult = new RetResult();
		retResult.setCode(code);
		retResult.setMessage(message);
		retResult.setData(null==object?"":object);
		retResult.setExtraData(null==extraObject?"":extraObject);
		return retResult;
	}
	public Object getExtraData() {
		return extraData;
	}
	public void setExtraData(Object extraData) {
		this.extraData = extraData;
	}
	
}
