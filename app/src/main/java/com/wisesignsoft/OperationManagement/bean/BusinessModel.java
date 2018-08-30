package com.wisesignsoft.OperationManagement.bean;

import java.util.List;

public class BusinessModel {
    private String bmDisplayName;
    private String bmHelp;
    private String bmId;
    private String bmModelName;
    private String bmProcessorJson;
    private String bmValidateJson;

    private AttrDefineListBean businessKeyAttrDefine;
    private String dmDisplayName;
    private String dmModelName;
    private String iconName;
    private int order;
    private String superBmModelName;
    private String width;

    private List<AttrDefineListBean> attrDefineList;

    public String getBmDisplayName() {
        return bmDisplayName;
    }

    public void setBmDisplayName(String bmDisplayName) {
        this.bmDisplayName = bmDisplayName;
    }

    public String getBmHelp() {
        return bmHelp;
    }

    public void setBmHelp(String bmHelp) {
        this.bmHelp = bmHelp;
    }

    public String getBmId() {
        return bmId;
    }

    public void setBmId(String bmId) {
        this.bmId = bmId;
    }

    public String getBmModelName() {
        return bmModelName;
    }

    public void setBmModelName(String bmModelName) {
        this.bmModelName = bmModelName;
    }

    public String getBmProcessorJson() {
        return bmProcessorJson;
    }

    public void setBmProcessorJson(String bmProcessorJson) {
        this.bmProcessorJson = bmProcessorJson;
    }

    public String getBmValidateJson() {
        return bmValidateJson;
    }

    public void setBmValidateJson(String bmValidateJson) {
        this.bmValidateJson = bmValidateJson;
    }

    public AttrDefineListBean getBusinessKeyAttrDefine() {
        return businessKeyAttrDefine;
    }

    public void setBusinessKeyAttrDefine(AttrDefineListBean businessKeyAttrDefine) {
        this.businessKeyAttrDefine = businessKeyAttrDefine;
    }

    public String getDmDisplayName() {
        return dmDisplayName;
    }

    public void setDmDisplayName(String dmDisplayName) {
        this.dmDisplayName = dmDisplayName;
    }

    public String getDmModelName() {
        return dmModelName;
    }

    public void setDmModelName(String dmModelName) {
        this.dmModelName = dmModelName;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getSuperBmModelName() {
        return superBmModelName;
    }

    public void setSuperBmModelName(String superBmModelName) {
        this.superBmModelName = superBmModelName;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public List<AttrDefineListBean> getAttrDefineList() {
        return attrDefineList;
    }

    public void setAttrDefineList(List<AttrDefineListBean> attrDefineList) {
        this.attrDefineList = attrDefineList;
    }
}
