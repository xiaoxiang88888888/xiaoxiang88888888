package com.xiaoxiang.result;

/**
 * 后台返回到前端的json格式
 *
 * @author xiang.xiaox
 */

public class JSONResult {
    //成功或失败
    private Boolean result;
    //错误的定段
    private String errorField;
    //错误的信息
    private String errorDesc;
    //数据
    private Object data;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getErrorField() {
        return errorField;
    }

    public void setErrorField(String errorField) {
        this.errorField = errorField;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
