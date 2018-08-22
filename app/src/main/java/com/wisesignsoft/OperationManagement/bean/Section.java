package com.wisesignsoft.OperationManagement.bean;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Section extends RealmObject {
    @PrimaryKey
    private String ID;
    private String label;
    private boolean isCurrent;
    private RealmList<WorkOrder> section;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public RealmList<WorkOrder> getSection() {
        return section;
    }

    public void setSection(RealmList<WorkOrder> section) {
        this.section = section;
    }
}
