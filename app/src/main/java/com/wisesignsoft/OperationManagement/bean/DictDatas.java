package com.wisesignsoft.OperationManagement.bean;

import java.util.List;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DictDatas extends RealmObject {

    public DictDatas(String key) {
        this.key = key;
    }

    public DictDatas() {
    }

    private String isTree;
    @PrimaryKey
    private String id=UUID.randomUUID().toString();
    private String key;
    private RealmList<DictDatasBean> dictDatas;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIsTree() {
        return isTree;
    }

    public void setIsTree(String isTree) {
        this.isTree = isTree;
    }

    public RealmList<DictDatasBean> getDictDatas() {
        return dictDatas;
    }

    public void setDictDatas(RealmList<DictDatasBean> dictDatas) {
        this.dictDatas = dictDatas;
    }
}
