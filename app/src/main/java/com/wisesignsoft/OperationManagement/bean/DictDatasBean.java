package com.wisesignsoft.OperationManagement.bean;

import java.util.UUID;

import javax.annotation.Nullable;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * 字典数据
 */
public class DictDatasBean extends RealmObject {
    public DictDatasBean() {
    }

    public DictDatasBean(String dictId, String dictValue, String dictParentValue, @Nullable String dictName, String srclib) {
        this.dictId = dictId;
        this.dictValue = dictValue;
        this.dictParentValue = dictParentValue;
        this.dictName = dictName;
        this.srclib = srclib;
    }

    @PrimaryKey
    private String id=UUID.randomUUID().toString();
    private String dictId;
    private String dictValue;
    private String dictParentValue;
    @Nullable
    private String dictName;
    private boolean selected;
    /*字典模型编码*/
    private String srclib;

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

    public String getSrclib() {
        return srclib;
    }

    public void setSrclib(String srclib) {
        this.srclib = srclib;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
}
