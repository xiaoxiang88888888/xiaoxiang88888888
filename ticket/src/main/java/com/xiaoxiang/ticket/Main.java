package com.xiaoxiang.ticket;

import com.xiaoxiang.ticket.util.ConstantUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class Main {
    public void getApplication() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean/spring-bean.xml");
        ConstantUtil constantUtil = (ConstantUtil)context.getBean("constant");
        System.out.println(constantUtil);
    }
}
