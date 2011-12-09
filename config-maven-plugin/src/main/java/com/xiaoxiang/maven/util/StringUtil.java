package com.xiaoxiang.maven.util;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class StringUtil {

    /**
     * 判断字符串为空
     *
     * @param str 字符串
     * @return true(空) false(非空)
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str) || "null".equals(str) || str.length() == 0;
    }

    /**
     * 将属性的名称中的.替换成_,因模板渲染变量中不能出现.
     *
     * @param str
     * @return
     */
    public static String replace(String str) {
        if (str == null) return null;
        return str.replace(".", "_");
    }

    /**
     * 将属性的名称中的.替换成_,因模板渲染变量中不能出现.
     *
     * @param str
     * @return
     */
    public static String replaceOther(String str) {
        if (str == null) return null;
        return str.replace("_", ".");
    }
}
