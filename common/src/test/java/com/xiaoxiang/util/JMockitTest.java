package com.xiaoxiang.util;

import com.xiaoxiang.model.Demo;
import mockit.Delegate;
import mockit.Mocked;
import mockit.NonStrict;
import mockit.NonStrictExpectations;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

/**
 * JMockit的一些使用方式说明
 *
 * @author xiang.xiaox
 */

public class JMockitTest extends JTester{
    @Mocked
    private Demo demo;

   @NonStrict
    private Demo demo2;

    @Test
    public void remarkTest() {
        System.out.println("demo before="+demo.getRemark());
        System.out.println("demo2 before="+demo2.getRemark());
        new NonStrictExpectations(demo) {
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
        System.out.println("demo after="+demo.getRemark());
        System.out.println("demo2 after="+demo2.getRemark());
    }


}
