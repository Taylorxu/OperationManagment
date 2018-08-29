package com.wisesignsoft.OperationManagement.bean;

import java.util.Map;

/**
 * Created by ycs on 2017/1/13.
 */

public class LinkParameter {
    public LinkParameter(String bmModelName, String expression, Map<String, String> attrsMap) {
        this.bmModelName = bmModelName;
        this.expression = expression;
        this.attrsMap = attrsMap;
    }

    /**
     * bmModelName :
     * attrsMap : {}
     * expression :
     */

    private String bmModelName;
    private String expression;
    private Map<String, String> attrsMap;

    public Map<String, String> getAttrsMap() {
        return attrsMap;
    }

    public void setAttrsMap(Map<String, String> attrsMap) {
        this.attrsMap = attrsMap;
    }

    public String getBmModelName() {
        return bmModelName;
    }

    public void setBmModelName(String bmModelName) {
        this.bmModelName = bmModelName;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
