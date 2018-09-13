package com.wisesignsoft.OperationManagement.net.response;

/**
 * Created by ycs on 2016/11/23.
 */

public class BaseResponse {
    public String i;
    public boolean isSame; //下一环节是否是同一个审批人
    public String returnMsg;
    public String returnState;

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public boolean isSame() {
        return isSame;
    }

    public void setSame(boolean same) {
        isSame = same;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getReturnState() {
        return returnState;
    }

    public void setReturnState(String returnState) {
        this.returnState = returnState;
    }

    public Error getError() {
        return new Error(Integer.decode(getReturnState()), returnMsg);
    }
}
