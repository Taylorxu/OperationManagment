package com.wisesignsoft.OperationManagement.net.response;

import android.os.Bundle;

import com.wisesignsoft.OperationManagement.bean.TaskItem;
import com.wisesignsoft.OperationManagement.net.response.BaseResponse;

import java.util.List;

/**
 * Created by xuzhiguang on 2018/08/21.
 */

public class BaseListDataResponse<T> extends BaseResponse {

    private String total;
    private T data;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
