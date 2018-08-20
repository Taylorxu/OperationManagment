package com.wisesignsoft.OperationManagement.bean;

import com.wisesignsoft.OperationManagement.net.response.BaseResponse;

public class ReturnValue extends BaseResponse {


    private String returnValue;

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }
}
