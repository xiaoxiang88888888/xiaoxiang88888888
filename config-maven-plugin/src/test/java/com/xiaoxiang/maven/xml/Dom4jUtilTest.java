package com.xiaoxiang.maven.xml;

import junit.framework.TestCase;

import java.io.File;

/**
 * 测试
 *
 * @author xiang.xiaox
 */

public class Dom4jUtilTest extends TestCase {
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testParse() {
        Dom4jUtil dom4jUtil = new Dom4jUtil();
        System.out.println(dom4jUtil.getPropDefaultMap());
        System.out.println(dom4jUtil.getPropDescMap());
        System.out.println(dom4jUtil.getTemplateMap());
        System.out.println(dom4jUtil.getTemplateEncodeMap());
    }

    public void testParse2() {
        Dom4jUtil dom4jUtil = new Dom4jUtil("META-INF" + File.separator + "autoconfig" + File.separator + "autoConfig2.xml",true);
        System.out.println(dom4jUtil.getPropDefaultMap());
        System.out.println(dom4jUtil.getPropDescMap());
        System.out.println(dom4jUtil.getTemplateMap());
        System.out.println(dom4jUtil.getTemplateEncodeMap());
    }
}
