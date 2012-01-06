package com.xiaoxiang.gen.util;

import junit.framework.TestCase;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class StringUtilTest extends TestCase{

    public void testUnderlineToCamel(){
        String str="area_id_name_";
        String newStr = StringUtil.underlineToCamel(str);
        System.out.println(newStr);
        assertEquals("areaIdName",newStr);
    }

    public void testCamelToUnderline(){
        String str="areaIdNameD";
        String newStr = StringUtil.camelToUnderline(str);
        System.out.println(newStr);
        assertEquals("area_id_name_d",newStr);
    }
}
