package com.opengroup.longmao.gwcommon.tools.result;

/**
 * 业务异常 基类
 * 
 * @author sandy
 *
 */
public class ImplException extends RuntimeException {
	/**  */
	private static final long serialVersionUID = 1548927237783032449L;

	/**
	 * 异常编码
	 */
	private String code;
	/**
	 * 异常消息
	 */
	private String message;

	private Object[] replacement;

	public ImplException(Throwable t, String code, String message) {
		super(message + "[" + code + "]", t);
		this.code = code;
		this.message = message;
	}

	public ImplException(String code, String message) {
		super(message + "[" + code + "]");
		this.code = code;
		this.message = message;
	}

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

	public Object[] getReplacement() {
		return replacement;
	}

	public void setReplacement(String... replacement) {
		this.replacement = replacement;
	}

}
