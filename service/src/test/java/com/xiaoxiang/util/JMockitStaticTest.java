package com.xiaoxiang.util;

import org.jtester.testng.JTester;

/**
 * JMockit的构造函数的mock,
 *
 * @author xiang.xiaox
 */

public class JMockitStaticTest extends JTester {
    //不能有这样的代码,否则会mock失败,
    /*@Mocked
    private Demo demo;*/



   /* @Test
    public void staticTest(){
        //new MockUp<Demo>  不是new MockUp(Demo)
        new MockUp<Demo>(){
           @SuppressWarnings("unused")
           @Mock
           public void $clinit() {
              Demo.staticName="test";
           }
        };
        want.string(Demo.getStaticName()).isEqualTo("test");
    }
*/
}
