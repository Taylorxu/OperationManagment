package com.wisesignsoft.OperationManagement.bean;

/**
 * Created by ycs on 2017/1/6.
 */

public class ConditionJudgment {
    private String keyName;
    private String key;
    private String value;
    private String operation;
    private String valueDesc;
    private Judgment valueName;

    public static class Judgment {
        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getValueDesc() {
        return valueDesc;
    }

    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
    }

    public Judgment getValueName() {
        return valueName;
    }

    public void setValueName(Judgment valueName) {
        this.valueName = valueName;
    }
}
