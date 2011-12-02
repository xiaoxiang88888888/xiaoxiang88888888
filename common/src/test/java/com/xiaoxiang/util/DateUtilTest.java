package com.xiaoxiang.util;

import mockit.Mock;
import mockit.Mocked;
import org.jtester.hamcrest.matcher.property.PropertiesArrayMatcher;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 测试日期
 *
 * @author xiang.xiaox
 */
public class DateUtilTest extends JTester {
    @Mocked(methods = "getCurrentDate")
    DateUtil dateUtil;

    @Test
    public void getCurrentDateTest() throws ParseException {
        // Mock 掉java.util.Date 类
        new MockUp<Date>() {
            //这里声明一个"it"对象，可以用于在构造器内部访问被Mock 的对象自身，
            //如同我们常用的"this"关键字
            Date it;

            // 这里对Date 类的构造方法进行Mock
            //指定日期为2011-11-11
            @SuppressWarnings({"unused"})
            @Mock
            public void $init() {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, 2011);
                c.set(2011, Calendar.NOVEMBER, 11);
                it.setTime(c.getTimeInMillis());
            }
        };
        //
        want.object(new Date())
                .is(new PropertiesArrayMatcher(
                        new String[]{"year", "month", "date"},
                        new Object[]{111,
                                Calendar.NOVEMBER, 11}
                )
                );
        Date date = new Date();
        System.out.println(DateUtil.getDateStr(date, "yyyy-MM-dd HH:mm:ss"));
        System.out.println(date.getYear());//2011-1900
        System.out.println(date.getMonth());//month
        System.out.println(date.getDate());//day
    }


    @Test
    public void getCurrentDate2Test() {
        new NonStrictExpectations() {
            {
                DateUtil.getCurrentDate();
                result = mockCalendar().getTime();
            }

        };
        want.string("2011").isEqualTo(DateUtil.getDateStr(DateUtil.getCurrentDate(),"yyyy"));
    }

    public static Calendar mockCalendar() {
        Calendar cal = Calendar.getInstance();
        cal.set(2011, 11, 11, 11, 11, 11);
        return cal;
    }
}
