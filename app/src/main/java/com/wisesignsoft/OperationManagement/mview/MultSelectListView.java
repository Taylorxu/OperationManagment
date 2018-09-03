package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.ColumnsJsonBean;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.ui.activity.ListActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.RealmObjectChangeListener;

/**
 * 添加普通列表
 * Created by ycs on 2016/12/27.
 */

public class MultSelectListView extends ListDateView implements ListDateView.IListDateClickListener, RealmObjectChangeListener<WorkOrder> {
    private WorkOrder wo;
    private List<ColumnsJsonBean> datas;

    public MultSelectListView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        initView(context, R.mipmap.add1, "添加列表");
        setIListDataClickListener(this);
    }

    public void setData(WorkOrder wo) {
        this.wo = wo;
        wo.addChangeListener(this);
        setTitle(wo.getName());
        hideAddBtn(wo.isModified());
        String columnsJson = wo.getColumnsJson();
        Gson gson = new Gson();
        datas = gson.fromJson(columnsJson, new TypeToken<List<ColumnsJsonBean>>() {
        }.getType());
        String str = wo.getValue();
        clearView();//重新赋值时 删除控件 与数据保持一致
        if (!TextUtils.isEmpty(str)) {
            List<Map<String, String>> list = gson.fromJson(str, new TypeToken<List<Map<String, String>>>() {
            }.getType());
            for (int i = 0; i < list.size(); i++) {
                StandBookView standBookView = new StandBookView(getContext());
                standBookView.setDeletePosition(i);
                standBookView.setDeleteHide(wo.isModified());
                addStandBook(standBookView);
                standBookView.setData1(getContext(), datas, list.get(i), 1, wo.getID());
            }
        }
    }

    @Override
    public void setOnAddView() {
        Gson gson = new Gson();
        String str = wo.getValue();
        List<Map<String, String>> list = gson.fromJson(str, new TypeToken<List<Map<String, String>>>() {
        }.getType());
        Map<String, String> map = new HashMap<>();
        StandBookView standBookView = new StandBookView(getContext());
        standBookView.setDeletePosition(list == null ? 0 : list.size());
        ListActivity.startSelf(getContext(), map, datas, wo.getID(), list == null ? 0 : list.size());
    }

    @Override
    public void setOnDelView(StandBookView standBookView) {
        Gson gson = new Gson();
        String str = wo.getValue();
        List<Map<String, String>> list = gson.fromJson(str, new TypeToken<List<Map<String, String>>>() {
        }.getType());
        if (list.size() > 0) {
            list.remove(standBookView.getDeletePosition());
            wo.setValue(list.toString());
            String temp = gson.toJson(list);
            WorkOrderDataManager.newInstance().modifyValue(wo.getID(), temp);
        }
    }

    @Override
    public void onChange(WorkOrder workOrder, @Nullable ObjectChangeSet changeSet) {
        if (changeSet.isDeleted()) return;
        setData(workOrder);
        WorkOrderDataManager.newInstance().setValueForLinkWorkOrder(workOrder);

    }
}
