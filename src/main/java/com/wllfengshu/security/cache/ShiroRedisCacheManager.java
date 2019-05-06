package com.wllfengshu.security.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.springframework.data.redis.cache.RedisCacheManager;

/**
 * 缓存管理器
 *
 * @author
 */
@Slf4j
public class ShiroRedisCacheManager implements CacheManager, Destroyable {

    private RedisCacheManager redisCacheManager;

    public RedisCacheManager getCacheManager() {
        return redisCacheManager;
    }

    public void setCacheManager(RedisCacheManager redisCacheManager) {
        this.redisCacheManager = redisCacheManager;
    }

    /**
     * 为了个性化配置redis存储时的key，我们选择了加前缀的方式，所以写了一个带名字及redis操作的构造函数的Cache类
     */
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        if (name == null) {
            return null;
        }
        return new ShiroRedisCache<>(name, getCacheManager());
    }

    @Override
    public void destroy() throws Exception {
        redisCacheManager = null;
    }
}
