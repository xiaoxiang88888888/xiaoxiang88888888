package com.xiaoxiang.task.base;

/**
 * 加入主机地址控制 ,相关任务都需要实现这个接口
 *
 * @author xiang.xiaox
 */

public interface HostTask {

    /**
     * 设置执行任务的主机ip地址。
     *
     * @param taskHost 主机ip地址。
     * @author yuanchu.xiaoyc
     */
    public void setTaskHost(String taskHost);

    /**
     * 取得执行任务的主机ip地址。
     *
     * @return
     * @author yuanchu.xiaoyc
     */
    public String getTaskHost();
}
