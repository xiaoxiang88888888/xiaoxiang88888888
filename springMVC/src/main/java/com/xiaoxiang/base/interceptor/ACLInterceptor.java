package com.xiaoxiang.base.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class ACLInterceptor extends HandlerInterceptorAdapter {
    protected static final Logger logger = LoggerFactory.getLogger(ACLInterceptor.class);


    /**
     * default servlet prefix
     */
    private static final String DEFAULT_SERVLET_PREFIX = "/servlet";

    /**
     * will be injected from context configuration file
     */
    /* private AclService service;*/
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        /*  String currentUri = request.getRequestURI();
       boolean isAccessible = true;
       //only intercept for annotated business controllers
       Controller c = AnnotationUtils.findAnnotation(handler.getClass(), Controller.class);
       if (c != null) {
           String[] grantedResource = getGrantedResource(request);
           if (grantedResource == null || grantedResource.length == 0) {
               throw new AccessDeniedException("No resource granted");
           }
           isAccessible = service.isAccessible(grantedResource, currentUri);
           if (logger.isDebugEnabled()) {
               logger.debug("ACL interceptor excueted. Accessible for Uri[" + currentUri + "] = " + isAccessible);
           }
           //if isAccessible==true, throw custom AccessDeniedException
           if (!isAccessible) throw new AccessDeniedException();
       }
       return isAccessible;*/
        return false;

    }

    /**
     * transfer the original Uri to resource Uri
     * e.g.:
     * original Uri: /servlet/hotels/ajax
     * target Uri  : /hotels/ajax
     *
     * @param originalUri
     * @return
     */
    protected String getUri(String originalUri) {
        return originalUri.substring(DEFAULT_SERVLET_PREFIX.length());
    }

    /**
     * Get the granted resource from session
     *
     * @param request
     * @return
     */
    protected String[] getGrantedResource(HttpServletRequest request) {
        //get the resources from current session
        //String[] uriResourcePattern = (String[]) request.getSession().getAttribute("uriResourcePattern");
        //TODO: mock data here
        String[] uriResourcePattern = new String[]{"/**"};

        return uriResourcePattern;
    }
}
