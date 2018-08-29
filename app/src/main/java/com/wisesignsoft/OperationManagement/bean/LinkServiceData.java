package com.wisesignsoft.OperationManagement.bean;

import java.util.List;

public class LinkServiceData {
    private int controlType;
    private String dmAttrName;
    private boolean hasEdit;
    private boolean hasRequired;
    private boolean hasVisible;
    private String value;
    private List<?> valueRange;

    public int getControlType() {
        return controlType;
    }

    public void setControlType(int controlType) {
        this.controlType = controlType;
    }

    public String getDmAttrName() {
        return dmAttrName;
    }

    public void setDmAttrName(String dmAttrName) {
        this.dmAttrName = dmAttrName;
    }

    public boolean isHasEdit() {
        return hasEdit;
    }

    public void setHasEdit(boolean hasEdit) {
        this.hasEdit = hasEdit;
    }

    public boolean isHasRequired() {
        return hasRequired;
    }

    public void setHasRequired(boolean hasRequired) {
        this.hasRequired = hasRequired;
    }

    public boolean isHasVisible() {
        return hasVisible;
    }

    public void setHasVisible(boolean hasVisible) {
        this.hasVisible = hasVisible;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<?> getValueRange() {
        return valueRange;
    }

    public void setValueRange(List<?> valueRange) {
        this.valueRange = valueRange;
    }
}
