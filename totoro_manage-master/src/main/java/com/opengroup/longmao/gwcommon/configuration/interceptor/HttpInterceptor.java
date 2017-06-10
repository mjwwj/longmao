package com.opengroup.longmao.gwcommon.configuration.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.opengroup.longmao.gwcommon.configuration.log.AccessLog;
import com.opengroup.longmao.gwcommon.configuration.log.GlobalConstant;
import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.configuration.log.GwsLoggerTypeEnum;
import com.opengroup.longmao.gwcommon.configuration.properties.EnvironmentConfig;
import com.opengroup.longmao.gwcommon.tools.ServletUtils;
import com.opengroup.longmao.gwcommon.tools.ip.IpUtil;

/**
 * http 拦截器 
 *
 * @version
 * @author Hermit 2017年4月12日 下午1:35:11
 * 
 */
public class HttpInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private EnvironmentConfig envConfig;

	@Override
	public boolean preHandle(HttpServletRequest request ,HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
		String sid = ServletUtils.getSid();
		if (request.getCookies() == null) {
			response.addCookie(ServletUtils.setCookie(GlobalConstant.SID_COOKIE_NAME, sid,envConfig.getWebDomain()));
		} else {
			// 获取sid的cookie
			Cookie cookie = ServletUtils.getCookieByName(request,GlobalConstant.SID_COOKIE_NAME);
			if (cookie == null) {
				response.addCookie(ServletUtils.setCookie(GlobalConstant.SID_COOKIE_NAME, sid,envConfig.getWebDomain()));
			} else {
				sid = cookie.getValue();
			}
		}

		String ip = IpUtil.getIpAddr(request);
		String url = request.getRequestURI();

		AccessLog accessLog = new AccessLog();
		accessLog.setIp(ip);
		accessLog.setUrl(url);
		accessLog.setSid(sid);
		accessLog.setTerminalType(request.getHeader("terminalType"));
		accessLog.setTerminalName(request.getHeader("terminalName"));
		accessLog.setDevicesId(request.getHeader("devicesId"));
		accessLog.setChannelId(request.getHeader("channelId"));
		accessLog.setUa(request.getHeader("User-Agent"));

		GlobalConstant.accessLog.set(accessLog);
		GwsLogger.info(GwsLoggerTypeEnum.ACCESSTRACE, accessLog.toString());
		super.afterCompletion(request, response, handler, ex);
	}

}
