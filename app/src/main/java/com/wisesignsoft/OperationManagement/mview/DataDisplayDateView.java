package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.utils.LogUtil;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.RealmObjectChangeListener;

public class DataDisplayDateView extends LinearLayout implements RealmObjectChangeListener<WorkOrder> {

    private KeyValueView kvv_data_display_date;

    public DataDisplayDateView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.data_display_date, this, true);
        kvv_data_display_date = (KeyValueView) view.findViewById(R.id.kvv_data_display_date);
    }

    public void setData(WorkOrder wo) {
        wo.addChangeListener(this);
        String title = wo.getName() + "：";
        String content = wo.getValue();
        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                kvv_data_display_date.setKeyText(title + " *");
            } else {
                kvv_data_display_date.setKeyText(title);
            }
        }
        if (!TextUtils.isEmpty(content)) {
            kvv_data_display_date.setValueText(content);
        } else {
            kvv_data_display_date.setValueText("");
        }
    }

    @Override
    public void onChange(WorkOrder workOrder, @Nullable ObjectChangeSet changeSet) {
        if (changeSet.isDeleted()) {
            return;
        }
        LogUtil.log(workOrder.getViewName() + "组件的value被改成" + workOrder.getValue());
        setData(workOrder);
    }
}
