package com.xiaoxiang.gen.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 相关字符处理工具类
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
     * 将字符串转为驼峰式. 下划线
     *
     * @param str
     * @return
     */
    public static String underlineToCamel(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        String[] strArray = str.split("_");
        if (strArray.length <= 1) return str;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strArray.length; i++) {
            String tempStr = strArray[i];
            if (i == 0) {
                builder.append(tempStr);
            } else {
                builder.append(Character.toUpperCase(tempStr.charAt(0)) + tempStr.substring(1, tempStr.length()));
            }
        }
        return builder.toString();
    }

    /**
     * 将大写字符串转为下划线.
     *
     * @param str
     * @return
     */
    public static String camelToUnderline(String str) {
        Pattern p = Pattern.compile("[A-Z]");
        if (str == null || str.equals("")) {
            return "";
        }
        StringBuilder builder = new StringBuilder(str);
        Matcher mc = p.matcher(str);
        int i = 0;
        while (mc.find()) {
            builder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
            i++;
        }
        if ('_' == builder.charAt(0)) {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }
}
