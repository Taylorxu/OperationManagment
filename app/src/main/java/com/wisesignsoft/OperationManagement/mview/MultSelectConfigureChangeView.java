package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.ResModelConfigure;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.ui.activity.MultSelectConfigureListActivity;

import java.util.List;
import java.util.Map;

/**
 * 添加配置项列表
 * Created by ycs on 2016/12/27.
 */

public class MultSelectConfigureChangeView extends ListDateView implements ListDateView.IListDateClickListener {
    private WorkOrder wo;
    private ResModelConfigure datas;

    public MultSelectConfigureChangeView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        initView(context, R.mipmap.peizhi, "添加配置项");
        setIListDataClickListener(this);
    }

    public void setData(WorkOrder wo) {
        this.wo = wo;
        setTitle(wo.getName());
        hideAddBtn(wo.isModified());
        String configure = wo.getResModelConfigure();
        Gson gson = new Gson();
        datas = gson.fromJson(configure, ResModelConfigure.class);
        String str = wo.getValue();
        List<Map<String, Object>> list;
        clearView();
        if (!TextUtils.isEmpty(str)) {
            list = gson.fromJson(str, new TypeToken<List<Map<String, Object>>>() {
            }.getType());
            for (int i = 0; i < list.size(); i++) {
                StandBookView standBookView = new StandBookView(getContext());
                addStandBook(standBookView);
                standBookView.setDeletePosition(i);
                standBookView.setDeleteHide(wo.isModified());
                standBookView.setData2(getContext(), datas, list.get(i), 2);
            }
        }
    }

    @Override
    public void setOnAddView() {
        Gson gson = new Gson();
        ResModelConfigure c = gson.fromJson(wo.getResModelConfigure(), ResModelConfigure.class);
        MultSelectConfigureListActivity.startSelf(getContext(), wo.getValue(), wo.getID(), c.getClassName());
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
}
