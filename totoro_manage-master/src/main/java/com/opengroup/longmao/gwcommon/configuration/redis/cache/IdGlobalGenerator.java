package com.opengroup.longmao.gwcommon.configuration.redis.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import com.opengroup.longmao.gwcommon.configuration.redis.CacheConstant;

/**
 * 全局ID生成器
 *
 * @version 
 * @author zengjq 2016年10月26日 下午11:26:55
 * 
 */
@Component
public  class IdGlobalGenerator {
	
    @Autowired
    private  RedisTemplate<Object, Object> redisClient;
    
    private static RedisAtomicLong counter;	
    

	/**
	 * 
	 * 获取ID
	 * 
	 * @author zengjq 2016年10月25日
	 * @return
	 */
	public  Long getSeqId() {
		Long seqId =null;
		try {
			seqId =getCacheSeqIncr();
		} catch (Exception e) {
		}
		if(seqId==null){
			seqId = getLocalCacheSeq();
		}
		return seqId;
	}
	
	/**
	 * 
	 * 细化粒度，以单表实体类名为序列前缀
	 * 
	 * @author liuyi 2016年4月21日
	 * @param clz
	 * @return
	 */
	public  Long getSeqId(Class<?> clz) {
		Long seqId =null;
		String prefix= clz.getSimpleName().toUpperCase();
		try {
			seqId =getCacheSeqIncr(prefix);
		} catch (Exception e) {
		}
		if(seqId==null){
			seqId = getLocalCacheSeq();
		}
		return seqId;
	}
	
	private static Long getRandNum() {
		Long incrNum = Math.round(Math.random() * 1000);
		return incrNum;
	}
	
	private static Long getLocalCacheSeq(){
		Long maxNum = System.currentTimeMillis();
		Long randNum = getRandNum();
		return maxNum * 1000 + randNum;
	}
	
	/**
	 * 
	 * 获取缓存ID系列值
	 * 
	 * @author liuyi 2016年4月15日
	 * @return
	 */
	private  Long getCacheSeqIncr(){
		 counter = new RedisAtomicLong(CacheConstant.CACHE_ID_GEN,
				 redisClient.getConnectionFactory(), getLocalCacheSeq());
		 return counter.incrementAndGet();
	}
	
	/**
	 * 
	 * 细化粒度获取自增ID
	 * 
	 * @author liuyi 2016年4月21日
	 * @param prefix
	 * @return
	 */
	private  Long getCacheSeqIncr(String prefix){
		 counter = new RedisAtomicLong(CacheConstant.CACHE_ID_GEN+prefix,
				 redisClient.getConnectionFactory());
		 return counter.incrementAndGet();
	}
	

}
