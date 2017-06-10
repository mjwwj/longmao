package com.opengroup.longmao.gwcommon.configuration.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.util.StringUtils;

import com.opengroup.longmao.gwcommon.tools.ip.IpUtil;

/**
 * 
 * 【redisKey值初始化】
 *
 * @version
 * @author zengjq 2016年10月25日 下午5:57:13
 *
 */
@Configuration
public class RedisValInit implements CommandLineRunner {

	@Autowired
	private RedisTemplate<Object, Object> redisClient;

	@Autowired
	@Qualifier("initSeqId")
	private RedisValConfig redisConfig;

	@Override
	public void run(String... arg0) throws Exception {
		if (null != redisConfig) {
			String prefix = redisConfig.getPrefix();
			String key = redisConfig.getKey();
			if (!StringUtils.isEmpty(key)) {
				String[] keys = key.split(",");
				if (keys.length > 0) {
					for (String k : keys) {
						new RedisAtomicLong(prefix + k.trim().toUpperCase(), redisClient.getConnectionFactory(),
								IpUtil.getSeq());
					}
				}
			}
		}
	}

}
