package com.xiaoxiang.ticket.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author xiaoxiang
 */
public class StringUtil {
    static String classPath = StringUtil.class.toString();

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
     * 字符串输出(为空也显示）
     *
     * @param args 原字符串
     * @return 字符串
     */
    public static String toString(String args) {
        if (args != null)
            return args;
        else
            return "";
    }

    /**
     * 验证 正则表达式
     *
     * @param regex 规则
     * @param value 字符串
     * @return boolean
     */
    public static boolean regex(String regex, String value) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        return m.find();
    }

    /**
     * 将中文参数转换
     *
     * @param str
     * @return
     */
    public static String encode(String str) {
        if (str == null)
            return "";
        int str_length = str.length();
        StringBuffer stringbuffer = new StringBuffer(str_length);
        for (int j = 0; j < str_length; j++) {
            char c;
            if ((c = str.charAt(j)) > '\377') {
                String str1 = Integer.toString(c, 16);
                stringbuffer.append('^');
                for (int k = str1.length(); k < 4; k++)
                    stringbuffer.append('0');

                stringbuffer.append(str1);
            } else if (c < '0' || c > '9' && c < 'A' || c > 'Z' && c < 'a' || c > 'z') {
                String str2 = Integer.toString(c, 16);
                stringbuffer.append('~');
                for (int l = str2.length(); l < 2; l++)
                    stringbuffer.append('0');

                stringbuffer.append(str2);
            } else {
                stringbuffer.append(str.charAt(j));
            }
        }

        return stringbuffer.toString();
    }


    /**
     * 将转换后的中文参数重新解码生成正常的中文
     *
     * @param str
     * @return
     */
    public static String decode(String str) {
        if (str == null)
            return "";
        int str_length = str.length();
        StringBuffer stringbuffer = new StringBuffer();
        for (int j = 0; j < str_length; j++) {
            char c;
            switch (c = str.charAt(j)) {
                case 126: // '~'
                    String str1 = str.substring(j + 1, (j + 4) - 1);
                    stringbuffer.append((char) Integer.parseInt(str1, 16));
                    j += 2;
                    break;

                case 94: // '^'
                    String str2 = str.substring(j + 1, j + 4 + 1);
                    stringbuffer.append((char) Integer.parseInt(str2, 16));
                    j += 4;
                    break;

                default:
                    stringbuffer.append(c);
                    break;
            }
        }

        return stringbuffer.toString();
    }

    /**
     * 清除过滤掉 内容中的html标记   一般
     *
     * @param content
     * @return
     */
    public static String filterHtmlTag(String content) {
        boolean flag = true;
        StringBuffer stringBuffer = new StringBuffer(2048);
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '<') {
                flag = false;
                continue;
            }
            if (content.charAt(i) == '>') {
                flag = true;
                continue;
            }
            if (content.charAt(i) == '\n') {
                flag = true;
                continue;
            }
            if (flag) {
                stringBuffer.append(content.charAt(i));
            }
        }
        return stringBuffer.toString().replaceAll("&nbsp;", "").replaceAll("&ldquo;", "").replaceAll("&rdquo;", "").replaceAll("\n", "");
    }

    /**
     * 清除过滤掉 内容中的html标记  根据正则表达式
     * String   yourRegEx   =   "( <\\s*[a-zA-Z][^> ]*> )|( </\\s*[a-zA-Z][^> ]*> ) ";//这个就是对应的去掉HTML标记的正则表达式
     *
     * @param content
     * @param regex
     * @return
     */
    public static String filterHtmlTagRegex(String content, String regex) {
        if (content == null) content = "";
        String resultstr = " ";
        if (regex == null) regex = "(<\\s*[a-zA-Z][^>]*>)|(</\\s*[a-zA-Z][^>]*>)";//这个就是对应的去掉HTML标记的正则表达式
        try {
            Pattern p = Pattern.compile(regex);//   设置比较模式
            Matcher m = p.matcher(content);
            resultstr = p.matcher(content).replaceAll(resultstr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultstr;
    }

    /**
     * 清除过滤掉 内容中的html标记  根据正则表达式
     *
     * @param content
     * @return
     */
    public static String filterHtmlTagRegex(String content) {
        String regex = "(<\\s*[a-zA-Z][^>]*>)|(</\\s*[a-zA-Z][^>]*>)";//这个就是对应的去掉HTML标记的正则表达式
        return filterHtmlTagRegex(content, regex);
    }

    /**
     * 获得UUID，
     *
     * @return
     */
    public static String getUUID() {
        String str = UUID.randomUUID().toString();
        //去掉-
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
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

    /**
     * 得到某日期的yyyy-MM-dd格式串
     * @param date
     * @param format
     * @return
     */
    public static String getDateTime(Date date, String format) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        }
    }

    public static void main(String[] args) {
        String content = "<b font dfdf><font</b>";
        content = "增加了一条菜单记录          属性如下----------   &#10; <br/> &nbsp <br/><b>存储编号：</b>4028e95220b235240120b23685da0036   &#10;<br/>   <b>资源：</b>   &#10;<br/>   <b>应用系统：</b>4028e4d51fa6fbc8011fa736f32a0008   &#10;<br/>   <b>上级菜单：</b>   &#10;<br/>   <b>菜单名称：</b><xiaixanfg   &#10;<br/>   <b>目标：</b>1   &#10;<br/>   <b>图标：</b>resource/icons/0.gif   &#10;<br/>   <b>是否隐藏：</b>2   &#10;<br/>   <b>菜单类型：</b>1   &#10;<br/>   <b>排序：</b>1066   &#10;<br/>   <b>描述：</b>&nbsp;xiaixanfg肖祥   &#10;<br/>   <b>是否展开：</b>2   &#10;<br/>   <b>页签标题：</b>xiaixanfg>   &#10;<br/>   ";
        String regex = "(<\\s*[a-zA-Z]*>)|(</\\s*[a-zA-Z]*>)";
        System.out.println(filterHtmlTag(content));
        System.out.println(filterHtmlTagRegex(content));
        System.out.println(filterHtmlTagRegex(content, regex));
    }

}
