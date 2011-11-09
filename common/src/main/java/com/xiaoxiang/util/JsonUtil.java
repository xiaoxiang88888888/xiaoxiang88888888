package com.xiaoxiang.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 使用jaskson 解析json  单例类
 *
 * @author xiang.xiaox
 */
public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static JsonUtil jsonUtil = new JsonUtil();
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 对象转JSON
     *
     * @param object
     * @return
     */
    public String ObjectToJson(Object object) {
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.error("json error", e);
        }
        return json;
    }

    /**
     * JSON转 对象
     *
     * @param json
     * @param cs
     * @return
     */
    public Object jsonToObject(String json, Class<?> cs) {
        Object object = null;
        try {
            object = mapper.readValue(json, cs);
        } catch (IOException e) {
            logger.error("json error", e);
        }
        return object;
    }

    /**
     * 集合转JSON
     *
     * @param object
     * @return
     */
    public String CollectionToJson(Object object) {
        return ObjectToJson(object);
    }

    /**
     * JSON转集合
     *
     * @param json
     * @param typeReference
     * @return
     */
    public Object jsonToCollection(String json, TypeReference typeReference) {
        Object object = null;
        try {
            object = mapper.readValue(json, typeReference);
        } catch (IOException e) {
            logger.error("json error", e);
        }
        return object;
    }

    private JsonUtil() {
    }

    public static JsonUtil getInstance() {
        return jsonUtil;
    }

}
