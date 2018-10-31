package com.wisesignsoft.OperationManagement.bean;

/**
 * 新建工单时，选择工单类型model
 */
public class VariousOrderBean {
    private String key;
    private String name;
    private boolean isSelect;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
