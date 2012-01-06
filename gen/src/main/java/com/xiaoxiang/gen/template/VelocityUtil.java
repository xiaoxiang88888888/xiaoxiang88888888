package com.xiaoxiang.gen.template;

import com.xiaoxiang.gen.pojo.Table;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * velocity渲染
 *
 * @author xiang.xiaox
 */

public class VelocityUtil {
    private final Logger logger = LoggerFactory.getLogger(VelocityUtil.class);

    private VelocityEngine velocityEngine;
    private VelocityContext context;

    private Table table;

    private String encoding;

    /**
     * 初始化引挚
     */
    public void init() {
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        //设置编码
        velocityEngine.setProperty(VelocityEngine.INPUT_ENCODING, "UTF-8");
        velocityEngine.setProperty(VelocityEngine.OUTPUT_ENCODING, "UTF-8");
        initContext();
    }

    /**
     * 上下文中加入相关变量
     */
    private void initContext() {
        context = new VelocityContext();

        //如果为空
        if (null == table) {
            return;
        }
        context.put("table", table);
    }

    /**
     * 生成模板
     *
     * @param path
     * @param descPath
     * @throws java.io.IOException
     */
    public void mergeTemplate(String path, String name, String descPath) throws IOException {
        if (null == path || null == descPath) {
            return;
        }
        if (encoding == null) encoding = "UTF-8";
        Template template = velocityEngine.getTemplate(path, encoding);
        if (null == template) {
            return;
        }
        Writer pw = null;

        try {
            File file = new File(path, name);
            if (!file.getParentFile().exists()) {
                boolean result = file.getParentFile().mkdirs();
                if (result) {
                    logger.info("生成到目录:" + file.getAbsolutePath());
                } else {
                    logger.info("生成失败:" + file.getAbsolutePath());
                }
            }
            pw = new OutputStreamWriter(new FileOutputStream(file), encoding);
            template.merge(context, pw);
            pw.flush();
        } catch (FileNotFoundException e) {
            logger.error("模板生成有错", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
