package com.wisesignsoft.OperationManagement.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.wisesignsoft.OperationManagement.net.response.BaseResponse;

public class TaskDetailBean extends BaseResponse implements Parcelable {

    private String state;
    private String PIKEY;
    private String CURRENT_TASKID;
    private String firstrequest;
    private String taskNodeType;
    private String formDocument;
    private String data;

    protected TaskDetailBean(Parcel in) {
        state = in.readString();
        PIKEY = in.readString();
        CURRENT_TASKID = in.readString();
        firstrequest = in.readString();
        taskNodeType = in.readString();
        formDocument = in.readString();
        data = in.readString();
    }

    public static final Creator<TaskDetailBean> CREATOR = new Creator<TaskDetailBean>() {
        @Override
        public TaskDetailBean createFromParcel(Parcel in) {
            return new TaskDetailBean(in);
        }

        @Override
        public TaskDetailBean[] newArray(int size) {
            return new TaskDetailBean[size];
        }
    };

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPIKEY() {
        return PIKEY;
    }

    public void setPIKEY(String PIKEY) {
        this.PIKEY = PIKEY;
    }

    public String getCURRENT_TASKID() {
        return CURRENT_TASKID;
    }

    public void setCURRENT_TASKID(String CURRENT_TASKID) {
        this.CURRENT_TASKID = CURRENT_TASKID;
    }

    public String getFirstrequest() {
        return firstrequest;
    }

    public void setFirstrequest(String firstrequest) {
        this.firstrequest = firstrequest;
    }

    public String getTaskNodeType() {
        return taskNodeType;
    }

    public void setTaskNodeType(String taskNodeType) {
        this.taskNodeType = taskNodeType;
    }

    public String getFormDocument() {
        return formDocument;
    }

    public void setFormDocument(String formDocument) {
        this.formDocument = formDocument;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(state);
        dest.writeString(PIKEY);
        dest.writeString(CURRENT_TASKID);
        dest.writeString(firstrequest);
        dest.writeString(taskNodeType);
        dest.writeString(formDocument);
        dest.writeString(data);
    }
}
