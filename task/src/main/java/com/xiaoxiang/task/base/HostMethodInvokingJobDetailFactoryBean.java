package com.xiaoxiang.task.base;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 加入主机地址控制
 *
 * @author xiang.xiaox
 */

public class HostMethodInvokingJobDetailFactoryBean extends MethodInvokingJobDetailFactoryBean {
    private static final Logger logger = LoggerFactory.getLogger(HostMethodInvokingJobDetailFactoryBean.class);

    @Override
    public void afterPropertiesSet() throws ClassNotFoundException,
            NoSuchMethodException {
        before();
        super.afterPropertiesSet();
        //after();//如果需要,请添回相关方法
    }

    //前置调用
    protected void before() {
        logger.debug("任务对象：" + super.getTargetObject());
        logger.debug("任务方法：" + super.getTargetMethod());
        logger.debug("任务参数："
                + ToStringBuilder.reflectionToString(super.getArguments()));
        //如果不一样,则重置任务目标
        if (isNotTaskHost()) {
            logger.debug("noExec()调用！");
            noExec();
        }
    }

    //检查是不是执行任务的主机。
    protected boolean isNotTaskHost() {
        return !isTaskHost();
    }

    //检查是否是执行任务的主机。
    protected boolean isTaskHost() {
        return getTaskHost().equals(getHostAddress());
    }

    //取得当前主机的ip地址。如果无法取得，则返回null。
    protected String getHostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("无法取得当前主机的ip地址，任务无法执行！", e);
            return null;
        }
    }

    //获取当前主机地址
    protected String getTaskHost() {
        logger.info("当前主机地址：" + getHostAddress());

        if (isEmptyTaskHost()) {
            logger.warn("没有指定ip，所有任务将不会被执行！");
            throw new IllegalArgumentException("当前主机地址：'" + getHostAddress()
                    + "'没有指定ip，所有任务将不会被执行！");
        }

        logger.info("任务主机地址：" + getTaskObject().getTaskHost());

        return getTaskObject().getTaskHost();
    }

    //指定运行任务的主机ip地址是否为空。
    protected boolean isEmptyTaskHost() {
        HostTask taskObj = getTaskObject();
        return (taskObj.getTaskHost() == null || taskObj.getTaskHost().length() == 0);
    }

    //检查任务是否实现了HostTask
    protected void validateObjectType() {
        if (!(super.getTargetObject() instanceof HostTask)) {
            logger.error("Class '" + super.getTargetObject().getClass()
                    + "' should implement the interface HostTask");
            throw new IllegalArgumentException("Class '"
                    + super.getTargetObject().getClass()
                    + "' should implement the interface HostTask");
        }
    }

    //取得执行任务的类。
    protected HostTask getTaskObject() {
        validateObjectType();
        return (HostTask) super.getTargetObject();
    }

    //将原方法替换为一个默认的空方法,不执行这个任务
    protected void noExec() {
       /* super.setTargetObject(new DefaultTask());
        super.setTargetMethod(DefaultTask.METHOD_NAME);
        super.setArguments(null);*/
    }

}
