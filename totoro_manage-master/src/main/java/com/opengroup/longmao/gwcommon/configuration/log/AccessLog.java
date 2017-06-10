package com.opengroup.longmao.gwcommon.configuration.log;

/**
 * 用户访问日志
 *
 * @version 
 * @author zengjq  2016年10月25日 下午5:14:38
 * 
 */
public class AccessLog {
	/**用户访问ip地址*/
	private String ip;
	/**用户访问url地址*/
	private String url;
	/**用户会话sid*/
	private String sid;
	/**用户id*/
	private String userId;
	/**用户ua信息*/
	private String ua;
	/**终端类型*/
	private String terminalType;
	/**终端型号*/
	private String terminalName;
	/**渠道号*/
	private String channelId;
	/**设备ID*/
	private String devicesId;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}
	public String getTerminalName() {
		return terminalName;
	}
	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getDevicesId() {
		return devicesId;
	}
	public void setDevicesId(String devicesId) {
		this.devicesId = devicesId;
	}
	
	@Override
	public String toString() {
		return "ip=" + ip + "`url=" + url + "`ua=" + ua
				+ "`terminalType=" + terminalType + "`terminalName=" + terminalName + "`channelId=" + channelId
				+ "`devicesId=" + devicesId;
	}		
	
}
