package com.xiaoxiang.task.base;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class DefaultTask implements HostTask {
    public final static String METHOD_NAME="nothing";
    private String taskHost;

    public void nothing() {
       //do_nothing;
    }

    public String getTaskHost() {
        return taskHost;
    }

    public void setTaskHost(String taskHost) {
        this.taskHost = taskHost;
    }
}
