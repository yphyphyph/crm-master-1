package com.shangma.cn.common.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.TimeUnit;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/23 10:11
 * 文件说明：
 */
@Component
@RequiredArgsConstructor
public class CacheService {


    public static final long defaultTime = 2;


    private final StringRedisTemplate stringRedisTemplate;


    public void setCacheWithCustomerTime(String key, String value, long minute) {
        stringRedisTemplate.opsForValue().set(key, value, minute, TimeUnit.MINUTES);
    }


    /**
     * 默认时间的缓存
     */

    public void setCacheWithDefaultTime(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value, defaultTime, TimeUnit.MINUTES);
    }


    /**
     * 设置缓存
     */

    public void setCache(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 判断缓存是否存在
     */

    public boolean exitsKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 获取缓存
     */
    public String getCache(String key) {

        if (exitsKey(key)) {
            return stringRedisTemplate.opsForValue().get(key);
        }
        return "";
    }


    /**
     * 删除缓存
     */

    public void clearCache(String key) {
        stringRedisTemplate.delete(key);
    }


}
