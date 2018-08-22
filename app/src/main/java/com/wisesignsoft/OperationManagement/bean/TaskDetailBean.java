package com.wisesignsoft.OperationManagement.bean;

import com.wisesignsoft.OperationManagement.net.response.BaseResponse;

public class TaskDetailBean extends BaseResponse {

    private String state;
    private String PIKEY;
    private String CURRENT_TASKID;
    private String firstrequest;
    private String taskNodeType;
    private String formDocument;
    private String data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPIKEY() {
        return PIKEY;
    }

    public void setPIKEY(String PIKEY) {
        this.PIKEY = PIKEY;
    }

    public String getCURRENT_TASKID() {
        return CURRENT_TASKID;
    }

    public void setCURRENT_TASKID(String CURRENT_TASKID) {
        this.CURRENT_TASKID = CURRENT_TASKID;
    }

    public String getFirstrequest() {
        return firstrequest;
    }

    public void setFirstrequest(String firstrequest) {
        this.firstrequest = firstrequest;
    }

    public String getTaskNodeType() {
        return taskNodeType;
    }

    public void setTaskNodeType(String taskNodeType) {
        this.taskNodeType = taskNodeType;
    }

    public String getFormDocument() {
        return formDocument;
    }

    public void setFormDocument(String formDocument) {
        this.formDocument = formDocument;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
