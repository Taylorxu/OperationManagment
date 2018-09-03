package com.wisesignsoft.OperationManagement.bean;

import java.io.Serializable;

/**
 * Created by ycs on 2016/12/17.
 */

public class Dict implements Serializable {
    private String key;
    private String value;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
