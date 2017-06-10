package com.opengroup.longmao.gwcommon.configuration.annotation;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;

/**
 * @author 
 */
public class JsonParameterBinder implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(JsonParam.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {

		Class<?> parameterType = parameter.getParameterType();

		String name = parameter.getParameterName();

		HttpServletRequest request = webRequest
				.getNativeRequest(HttpServletRequest.class);

		if (parameterType.getName().equals(Object.class.getName())) {
			throw new RuntimeException(String.format(
					"no exact type assigned for parameter '%s'", name));

		}

		String value = request.getParameter(name);

		if (value != null) {
			Object obj = null;

			try {
				obj = JSON.parse(value);
			} catch (Exception e) {
				return null;
			}

			return parseResult(parameter, obj);
		}

		return null;
	}

	/**
	 * 将JSON对象转换成指定的用户返回值类型
	 * 
	 * @param type
	 * @param jsonObject
	 * @return
	 * @throws JSONException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object parseResult(MethodParameter parameter, Object resultObject)
			throws JSONException {
		if (resultObject == null) {
			throw new JSONException("result is empty.");
		}

		Object result = null;

		Class<?> parameterType = parameter.getParameterType();

		boolean isArray = parameterType.isArray();
		boolean isCollection = Collection.class.isAssignableFrom(parameterType);

		Class<?> componentType = parameterType.getComponentType();

		if ((isArray || isCollection) && resultObject instanceof JSONArray) {

			if (!isArray) {
				componentType = (Class<?>) (((ParameterizedType) parameter
						.getGenericParameterType()).getActualTypeArguments()[0]);
			}

			JSONArray jsonArray = (JSONArray) resultObject;
			int size = jsonArray.size();

			if (isArray) {
				result = Array.newInstance(componentType, size);
			} else {
				result = new ArrayList(size);
			}

			for (int i = 0; i < size; i++) {

				Object value = jsonArray.getObject(i, componentType);

				if (isArray) {
					Array.set(result, i, value);
				} else {
					((List) result).add(value);
				}
			}
		} else {
			if (resultObject instanceof JSONObject) {
				result = JSONObject.toJavaObject((JSONObject) resultObject,
						parameterType);
			} else {
				result = TypeUtils.castToJavaBean(resultObject, parameterType);
			}
		}

		return result;
	}

}
