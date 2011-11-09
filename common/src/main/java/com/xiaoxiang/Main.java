package com.xiaoxiang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试类
 *
 * @author xiang.xiaox
 */

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private List<String> list = new ArrayList<String>();

    @Override
    public String toString() {
        return "435345786肖祥7890890890890890890";
    }

    public List<String> getList() {
        logger.debug("得到debug信息8");
        logger.info("得到info信息8");
        logger.warn("得到warn信息8");
        logger.error("得到error信息8");

        for (int i = 0; i < 10; i++) {
            list.add("次夺" + i);
        }
        return list;
    }
}
