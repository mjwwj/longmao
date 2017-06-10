package com.opengroup.longmao.gwcommon.configuration.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.opengroup.longmao.gwcommon.configuration.log.GwsLogger;
import com.opengroup.longmao.gwcommon.configuration.properties.RedisConfig;
import com.opengroup.longmao.gwcommon.configuration.redis.cache.BaseRedisCacheImpl;
import com.opengroup.longmao.gwcommon.configuration.redis.cache.CacheClient;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * redis配置
 *
 * @version
 * @author Hermit 2017年4月15日 下午3:07:39
 *
 */
@Configuration
@EnableAutoConfiguration
public class RedisCacheConfig {

	@Autowired
	private JdkSerializationRedisSerializer valueSerializer;

	@Autowired
	private StringRedisSerializer keySerializer;
	
	@Autowired
	private RedisConfig redisConfig;

	@Bean
	// @ConfigurationProperties(prefix="spring.redis")
	public JedisPoolConfig getRedisConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(redisConfig.getMaxTotal());
		config.setMaxIdle(redisConfig.getMaxIdle());
		config.setMinIdle(redisConfig.getMinIdle());
		config.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
		GwsLogger.info("JedisPoolConfig bean init success.");
		return config;
	}

	@Bean
	// @ConfigurationProperties(prefix="spring.redis")
	public JedisConnectionFactory getConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		JedisPoolConfig config = getRedisConfig();
		factory.setPoolConfig(config);
		factory.setHostName(redisConfig.getHost());
		factory.setPort(redisConfig.getPort());
		if (StringUtils.isNotBlank(redisConfig.getPassword())) {
			factory.setPassword(redisConfig.getPassword().trim());
		}
		GwsLogger.info("JedisConnectionFactory bean init success.");
		return factory;
	}

	// @Bean
	// public JedisConnectionFactory redisConnectionFactory() {
	// JedisConnectionFactory factory = new JedisConnectionFactory();
	// factory.setUsePool(true);
	// factory.setHostName(host);
	// factory.setPort(port);
	// if (StringUtils.isNotBlank(password)) {
	// factory.setPassword(password.trim());
	// }
	// return factory;
	// }

	// @Bean
	// public RedisTemplate<?, ?> getRedisTemplate(){
	// RedisTemplate<?,?> template = new
	// StringRedisTemplate(getConnectionFactory());
	// return template;
	// }

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setKeySerializer(keySerializer);
		template.setValueSerializer(valueSerializer);
		template.setDefaultSerializer(valueSerializer);
		template.setHashValueSerializer(valueSerializer);
		template.setConnectionFactory(factory);
		return template;
	}

	@Bean
	public StringRedisSerializer stringRedisSerializer() {
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		return stringRedisSerializer;
	}

	@Bean
	public JdkSerializationRedisSerializer jdkRedisSerializer() {
		JdkSerializationRedisSerializer jdkRedisSerializer = new JdkSerializationRedisSerializer();
		return jdkRedisSerializer;
	}

	@Bean
	public CacheClient redisClientFactory() {
		CacheClient cc = new BaseRedisCacheImpl();
		return cc;
	}

}
