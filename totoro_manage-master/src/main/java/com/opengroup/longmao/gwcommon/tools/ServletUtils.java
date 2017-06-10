package com.opengroup.longmao.gwcommon.tools;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.opengroup.longmao.gwcommon.configuration.log.GlobalConstant;

/**
 * sprint servlet工具类
 *
 * @version 
 * @author Hermit  2017年2月19日 下午6:37:30
 * 
 */
public final class ServletUtils {
	
	
	/**
	 * 
	 * 获取用户SID
	 * 
	 * @author liuyi 2016年4月19日
	 * @return
	 */
	public static String getSid(){	
		return UUID.randomUUID().toString()+"_"+System.currentTimeMillis();
	}
	
    public static Cookie getCookieByName (HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
    
    public static Cookie setCookie(String name,String value,String domain){
    	Cookie cookie  = new Cookie(name, value);
    	cookie.setDomain(domain);
    	cookie.setPath("/");
    	cookie.setHttpOnly(true);
    	//cookie.setSecure(true);
    	cookie.setMaxAge(GlobalConstant.SID_COOKIE_MAXAGE);
    	return cookie;
    }
	
	  /**
	   * 
	   * 获取当前线程的请求
	   * 
	   * @author liuyi 2016年4月19日
	   * @return
	   */
	  public static HttpServletRequest getRequest() {
	        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
	        return ((ServletRequestAttributes) ra).getRequest();
	    }
}
