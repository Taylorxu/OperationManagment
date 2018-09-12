package com.wisesignsoft.OperationManagement.bean;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class ButtonModel extends RealmObject {
    @PrimaryKey
    private String ID;
    private String value;
    private String viewName;
    private RealmList<NextNode> nextNode;
    private TaskStrategy taskStrategy;

    public TaskStrategy getTaskStrategy() {
        return taskStrategy;
    }

    public void setTaskStrategy(TaskStrategy taskStrategy) {
        this.taskStrategy = taskStrategy;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public RealmList<NextNode> getNextNode() {
        return nextNode;
    }

    public void setNextNode(RealmList<NextNode> nextNode) {
        this.nextNode = nextNode;
    }

}
