package com.wfm.servicesystem.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;

import static java.util.Collections.singletonMap;

/**
 * description: RedisConfig
 * date: 2019-12-17 10:56
 *
 * @author: wfm
 * @version: 1.0
 */
@Configuration
@EnableCaching // 开启缓存支持
public class RedisConfig extends CachingConfigurerSupport {

    @Resource
    private LettuceConnectionFactory lettuceConnectionFactory;

    //	/**
    //	 * @description 自定义的缓存key的生成策略 若想使用这个key
    //	 *              只需要讲注解上keyGenerator的值设置为keyGenerator即可</br>
    //	 * @return 自定义策略生成的key
    //	 */
    //	@Override
    //	@Bean
    //	public KeyGenerator keyGenerator() {
    //		return new KeyGenerator() {
    //			@Override
    //			public Object generate(Object target, Method method, Object... params) {
    //				StringBuilder sb = new StringBuilder();
    //				sb.append(target.getClass().getName());
    //				sb.append(method.getDeclaringClass().getName());
    //				Arrays.stream(params).map(Object::toString).forEach(sb::append);
    //				return sb.toString();
    //			}
    //		};
    //	}

    /**
     * 缓存配置管理器
     * @param factory
     * @return
     */
    public CacheManager cacheManager(LettuceConnectionFactory factory) {
        // 生成一个默认配置，通过config对象即可对缓存进行自定义配置（缓存默认有效期 1小时）
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(1));
        RedisCacheConfiguration redisCacheConfiguration = config
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        // 以锁写入的方式创建RedisCacheWriter对象
        //RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(factory);
        // 创建默认缓存配置对象
        /* 默认配置，设置缓存有效期 1小时*/
        //RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(1));
        /* 自定义配置test:demo 的超时时间为 5分钟*/
        RedisCacheManager cacheManager = RedisCacheManager
                .builder(RedisCacheWriter.lockingRedisCacheWriter(factory))
                .cacheDefaults(redisCacheConfiguration)
                .withInitialCacheConfigurations(singletonMap("test:demo", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)).disableCachingNullValues()))
                .transactionAware().build();
        return cacheManager;
    }

    /**
     * RedisTemplate配置
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory factory){
        RedisTemplate<String,Object> template = new RedisTemplate <>();
        template.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }
}
