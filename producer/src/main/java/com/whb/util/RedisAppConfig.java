package com.whb.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * @program: junitTest
 * @description
 * @author: 吴徽宝
 * @create: 2021-07-13 11:05
 **/
@Configuration
public class RedisAppConfig implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(RedisAppConfig.class);
    @Value("${redis.hostName}")
    private String hostName;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.maxIdle}")
    private int maxIdle;
    @Value("${redis.maxTotal}")
    private int maxTotal;
    @Value("${redis.maxWaitMillis}")
    private int maxWaitMillis;
    @Value("${redis.sentinel.master}")
    private String sentinelMaster;

    @Value("${redis.sentinel.port1}")
    private Integer sentinelPort1;
    @Value("${redis.sentinel.port2}")
    private Integer sentinelPort2;
    @Value("${redis.sentinel.port3}")
    private Integer sentinelPort3;



    /**
     * GenericObjectPoolConfig 连接池配置
     */
    @Bean
    public GenericObjectPoolConfig poolConfig() {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        genericObjectPoolConfig.setMaxTotal(maxTotal);
        genericObjectPoolConfig.setMaxWaitMillis(maxWaitMillis);
        return genericObjectPoolConfig;
    }


    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(GenericObjectPoolConfig poolConfig) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(hostName,port);
        redisStandaloneConfiguration.setPassword(password);
        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .poolConfig(poolConfig)
                .build();
        return new LettuceConnectionFactory(redisStandaloneConfiguration,clientConfig);
    }
    @Bean
    public LettuceClientConfiguration lettuceClientConfiguration(GenericObjectPoolConfig genericObjectPoolConfig) {
        return LettucePoolingClientConfiguration.builder()
                .poolConfig(genericObjectPoolConfig)
                .build();
    }
    /**
     * @Description RedisTemplate配置类
     * 默认使用jdk自带的序列化，存值会出现/0Xab/0xac/00 乱码前缀
     * 修改序列化器，使用阿里的FastJson2JsonRedisSerializer
     * @param lettuceConnectionFactory
     * @return
     */
    @Bean(name = {"redisTemplate"})
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        redisTemplate.setValueSerializer(serializer);
        return redisTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("configuration is init");
    }
}
