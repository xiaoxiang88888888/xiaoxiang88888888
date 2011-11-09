package com.xiaoxiang.filter;

import ch.qos.logback.classic.ClassicConstants;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义MDC过滤器
 *
 * @author xiang.xiaox
 */

public class MDCFilter implements Filter {
    public final static String REQUEST_REMOTE_HOST_MDC_KEY = "remoteHost";
    public final static String REQUEST_USER_AGENT_MDC_KEY = "userAgent";
    public final static String REQUEST_REQUEST_URI = "requestURI";
    public final static String REQUEST_QUERY_STRING = "queryString";
    public final static String REQUEST_REQUEST_URL = "requestURL";
    public final static String REQUEST_X_FORWARDED_FOR = "xForwardedFor";
    public final static String REQUEST_METHOD = "method";

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
        //nothing
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        insertIntoMDC(request);
        try {
            chain.doFilter(request, response);
        } finally {
            clearMDC();
        }
    }

    void insertIntoMDC(ServletRequest request) {
        MDC.put(REQUEST_REMOTE_HOST_MDC_KEY, request.getRemoteHost());
        if (request instanceof HttpServletRequest) {
            HttpServletRequest hsReq = (HttpServletRequest) request;
            MDC.put(REQUEST_REQUEST_URI, hsReq.getRequestURI());
            StringBuffer requestURL = hsReq.getRequestURL();
            if (requestURL != null) {
                MDC.put(REQUEST_REQUEST_URL, requestURL.toString());
            }
            MDC.put(REQUEST_QUERY_STRING, hsReq.getQueryString());
            MDC.put(REQUEST_USER_AGENT_MDC_KEY, hsReq.getHeader("User-Agent"));
            MDC.put(REQUEST_X_FORWARDED_FOR, hsReq.getHeader("X-Forwarded-For"));
            MDC.put(REQUEST_METHOD, hsReq.getMethod());
        }

    }

    void clearMDC() {
        MDC.remove(REQUEST_REMOTE_HOST_MDC_KEY);
        MDC.remove(REQUEST_REQUEST_URI);
        MDC.remove(REQUEST_QUERY_STRING);
        MDC.remove(REQUEST_REQUEST_URL);
        MDC.remove(REQUEST_USER_AGENT_MDC_KEY);
        MDC.remove(REQUEST_X_FORWARDED_FOR);
        MDC.remove(REQUEST_METHOD);
    }

    @Override
    public void destroy() {
        //nothing
    }
}
