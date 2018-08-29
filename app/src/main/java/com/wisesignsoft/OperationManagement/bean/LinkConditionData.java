package com.wisesignsoft.OperationManagement.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 控件之间 相互影响的依据数据模型
 */
public class LinkConditionData extends RealmObject {
    @PrimaryKey
    private int id;
    private String workOrderId;
    private String expreDesc;
    private String methodName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(String workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getExpreDesc() {
        return expreDesc;
    }

    public void setExpreDesc(String expreDesc) {
        this.expreDesc = expreDesc;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
