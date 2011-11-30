package com.xiaoxiang.util;

import org.jtester.testng.JTester;
import org.testng.annotations.*;

/**
 * 字符功能测试
 *
 * @author xiang.xiaox
 */
@Test(groups = {"stringUtil"})
public class StringUtilTest extends JTester {

    @BeforeTest
    public void beforeTest(){
        System.out.println("在测试方法运行之前运行:BeforeTest");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("在测试方法之后运行:AfterTest");
    }

    @BeforeGroups
    public void beforeGroups(){
        System.out.println("在测试组之前运行:BeforeGroups");
    }

    @AfterGroups
    public void afterGroups(){
        System.out.println("在测试组之后运行:AfterGroups");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("在测试类之前运行:BeforeClass");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("在测试类之后运行:AfterClass");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("在测试方法之前运行:BeforeMethod");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("在测试方法之后运行:AfterMethod");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("在测试套件之前运行:BeforeSuite");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("在测试套件之后运行:AfterSuite");
    }

    @Test
    public void isEmptyTest() {
        want.bool(StringUtil.isBlank(null)).is(true);
        want.bool(StringUtil.isBlank("")).is(true);
        want.bool(true).notEqualTo(StringUtil.isEmpty("测试"));
    }

    @Test
    public void toStringTest() {
        want.string("").isEqualTo(StringUtil.toString(null));
        want.string("").isEqualTo(StringUtil.toString(""));
        want.string("测试").isEqualTo(StringUtil.toString("测试"));
    }

    @Test(groups = {"regex"})
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
