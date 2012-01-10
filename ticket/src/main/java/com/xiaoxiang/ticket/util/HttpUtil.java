package com.xiaoxiang.ticket.util;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private ConstantUtil constant;
    private static String tempFilePath = "/httptemp";
    private static Map<String, DefaultHttpClient> _threadHttpClient = new HashMap<String, DefaultHttpClient>();
    private static Map<String, HttpContext> _threadHttpContext = new HashMap<String, HttpContext>();

    public void init() {
        if (constant != null && !StringUtil.isEmpty(constant.getTempFilePath())) {
            tempFilePath = constant.getTempFilePath();
        }
        tempFilePath = this.getClass().getResource("/").getPath() + tempFilePath;
        File tempFilePathFile = new File(tempFilePath);
        if (!tempFilePathFile.exists())
            tempFilePathFile.mkdirs();
    }

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

    /**
     * 打印Cookies信息
     *
     * @param httpContext
     */
    public static void printCookies(HttpContext httpContext) {
//		CookieStore cookieStore = (CookieStore) httpContext.getAttribute(ClientContext.COOKIE_STORE);
//		List<Cookie> cookies = cookieStore.getCookies();
//		if (cookies.isEmpty()) {
//			System.out.println("None");
//		  } else {
//		for (int i = 0; i < cookies.size(); i++) {
//			System.out.println("- " + cookies.get(i).toString());
//			}
//		}
    }

    /**
     * 获取请求URL的网页内容
     */
    public static HttpClient createHttpClient() {
        HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
        DefaultHttpClient httpclient = _threadHttpClient.get("httpclient");
        if (httpclient != null) {
            return httpclient;
        }
        httpclient = new DefaultHttpClient();
        _threadHttpClient.put("httpclient", httpclient);

        try {
            TrustManager easyTrustManager = new X509TrustManager() {
                public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
                    //To change body of implemented methods use File | Settings | File Templates.
                }

                public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
                    //To change body of implemented methods use File | Settings | File Templates.
                }

                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];  //To change body of implemented methods use File | Settings | File Templates.
                }
            };

            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[]{easyTrustManager}, null);
            SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
            sf.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
            Scheme sch = new Scheme("https", sf, 443);

            httpclient.getConnectionManager().getSchemeRegistry().register(sch);
        } catch (Exception e) {
            e.printStackTrace();
        }

        httpclient.addRequestInterceptor(new HttpRequestInterceptor() {
            public void process(final HttpRequest request,
                                final HttpContext context) throws HttpException,
                    IOException {
                // if (!request.containsHeader("Accept-Encoding")) {
                // request.addHeader("Accept-Encoding", "gzip");
                // }
                if (!request.containsHeader("Accept")) {
                    request.addHeader("Accept", "*/*");
                }
                if (request.containsHeader("User-Agent")) {
                    request.removeHeaders("User-Agent");
                }
                if (request.containsHeader("Connection")) {
                    request.removeHeaders("Connection");
                }
                request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:8.0) Gecko/20100101 Firefox/8.0");
                request.addHeader("Connection", "keep-alive");
                request.addHeader("Referer", "https://dynamic.12306.cn/otsweb/");
                // request.addHeader("Connection", "close");
            }

        });
        httpclient.addResponseInterceptor(new HttpResponseInterceptor() {
            public void process(final HttpResponse response,
                                final HttpContext context) throws HttpException,
                    IOException {
//				HttpEntity entity = response.getEntity();
            }
        });
        httpclient.setRedirectStrategy(new RedirectUtil());
        return httpclient;
    }

    private static HttpContext getHttpContext(String urlHost, String cookies) {
        HttpContext httpContext = _threadHttpContext.get("httpContext");

        if (httpContext != null) {
            printCookies(httpContext);
            return httpContext;
        }
        httpContext = new BasicHttpContext();
        CookieStore cookieStore = createCookieStore(urlHost, cookies);
        httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
        _threadHttpContext.put("httpContext", httpContext);
        printCookies(httpContext);
        return httpContext;
    }

    public static void shutdownHttpClient() {
        _threadHttpContext.remove("httpContext");
        DefaultHttpClient httpclient = _threadHttpClient.get("httpclient");
        if (httpclient != null) {
            httpclient.getConnectionManager().shutdown();
        }
        _threadHttpClient.remove("httpclient");
    }

    public static CookieStore createCookieStore(String urlHost, String cookieStr) {
        // Create a local instance of cookie store
        CookieStore cookieStore = new BasicCookieStore();
        if (cookieStr == null || "".equals(cookieStr))
            return cookieStore;
        String domain = urlHost.substring(urlHost.indexOf("//") + 2);
        if (null != cookieStr && !cookieStr.trim().equals("")) {
            String[] cookies = cookieStr.split(";");
            // userCookieList = new AttributeList();
            for (int i = 0; i < cookies.length; i++) {
                int _i = cookies[i].indexOf("=");
                if (_i != -1) {
                    String name = cookies[i].substring(0, _i);
                    String value = cookies[i].substring(_i + 1);
                    BasicClientCookie _cookie = new BasicClientCookie(name,
                            value);
                    _cookie.setDomain(domain);
                    cookieStore.addCookie(_cookie);
                }
            }
        }
        return cookieStore;
    }

    public static List<NameValuePair> createNameValuePair(String params) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (null != params && !params.trim().equals("")) {
            String[] _params = params.split("&");
            // userCookieList = new AttributeList();
            for (int i = 0; i < _params.length; i++) {
                int _i = _params[i].indexOf("=");
                if (_i != -1) {
                    String name = _params[i].substring(0, _i);
                    String value = _params[i].substring(_i + 1);
                    nvps.add(new BasicNameValuePair(name, value));
                }
            }
        }
        return nvps;
    }

    public static String doGetBody(String url, String cookieStr) {
        url = url.replaceAll("###(.*)", "");
        try {
            String urlEx = url.substring(0, url.lastIndexOf("/"));
            String urlHost = url;
            try {
                urlHost = url.substring(0, url.indexOf("/", 9));
            } catch (Exception e) {
            }

            HttpClient httpclient = createHttpClient();
            HttpContext localContext = getHttpContext(urlHost, cookieStr);
            String resultBody = null;
            int _count = 0;
            String loadUrl = url;
            HttpGet httpget = null;
            while (_count++ < 5) {
                try {
                    logger.info("加载(" + _count + "):" + url + "==>" + loadUrl);
                    localContext.removeAttribute(RedirectUtil.REDIRECT_LOCATIONS);
                    httpget = new HttpGet(loadUrl);

                    HttpResponse response = httpclient.execute(httpget,
                            localContext);
                    String locationUrl = checkLocation(response);
                    if (locationUrl != null) {
                        loadUrl = locationUrl;
                        if (!loadUrl.startsWith("/") && loadUrl.indexOf("://") == -1)
                            loadUrl = urlEx + loadUrl;
                        else if (loadUrl.indexOf("://") == -1) {
                            loadUrl = urlHost + loadUrl;
                        }
                        continue;
                    }
                    if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                        continue;
                    }
                    HttpEntity entity = response.getEntity();
                    // Consume response content
                    if (entity != null) {
                        resultBody = EntityUtils.toString(entity);
                        entity.consumeContent();
                        locationUrl = checkLocation(resultBody);
                        if (resultBody == null) {
                        } else {
                            locationUrl = checkLocation(resultBody);
                            if (locationUrl != null) {
                                loadUrl = locationUrl;
                                if (!loadUrl.startsWith("/"))
                                    loadUrl = urlEx + loadUrl;
                                else if (loadUrl.indexOf("://") == -1) {
                                    loadUrl = urlHost + loadUrl;
                                }
                            } else
                                break;
                        }
                    }

                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (httpget != null)
                        httpget.abort();
                }
            }
            return resultBody;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File doGetFile(String url, String cookieStr) {
        url = url.replaceAll("###(.*)", "");
        String urlHost = url;
        try {
            urlHost = url.substring(0, url.indexOf("/", 9));
        } catch (Exception e) {
        }

        HttpClient httpclient = createHttpClient();
        HttpContext localContext = getHttpContext(urlHost, cookieStr);
        HttpGet httpget = new HttpGet(url);

        HttpResponse response;
        try {
            response = httpclient.execute(httpget, localContext);
            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            String filename = new Date().getTime() + ".temp";
            if (entity != null) {
                BufferedInputStream bis = new BufferedInputStream(entity
                        .getContent());
                File file = new File(tempFilePath + "/" + filename);
                FileOutputStream fs = new FileOutputStream(file);

                byte[] buf = new byte[1024];
                int len = bis.read(buf);
                if (len == -1 || len == 0) {
                    file.delete();
                    file = null;
                    entity.consumeContent();
                    return file;
                }
                while (len != -1) {
                    fs.write(buf, 0, len);
                    len = bis.read(buf);
                }
                fs.close();

                entity.consumeContent();
                return file;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String doPostBody(String url, String params, String cookieStr, String encode, boolean redirt) {
        url = url.replaceAll("###(.*)", "");
        String urlEx = url.substring(0, url.lastIndexOf("/"));
        String urlHost = url;
        try {
            urlHost = url.substring(0, url.indexOf("/", 9));
        } catch (Exception e) {
        }

        HttpClient httpclient = createHttpClient();
        HttpContext localContext = getHttpContext(urlHost, cookieStr);
        int _count = 0;
        String loadUrl = null;
        HttpPost httpost = null;
        String resultBody = null;

        while (_count++ < 5) {
            try {
                logger.info("提交(" + _count + "):" + url);
                httpost = new HttpPost(url);
                httpost.setHeader("Referer", url);

                if (encode == null) {
                    StringEntity stringEntity = new StringEntity(params);
                    httpost.setEntity(stringEntity);
                    httpost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                } else {
                    List<NameValuePair> nvps = createNameValuePair(params);
                    try {
                        httpost.setEntity(new UrlEncodedFormEntity(nvps,
                                encode));
                    } catch (UnsupportedEncodingException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }

                HttpResponse response = httpclient.execute(httpost,
                        localContext);

                String locationUrl = checkLocation(response);
                if (locationUrl != null) {
                    loadUrl = locationUrl;
                    if (!loadUrl.startsWith("/")
                            && loadUrl.indexOf("://") == -1)
                        loadUrl = urlEx + loadUrl;
                    else if (loadUrl.indexOf("://") == -1) {
                        loadUrl = urlHost + loadUrl;
                    }
                    break;
                }
                if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    continue;
                }
                HttpEntity entity = response.getEntity();
                // Consume response content
                if (entity != null) {
                    resultBody = EntityUtils.toString(entity);
                    entity.consumeContent();
                    locationUrl = checkLocation(resultBody);
                    if (resultBody == null) {
                    } else {
                        locationUrl = checkLocation(resultBody);
                        if (locationUrl != null) {
                            loadUrl = locationUrl;
                            if (!loadUrl.startsWith("/"))
                                loadUrl = urlEx + loadUrl;
                            else if (loadUrl.indexOf("://") == -1) {
                                loadUrl = urlHost + loadUrl;
                            }
                        } else
                            break;
                    }
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (httpost != null)
                    httpost.abort();
            }
        }
        if (loadUrl != null && redirt)
            resultBody = doGetBody(loadUrl, null);
        return resultBody;
    }

    public static String doPostBody(String url, byte[] content,
                                    Header[] headers, String cookieStr, String encode, boolean redirt) {
        if (encode == null)
            encode = HTTP.UTF_8;

        String urlEx = url.substring(0, url.lastIndexOf("/"));
        String urlHost = url;
        try {
            urlHost = url.substring(0, url.indexOf("/", 9));
        } catch (Exception e) {
        }
        HttpClient httpclient = createHttpClient();
        HttpContext localContext = getHttpContext(urlHost, cookieStr);
        int _count = 0;
        String loadUrl = null;
        HttpPost httpost = null;
        String resultBody = null;

        while (_count++ < 5) {
            try {
                logger.info("提交(" + _count + "):" + url);
                httpost = new HttpPost(url);

                ByteArrayEntity byteArrayEntity = new ByteArrayEntity(content);
                httpost.setEntity(byteArrayEntity);

                httpost.setHeaders(headers);

                HttpResponse response = httpclient.execute(httpost,
                        localContext);

                String locationUrl = checkLocation(response);
                if (locationUrl != null) {
                    loadUrl = locationUrl;
                    if (!loadUrl.startsWith("/")
                            && loadUrl.indexOf("://") == -1)
                        loadUrl = urlEx + loadUrl;
                    else if (loadUrl.indexOf("://") == -1) {
                        loadUrl = urlHost + loadUrl;
                    }
                    break;
                }
                if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    continue;
                }
                HttpEntity entity = response.getEntity();
                // Consume response content
                if (entity != null) {
                    resultBody = EntityUtils.toString(entity);
                    entity.consumeContent();
                    locationUrl = checkLocation(resultBody);
                    if (resultBody == null) {
                    } else {
                        locationUrl = checkLocation(resultBody);
                        if (locationUrl != null) {
                            loadUrl = locationUrl;
                            if (!loadUrl.startsWith("/"))
                                loadUrl = urlEx + loadUrl;
                            else if (loadUrl.indexOf("://") == -1) {
                                loadUrl = urlHost + loadUrl;
                            }
                        } else
                            break;
                    }
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (httpost != null)
                    httpost.abort();
            }
        }
        if (loadUrl != null && redirt)
            resultBody = doGetBody(loadUrl, null);
        return resultBody;
    }


    public static String doPostBody(String url, Map<String, String> stringBody, Map<String, File> fileBody,
                                    Header[] headers, String cookieStr, String encode, boolean redirt) {
        if (encode == null)
            encode = HTTP.UTF_8;

        String urlEx = url.substring(0, url.lastIndexOf("/"));
        String urlHost = url;
        try {
            urlHost = url.substring(0, url.indexOf("/", 9));
        } catch (Exception e) {
        }
        HttpClient httpclient = createHttpClient();
        HttpContext localContext = getHttpContext(urlHost, cookieStr);
        int _count = 0;
        String loadUrl = null;
        HttpPost httpost = null;
        String resultBody = null;

        while (_count++ < 5) {
            try {
                logger.info("提交(" + _count + "):" + url);
                httpost = new HttpPost(url);
                httpost.setHeaders(headers);

                MultipartEntity reqEntity = new MultipartEntity();
                for (Iterator<Map.Entry<String, File>> it = fileBody.entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry<String, File> fileEntry = it.next();
                    FileBody file = new FileBody(fileEntry.getValue());
                    reqEntity.addPart(fileEntry.getKey(), file);
                }

                for (Iterator<Map.Entry<String, String>> it = stringBody.entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry<String, String> stringEntry = it.next();
                    StringBody str = new StringBody(stringEntry.getValue());
                    reqEntity.addPart(stringEntry.getKey(), str);
                }
                //设置请求
                httpost.setEntity(reqEntity);

                HttpResponse response = httpclient.execute(httpost,
                        localContext);

                String locationUrl = checkLocation(response);
                if (locationUrl != null) {
                    loadUrl = locationUrl;
                    if (!loadUrl.startsWith("/")
                            && loadUrl.indexOf("://") == -1)
                        loadUrl = urlEx + loadUrl;
                    else if (loadUrl.indexOf("://") == -1) {
                        loadUrl = urlHost + loadUrl;
                    }
                    break;
                }
                if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    continue;
                }
                HttpEntity entity = response.getEntity();
                // Consume response content
                if (entity != null) {
                    resultBody = EntityUtils.toString(entity);
                    entity.consumeContent();
                    locationUrl = checkLocation(resultBody);
                    if (resultBody == null) {
                    } else {
                        locationUrl = checkLocation(resultBody);
                        if (locationUrl != null) {
                            loadUrl = locationUrl;
                            if (!loadUrl.startsWith("/"))
                                loadUrl = urlEx + loadUrl;
                            else if (loadUrl.indexOf("://") == -1) {
                                loadUrl = urlHost + loadUrl;
                            }
                        } else
                            break;
                    }
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (httpost != null)
                    httpost.abort();
            }
        }
        if (loadUrl != null && redirt)
            resultBody = doGetBody(loadUrl, null);
        return resultBody;
    }

    /**
     * 检查是否包含链接转向，3种方法<br>
     * <ol>
     * <li>头部包含“location:”或“content-location:”，返回代号302</li>
     * <li>内容部分包含“meta http-equiv=refresh content="2;URL=..."”</li>
     * <li>js脚本刷新，正则为："(?s)<script.{0,50}?>\\s*((document)|(window)|(this))\\.location(\\.href)?\\s*="</li>
     * </ol>
     */
    private static String checkLocation(HttpResponse response) {
        Header[] headers = response.getAllHeaders();
        for (Header header : headers) {
            if (header.getName().equalsIgnoreCase("location")
                    || header.getName().equalsIgnoreCase("content-location")) {
                return header.getValue();
            }
        }
        return null;
    }

    /**
     * 检查是否包含链接转向，3种方法<br>
     * <ol>
     * <li>内容部分包含“meta http-equiv=refresh content="2;URL=..."”</li>
     * <li>js脚本刷新，正则为："(?s)<script.{0,50}?>\\s*((document)|(window)|(this))\\.location(\\.href)?\\s*="</li>
     * </ol>
     */
    private static String checkLocation(String httpBody) {
        String locationUrl = null;
        // 2.
        String bodyLocationStr = "";
        if (httpBody.length() > 5120) {
            bodyLocationStr = httpBody.substring(0, 5120);// 太长则截取部分内容
        } else {
            bodyLocationStr = httpBody;
        }
        bodyLocationStr = bodyLocationStr.replaceAll("<!--(?s).*?-->", "")
                .replaceAll("['\"]", "");// 去除注释和引号部分

        int metaLocation = -1;
        metaLocation = bodyLocationStr.toLowerCase().indexOf("http-equiv=refresh");
        if (metaLocation != -1) {
            String locationPart = bodyLocationStr.substring(metaLocation,
                    bodyLocationStr.indexOf(">", metaLocation));
            metaLocation = locationPart.toLowerCase().indexOf("url");
            if (metaLocation != -1) {
                // 假定url=...是在 > 之前最后的部分
                locationUrl = locationPart.substring(metaLocation + 4,
                        locationPart.length()).replaceAll("\\s+[^>]*", "");
                return locationUrl;
            }
        }
        // 3.
        Matcher locationMath = Pattern
                .compile(
                        "(?s)<script.{0,50}?>\\s*((document)|(window)|(this))\\.location(\\.href)?\\s*=")
                .matcher(httpBody.toLowerCase());
        if (locationMath.find()) {
            String[] cs = httpBody.substring(locationMath.end()).trim().split(
                    "[> ;<]");
            locationUrl = cs[0];
            cs = null;
            return locationUrl;
        }
        // 没有转向
        return null;
    }

    public void setConstant(ConstantUtil constant) {
        this.constant = constant;
    }
}
