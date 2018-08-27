package com.wisesignsoft.OperationManagement.bean;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DictDatas extends RealmObject {
    private String isTree;
    @PrimaryKey
    private int id;
    private String key;
    private RealmList<DictDatasBean> dictDatas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
