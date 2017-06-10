package com.opengroup.longmao.gwcommon.configuration.redis.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;

/**
 * 
 * REDIS缓存实现类
 *
 * @version 
 * @author liuyi  2016年4月25日 下午1:44:12
 *
 */
@Service
public class BaseRedisCacheImpl extends AbstractBaseRedis implements CacheClient {

	/**
	 * 设置缓存值
	 * 
	 * (non-Javadoc)
	 * @see com.gws.utils.cache.CacheClient#set(java.lang.String, java.lang.Object, java.lang.Long, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> boolean set(String prefix,String key, T t, Long timeout) {
		if (key == null || prefix == null) {
			return false;
		}
		String realKey = prefix + key;
		redisTemplate.opsForValue().set(realKey, t, timeout,TimeUnit.SECONDS);
		return true;
	}

	/**
	 * 获取缓存值
	 * 
	 * (non-Javadoc)
	 * @see com.gws.utils.cache.CacheClient#get(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String prefix,String key) {
		if (key == null || prefix == null) {
			return null;
		}
		String realKey = prefix + key;
		return   (T) redisTemplate.opsForValue().get(realKey);
	}

	/**
	 * 删除缓存
	 * 
	 * (non-Javadoc)
	 * @see com.gws.utils.cache.CacheClient#delete(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void delete(String prefix,String key) {
		if (key == null || prefix == null) {
			return;
		}
		String realKey = prefix + key;
		redisTemplate.delete(realKey);
	}

	/**
	 * 获取列表缓存
	 * 
	 * (non-Javadoc)
	 * @see com.gws.utils.cache.CacheClient#getList(java.lang.String, java.lang.String, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(String prefix, String key) {
		if (key == null || prefix == null) {
			return null;
		}
		String realKey = prefix + key;
		@SuppressWarnings("rawtypes")
		ListOperations listOps= redisTemplate.opsForList();
		return redisTemplate.opsForList().range(realKey, 0, listOps.size(realKey) - 1);
	}

	/**
	 * 【请在此输入描述文字】
	 * 
	 * (non-Javadoc)
	 * @see com.gws.utils.cache.CacheClient#setList(java.lang.String, java.lang.String, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> boolean setList(String prefix, String key, List<T> t) {
		if (key == null || prefix == null) {
			return false;
		}
		String realKey = prefix + key;
	    redisTemplate.opsForList().leftPushAll(realKey, t);
//		redisTemplate.opsForList().rightPushAll(realKey, t);
		return false;
	
	}


	
}  
