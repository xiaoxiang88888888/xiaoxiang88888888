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
}
