package com.xiaoxiang.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.text.Collator;
import java.util.Locale;

/**
 * 汉字转拼音,主要用于排序
 *
 * @author xiang.xiaox
 */

public class PinYinUtil {
    private static HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();

    static {
        // 不需要声调
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // 遇到“ü” 显示成V
        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        // 所有输出大写
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    }

    /**
     * 汉字转化为拼音
     * 注意:加入一个unicode符号(FFFFD)确保汉字在其它字符类型后面
     * 用于排序
     *
     * @param inputString
     * @return
     */
    public static String getPingYin(String inputString) {
        char[] input = inputString.trim().toCharArray();
        String output = "";

        try {
            for (int i = 0; i < input.length; i++) {
                if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) { //如果是汉字
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], outputFormat);
                    output += "�" + temp[0];//加入一个unicode符号(FFFFD)确保汉字在其它字符类型后面
                } else {//否则原样输出
                    output += java.lang.Character.toString(input[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

        return output;
    }

    /**
     * 汉字转化为拼音后,进行比较
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int compare(String str1, String str2) {
        return Collator.getInstance(Locale.CHINESE).compare(getPingYin(str1),
                getPingYin(str2));
    }


    /**
     * 将汉字转换为全拼
     *
     * @param src
     * @return String
     */
    public static String getPinYin(String src) {
        char[] t1;
        t1 = src.toCharArray();
        String[] t2;
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断能否为汉字字符
                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中
                    t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
                } else {
                    // 如果不是汉字字符，间接取出字符并连接到字符串t4后
                    t4 += Character.toString(t1[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return t4;
    }

    /**
     * 提取每个汉字的首字母
     *
     * @param str
     * @return String
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            // 提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    /**
     * 将字符串转换成ASCII码
     *
     * @param cnStr
     * @return String
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        // 将字符串转换成字节序列
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // 将每个字符转换成ASCII码
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

}
