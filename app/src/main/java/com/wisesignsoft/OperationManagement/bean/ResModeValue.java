package com.wisesignsoft.OperationManagement.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ycs on 2016/12/12.
 */

public class ResModeValue implements Serializable{


    private String bmDisplayName;
    private String bmModelName;
    private String dmAttrName;
    private String dmDisplayName;
    private String toBmModelName;

    private List<ConfigValueJsonBean> configValueJson;

    public String getBmDisplayName() {
        return bmDisplayName;
    }

    public void setBmDisplayName(String bmDisplayName) {
        this.bmDisplayName = bmDisplayName;
    }

    public String getBmModelName() {
        return bmModelName;
    }

    public void setBmModelName(String bmModelName) {
        this.bmModelName = bmModelName;
    }

    public String getDmAttrName() {
        return dmAttrName;
    }

    public void setDmAttrName(String dmAttrName) {
        this.dmAttrName = dmAttrName;
    }

    public String getDmDisplayName() {
        return dmDisplayName;
    }

    public void setDmDisplayName(String dmDisplayName) {
        this.dmDisplayName = dmDisplayName;
    }

    public String getToBmModelName() {
        return toBmModelName;
    }

    public void setToBmModelName(String toBmModelName) {
        this.toBmModelName = toBmModelName;
    }

    public List<ConfigValueJsonBean> getConfigValueJson() {
        return configValueJson;
    }

    public void setConfigValueJson(List<ConfigValueJsonBean> configValueJson) {
        this.configValueJson = configValueJson;
    }

    public static class ConfigValueJsonBean implements Serializable {
        private String mx_internal_uid;

        private FromDmAttrNameBean fromDmAttrName;

        private ToFmAttrNameBean toFmAttrName;

        public String getMx_internal_uid() {
            return mx_internal_uid;
        }

        public void setMx_internal_uid(String mx_internal_uid) {
            this.mx_internal_uid = mx_internal_uid;
        }

        public FromDmAttrNameBean getFromDmAttrName() {
            return fromDmAttrName;
        }

        public void setFromDmAttrName(FromDmAttrNameBean fromDmAttrName) {
            this.fromDmAttrName = fromDmAttrName;
        }

        public ToFmAttrNameBean getToFmAttrName() {
            return toFmAttrName;
        }

        public void setToFmAttrName(ToFmAttrNameBean toFmAttrName) {
            this.toFmAttrName = toFmAttrName;
        }

        public static class FromDmAttrNameBean implements Serializable{
            private String id;
            private String dmAttrName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDmAttrName() {
                return dmAttrName;
            }

            public void setDmAttrName(String dmAttrName) {
                this.dmAttrName = dmAttrName;
            }
        }

        public static class ToFmAttrNameBean implements Serializable{
            private String id;
            private String dmAttrName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDmAttrName() {
                return dmAttrName;
            }

            public void setDmAttrName(String dmAttrName) {
                this.dmAttrName = dmAttrName;
            }
        }
    }
}
