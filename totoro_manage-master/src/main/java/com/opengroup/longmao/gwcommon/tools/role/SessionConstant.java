/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.opengroup.longmao.gwcommon.tools.role;

import org.springframework.context.ApplicationContext;

/**
 * 常量配置
 *
 * @version 
 * @author zengjq  2016年4月9日 下午4:30:38
 * 
 */
public class SessionConstant {
    public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_USER_RIGHTS = "sessionUserRights";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
//	public static final String NO_INTERCEPTOR_PATH = ".*/((loginSubmit)|(loginOut)|(code)).*"; // 不对匹配该值的访问路径拦截（正则）	
    public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)).*"; // 不对匹配该值的访问路径拦截（正则）
	//验证码
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
    public static ApplicationContext WEB_APP_CONTEXT = null; // 该值会在web容器启动时由WebAppContextListener初始化
}