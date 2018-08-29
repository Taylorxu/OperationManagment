package com.wisesignsoft.OperationManagement.bean;

import io.realm.RealmObject;

public class BMForm extends RealmObject {
    private String bmModelName;
    private String superBmModelName;
    private String bmIcon;
    private String bmHelp;
    private String bmDisplayName;
    private String width;
    private String height;
    private String order;
    private String modelName;
    private String dmDisplayName;
    private String hasFlowChart;
    private String hasDataRelation;
    private String hasAuditRecord;
    private String hasOperLog;
    private String hasKB;
    private String hasbmHelp;
    private String conditionJudgment;
    private String sortingSet;
    private String specificValueUpdate;

    public String getBmModelName() {
        return bmModelName;
    }

    public void setBmModelName(String bmModelName) {
        this.bmModelName = bmModelName;
    }

    public String getSuperBmModelName() {
        return superBmModelName;
    }

    public void setSuperBmModelName(String superBmModelName) {
        this.superBmModelName = superBmModelName;
    }

    public String getBmIcon() {
        return bmIcon;
    }

    public void setBmIcon(String bmIcon) {
        this.bmIcon = bmIcon;
    }

    public String getBmHelp() {
        return bmHelp;
    }

    public void setBmHelp(String bmHelp) {
        this.bmHelp = bmHelp;
    }

    public String getBmDisplayName() {
        return bmDisplayName;
    }

    public void setBmDisplayName(String bmDisplayName) {
        this.bmDisplayName = bmDisplayName;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getDmDisplayName() {
        return dmDisplayName;
    }

    public void setDmDisplayName(String dmDisplayName) {
        this.dmDisplayName = dmDisplayName;
    }

    public String getHasFlowChart() {
        return hasFlowChart;
    }

    public void setHasFlowChart(String hasFlowChart) {
        this.hasFlowChart = hasFlowChart;
    }

    public String getHasDataRelation() {
        return hasDataRelation;
    }

    public void setHasDataRelation(String hasDataRelation) {
        this.hasDataRelation = hasDataRelation;
    }

    public String getHasAuditRecord() {
        return hasAuditRecord;
    }

    public void setHasAuditRecord(String hasAuditRecord) {
        this.hasAuditRecord = hasAuditRecord;
    }

    public String getHasOperLog() {
        return hasOperLog;
    }

    public void setHasOperLog(String hasOperLog) {
        this.hasOperLog = hasOperLog;
    }

    public String getHasKB() {
        return hasKB;
    }

    public void setHasKB(String hasKB) {
        this.hasKB = hasKB;
    }

    public String getHasbmHelp() {
        return hasbmHelp;
    }

    public void setHasbmHelp(String hasbmHelp) {
        this.hasbmHelp = hasbmHelp;
    }

    public String getConditionJudgment() {
        return conditionJudgment;
    }

    public void setConditionJudgment(String conditionJudgment) {
        this.conditionJudgment = conditionJudgment;
    }

    public String getSortingSet() {
        return sortingSet;
    }

    public void setSortingSet(String sortingSet) {
        this.sortingSet = sortingSet;
    }

    public String getSpecificValueUpdate() {
        return specificValueUpdate;
    }

    public void setSpecificValueUpdate(String specificValueUpdate) {
        this.specificValueUpdate = specificValueUpdate;
    }
}
