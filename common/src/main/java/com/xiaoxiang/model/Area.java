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
    private Area area;
    /**
     * 地区编码
     */
    @LogAnnotation(title = "地区编码", orderNo = 2)
    private String areacode;
    /**
     * 地区名称
     */
    @LogAnnotation(title = "地区名称", orderNo = 3)
    private String areaname;
    /**
     * 排序号
     */
    @LogAnnotation(title = "排序号", orderNo = 4)
    private Integer orderno;
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

    public Area(String areaId, Area area, String areacode, String areaname, Integer orderno, String remark) {
        this.areaId = areaId;
        this.area = area;
        this.areacode = areacode;
        this.areaname = areaname;
        this.orderno = orderno;
        this.remark = remark;
    }


    public String getAreaId() {
        return this.areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public Area getArea() {
        return this.area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getAreacode() {
        return this.areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getAreaname() {
        return this.areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public Integer getOrderno() {
        return this.orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
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