package com.xiaoxiang.cache;

/**
 * 缓存接口 实现可能是本地缓存也可能是memcached,或redis等类似内存缓存服务器
 *
 * @author xiang.xiaox
 */

public interface Cache {

    /**
     * 获取缓存中对应key的值
     *
     * @param key
     */
    public String get(String key);

    /**
     * 设置缓存中对应key的值
     * 过期时间,默认为一天
     *
     * @param key
     * @param value
     */
    public void set(String key, String value);

    /**
     * 设置缓存中对应key的值
     *
     * @param key
     * @param value
     * @param expire 过期时间,以秒为单位
     */
    public void set(String key, String value, int expire);

    /**
     * 删除缓存中对应key
     *
     * @param key
     */
    public void del(String key);
}
