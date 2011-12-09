package com.xiaoxiang.maven.properties;

import com.xiaoxiang.maven.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * 属性文件读取
 *
 * @author xiang.xiaox
 */

public class PropertiesUtil {
    private final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    //默认的properties配置文件
    private final static String DEFAULT_PROPS_PATH = System.getProperty("user.home") + File.separator + "autoConfig.properties";
    //属性对象
    private Properties props;
    //可自定义文件路径
    private String propPath;

    public PropertiesUtil() {
        this(DEFAULT_PROPS_PATH);
    }

    public PropertiesUtil(String propPath) {
        this.propPath = propPath;
        init();
    }

    /**
     * 初始化，如果文件不存在会默认创建一个
     */
    private void init() {
        File file = new File(propPath);
        FileInputStream inputStream = null;
        try {
            if (!file.exists()) {
                boolean result = file.createNewFile();
                if (result) {
                    logger.info("[Create The File]: " + file.getName());
                }
            }
            props = new Properties();
            inputStream = new FileInputStream(file);
            props.load(inputStream);
        } catch (IOException e) {
            logger.error("属性文件异常:", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error("关闭流出现异常:", e);
            }
        }
    }

    /**
     * 关闭文件，将新的配置项的值写入到文件中
     */
    public void close() {
        File file = new File(propPath);
        FileOutputStream outputStream = null;
        try {
            if (!file.exists()) {
                boolean result = file.createNewFile();
                if (result) {
                    logger.info("创建文件: " + file.getAbsolutePath());
                }
            }
            outputStream = new FileOutputStream(file);
            if (null != props) {
                props.store(outputStream, "");
            }
        } catch (IOException e) {
            logger.error("出现异常:", e);
        } finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (IOException e) {
                logger.error("关闭流出现异常:", e);
            }
        }
    }

    /**
     * 获取属性值，如果配置文件中不存在，则返回默认的defaultvalue
     * 如果defaultValue為空，則提示用戶输入
     *
     * @param key
     * @param defaultValue
     * @param description
     * @return
     */
    public String getProps(String key, String defaultValue, String description) {
        boolean storeProperty = false;
        String value = props.getProperty(StringUtil.replaceOther(key));

        if (isBlank(value)) {
            storeProperty = true;
            value = defaultValue;
        }

        if (isBlank(value)) {
            System.out.println("Enter the key ");
            if (!isBlank(description)) {
                System.out.println(key + "(" + description + ") value: ");
            } else {
                System.out.println(key + " value: ");
            }
            value = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                value = reader.readLine();
                while (value != null) {
                    if ("exit".equalsIgnoreCase(value)) break;
                    value = reader.readLine();
                }
                reader.close();
                storeProperty = true;
            } catch (IOException e) {
                logger.error("出现异常:", e);
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("关闭流出现异常:", e);
                }
            }

        }
        if (storeProperty) {
            props.setProperty(key, value);
        }

        return value;
    }


    private boolean isBlank(String str) {
        return str == null || str.equals("");
    }

    public Properties getProps() {
        return props;
    }

    public String getPropPath() {
        return propPath;
    }

    public void setPropPath(String propPath) {
        this.propPath = propPath;
    }
}
