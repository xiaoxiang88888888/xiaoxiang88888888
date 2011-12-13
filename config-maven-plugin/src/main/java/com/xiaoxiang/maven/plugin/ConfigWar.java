package com.xiaoxiang.maven.plugin;

import com.xiaoxiang.maven.properties.PropertiesUtil;
import com.xiaoxiang.maven.template.VelocityUtil;
import com.xiaoxiang.maven.util.StringUtil;
import com.xiaoxiang.maven.xml.Dom4jUtil;
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
     * 自定义的配置文件,用于指定生成哪些文件
     */
    String configPath;

    /**
     * 模块类型 freemarker 或者 velocity
     */
    String templateType;

    /**
     * 文件编码
     */
    String encoding;

    /**
     * 要处理的文件路径
     */
    String prefixDir;


    public void execute() {
        genFiles();
    }

    /**
     * 生成文件
     */
    private void genFiles() {
        Dom4jUtil dom4jUtil;
        String templatePath = prefixDir;
        logger.info("prefixDir="+prefixDir);
        if (StringUtil.isEmpty(configPath)) {
            dom4jUtil = new Dom4jUtil(prefixDir);
        } else {
            dom4jUtil = new Dom4jUtil(templatePath, configPath, false);
        }
        PropertiesUtil propertiesUtil;
        if (StringUtil.isEmpty(propertiesPath)) {
            propertiesUtil = new PropertiesUtil();
        } else {
            propertiesUtil = new PropertiesUtil(propertiesPath);
        }
        if ("velocity".equalsIgnoreCase(templateType)) {
            VelocityUtil velocityUtil = new VelocityUtil();
            velocityUtil.setDom4jUtil(dom4jUtil);
            velocityUtil.setPropertiesUtil(propertiesUtil);
            velocityUtil.setClassPath(false);
            velocityUtil.setTemplatePath(templatePath);
            velocityUtil.init();
            velocityUtil.mergeTemplate(templatePath);
        } else {
            logger.error("配置的模板类型 " + templateType + " 不对!");
        }
        //将新的配置项写入autoConfig.properties文件中
        propertiesUtil.close(dom4jUtil.getPropDescMap());
    }


    public void setPropertiesPath(String propertiesPath) {
        this.propertiesPath = propertiesPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setPrefixDir(String prefixDir) {
        this.prefixDir = prefixDir;
    }
}
