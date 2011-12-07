package com.xiaoxiang.model;

import com.xiaoxiang.base.BaseVO;

/**
 * Demo  示例
 *
 * @author xiaoxiang
 */
public class Demo extends BaseVO<String> implements java.io.Serializable {
    /**
     * 示例编号
     */
    private String demoId;

    /**
     * 示例编号
     */
    private String parentDemoId;

    /**
     * 示例编码
     */
    private String demoCode;

    /**
     * 示例名称
     */
    private String demoName;
    /**
     * 排序号
     */
    private Integer orderNo;

    /**
     * 备注
     */
    private String remark;

    public Demo() {

    }

    public Demo(String demoName, String remark) {
        this.demoName = demoName;
        this.remark = remark;
    }


    public Demo(String demoId) {
        this.demoId = demoId;
    }


    @Override
    public String toString() {
        return getClass().getName() + ":" + getDemoId();
    }

    @Override
    public boolean equals(Object obj) {
        return !(null == getDemoId() || null == obj) && (obj instanceof Demo) && ((Demo) obj).getDemoId().equals(getDemoId());
    }

    @Override
    public int hashCode() {
        return null == getDemoId() ? super.hashCode() : getDemoId().hashCode();
    }

    public String getDemoId() {
        return demoId;
    }

    public void setDemoId(String demoId) {
        this.demoId = demoId;
    }

    public String getParentDemoId() {
        return parentDemoId;
    }

    public void setParentDemoId(String parentDemoId) {
        this.parentDemoId = parentDemoId;
    }

    public String getDemoCode() {
        return demoCode;
    }

    public void setDemoCode(String demoCode) {
        this.demoCode = demoCode;
    }

    public String getDemoName() {
        return demoName;
    }

    public void setDemoName(String demoName) {
        this.demoName = demoName;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStr() {
        if(!getPrivateStr()){
          return "is null";
        }else{
          return "is not null";
        }
    }

    private boolean getPrivateStr(){
        return false;
    }
}