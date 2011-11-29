package com.xiaoxiang.util;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

/**
 * 测试拼单
 *
 * @author xiang.xiaox
 */

public class PinYinUtilTest extends JTester{

    @Test
    public void PinYinUtil(){
        want.string("�xiao�xiang").isEqualTo(PinYinUtil.getPingYin("肖祥"));
    }
}
