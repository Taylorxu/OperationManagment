package com.wisesignsoft.OperationManagement.bean;

public class ColumnsProperty {

    /**
     * hasModified : true
     * name : 客户单位名称
     * id : textinput_2
     * required : true
     * hasVisible : true
     * type : TextInput
     * columnWidth : 200
     * dmAttrName : COMPANY
     */

    private boolean hasModified;
    private String name;
    private String id;
    private boolean required;
    private boolean hasVisible;
    private String type;
    private int columnWidth;
    private String dmAttrName;

    public boolean isHasModified() {
        return hasModified;
    }

    public void setHasModified(boolean hasModified) {
        this.hasModified = hasModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isHasVisible() {
        return hasVisible;
    }

    public void setHasVisible(boolean hasVisible) {
        this.hasVisible = hasVisible;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    public void setColumnWidth(int columnWidth) {
        this.columnWidth = columnWidth;
    }

    public String getDmAttrName() {
        return dmAttrName;
    }

    public void setDmAttrName(String dmAttrName) {
        this.dmAttrName = dmAttrName;
    }
}
