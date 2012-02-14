package com.xiaoxiang.gen;

import com.xiaoxiang.gen.jdbc.TableUtil;
import com.xiaoxiang.gen.pojo.Table;
import com.xiaoxiang.gen.template.VelocityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class Main {
    private final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * 需要生成表
     */
    String tableName;

    /**
     * 模板地址
     */
    String templateDir;

    /**
     * 模板名称
     */
    String templateName;

    /**
     * 模块类型 freemarker 或者 velocity
     */
    String templateType;

    /**
     * 文件编码
     */
    String encoding;

    /**
     * 生成文件路径
     */
    String destDir;

    //数据库连接信息
    String driver;
    String url;
    String user;
    String password;


    public void execute() {
        genFiles();
    }

    /**
     * 生成文件
     */
    private void genFiles() {
        String templatePath = templateDir;
        logger.info("templateDir=" + templateDir);
        /* String path = templateDir + "/" + templateName;
        File file = new File(path);
        if (!file.exists()) {
            logger.info(path + ",文件不存在,跳过");
            return;
        }*/
        TableUtil tableUtil = new TableUtil();
        tableUtil.setDriver(driver);
        tableUtil.setUrl(url);
        tableUtil.setUser(user);
        tableUtil.setPassword(password);
        Table table;
        try {
            table = tableUtil.getTable(tableName);
        } catch (Exception e) {
            logger.error("出现异常:", e);
            return;
        }

        if ("velocity".equalsIgnoreCase(templateType)) {
            VelocityUtil velocityUtil = new VelocityUtil();
            velocityUtil.setTable(table);
            velocityUtil.setEncoding(encoding);
            velocityUtil.init();
            try {
                velocityUtil.mergeTemplate(templatePath, templateName, destDir);
            } catch (IOException e) {
                logger.error("发生异常!", e);
                return;
            }
        } else {
            logger.error("配置的模板类型 " + templateType + " 不对!");
        }
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTemplateDir() {
        return templateDir;
    }

    public void setTemplateDir(String templateDir) {
        this.templateDir = templateDir;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getDestDir() {
        return destDir;
    }

    public void setDestDir(String destDir) {
        this.destDir = destDir;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
