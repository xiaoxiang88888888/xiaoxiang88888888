package com.xiaoxiang.util;

import com.xiaoxiang.model.Demo;
import mockit.Mocked;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;

/**
 * JMockit的私有函数的mock,
 *
 * @author xiang.xiaox
 */

public class JMockitPrviateTest extends JTester {
    @Mocked(methods = "getPrivateStr")
    private Demo demo;


    @Test
    public void getStrTest() {
        /**
         * 录制私有方法getPrivateStr 返回值
         */
        new Expectations() {
            {
                /*
                * 该方法模拟实例上调用非访问的方法(private 方法)
                * @param mock 需要mock 的实例对象
                * @param methodName 预期的方法的名称
                * @param methodArgs 调用零或多个非空预期的参数值
                */
                //无参私有方法调用
                invoke(demo, "getPrivateStr");
                result = false;
                //有参私有方法调用,withAny(..),是String 类型的任意字符串
                invoke(demo, "getPrivateStr");
                result = true;
            }
        };
        //返回值正确

        want.string(demo.getStr()).isEqualTo("is null");
        want.string(demo.getStr()).isEqualTo("is not null");

    }

}
