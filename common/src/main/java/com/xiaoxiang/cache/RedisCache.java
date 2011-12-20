package com.xiaoxiang.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * redis 缓存实现
 *
 * @author xiang.xiaox
 */

public class RedisCache implements Cache {

    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private ShardedJedisPool shardedJedisPool;

    private ShardedJedis jedis;
    //spring配置中需调用这个方法
    public void init(){
        jedis = shardedJedisPool.getResource();
    }

    /**
     * 获取缓存中对应key的值
     *
     * @param key
     */
    public String get(String key) {
        return jedis.get(key);
    }

    /**
     * 设置缓存中对应key的值
     * 过期时间,默认为一天
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        jedis.set(key, value);
        jedis.expire(key, CacheConstant.DEFALUT_EXPIRE_TIME);
    }

    /**
     * 设置缓存中对应key的值
     *
     * @param key
     * @param value
     * @param expire 过期时间,以秒为单位
     */
    public void set(String key, String value, int expire) {
        jedis.set(key, value);
        jedis.expire(key, expire);
    }

    /**
     * 删除缓存中对应key
     *
     * @param key
     */
    public void del(String key) {
        jedis.del(key);
    }


    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }
}
