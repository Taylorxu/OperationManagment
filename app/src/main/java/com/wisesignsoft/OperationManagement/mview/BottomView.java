package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;
import com.wisesignsoft.OperationManagement.bean.EventClassificationModel;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.CallBack;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.ui.activity.EventClassificationActivity;
import com.wisesignsoft.OperationManagement.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.RealmObjectChangeListener;

public class BottomView extends RelativeLayout implements View.OnClickListener, RealmObjectChangeListener<WorkOrder> {
    private BaseView baseView;
    private WorkOrder wo;

    public BottomView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_view, this, true);
        baseView = (BaseView) view.findViewById(R.id.bv_bottom_view);
        baseView.setOnClickListener(this);
    }

    /**
     * @param wo
     */
    public void setData(final WorkOrder wo) {
        this.wo = wo;
        String title = wo.getName();
        String value = wo.getValue();
        WorkOrderDataManager.newInstance().getDicValue(value, new CallBack<String>() {
            @Override
            public void onResponse(String dicValue) {
                if (!TextUtils.isEmpty(dicValue)) {
                    baseView.setTv_right(dicValue);
                } else {
                    baseView.setTv_right("");
                }
            }
        });


        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                baseView.setTv_left(title + " *");
            } else {
                baseView.setTv_left(title);
            }
        }
        if (!wo.isModified()) {
            baseView.clearFocus();
            baseView.setFocusable(false);
            baseView.setClickable(false);
        } else {
            baseView.setFocusable(true);
            baseView.setClickable(true);
            baseView.setFocusableInTouchMode(true);
        }
    }

    @Override
    public void onClick(View view) {

        WorkOrderDataManager.newInstance().getDictDatasBySrclib(wo.getSrclib(), new CallBack<List<DictDatasBean>>() {
            @Override
            public void onResponse(List<DictDatasBean> dictDatasBeans) {
                final BottomDialog dialog = new BottomDialog(getContext(), R.style.add_dialog);
                dialog.setUpData(dictDatasBeans);
                dialog.setOnTitleClickListener(new BottomDialog.OnTitleClickListener() {
                    @Override
                    public void onTitleRightClickListener(String province, String name) {
                        baseView.setTv_right(name);
                        WorkOrderDataManager.newInstance().modifyValue(wo.getID(), province);
                    }
                });
                if (!dialog.isShowing()) {
                    dialog.show();
                }
            }
        });


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
