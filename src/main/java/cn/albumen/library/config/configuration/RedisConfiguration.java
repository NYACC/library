package cn.albumen.library.config.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis 连接池配置
 *
 * @author Albumen
 */
@Configuration
public class RedisConfiguration {
    @Value("${redis.maxIdle}")
    String maxIdle;
    @Value("${redis.maxTotal}")
    String maxTotal;
    @Value("${redis.maxWaitMillis}")
    String maxWaitMillis;
    @Value("${redis.minEvictableIdleTimeMillis}")
    String minEvictableIdleTimeMillis;
    @Value("${redis.numTestsPerEvictionRun}")
    String numTestsPerEvictionRun;
    @Value("${redis.timeBetweenEvictionRunsMillis}")
    String timeBetweenEvictionRunsMillis;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(Integer.parseInt(maxIdle));
        poolConfig.setMaxWaitMillis(Integer.parseInt(maxWaitMillis));
        poolConfig.setMinEvictableIdleTimeMillis(Integer.parseInt(minEvictableIdleTimeMillis));
        poolConfig.setNumTestsPerEvictionRun(Integer.parseInt(numTestsPerEvictionRun));
        poolConfig.setTimeBetweenEvictionRunsMillis(Integer.parseInt(timeBetweenEvictionRunsMillis));
        return poolConfig;
    }

    @Bean
    public StringRedisTemplate redisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }
}
