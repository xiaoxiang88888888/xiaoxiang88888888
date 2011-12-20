package com.xiaoxiang.cache;

import com.xiaoxiang.util.JsonUtil;

/**
 * 用于统一处理缓存操作,缓存不用分散到各处
 *
 * @author xiang.xiaox
 */

public class CacheSupport {

    private JsonUtil jsonUtil = JsonUtil.getInstance();
    private RedisCache cache;

    /**
     * 测试取得
     *
     * @param key
     */
    public String cacheTestGet(String key) {
        return cache.get(CacheConstant.PREFIX_TEST + key);
    }

    /**
     * 测试删除
     *
     * @param key
     */
    public void cacheTestDel(String key) {
        cache.del(CacheConstant.PREFIX_TEST + key);
    }

    /**
     * 测试增加
     *
     * @param obj
     * @param key
     */
    public void cacheTestSet(String key, Object obj, int expire) {
        cache.set(CacheConstant.PREFIX_TEST + key, jsonUtil.CollectionToJson(obj), expire);
    }

    /**
     * 测试增加,默认1天
     *
     * @param obj
     * @param key
     */
    public void cacheTestSet(String key, Object obj) {
        cache.set(CacheConstant.PREFIX_TEST + key, jsonUtil.CollectionToJson(obj));
    }


    public void setCache(RedisCache cache) {
        this.cache = cache;
    }

    public RedisCache getCache() {
        return cache;
    }
}
