package com.xiaoxiang.util;

import com.xiaoxiang.model.Demo;
import mockit.Mock;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;

/**
 * JMockit的构造函数的mock,
 *
 * @author xiang.xiaox
 */

public class JMockitConstructorTest extends JTester {
    //不能有这样的代码,否则会mock失败,
    /*@Mocked
    private Demo demo;*/



    @Test
    public void constructorTest(){
        //new MockUp<Demo>  不是new MockUp(Demo)
        new MockUp<Demo>(){
           // 变量名必须为it,相当于this 关键字.
           Demo it;
           @SuppressWarnings("unused")
           @Mock
           public void $init() {
               it.setDemoName("9");
           }
        };
        want.string(new Demo().getDemoName()).isEqualTo("9");
    }

}
