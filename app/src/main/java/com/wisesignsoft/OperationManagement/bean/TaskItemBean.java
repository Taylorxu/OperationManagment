package com.wisesignsoft.OperationManagement.bean;

public class TaskItemBean {
    private String PIKEY;
    private String PROCESS_KEY_NAME;
    private String TITLE;
    private String CURRENT_DEALER;
    private String CREATOR;
    private String CREATEDATE;

    public String getPIKEY() {
        return PIKEY;
    }

    public void setPIKEY(String PIKEY) {
        this.PIKEY = PIKEY;
    }

    public String getPROCESS_KEY_NAME() {
        return PROCESS_KEY_NAME;
    }

    public void setPROCESS_KEY_NAME(String PROCESS_KEY_NAME) {
        this.PROCESS_KEY_NAME = PROCESS_KEY_NAME;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getCURRENT_DEALER() {
        return CURRENT_DEALER;
    }

    public void setCURRENT_DEALER(String CURRENT_DEALER) {
        this.CURRENT_DEALER = CURRENT_DEALER;
    }

    public String getCREATOR() {
        return CREATOR;
    }

    public void setCREATOR(String CREATOR) {
        this.CREATOR = CREATOR;
    }

    public String getCREATEDATE() {
        return CREATEDATE;
    }

    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }
}
