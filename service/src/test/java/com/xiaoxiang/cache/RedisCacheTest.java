package com.xiaoxiang.cache;

import com.xiaoxiang.util.JsonUtil;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

/**
 * Redis 缓存测试
 *
 * @author xiang.xiaox
 */
@SpringApplicationContext({"bean/spring-cache.xml"})
public class RedisCacheTest extends JTester {
    @SpringBeanByType
    private CacheSupport cacheSupport;

    @Test
    public void setTest() {
        String key = "0000";
        String value = "9999";
        cacheSupport.cacheTestSet(key, value);
        String result = cacheSupport.cacheTestGet(key);
        result = JsonUtil.getInstance().jsonToObject(result,String.class).toString();
        want.string("9999").isEqualTo(result);
    }

    @Test
    public void setExpireTest() {
        String key = "0000";
        String value = "9999";
        int expire = 2;
        cacheSupport.cacheTestSet(key, value, expire);
        String result = cacheSupport.cacheTestGet(key);
        result = JsonUtil.getInstance().jsonToObject(result,String.class).toString();
        want.string("9999").isEqualTo(result);
        try {
            Thread.sleep(10000);//括号里面的10000代表10000毫秒，也就是10秒，可以该成你需要的时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        want.object(cacheSupport.cacheTestGet(key)).isNull();
    }

    @Test
    public void getTest() {
        String key = "0000";
        String value = "9999";
        cacheSupport.cacheTestSet(key, value);
        String result = cacheSupport.cacheTestGet(key);
        result = JsonUtil.getInstance().jsonToObject(result,String.class).toString();
        want.string("9999").isEqualTo(result);
    }

    @Test
    public void delTest() {
        String key = "0000";
        String value = "9999";
        cacheSupport.cacheTestSet(key, value);
        String result = cacheSupport.cacheTestGet(key);
        result = JsonUtil.getInstance().jsonToObject(result,String.class).toString();
        want.string("9999").isEqualTo(result);
        cacheSupport.cacheTestDel(key);
        want.object(cacheSupport.cacheTestGet(key)).isNull();
    }


}
