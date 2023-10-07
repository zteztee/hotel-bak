package com.zte.springboot_08_ssmp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;


    @Configuration
    public class RedisConfig {

        @Autowired
        private RedisConnectionFactory redisConnectionFactory;

        @Bean
        public RedisTemplate<String, Object> redisTemplate() {
            RedisTemplate<String, Object> template = new RedisTemplate<>();
            template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
            template.setKeySerializer(new StringRedisSerializer());

            template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
            template.setHashKeySerializer(new StringRedisSerializer());
            template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
            template.setConnectionFactory(redisConnectionFactory);
            return template;
        }

//    @Bean
//    public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPool,
//                                                         RedisStandaloneConfiguration jedisConfig) {
//        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(jedisConfig);
//        connectionFactory.setPoolConfig(jedisPool);
//        return connectionFactory;
//    }
//
//    @Configuration
//    public static class JedisPoolConf {
//        @Value("${spring.redis.host:127.0.0.1}")
//        private String host;
//        @Value("${spring.redis.port:6379}")
//        private Integer port;
//        @Value("${spring.redis.password:}")
//        private String password;
//        @Value("${spring.redis.database:0}")
//        private Integer database;
//
//        @Value("${spring.redis.jedis.pool.max-active:8}")
//        private Integer maxActive;
//        @Value("${spring.redis.jedis.pool.max-idle:8}")
//        private Integer maxIdle;
//        @Value("${spring.redis.jedis.pool.max-wait:-1}")
//        private Long maxWait;
//        @Value("${spring.redis.jedis.pool.min-idle:0}")
//        private Integer minIdle;
//
//        @Bean
//        public JedisPoolConfig jedisPool() {
//            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//            jedisPoolConfig.setMaxIdle(maxIdle);
//            jedisPoolConfig.setMaxWaitMillis(maxWait);
//            jedisPoolConfig.setMaxTotal(maxActive);
//            jedisPoolConfig.setMinIdle(minIdle);
//            return jedisPoolConfig;
//        }
//
//        @Bean
//        public RedisStandaloneConfiguration jedisConfig() {
//            RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//            config.setHostName(host);
//            config.setPort(port);
//            config.setDatabase(database);
//            config.setPassword(RedisPassword.of(password));
//            return config;
//        }
//    }
    }

