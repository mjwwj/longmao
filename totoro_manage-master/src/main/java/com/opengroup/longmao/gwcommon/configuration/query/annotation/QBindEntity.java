package com.opengroup.longmao.gwcommon.configuration.query.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询绑定实体类
 *
 * @version 
 * @author zengjq  2016年10月26日 下午6:18:03
 * 
 */
@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface QBindEntity {
	public abstract Class<?> entityClass();
}
