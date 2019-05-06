package com.wllfengshu.security.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.CacheException;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.Collection;
import java.util.Set;

/**
 * 自定义缓存 将数据存入到redis中
 *
 * @param <K>
 * @param <V>
 * @author wllfengshu
 */
@Slf4j
public class ShiroRedisCache<K, V> implements org.apache.shiro.cache.Cache<K, V> {

    private RedisCacheManager redisCacheManager;
    private Cache cache;

    public ShiroRedisCache(String name, RedisCacheManager redisCacheManager) {
        if (name == null || redisCacheManager == null) {
            throw new IllegalArgumentException("cacheManager or CacheName cannot be null.");
        }
        this.redisCacheManager = redisCacheManager;
        this.cache = redisCacheManager.getCache(name);
    }

    @Override
    public V get(K key) throws CacheException {
        log.info("从缓存中获取key为{}的缓存信息", key);
        if (key == null) {
            log.info("缓存中没有为{}的key",key);
            return null;
        }
        ValueWrapper valueWrapper = cache.get(key);
        if (valueWrapper == null) {
            log.info("缓存中为{}的key没有值", key);
            return null;
        }
        return (V) valueWrapper.get();
    }

    @Override
    public V put(K key, V value) throws CacheException {
        log.debug("创建新的缓存，信息为：{}={}", key, value);
        cache.put(key, value);
        return get(key);
    }

    @Override
    public V remove(K key) throws CacheException {
        log.info("删除key为{}的缓存", key);
        V v = get(key);
        //干掉这个名字为key的缓存
        cache.evict(key);
        return v;
    }

    @Override
    public void clear() throws CacheException {
        log.info("清空所有的缓存");
        cache.clear();
    }

    @Override
    public int size() {
        return redisCacheManager.getCacheNames().size();
    }

    /**
     * 获取缓存中所的key值
     */
    @Override
    public Set<K> keys() {
        return (Set<K>) redisCacheManager.getCacheNames();
    }

    /**
     * 获取缓存中所有的values值
     */
    @Override
    public Collection<V> values() {
        return (Collection<V>) cache.get(redisCacheManager.getCacheNames()).get();
    }

    @Override
    public String toString() {
        return "ShiroSpringCache [cache=" + cache + "]";
    }
}
