package com.xiaoxiang.tool;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class HttpUtilTest extends TestCase {

    public void testPost() throws IOException {
        //String url = "http://blog.csdn.net/tujiyue/article/details/6446121";
        String url = "http://www.xiaoxiang.com:8088/index.jsp";
        Map<String, String> params = new HashMap<String,String>();
        params.put("name","肖祥");
        String str = HttpUtil.doPost(url, params, "UTF-8", true);
        System.out.println(str);
        str = HttpUtil.doPost(url, null, "UTF-8", false);
        System.out.println(str);
    }

    public void testGet() throws IOException {
        String url = "http://www.xiaoxiang.com:8088/index.jsp?name=56";
        String str = HttpUtil.doGet(url, "name=56", "UTF-8", true);
        System.out.println(str);
        str = HttpUtil.doGet(url, null, "UTF-8", false);
        System.out.println(str);
    }
}
