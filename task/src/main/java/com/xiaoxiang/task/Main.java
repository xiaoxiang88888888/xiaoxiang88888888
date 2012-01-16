package com.xiaoxiang.task;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 主类,启动task
 *
 * @author xiang.xiaox
 */
public class Main {
    public static void start() throws Exception {
        //启动任务
        new ClassPathXmlApplicationContext(new String[]{
                "classpath*:bean/spring-datasource.xml",
                "classpath*:bean/spring-area.xml",
                "classpath*:bean/spring-task.xml"});
    }

    public static void main(String[] args) throws Exception {
        start();
    }
}
