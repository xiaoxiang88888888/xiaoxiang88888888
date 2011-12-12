package com.xiaoxiang.maven.xml;

import com.xiaoxiang.maven.util.StringUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用Dom4j解析 xml
 *
 * @author xiang.xiaox
 */
public class Dom4jUtil {
    private final Logger logger = LoggerFactory.getLogger(Dom4jUtil.class);
    //默认的XML配置文件
    private final static String DEFAULT_CONFIG_PATH = "META-INF/autoconfig/autoConfig.xml";
    //相关标签定义
    private final static String TAG_NAME_ROOT = "config"; //顶级节点
    private final static String TAG_NAME_PROPS = "props"; //属性组
    private final static String TAG_NAME_PROP = "prop"; //单个属性
    private final static String TAG_NAME_GENS = "gens"; //生成文件集
    private final static String TAG_NAME_GEN = "gen"; //单个文件
    //dom4j
    private Document document;
    //用户可自定义目录
    private String configPath;
    //相关结果放入map待用,key为属性名称
    private Map<String, String> propDescMap;//属性描述 prop name==>desc
    private Map<String, String> propDefaultMap;//prop name==>default
    private Map<String, String> templateMap;//需要渲染的模板 模板的目录==>生成的目录
    private Map<String, String> templateEncodeMap;//生成模板的编码  模板的目录==>编码

    /**
     * 解析
     *
     * @param path
     * @param isClassPath
     */
    public Dom4jUtil(String templatePath, String path, boolean isClassPath) {
        this.configPath = path;
        InputStream inputStream = null;
        if (!isClassPath) {
            String tempDir =templatePath+path;
            try {
                inputStream = new FileInputStream(new File(tempDir));
                logger.info("configPath = " + (tempDir));
            } catch (FileNotFoundException e) {
                logger.error("错误的配置文件路径:" + (tempDir), e);
            }
        } else {
            logger.info("configPath = " + this.getClass().getResource("/" + configPath).getPath());
            inputStream = this.getClass().getResourceAsStream("/" + configPath);
        }
        parser(inputStream);
    }

    /**
     * 使用绝对路径
     *
     * @param prefix
     */
    public Dom4jUtil(String prefix) {
        this(prefix, DEFAULT_CONFIG_PATH, false);
    }

    /**
     * 默认从classPath加载相关配置文件
     */
    public Dom4jUtil() {
        this("", DEFAULT_CONFIG_PATH, true);
    }

    /**
     * 解析相关文件,并把结果放入map
     *
     * @param inputStream
     */
    public void parser(InputStream inputStream) {
        if (null == inputStream) {
            logger.info("autoConfig.xml 文件不存在!");
            return;
        }
        String templatePath = configPath.substring(0, configPath.lastIndexOf("/") + 1);
        SAXReader reader = new SAXReader();
        propDescMap = new LinkedHashMap<String, String>();
        propDefaultMap = new LinkedHashMap<String, String>();
        templateMap = new LinkedHashMap<String, String>();
        templateEncodeMap = new LinkedHashMap<String, String>();
        try {
            document = reader.read(inputStream);
            Element root = document.getRootElement();
            //String rootDesc = root.attributeValue("description");

            for (Iterator itrProps = root.elementIterator(TAG_NAME_PROPS); itrProps.hasNext(); ) {
                Element propsElement = (Element) itrProps.next();
                for (Iterator itr = propsElement.elementIterator(TAG_NAME_PROP); itr.hasNext(); ) {
                    Element e = (Element) itr.next();
                    propDefaultMap.put(StringUtil.replace(e.attributeValue("name")), e.attributeValue("defaultValue"));
                    propDescMap.put(StringUtil.replace(e.attributeValue("name")), e.attributeValue("description"));
                }
            }

            Element gens = root.element(TAG_NAME_GENS);
            for (Iterator itr = gens.elementIterator(TAG_NAME_GEN); itr.hasNext(); ) {
                Element e = (Element) itr.next();
                templateMap.put(templatePath + e.attributeValue("template"), e.attributeValue("destfile"));
                templateEncodeMap.put(templatePath + e.attributeValue("template"), e.attributeValue("encode"));
            }
        } catch (DocumentException e) {
            logger.error("解析xml文件出错:", e);
        }
    }


    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public Map<String, String> getPropDescMap() {
        return propDescMap;
    }

    public Map<String, String> getPropDefaultMap() {
        return propDefaultMap;
    }

    public Map<String, String> getTemplateMap() {
        return templateMap;
    }

    public Map<String, String> getTemplateEncodeMap() {
        return templateEncodeMap;
    }
}
