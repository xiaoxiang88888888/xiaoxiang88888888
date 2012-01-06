package com.xiaoxiang.gen.pojo;

import java.util.List;

/**
 * 表名
 *
 * @author xiang.xiaox
 */

public class Table {

    private String name; //名称
    
    private String className;//用于文件生成类名

    private String desc; //描述

    private String identifierProperty;//主键名称

    private String pk;//首字母大写的主键名称

    private List<Field> fields;//相关属性

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

    public String getIdentifierProperty() {
        return identifierProperty;
    }

    public void setIdentifierProperty(String identifierProperty) {
        this.identifierProperty = identifierProperty;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getClassName() {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1, name.length());
    }

    public String getPk() {
        if(identifierProperty==null)return "";
        return Character.toUpperCase(identifierProperty.charAt(0)) + identifierProperty.substring(1, identifierProperty.length());
    }
}
