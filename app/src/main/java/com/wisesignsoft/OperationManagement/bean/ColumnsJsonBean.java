package com.wisesignsoft.OperationManagement.bean;

import java.io.Serializable;

/**
 * Created by ycs on 2016/12/21.
 */

public class ColumnsJsonBean implements Serializable {


    private DataTypeObjBean dataTypeObj;


    private DataFieldObjBean dataFieldObj;


    private String mx_internal_uid;
    /**
     * value : 0
     * name : 否
     */

    private RequiredObjBean requiredObj;


    private IsMultipleBean isMultiple;
    private ShowTargetObjBean showTargetObj;
    private int width;
    private DataSourceObjBean dataSourceObj;
    private String headerText;


    private OperateTypeBean operateType;

    public DataTypeObjBean getDataTypeObj() {
        return dataTypeObj;
    }

    public void setDataTypeObj(DataTypeObjBean dataTypeObj) {
        this.dataTypeObj = dataTypeObj;
    }

    public DataFieldObjBean getDataFieldObj() {
        return dataFieldObj;
    }

    public void setDataFieldObj(DataFieldObjBean dataFieldObj) {
        this.dataFieldObj = dataFieldObj;
    }

    public String getMx_internal_uid() {
        return mx_internal_uid;
    }

    public void setMx_internal_uid(String mx_internal_uid) {
        this.mx_internal_uid = mx_internal_uid;
    }

    public RequiredObjBean getRequiredObj() {
        return requiredObj;
    }

    public void setRequiredObj(RequiredObjBean requiredObj) {
        this.requiredObj = requiredObj;
    }

    public IsMultipleBean getIsMultiple() {
        return isMultiple;
    }

    public void setIsMultiple(IsMultipleBean isMultiple) {
        this.isMultiple = isMultiple;
    }

    public ShowTargetObjBean getShowTargetObj() {
        return showTargetObj;
    }

    public void setShowTargetObj(ShowTargetObjBean showTargetObj) {
        this.showTargetObj = showTargetObj;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public DataSourceObjBean getDataSourceObj() {
        return dataSourceObj;
    }

    public void setDataSourceObj(DataSourceObjBean dataSourceObj) {
        this.dataSourceObj = dataSourceObj;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public OperateTypeBean getOperateType() {
        return operateType;
    }

    public void setOperateType(OperateTypeBean operateType) {
        this.operateType = operateType;
    }

    public static class DataTypeObjBean implements Serializable {
        private String value;
        private String name;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class DataFieldObjBean implements Serializable {
        private String value;
        private String name;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class RequiredObjBean implements Serializable {
        private int value;
        private String name;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class IsMultipleBean implements Serializable {
        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static class ShowTargetObjBean implements Serializable {
    }

    public static class DataSourceObjBean implements Serializable {
    }

    public static class OperateTypeBean implements Serializable {
        private Object value;
        private String name;

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
