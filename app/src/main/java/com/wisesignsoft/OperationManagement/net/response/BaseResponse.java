package com.wisesignsoft.OperationManagement.net.response;

/**
 * Created by ycs on 2016/11/23.
 */

public class BaseResponse {
    public String returnMsg;
    public String returnState;
    public String i;

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
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
}
