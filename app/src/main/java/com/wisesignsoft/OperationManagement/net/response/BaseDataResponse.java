package com.wisesignsoft.OperationManagement.net.response;

/**
 * Created by xuzhiguang on 2018/08/21.
 * 返回的数据部分 字段名为data
 */

public class BaseDataResponse<T> extends BaseResponse {

    private String total;
    private T data;
    private T returnValue;
    private T resources;
    private T result;


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(T returnValue) {
        this.returnValue = returnValue;
    }

    public T getResources() {
        return resources;
    }

    public void setResources(T resources) {
        this.resources = resources;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
