package com.wisesignsoft.OperationManagement.bean;

import java.util.List;


public class BMForm {
    private String bmModelName;
    private String superBmModelName;
    private String bmIcon;
    private String bmHelp;
    private String bmDisplayName;
    private String width;
    private String height;
    private String order;
    private String modelName;
    private String dmDisplayName;
    private String hasFlowChart;
    private String hasDataRelation;
    private String hasAuditRecord;
    private String hasOperLog;
    private String hasKB;
    private String hasbmHelp;
    private String conditionJudgment;
    private String sortingSet;
    private String specificValueUpdate;

    public String getBmModelName() {
        return bmModelName;
    }

    public void setBmModelName(String bmModelName) {
        this.bmModelName = bmModelName;
    }

    public String getSuperBmModelName() {
        return superBmModelName;
    }

    public void setSuperBmModelName(String superBmModelName) {
        this.superBmModelName = superBmModelName;
    }

    public String getBmIcon() {
        return bmIcon;
    }

    public void setBmIcon(String bmIcon) {
        this.bmIcon = bmIcon;
    }

    public String getBmHelp() {
        return bmHelp;
    }

    public void setBmHelp(String bmHelp) {
        this.bmHelp = bmHelp;
    }

    public String getBmDisplayName() {
        return bmDisplayName;
    }

    public void setBmDisplayName(String bmDisplayName) {
        this.bmDisplayName = bmDisplayName;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getDmDisplayName() {
        return dmDisplayName;
    }

    public void setDmDisplayName(String dmDisplayName) {
        this.dmDisplayName = dmDisplayName;
    }

    public String getHasFlowChart() {
        return hasFlowChart;
    }

    public void setHasFlowChart(String hasFlowChart) {
        this.hasFlowChart = hasFlowChart;
    }

    public String getHasDataRelation() {
        return hasDataRelation;
    }

    public void setHasDataRelation(String hasDataRelation) {
        this.hasDataRelation = hasDataRelation;
    }

    public String getHasAuditRecord() {
        return hasAuditRecord;
    }

    public void setHasAuditRecord(String hasAuditRecord) {
        this.hasAuditRecord = hasAuditRecord;
    }

    public String getHasOperLog() {
        return hasOperLog;
    }

    public void setHasOperLog(String hasOperLog) {
        this.hasOperLog = hasOperLog;
    }

    public String getHasKB() {
        return hasKB;
    }

    public void setHasKB(String hasKB) {
        this.hasKB = hasKB;
    }

    public String getHasbmHelp() {
        return hasbmHelp;
    }

    public void setHasbmHelp(String hasbmHelp) {
        this.hasbmHelp = hasbmHelp;
    }

    public String getConditionJudgment() {
        return conditionJudgment;
    }

    public void setConditionJudgment(String conditionJudgment) {
        this.conditionJudgment = conditionJudgment;
    }

    public String getSortingSet() {
        return sortingSet;
    }

    public void setSortingSet(String sortingSet) {
        this.sortingSet = sortingSet;
    }

    public String getSpecificValueUpdate() {
        return specificValueUpdate;
    }

    public void setSpecificValueUpdate(String specificValueUpdate) {
        this.specificValueUpdate = specificValueUpdate;
    }

    public static class ValueUpdate{

        /**
         * formItem : {"bmModelName":"PRO_PROCESS","isCanUpdate":true,"dmAttrDisplayName":"流程状态","name":"问题状态","dataProvider":null,"modelName":"PRO_PROCESS","isFilterUser":false,"parentDeptJSON":null,"isVisible":false,"modified":true,"required":true,"sectionId":"Section0","dmAttrName":"PROCESS_STATE","value":"","isExecute":false,"maxChars":null,"id":"combobox_10","isMult":false,"nodeType":"ComboBox","srclib":"PRO_STA","srclib_label":"问题状态","value_label":"null","ID":"combobox_10","isCanDelete":true,"configModifiedField":null,"seeHisWorkOrderInfo":false,"precision":null,"isCanAdd":true,"allColumnsJson":null}
         * formItemValue : {"value":"PRO_STA:44f0b7b9-5a14-414e-b354-44c73f2a1854","name":"新提交"}
         */

        private List<ValuesBean> values;

        public List<ValuesBean> getValues() {
            return values;
        }

        public void setValues(List<ValuesBean> values) {
            this.values = values;
        }

        public static class ValuesBean {
            /**
             * bmModelName : PRO_PROCESS
             * isCanUpdate : true
             * dmAttrDisplayName : 流程状态
             * name : 问题状态
             * dataProvider : null
             * modelName : PRO_PROCESS
             * isFilterUser : false
             * parentDeptJSON : null
             * isVisible : false
             * modified : true
             * required : true
             * sectionId : Section0
             * dmAttrName : PROCESS_STATE
             * value :
             * isExecute : false
             * maxChars : null
             * id : combobox_10
             * isMult : false
             * nodeType : ComboBox
             * srclib : PRO_STA
             * srclib_label : 问题状态
             * value_label : null
             * ID : combobox_10
             * isCanDelete : true
             * configModifiedField : null
             * seeHisWorkOrderInfo : false
             * precision : null
             * isCanAdd : true
             * allColumnsJson : null
             */

            private FormItemBean formItem;
            /**
             * value : PRO_STA:44f0b7b9-5a14-414e-b354-44c73f2a1854
             * name : 新提交
             */

            private FormItemValueBean formItemValue;

            public FormItemBean getFormItem() {
                return formItem;
            }

            public void setFormItem(FormItemBean formItem) {
                this.formItem = formItem;
            }

            public FormItemValueBean getFormItemValue() {
                return formItemValue;
            }

            public void setFormItemValue(FormItemValueBean formItemValue) {
                this.formItemValue = formItemValue;
            }

            public static class FormItemBean {
                private String bmModelName;
                private boolean isCanUpdate;
                private String dmAttrDisplayName;
                private String name;
                private Object dataProvider;
                private String modelName;
                private boolean isFilterUser;
                private Object parentDeptJSON;
                private boolean isVisible;
                private boolean modified;
                private boolean required;
                private String sectionId;
                private String dmAttrName;
                private String value;
                private boolean isExecute;
                private Object maxChars;
                private String id;
                private boolean isMult;
                private String nodeType;
                private String srclib;
                private String srclib_label;
                private String value_label;
                private String ID;
                private boolean isCanDelete;
                private Object configModifiedField;
                private boolean seeHisWorkOrderInfo;
                private Object precision;
                private boolean isCanAdd;
                private Object allColumnsJson;

                public String getBmModelName() {
                    return bmModelName;
                }

                public void setBmModelName(String bmModelName) {
                    this.bmModelName = bmModelName;
                }

                public boolean isIsCanUpdate() {
                    return isCanUpdate;
                }

                public void setIsCanUpdate(boolean isCanUpdate) {
                    this.isCanUpdate = isCanUpdate;
                }

                public String getDmAttrDisplayName() {
                    return dmAttrDisplayName;
                }

                public void setDmAttrDisplayName(String dmAttrDisplayName) {
                    this.dmAttrDisplayName = dmAttrDisplayName;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Object getDataProvider() {
                    return dataProvider;
                }

                public void setDataProvider(Object dataProvider) {
                    this.dataProvider = dataProvider;
                }

                public String getModelName() {
                    return modelName;
                }

                public void setModelName(String modelName) {
                    this.modelName = modelName;
                }

                public boolean isIsFilterUser() {
                    return isFilterUser;
                }

                public void setIsFilterUser(boolean isFilterUser) {
                    this.isFilterUser = isFilterUser;
                }

                public Object getParentDeptJSON() {
                    return parentDeptJSON;
                }

                public void setParentDeptJSON(Object parentDeptJSON) {
                    this.parentDeptJSON = parentDeptJSON;
                }

                public boolean isIsVisible() {
                    return isVisible;
                }

                public void setIsVisible(boolean isVisible) {
                    this.isVisible = isVisible;
                }

                public boolean isModified() {
                    return modified;
                }

                public void setModified(boolean modified) {
                    this.modified = modified;
                }

                public boolean isRequired() {
                    return required;
                }

                public void setRequired(boolean required) {
                    this.required = required;
                }

                public String getSectionId() {
                    return sectionId;
                }

                public void setSectionId(String sectionId) {
                    this.sectionId = sectionId;
                }

                public String getDmAttrName() {
                    return dmAttrName;
                }

                public void setDmAttrName(String dmAttrName) {
                    this.dmAttrName = dmAttrName;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public boolean isIsExecute() {
                    return isExecute;
                }

                public void setIsExecute(boolean isExecute) {
                    this.isExecute = isExecute;
                }

                public Object getMaxChars() {
                    return maxChars;
                }

                public void setMaxChars(Object maxChars) {
                    this.maxChars = maxChars;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public boolean isIsMult() {
                    return isMult;
                }

                public void setIsMult(boolean isMult) {
                    this.isMult = isMult;
                }

                public String getNodeType() {
                    return nodeType;
                }

                public void setNodeType(String nodeType) {
                    this.nodeType = nodeType;
                }

                public String getSrclib() {
                    return srclib;
                }

                public void setSrclib(String srclib) {
                    this.srclib = srclib;
                }

                public String getSrclib_label() {
                    return srclib_label;
                }

                public void setSrclib_label(String srclib_label) {
                    this.srclib_label = srclib_label;
                }

                public String getValue_label() {
                    return value_label;
                }

                public void setValue_label(String value_label) {
                    this.value_label = value_label;
                }

                public String getID() {
                    return ID;
                }

                public void setID(String ID) {
                    this.ID = ID;
                }

                public boolean isIsCanDelete() {
                    return isCanDelete;
                }

                public void setIsCanDelete(boolean isCanDelete) {
                    this.isCanDelete = isCanDelete;
                }

                public Object getConfigModifiedField() {
                    return configModifiedField;
                }

                public void setConfigModifiedField(Object configModifiedField) {
                    this.configModifiedField = configModifiedField;
                }

                public boolean isSeeHisWorkOrderInfo() {
                    return seeHisWorkOrderInfo;
                }

                public void setSeeHisWorkOrderInfo(boolean seeHisWorkOrderInfo) {
                    this.seeHisWorkOrderInfo = seeHisWorkOrderInfo;
                }

                public Object getPrecision() {
                    return precision;
                }

                public void setPrecision(Object precision) {
                    this.precision = precision;
                }

                public boolean isIsCanAdd() {
                    return isCanAdd;
                }

                public void setIsCanAdd(boolean isCanAdd) {
                    this.isCanAdd = isCanAdd;
                }

                public Object getAllColumnsJson() {
                    return allColumnsJson;
                }

                public void setAllColumnsJson(Object allColumnsJson) {
                    this.allColumnsJson = allColumnsJson;
                }
            }

            public static class FormItemValueBean {
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
        }
    }
}
