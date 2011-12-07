package com.xiaoxiang.util;

import com.xiaoxiang.model.Demo;
import mockit.Cascading;
import mockit.Delegate;
import mockit.Mocked;
import mockit.NonStrict;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;

/**
 * JMockit的一些使用方式说明
 *
 * @author xiang.xiaox
 */

public class JMockitTest extends JTester {
    //Cascading 级联mock
    @Cascading
    @Mocked
    private Demo demo;

    @NonStrict
    private Demo demo2;

    @Test
    public void remarkTest() {
        System.out.println("demo before=" + demo.getRemark());
        System.out.println("demo2 before=" + demo2.getRemark());
        new NonStrictExpectations(demo) {//传不传demo,测试了一下,好像没有关系

            {

                demo.getRemark();
                //在delegate中指定的方法，只需要参数类型跟被代理的函数一致就可以，方法的名称并不强制要求一样。
                // 但是，如果Delegate中存在2个方法，而且参数类型和被代理函数都一样，
                // 那么就要求方法的名称必须是一致的
                result = new Delegate() {
                    @SuppressWarnings("unused")
                    public String getRemark() {
                        return "remark";
                    }
                };
                //被调用1 次
                times = 1;
                //被调用1-2 次
                //maxTimes = 2;minTimes=1;
            }
        };
        System.out.println("demo after=" + demo.getRemark());
        System.out.println("demo2 after=" + demo2.getRemark());
    }

    @Test
    public void nameTest() {
        System.out.println("demo before=" + demo.getDemoName());
        /**
         * 这种方法，我们需要显式的在@Mocked中指定需要被mock或不被mock的方法，
         * 一种方便的方法是让框架来判断哪些方法需要被mock，而哪些方法不需要被mock。
         * Jmockit提供了这么一个方便之门，你可以在Expectations匿名类的构造参数中指定那些需要被动态mock的类和变量。
         * 这样的话，在Expectations中指定的行为将被mock，不在Expectations中指定的行为将不被mock
         *
         * 匿名类Expectations有一个参数Demo.class，那么jmockit就会认为在匿名类中显式
         * 声明的Demo行为将会被mock，而那些没有声明的行为将调用原始的业务方法
         */
        new NonStrictExpectations(Demo.class) {//传不传demo,测试了一下,好像没有关系

            {
                //demo.setDemoName(anyString);
                demo.getRemark();
                /// Demo的getRemark被代理，不管原来逻辑如何，执行的都是被代理的逻辑
                result = new Delegate() {
                    @SuppressWarnings("unused")
                    public String getRemark() {
                        return demo.getDemoName();
                    }
                };
            }
        };
        demo.setDemoName("xiao2");
        System.out.println("demo after=" + demo.getRemark());
        demo.setDemoName("xiao4");
        System.out.println("demo after=" + demo.getRemark());
    }


}
