package com.xiaoxiang.exception;


import java.util.ArrayList;
import java.util.List;

/**
 * 自定义异常基类
 *
 * @author xiaoxiang
 */
public class BaseException extends Exception {
    static String classPath = BaseException.class.toString();

    /**
     * 根异常
     */
    protected Throwable rootCause = null;
    /**
     * 异常数组
     */
    private List exceptions = new ArrayList();
    /**
     * 窗口类型
     */
    private String windowType;
    /**
     * 导入地址
     */
    private String forwardPath;
    /**
     * 消息健
     */
    private String messageKey = null;
    /**
     * 消息
     */
    private Object[] messageArgs = null;

    public Object[] getMessageArgs() {
        return messageArgs;
    }

    public void setMessageArgs(Object[] messageArgs) {
        this.messageArgs = messageArgs;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public BaseException(String strMessage) {
        super(strMessage);
    }

    public BaseException(Throwable e) {
        super(e);
        rootCause = e;
    }

    public BaseException(Throwable e, String message) {
        super(message);
        rootCause = e;
    }

    public BaseException(Throwable e, String message, String forwardPath) {
        super(message);
        rootCause = e;
        this.forwardPath = forwardPath;
    }

    public BaseException() {
    }

    public String getWindowType() {
        return windowType;
    }

    public void setWindowType(String windowType) {
        this.windowType = windowType;
    }

    public String getForwardPath() {
        return forwardPath;
    }

    public void setForwardPath(String forwardPath) {
        this.forwardPath = forwardPath;
    }

    public String getTypeDiscription() {
        return "\u51FA\u73B0\u7CFB\u7EDF\u6CA1\u6709\u660E\u786E\u5B9A\u4E49\u7684\u5F02\u5E38\uFF0C";
    }

    public Throwable getRootCause() {
        return rootCause;
    }

    public void setRootCause(Throwable rootCause) {
        this.rootCause = rootCause;
    }

    public List getExceptions() {
        return exceptions;
    }

    public void addException(BaseException ex) {
        exceptions.add(ex);
    }
}
