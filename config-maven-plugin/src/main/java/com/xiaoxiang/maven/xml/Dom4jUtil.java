package com.xiaoxiang.maven.xml;

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
    private final static String DEFAULT_CONFIG_PATH = "META-INF" + File.separator + "autoconfig" + File.separator + "autoConfig.xml";
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
    private Map<String, Object> propDescMap;//属性描述 prop name==>desc
    private Map<String, Object> propDefaultMap;//prop name==>default
    private Map<String, Object> templateMap;//需要渲染的模板 模板的目录==>生成的目录
    private Map<String, Object> templateEncodeMap;//生成模板的编码  模板的目录==>编码

    /**
     * 解析
     *
     * @param path
     * @param isClassPath
     */
    public Dom4jUtil(String path, boolean isClassPath) {
        this.configPath = path;
        InputStream inputStream = null;

        if (!isClassPath) {
            try {
                inputStream = new FileInputStream(new File(path));
            } catch (FileNotFoundException e) {
                logger.error("错误的配置文件路径:" + path, e);
            }
        } else {
            inputStream = this.getClass().getResourceAsStream("/" + configPath);
        }
        parser(inputStream);
    }

    /**
     * 默认从classPath加载相关配置文件
     */
    public Dom4jUtil() {
        this(DEFAULT_CONFIG_PATH, true);
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
        SAXReader reader = new SAXReader();
        propDescMap = new LinkedHashMap<String, Object>();
        propDefaultMap = new LinkedHashMap<String, Object>();
        templateMap = new LinkedHashMap<String, Object>();
        templateEncodeMap = new LinkedHashMap<String, Object>();
        try {
            document = reader.read(inputStream);
            Element root = document.getRootElement();
            //String rootDesc = root.attributeValue("description");

            for (Iterator itrProps = root.elementIterator(TAG_NAME_PROPS); itrProps.hasNext(); ) {
                Element propsElement = (Element) itrProps.next();
                for (Iterator itr = propsElement.elementIterator(TAG_NAME_PROP); itr.hasNext(); ) {
                    Element e = (Element) itr.next();
                    propDefaultMap.put(replace(e.attributeValue("name")), e.attributeValue("defaultValue"));
                    propDescMap.put(replace(e.attributeValue("name")), e.attributeValue("description"));
                }
            }

            Element gens = root.element(TAG_NAME_GENS);
            for (Iterator itr = gens.elementIterator(TAG_NAME_GEN); itr.hasNext(); ) {
                Element e = (Element) itr.next();
                templateMap.put(e.attributeValue("template"), e.attributeValue("destfile"));
                templateEncodeMap.put(e.attributeValue("template"), e.attributeValue("encode"));
            }
        } catch (DocumentException e) {
            logger.error("解析xml文件出错:", e);
        }
    }

    /**
     * 将属性的名称中的.替换成_,因模板渲染变量中不能出现.
     *
     * @param str
     * @return
     */
    private String replace(String str) {
        if (str == null) return null;
        return str.replace(".", "_");
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public Map<String, Object> getPropDescMap() {
        return propDescMap;
    }

    public Map<String, Object> getPropDefaultMap() {
        return propDefaultMap;
    }

    public Map<String, Object> getTemplateMap() {
        return templateMap;
    }

    public Map<String, Object> getTemplateEncodeMap() {
        return templateEncodeMap;
    }
}
