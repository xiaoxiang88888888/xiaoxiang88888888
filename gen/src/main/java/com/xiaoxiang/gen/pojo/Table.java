package com.xiaoxiang.gen.pojo;

import java.util.List;

/**
 * 表名
 *
 * @author xiang.xiaox
 */

public class Table {

    private String name; //名称

    private String desc; //描述

    private String identifierProperty;//主键名称

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
}
