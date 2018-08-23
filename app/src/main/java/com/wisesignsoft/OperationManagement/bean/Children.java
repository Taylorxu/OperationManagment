package com.wisesignsoft.OperationManagement.bean;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Children extends RealmObject{
    private RealmList<Children> children;
    private String deptCode;
    private String deptFullName;
    private String deptId;
    private String deptNum;
    private int deptOrder;
    private String deptParentId;
    private String deptRemark;
    private String deptShortName;
    private String fullPathName;
    private boolean selected;

    public RealmList<Children> getChildren() {
        return children;
    }

    public void setChildren(RealmList<Children> children) {
        this.children = children;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptFullName() {
        return deptFullName;
    }

    public void setDeptFullName(String deptFullName) {
        this.deptFullName = deptFullName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptNum() {
        return deptNum;
    }

    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    public int getDeptOrder() {
        return deptOrder;
    }

    public void setDeptOrder(int deptOrder) {
        this.deptOrder = deptOrder;
    }

    public String getDeptParentId() {
        return deptParentId;
    }

    public void setDeptParentId(String deptParentId) {
        this.deptParentId = deptParentId;
    }

    public String getDeptRemark() {
        return deptRemark;
    }

    public void setDeptRemark(String deptRemark) {
        this.deptRemark = deptRemark;
    }

    public String getDeptShortName() {
        return deptShortName;
    }

    public void setDeptShortName(String deptShortName) {
        this.deptShortName = deptShortName;
    }

    public String getFullPathName() {
        return fullPathName;
    }

    public void setFullPathName(String fullPathName) {
        this.fullPathName = fullPathName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
