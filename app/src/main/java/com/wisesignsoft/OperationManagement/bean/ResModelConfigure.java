package com.wisesignsoft.OperationManagement.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ycs on 2016/12/28.
 */

public class ResModelConfigure implements Serializable {

    private String displayName;
    private String className;

    private List<AttrDatasOfFormBean> attrDatasOfForm;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<AttrDatasOfFormBean> getAttrDatasOfForm() {
        return attrDatasOfForm;
    }

    public void setAttrDatasOfForm(List<AttrDatasOfFormBean> attrDatasOfForm) {
        this.attrDatasOfForm = attrDatasOfForm;
    }

    public static class AttrDatasOfFormBean {
        private boolean hasBusinessKey;
        private String dmAttrName;
        private boolean hasModified;
        private int orderType;
        private boolean hasMultiChoice;
        private String dmAttrDisplayName;
        private Object columnLinkType;
        private boolean hasDateTime;
        private Object dictModelName;
        private String mx_internal_uid;
        private String id;
        private String name;
        private boolean selected;
        private boolean required;
        private int columnWidth;
        private String type;

        public boolean isHasBusinessKey() {
            return hasBusinessKey;
        }

        public void setHasBusinessKey(boolean hasBusinessKey) {
            this.hasBusinessKey = hasBusinessKey;
        }

        public String getDmAttrName() {
            return dmAttrName;
        }

        public void setDmAttrName(String dmAttrName) {
            this.dmAttrName = dmAttrName;
        }

        public boolean isHasModified() {
            return hasModified;
        }

        public void setHasModified(boolean hasModified) {
            this.hasModified = hasModified;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public boolean isHasMultiChoice() {
            return hasMultiChoice;
        }

        public void setHasMultiChoice(boolean hasMultiChoice) {
            this.hasMultiChoice = hasMultiChoice;
        }

        public String getDmAttrDisplayName() {
            return dmAttrDisplayName;
        }

        public void setDmAttrDisplayName(String dmAttrDisplayName) {
            this.dmAttrDisplayName = dmAttrDisplayName;
        }

        public Object getColumnLinkType() {
            return columnLinkType;
        }

        public void setColumnLinkType(Object columnLinkType) {
            this.columnLinkType = columnLinkType;
        }

        public boolean isHasDateTime() {
            return hasDateTime;
        }

        public void setHasDateTime(boolean hasDateTime) {
            this.hasDateTime = hasDateTime;
        }

        public Object getDictModelName() {
            return dictModelName;
        }

        public void setDictModelName(Object dictModelName) {
            this.dictModelName = dictModelName;
        }

        public String getMx_internal_uid() {
            return mx_internal_uid;
        }

        public void setMx_internal_uid(String mx_internal_uid) {
            this.mx_internal_uid = mx_internal_uid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public boolean isRequired() {
            return required;
        }

        public void setRequired(boolean required) {
            this.required = required;
        }

        public int getColumnWidth() {
            return columnWidth;
        }

        public void setColumnWidth(int columnWidth) {
            this.columnWidth = columnWidth;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
