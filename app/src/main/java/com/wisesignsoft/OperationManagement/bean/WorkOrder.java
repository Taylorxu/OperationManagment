package com.wisesignsoft.OperationManagement.bean;


import java.io.Serializable;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;


public class WorkOrder extends RealmObject implements Serializable {
    @PrimaryKey
    private String ID;
  /*  @LinkingObjects("section")
    private RealmResults<Section> owners;*/
    /*控件名字*/
    private String viewName;
    /*是否显示*/
    private boolean isVisible;
    /*是否可编辑*/
    private boolean modified;
    /*是否查看历史工单记录*/
    private boolean seeHisWorkOrderInfo;
    /*是否业务主键*/
    private boolean hasBusinessKey;
    /*是否派发事件*/
    private boolean isDispatcherEvent;
    /*业务模型编码*/
    private String bmModelName;
    /*数据模型显示名称*/
    private String dmAttrDisplayName;
    /*是否必填*/
    private boolean required;
    /*数据模型字段*/
    private String dmAttrName;
    /*显示名称*/
    private String name;
    /*名字显示框*/
    private String displayName;
    /*=================================特有属性=========================*/
    /*最大可输入字节*/
    private int maxChars;
    /*自动生成规则*/
    private String identityRule;
    /*字典名称*/
    private String srclib_label;
    /*字典模型编码*/
    private String srclib;
    /*是否多选*/
    private boolean isMult;
    /*是否实时更新*/
    private boolean realTimeUpdate;
    /*查询范围*/
    private String queryScope;
    /*携带对端字段的值赋给本端组件规则*/
    private String resModelValueJson;
    /*是否添加*/
    private boolean isCanAdd;
    /*是否修改*/
    private boolean isCanUpdate;
    /*是否删除*/
    private boolean isCanDelete;
    /*所有的列*/
    private String allColumnsJson;
    /*可修改的字段*/
    private String configModifiedField;
    /*显示的列*/
    private String columnsJson;
    /*是否可选以后时间*/
    private boolean isUpdateLaterDate;
    /*是否可选以前时间*/
    private boolean isUpdateBeforeDate;
    /*是否显示时分秒*/
    private boolean hasDateTime;
    /*可输入数字长度*/
    private String inputLength;
    /*数字精度*/
    private String precision;
    /*角色组配置规则*/
    private String parentRoleJSON;
    /*是否显示边框*/
    private boolean isShowBorder;
    /*显示的列配置规则*/
    private String personInfo;
    /*是否过滤人员*/
    private boolean isFilterUser;
    /*显示格式*/
    private String styleJson;
    /*模板配置*/
    private String templateJson;
    /*是否允许删除附件*/
    private boolean isDeleteAttachment;
    /*所属组织*/
    private String parentDeptJSON;
    /*配置规则*/
    private String resModelConfigure;
    private String toDmAttrName;
    private String relationBmModelObj;

    /*选中的值*/
    private String value_label;
    /*当前的值*/
    private String value;
    //    private List<ImageItem> imags;
    /*联动方法*/
    private String methodName;
    /*联动描述*/
    private String expreDesc;
    /*是否联动*/
    private boolean isLink;
    /**
     * methodName ，expreDesc 字段集合
     * 目的：因为存在多个联动，但是在初始化联动数据集合 和 workorder 的isLink 时workorder的expreDesc 也会被更改。
     * 会出现 expreDesc 的值总是最后一个
     */
    private RealmList<byte[]> expreDescList;


   /* public RealmResults<Section> getOwners() {
        return owners;
    }

    public void setOwners(RealmResults<Section> owners) {
        this.owners = owners;
    }*/

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getExpreDesc() {
        return expreDesc;
    }

    public void setExpreDesc(String expreDesc) {
        this.expreDesc = expreDesc;
    }

    public boolean isLink() {
        return isLink;
    }

    public void setLink(boolean link) {
        isLink = link;
    }

   /* public List<ImageItem> getImags() {
        return imags;
    }

    public void setImags(List<ImageItem> imags) {
        this.imags = imags;
    }*/

    public String getToDmAttrName() {
        return toDmAttrName;
    }

    public String getRelationBmModelObj() {
        return relationBmModelObj;
    }

    public void setRelationBmModelObj(String relationBmModelObj) {
        this.relationBmModelObj = relationBmModelObj;
    }

    public void setToDmAttrName(String toDmAttrName) {
        this.toDmAttrName = toDmAttrName;
    }

    public boolean isMult() {
        return isMult;
    }

    public void setMult(boolean mult) {
        isMult = mult;
    }

    public boolean isRealTimeUpdate() {
        return realTimeUpdate;
    }

    public void setRealTimeUpdate(boolean realTimeUpdate) {
        this.realTimeUpdate = realTimeUpdate;
    }

    public String getQueryScope() {
        return queryScope;
    }

    public void setQueryScope(String queryScope) {
        this.queryScope = queryScope;
    }

    public String getResModelValueJson() {
        return resModelValueJson;
    }

    public void setResModelValueJson(String resModelValueJson) {
        this.resModelValueJson = resModelValueJson;
    }

    public boolean isCanAdd() {
        return isCanAdd;
    }

    public void setCanAdd(boolean canAdd) {
        isCanAdd = canAdd;
    }

    public boolean isCanUpdate() {
        return isCanUpdate;
    }

    public void setCanUpdate(boolean canUpdate) {
        isCanUpdate = canUpdate;
    }

    public boolean isCanDelete() {
        return isCanDelete;
    }

    public void setCanDelete(boolean canDelete) {
        isCanDelete = canDelete;
    }

    public String getAllColumnsJson() {
        return allColumnsJson;
    }

    public void setAllColumnsJson(String allColumnsJson) {
        this.allColumnsJson = allColumnsJson;
    }

    public String getConfigModifiedField() {
        return configModifiedField;
    }

    public void setConfigModifiedField(String configModifiedField) {
        this.configModifiedField = configModifiedField;
    }

    public String getColumnsJson() {
        return columnsJson;
    }

    public void setColumnsJson(String columnsJson) {
        this.columnsJson = columnsJson;
    }

    public boolean isUpdateLaterDate() {
        return isUpdateLaterDate;
    }

    public void setUpdateLaterDate(boolean updateLaterDate) {
        isUpdateLaterDate = updateLaterDate;
    }

    public boolean isUpdateBeforeDate() {
        return isUpdateBeforeDate;
    }

    public void setUpdateBeforeDate(boolean updateBeforeDate) {
        isUpdateBeforeDate = updateBeforeDate;
    }

    public boolean isHasDateTime() {
        return hasDateTime;
    }

    public void setHasDateTime(boolean hasDateTime) {
        this.hasDateTime = hasDateTime;
    }

    public String getInputLength() {
        return inputLength;
    }

    public void setInputLength(String inputLength) {
        this.inputLength = inputLength;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getParentRoleJSON() {
        return parentRoleJSON;
    }

    public void setParentRoleJSON(String parentRoleJSON) {
        this.parentRoleJSON = parentRoleJSON;
    }

    public boolean isShowBorder() {
        return isShowBorder;
    }

    public void setShowBorder(boolean showBorder) {
        isShowBorder = showBorder;
    }

    public String getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(String personInfo) {
        this.personInfo = personInfo;
    }

    public boolean isFilterUser() {
        return isFilterUser;
    }

    public void setFilterUser(boolean filterUser) {
        isFilterUser = filterUser;
    }

    public String getStyleJson() {
        return styleJson;
    }

    public void setStyleJson(String styleJson) {
        this.styleJson = styleJson;
    }

    public String getTemplateJson() {
        return templateJson;
    }

    public void setTemplateJson(String templateJson) {
        this.templateJson = templateJson;
    }

    public boolean isDeleteAttachment() {
        return isDeleteAttachment;
    }

    public void setDeleteAttachment(boolean deleteAttachment) {
        isDeleteAttachment = deleteAttachment;
    }

    public String getParentDeptJSON() {
        return parentDeptJSON;
    }

    public void setParentDeptJSON(String parentDeptJSON) {
        this.parentDeptJSON = parentDeptJSON;
    }

    public String getResModelConfigure() {
        return resModelConfigure;
    }

    public void setResModelConfigure(String resModelConfigure) {
        this.resModelConfigure = resModelConfigure;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public String getSrclib() {
        return srclib;
    }

    public void setSrclib(String srclib) {
        this.srclib = srclib;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public boolean isSeeHisWorkOrderInfo() {
        return seeHisWorkOrderInfo;
    }

    public void setSeeHisWorkOrderInfo(boolean seeHisWorkOrderInfo) {
        this.seeHisWorkOrderInfo = seeHisWorkOrderInfo;
    }

    public boolean isHasBusinessKey() {
        return hasBusinessKey;
    }

    public void setHasBusinessKey(boolean hasBusinessKey) {
        this.hasBusinessKey = hasBusinessKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getMaxChars() {
        return maxChars;
    }

    public void setMaxChars(int maxChars) {
        this.maxChars = maxChars;
    }

    public boolean isDispatcherEvent() {
        return isDispatcherEvent;
    }

    public void setDispatcherEvent(boolean dispatcherEvent) {
        isDispatcherEvent = dispatcherEvent;
    }

    public String getBmModelName() {
        return bmModelName;
    }

    public void setBmModelName(String bmModelName) {
        this.bmModelName = bmModelName;
    }

    public String getDmAttrDisplayName() {
        return dmAttrDisplayName;
    }

    public void setDmAttrDisplayName(String dmAttrDisplayName) {
        this.dmAttrDisplayName = dmAttrDisplayName;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    public String getIdentityRule() {
        return identityRule;
    }

    public void setIdentityRule(String identityRule) {
        this.identityRule = identityRule;
    }

    public String getDmAttrName() {
        return dmAttrName;
    }

    public void setDmAttrName(String dmAttrName) {
        this.dmAttrName = dmAttrName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<byte[]> getExpreDescList() {
        return expreDescList;
    }

    public void setExpreDescList(RealmList<byte[]> expreDescList) {
        this.expreDescList = expreDescList;
    }

    @Override
    public String toString() {
        return "WorkOrder{" +
                "viewName='" + viewName + '\'' +
                ", isVisible=" + isVisible +
                ", modified=" + modified +
                ", seeHisWorkOrderInfo=" + seeHisWorkOrderInfo +
                ", hasBusinessKey=" + hasBusinessKey +
                ", isDispatcherEvent=" + isDispatcherEvent +
                ", bmModelName='" + bmModelName + '\'' +
                ", dmAttrDisplayName='" + dmAttrDisplayName + '\'' +
                ", required=" + required +
                ", dmAttrName='" + dmAttrName + '\'' +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", maxChars=" + maxChars +
                ", identityRule='" + identityRule + '\'' +
                ", srclib_label='" + srclib_label + '\'' +
                ", srclib='" + srclib + '\'' +
                ", isMult=" + isMult +
                ", realTimeUpdate=" + realTimeUpdate +
                ", queryScope='" + queryScope + '\'' +
                ", resModelValueJson='" + resModelValueJson + '\'' +
                ", isCanAdd=" + isCanAdd +
                ", isCanUpdate=" + isCanUpdate +
                ", isCanDelete=" + isCanDelete +
                ", allColumnsJson='" + allColumnsJson + '\'' +
                ", configModifiedField='" + configModifiedField + '\'' +
                ", columnsJson='" + columnsJson + '\'' +
                ", isUpdateLaterDate=" + isUpdateLaterDate +
                ", isUpdateBeforeDate=" + isUpdateBeforeDate +
                ", hasDateTime=" + hasDateTime +
                ", inputLength='" + inputLength + '\'' +
                ", precision='" + precision + '\'' +
                ", parentRoleJSON='" + parentRoleJSON + '\'' +
                ", isShowBorder=" + isShowBorder +
                ", personInfo='" + personInfo + '\'' +
                ", isFilterUser='" + isFilterUser + '\'' +
                ", styleJson='" + styleJson + '\'' +
                ", templateJson='" + templateJson + '\'' +
                ", isDeleteAttachment=" + isDeleteAttachment +
                ", parentDeptJSON='" + parentDeptJSON + '\'' +
                ", resModelConfigure='" + resModelConfigure + '\'' +
                ", value_label='" + value_label + '\'' +
                ", value='" + value + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }

}
