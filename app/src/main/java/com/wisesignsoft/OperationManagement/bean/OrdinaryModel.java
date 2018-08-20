package com.wisesignsoft.OperationManagement.bean;

/**
 * Created by ycs on 2016/12/5.
 */

public class OrdinaryModel {
    private String name;
    private int reId;
    private String resPar;
    private String resUrl;

    public String getResPar() {
        return resPar;
    }

    public void setResPar(String resPar) {
        this.resPar = resPar;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReId() {
        return reId;
    }

    public void setReId(int reId) {
        this.reId = reId;
    }
}
