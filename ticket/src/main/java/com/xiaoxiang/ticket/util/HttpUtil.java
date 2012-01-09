package com.xiaoxiang.ticket.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 执行一个HTTP GET请求，返回请求响应的HTML
     *
     * @param url         请求的URL地址
     * @param queryString 请求的查询参数,可以为null
     * @param charset     字符集
     * @param pretty      是否美化
     * @return 返回请求响应的HTML
     */
    public static String doGet(String url, String queryString, String charset, boolean pretty) throws IOException {
        StringBuilder responseStr = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        HttpResponse response;
        HttpEntity entity;
        try {
            if (!StringUtil.isEmpty(queryString)) {
                //对get请求参数做了http请求默认编码，好像没有任何问题，汉字编码后，就成为%式样的字符串
                // get.setURI(url);
            }
            response = client.execute(get);
            entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), charset));

                String line;
                while ((line = reader.readLine()) != null) {
                    if (pretty)
                        responseStr.append(line).append(System.getProperty("line.separator"));
                    else
                        responseStr.append(line);
                }
                reader.close();
            }
        } catch (Exception e) {
            logger.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e);
        } finally {
            //关闭连接
            client.getConnectionManager().shutdown();
        }
        return responseStr.toString();
    }

    /**
     * 执行一个HTTP POST请求，返回请求响应的HTML
     *
     * @param url     请求的URL地址
     * @param params  请求的查询参数,可以为null
     * @param charset 字符集
     * @param pretty  是否美化
     * @return 返回请求响应的HTML
     */
    public static String doPost(String url, Map<String, String> params, String charset, boolean pretty) throws IOException {
        StringBuilder responseStr = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        HttpResponse response;
        HttpEntity entity;
        //设置Http Post数据
        if (params != null) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                //不能使用下需注释的方式传入参数
                //client.getParams().setParameter(entry.getKey(), entry.getValue());
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        }
        try {
            response = client.execute(post);
            entity = response.getEntity();
            //responseStr = EntityUtils.toString(response.getEntity());
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (pretty) {
                        responseStr.append(line).append(System.getProperty("line.separator"));
                    } else {
                        responseStr.append(line);
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            logger.error("执行HTTP Post请求 " + url + " 时，发生异常！", e);
        } finally {
            //关闭连接
            client.getConnectionManager().shutdown();
        }
        return responseStr.toString();
    }
}
