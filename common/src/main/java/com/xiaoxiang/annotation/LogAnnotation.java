package com.xiaoxiang.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 日志元数据类 用于操作相关数据库时,记录相关日志操作
 *
 * @author xiang.xiaox
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {

    /**
     * 描述
     */
    String title();

    /**
     * 是否主键
     */
    boolean isPK() default false;

    /**
     * 是否CLOB
     */
    boolean isClob() default false;

    /**
     * 是否BLOB
     */
    boolean isBlob() default false;

    /**
     * 是否记录此属性
     */
    boolean isRecord() default true;

    /**
     * 显示序号
     */
    int orderNo() default 0;

}
