package com.xiaoxiang.util;

import com.xiaoxiang.model.Demo;
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
                result = new Delegate() {
                    @SuppressWarnings("unused")
                    public String getRemark() {
                        return "remark";
                    }
                };
            }
        };
        System.out.println("demo after=" + demo.getRemark());
        System.out.println("demo2 after=" + demo2.getRemark());
    }

   /* @Test
    public void nameTest(final Demo demo) {
        System.out.println("demo before=" + demo.getDemoName());
        new NonStrictExpectations(2) {//传不传demo,测试了一下,好像没有关系
            {
                demo.setDemoName(anyString);
                
                demo.getRemark();
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
    }*/

}
