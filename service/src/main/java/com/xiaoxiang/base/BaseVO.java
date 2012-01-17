package com.xiaoxiang.base;

import com.xiaoxiang.util.StringUtil;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * vo对象的基类，提供基本的tostring,equals,hashcode方法.
 *
 * @author xiaoxiang
 */
public abstract class BaseVO<FK extends Serializable> implements Serializable {
    static String classPath = BaseVO.class.toString();

    /**
     * ID唯一标识一个业务对象
     */
    protected String id;

    public String getId() {
        if (id != null) {
            return StringUtil.isEmpty(id) ? null : id;
        } else {
            return id;
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getName() + ":" + getId();
    }


    @Override
    public boolean equals(Object o) {
        return !(getId() == null || o == null) && ((BaseVO) o).getId().equals(getId());
    }

    /**
     * 对树菜单有影响
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }


}
