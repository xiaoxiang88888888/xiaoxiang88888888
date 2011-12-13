package com.xiaoxiang.maven.plugin;

import com.xiaoxiang.maven.war.WarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 说明
 *
 * @author xiang.xiaox
 * @goal configWar
 */

public class ConfigWar {
    private final Logger logger = LoggerFactory.getLogger(ConfigWar.class);


    /**
     * 自定义的属性文件,用于动态生成其它文件的数据源
     */
    String propertiesPath;

    /**
     * 模块类型 freemarker 或者 velocity
     */
    String templateType;

    /**
     * 文件编码
     */
    String encoding;


    public void execute() {
        genFiles();
    }

    /**
     * 生成文件
     */
    private void genFiles() {
        WarUtil.getFiles();
    }
}
