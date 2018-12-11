package cn.albumenj.util;

import cn.albumenj.constant.SecurityConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author Albumen
 */
public class RedisUtil {
    private StringRedisTemplate redisTemplate;

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * String类型缓存获取
     * @param key 键
     * @return 值
     */
    public String get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * String类型缓存保存
     * @param key 键
     * @param value 值
     * @return true：成功；false：失败
     */
    public boolean set(String key, String value) throws Exception {
        if (StringUtils.isNotEmpty(key) && null != value) {
            redisTemplate.opsForValue().set(key, value, SecurityConfig.EXP_TIME, TimeUnit.MILLISECONDS);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除键值
     * @param key
     * @return
     */
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }
}

