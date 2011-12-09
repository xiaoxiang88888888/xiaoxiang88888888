package com.xiaoxiang.maven.properties;

import junit.framework.TestCase;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class PropertiesUtilTest extends TestCase {

    public void testParse() {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        System.out.println(propertiesUtil.getProps());
        System.out.println(propertiesUtil.getPropPath());
        System.out.println(propertiesUtil.getProps());
    }
}
