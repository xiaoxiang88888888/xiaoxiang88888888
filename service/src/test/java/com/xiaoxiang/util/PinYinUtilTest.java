package com.xiaoxiang.util;

import org.jtester.testng.JTester;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.Test;

/**
 * 测试拼单
 *
 * @author xiang.xiaox
 */

public class PinYinUtilTest extends JTester{

    @Test
    @Rollback(true)
    public void PinYinUtil(){
        want.string("�xiao�xiang").isEqualTo(PinYinUtil.getPingYin("肖祥"));
        want.string("�xiao�xiang").isEqualTo(PinYinUtil.getPingYin("肖祥"));
    }
}
