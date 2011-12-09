package com.xiaoxiang.maven.template;

import com.xiaoxiang.maven.plugin.ConfigCompileMojo;
import com.xiaoxiang.maven.properties.PropertiesUtil;
import com.xiaoxiang.maven.util.StringUtil;
import com.xiaoxiang.maven.xml.Dom4jUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Map;

/**
 * velocity渲染
 *
 * @author xiang.xiaox
 */

public class VelocityUtil {
    private final Logger logger = LoggerFactory.getLogger(VelocityUtil.class);

    private VelocityEngine velocityEngine;
    private VelocityContext context;

    private Dom4jUtil dom4jUtil;
    private PropertiesUtil propertiesUtil;

    private boolean isClassPath;
    private String templatePath;

    /**
     * 初始化引挚
     */
    public void init() {
        velocityEngine = new VelocityEngine();
        if (!isClassPath) {
            if (StringUtil.isEmpty(templatePath)) {
                // 如果选择了以文件的方式进行文件加载，但是又没有制定path，则默认到根目录
                templatePath = System.getProperty("user.home");
            }
            velocityEngine.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, templatePath);
        } else {
            velocityEngine.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        }
        initContext();
    }

    /**
     * 上下文中加入相关变量
     */
    private void initContext() {
        context = new VelocityContext();

        Map<String, String> propDefaultMap = dom4jUtil.getPropDefaultMap();
        Map<String, String> propDescMap = dom4jUtil.getPropDescMap();
        //如果为空
        if (null == propDefaultMap) {
            return;
        }
        for (Map.Entry<String, String> entry : propDefaultMap.entrySet()) {
            String key = entry.getKey();
            String defaultValue = entry.getValue();
            String description = propDescMap.get(key);
            String value = propertiesUtil.getProps(key, defaultValue, description);
            context.put(StringUtil.replace(key), value);
        }
    }

    /**
     * 生成模板
     *
     * @param path
     */
    public void mergeTemplate(String path) {
        Map<String, String> genFiles = dom4jUtil.getTemplateMap();
        Map<String, String> templateEncodeMap = dom4jUtil.getTemplateEncodeMap();

        if (null == genFiles) {
            return;
        }
        for (Map.Entry<String, String> entry : genFiles.entrySet()) {
            Template template = velocityEngine.getTemplate(entry.getKey(), getEncode(templateEncodeMap, entry.getKey()));
            if (null == template) {
                continue;
            }
            PrintWriter pw = null;

            try {
                File file = new File(path, entry.getValue());
                if (!file.getParentFile().exists()) {
                    boolean result = file.getParentFile().mkdirs();
                    if (result) {
                        logger.info("生成到目录:" + file.getAbsolutePath());
                    } else {
                        logger.info("生成失败:" + file.getAbsolutePath());
                    }
                }
                pw = new PrintWriter(new FileOutputStream(file));
                template.merge(context, pw);
            } catch (FileNotFoundException e) {
                logger.error("模板生成有错", e);
            } finally {
                if (pw != null) {
                    pw.close();
                }
            }
        }
    }

    public void mergeTemplate() {
        Map<String, String> genFiles = dom4jUtil.getTemplateMap();
        Map<String, String> templateEncodeMap = dom4jUtil.getTemplateEncodeMap();

        if (null == genFiles) {
            return;
        }

        for (Map.Entry<String, String> entry : genFiles.entrySet()) {
            Template template = velocityEngine.getTemplate(entry.getKey(), getEncode(templateEncodeMap, entry.getKey()));
            if (null == template) {
                continue;
            }
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(new FileOutputStream(new File(ConfigCompileMojo.class.getResource("/").getFile(), entry.getValue())));
                template.merge(context, pw);
            } catch (FileNotFoundException e) {
                logger.error("文件未找到:", e);
            } finally {
                if (pw != null) {
                    pw.close();
                }
            }
        }
    }

    /**
     * 获取相关编码
     *
     * @param templateEncodeMap
     * @param fileStr
     * @return
     */
    private String getEncode(Map<String, String> templateEncodeMap, String fileStr) {
        String charset = templateEncodeMap.get(fileStr);
        if (StringUtil.isEmpty(charset)) return "UTF-8";
        return charset;
    }


    public void setDom4jUtil(Dom4jUtil dom4jUtil) {
        this.dom4jUtil = dom4jUtil;
    }

    public void setPropertiesUtil(PropertiesUtil propertiesUtil) {
        this.propertiesUtil = propertiesUtil;
    }

    public void setClassPath(boolean classPath) {
        isClassPath = classPath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }
}
