package com.xiaoxiang.util;

import org.jtester.core.junit.JTester;
import org.testng.annotations.Test;

/**
 * 字符功能测试
 *
 * @author xiang.xiaox
 */

public class StringUtilTest extends JTester {

    @Test
    public void isEmptyTest() {
        assert StringUtil.isEmpty(null);
        assert StringUtil.isEmpty("");
    }

    @Test
    public void toStringTest() {
        want.string("").isEqualTo(StringUtil.toString(null));
        want.string("").isEqualTo(StringUtil.toString(""));
        want.string("测试").isEqualTo(StringUtil.toString("测试"));
    }

    @Test
    public void regexTest() {
        want.bool(StringUtil.regex("", "")).isEqualTo(true);
        want.bool(StringUtil.regex("", "")).isEqualTo(true);
        want.bool(StringUtil.regex("", "")).isEqualTo(true);
    }

    @Test
    public void encodeTest() {
        want.string("").isEqualTo(StringUtil.encode(""));
        want.string("").isEqualTo(StringUtil.encode(null));
        want.string("^8096^7965xiaoxiang~20~7e~21~3c~3e~40~23~24~25~5e~26~2a~28~29~2d~5f~2b~3d~7c~7b~7d~22")
                .isEqualTo(StringUtil.encode("肖祥xiaoxiang ~!<>@#$%^&*()-_+=|{}\""));
    }

    @Test
    public void decodeTest() {
        want.string("").isEqualTo(StringUtil.decode(""));
        want.string("").isEqualTo(StringUtil.decode(null));
        want.string("肖祥xiaoxiang ~!<>@#$%^&*()-_+=|{}\"")
                .isEqualTo(StringUtil.decode("^8096^7965xiaoxiang~20~7e~21~3c~3e~40~23~24~25~5e~26~2a~28~29~2d~5f~2b~3d~7c~7b~7d~22"));
    }

    @Test
    public void getUUIDTest() {
        want.number(32).isEqualTo(StringUtil.getUUID().length());
    }


}
