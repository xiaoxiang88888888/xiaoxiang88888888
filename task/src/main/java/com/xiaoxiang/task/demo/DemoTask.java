package com.xiaoxiang.task.demo;

import com.xiaoxiang.area.service.AreaService;
import com.xiaoxiang.model.Area;
import com.xiaoxiang.task.base.HostTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class DemoTask implements HostTask {
    private static final Logger logger = LoggerFactory.getLogger(DemoTask.class);

    private AreaService areaService;

    private String taskHost;
    int i = 0;

    public void demo() throws Exception {
        String ip = InetAddress.getLocalHost().getHostAddress();
        //再一次确保
        if (!taskHost.equals(ip)) {
            logger.info("当前机器与配置的任务机不同," + ip + "!=" + taskHost);
            return;
        }
        Area area = areaService.findEntityBykey("01");
        logger.info("area ", area.toString());
        logger.info("demo" + (i++));
    }

    public void setAreaService(AreaService areaService) {
        this.areaService = areaService;
    }

    @Override
    public void setTaskHost(String taskHost) {
        this.taskHost = taskHost;
    }

    @Override
    public String getTaskHost() {
        return taskHost;
    }
}
