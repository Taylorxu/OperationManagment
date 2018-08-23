package com.wisesignsoft.OperationManagement.bean;

import com.wisesignsoft.OperationManagement.bean.District;

/**
 * 字典数据
 */
public class DictDatasBean extends District {
    private String dictValue;
    private String dictId;
    private String dictParentValue;
    private String dictName;

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictParentValue() {
        return dictParentValue;
    }

    public void setDictParentValue(String dictParentValue) {
        this.dictParentValue = dictParentValue;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
}
