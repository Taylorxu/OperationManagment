package com.wisesignsoft.OperationManagement.bean;

/**
 * Created by ycs on 2017/1/5.
 */

public class ConfigureBean {
    private String OBJECTID;
    private String textValue;
    private String bmModelName;

    public String getOBJECTID() {
        return OBJECTID;
    }

    public void setOBJECTID(String OBJECTID) {
        this.OBJECTID = OBJECTID;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public String getBmModelName() {
        return bmModelName;
    }

    public void setBmModelName(String bmModelName) {
        this.bmModelName = bmModelName;
    }
}
