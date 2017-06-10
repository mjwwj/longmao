package com.opengroup.longmao.gwcommon.configuration.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.opengroup.longmao.gwcommon.tools.role.SessionConstant;

/**
 * 
 * 【登录拦截器】
 *
 * @version
 * @author liunan 2016年4月11日 下午5:50:28
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		if (path.matches(SessionConstant.NO_INTERCEPTOR_PATH)) {
			return true;
		} else {
			HttpSession session = request.getSession();
			Object obj =session.getAttribute(SessionConstant.SESSION_USER);
			if (obj != null) {
				return true;
			} else {
				response.sendRedirect(request.getContextPath()+ "/login");
				return false;
			}
		}
	}

}