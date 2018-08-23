package com.wisesignsoft.OperationManagement.bean;

import java.util.List;

public class DictDatas {
    private String isTree;
    private List<DictDatasBean> dictDatas;

    public String getIsTree() {
        return isTree;
    }

    public void setIsTree(String isTree) {
        this.isTree = isTree;
    }

    public List<DictDatasBean> getDictDatas() {
        return dictDatas;
    }

    public void setDictDatas(List<DictDatasBean> dictDatas) {
        this.dictDatas = dictDatas;
    }
}
