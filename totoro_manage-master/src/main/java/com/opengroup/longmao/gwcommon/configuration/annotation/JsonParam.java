package com.opengroup.longmao.gwcommon.configuration.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标识一个参数，使之以JSON方式来绑定数据。
 * <p>
 * 用法如下：
 * </p>
 * <ol>
 * <li>指定参数名称：<code>@JsonParam("name")</code>。</li>
 * </ol>
 * 
 * @author 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface JsonParam {
}
