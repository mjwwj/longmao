package com.opengroup.longmao.gwcommon.configuration.redis.cache;

import java.util.List;

/**
 * 
 * 缓存客户端接口类
 *
 * @version 
 * @author liuyi  2016年4月25日 下午1:48:15
 *
 */
public interface CacheClient {
	/**
	 * 
	 * 设置对象
	 * 
	 * @author liuyi 2016年4月20日
	 * @param key
	 * @param object
	 * @param timeout
	 * @param clazz
	 * @return
	 */
    public <T> boolean set(String prefix,String key, T t, Long timeout);  
    
    
    /**
     * 
     * 设置列表缓存
     * 
     * @author liuyi 2016年4月25日
     * @param prefix
     * @param key
     * @param t
     * @param timeout
     * @return
     */
    public <T> boolean setList(String prefix, String key, List<T> t);
    
    /**
     * 
     * 获取对象
     * 
     * @author liuyi 2016年4月20日
     * @param key
     * @return
     */
    public <T> T get(String prefix,String key);
    
    
    
    /**
     * 
     * 获取列表缓存
     * 
     * @author liuyi 2016年4月25日
     * @param prefix
     * @param key
     * @param t
     * @return
     */
    public <T> List<T> getList(String prefix,String key);
    
    /**
     * 
     * 删除缓存
     * 
     * @author liuyi 2016年4月20日
     * @param key
     */
    public void delete(String prefix,String key);
}
