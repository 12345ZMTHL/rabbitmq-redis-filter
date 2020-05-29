package com.jiang.rabbitmqredisfilter.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName: JedisConfig
 * @description: redis配置类
 * @author: lvjx
 * @create: 2020-05-29 14:51
 **/
@Configuration
public class RedisConfig {

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
        return jedisPool;
    }

    @Bean
    public Jedis jedis(@Qualifier(value = "jedisPool") JedisPool jedisPool) {
        return jedisPool.getResource();
    }
}
