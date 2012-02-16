package com.xiaoxiang.model;

import com.xiaoxiang.annotation.LogAnnotation;
import com.xiaoxiang.base.BaseVO;

/**
 * Area  地区
 *
 * @author xiaoxiang
 */
@LogAnnotation(title = "地区")
public class Area extends BaseVO<String> implements java.io.Serializable {

    /**
     * 地区编号
     */
    @LogAnnotation(title = "地区编号", isPK = true, orderNo = 0)
    private String areaId;
    /**
     * 地区编号
     */
    @LogAnnotation(title = "地区编号", orderNo = 1)
    private String parentId;
    /**
     * 地区编码
     */
    @LogAnnotation(title = "地区编码", orderNo = 2)
    private String code;
    /**
     * 地区名称
     */
    @LogAnnotation(title = "地区名称", orderNo = 3)
    private String name;
    /**
     * 排序号
     */
    @LogAnnotation(title = "排序号", orderNo = 4)
    private Integer orderNo;
    /**
     * 备注
     */
    @LogAnnotation(title = "备注", orderNo = 5)
    private String remark;

    public Area() {
    }


    public Area(String areaId) {
        this.areaId = areaId;
    }

    public Area(String areaId, String parentId, String code, String name, Integer orderNo, String remark) {
        this.areaId = areaId;
        this.parentId = parentId;
        this.code = code;
        this.name = name;
        this.orderNo = orderNo;
        this.remark = remark;
    }


    public String getAreaId() {
        return this.areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return getClass().getName() + ":" + getAreaId();
    }

    @Override
    public boolean equals(Object obj) {
        return !(null == getAreaId() || null == obj) && (obj instanceof Area) && ((Area) obj).getAreaId().equals(getAreaId());
    }

    @Override
    public int hashCode() {
        return null == getAreaId() ? super.hashCode() : getAreaId().hashCode();
    }


}