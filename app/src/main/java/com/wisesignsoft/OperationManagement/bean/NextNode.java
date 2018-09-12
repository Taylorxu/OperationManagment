package com.wisesignsoft.OperationManagement.bean;

import io.realm.RealmObject;

public class NextNode extends RealmObject {
    private String viewName;
    private String name;
    private String to;
    private String isDefaultPath;
    private String taskStrategy;
    private String specificValueUpdate;
    private String nameDesc;
    private String isDependCondition;

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getIsDefaultPath() {
        return isDefaultPath;
    }

    public void setIsDefaultPath(String isDefaultPath) {
        this.isDefaultPath = isDefaultPath;
    }

    public String getTaskStrategy() {
        return taskStrategy;
    }

    public void setTaskStrategy(String taskStrategy) {
        this.taskStrategy = taskStrategy;
    }

    public String getSpecificValueUpdate() {
        return specificValueUpdate;
    }

    public void setSpecificValueUpdate(String specificValueUpdate) {
        this.specificValueUpdate = specificValueUpdate;
    }

    public String getNameDesc() {
        return nameDesc;
    }

    public void setNameDesc(String nameDesc) {
        this.nameDesc = nameDesc;
    }

    public String getIsDependCondition() {
        return isDependCondition;
    }

    public void setIsDependCondition(String isDependCondition) {
        this.isDependCondition = isDependCondition;
    }
}
