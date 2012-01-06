package com.xiaoxiang.gen.pojo;

/**
 * 字段属性
 *
 * @author xiang.xiaox
 */

public class Field {

    private String name; //名称

    private String methodName; //用于文件生成,首字母大写

    private String desc; //描述

    private String type; //类型

    private boolean pk;//是否主键

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPk() {
        return pk;
    }

    public void setPk(boolean pk) {
        this.pk = pk;
    }

    public String getMethodName() {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1, name.length());
    }
}
