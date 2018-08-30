package com.wisesignsoft.OperationManagement.bean;

import java.util.List;
import java.util.Map;

public class SingleResModelListData {
    private int total;
    private List<Map<String, String>> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Map<String, String>> getData() {
        return data;
    }

    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }

}
