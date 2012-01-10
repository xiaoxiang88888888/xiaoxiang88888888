package com.xiaoxiang.ticket;

import com.xiaoxiang.ticket.util.ConstantUtil;
import com.xiaoxiang.ticket.util.HttpUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class Main {

    public static void start() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean/spring-bean.xml");
        ConstantUtil constant = (ConstantUtil) context.getBean("constant");
        HttpUtil http = (HttpUtil) context.getBean("http");
        Ticket ticket = (Ticket) context.getBean("ticket");
        System.out.println(constant);
        System.out.println(http);
        System.out.println(ticket);
        //开始订票
        ticket.task();
    }

    public static void main(String[] args) throws Exception{
        start();
    }
}
