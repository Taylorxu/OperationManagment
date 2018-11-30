package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.MyCallBack;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.utils.LogUtil;

import java.util.List;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.RealmObjectChangeListener;

public class BottomView extends RelativeLayout implements RealmObjectChangeListener<WorkOrder> {
    private BaseView baseView;
    private WorkOrder wo;

    public BottomView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_view, this, true);
        baseView = (BaseView) view.findViewById(R.id.bv_bottom_view);
        baseView.setOnClickListener(new ClickListener());
    }

    /**
     * @param wo
     */
    public void setData(final WorkOrder wo) {
        this.wo = wo;
        wo.addChangeListener(this);
        String title = wo.getName();
        final String value = wo.getValue();
        if (!TextUtils.isEmpty(value)) {//用户选择的时候value值是字典的ID
            WorkOrderDataManager.newInstance().getDicValueById(value, wo.getSrclib(), new MyCallBack<String>() {
                @Override
                public void onResponse(String dicValue) {
                    if (!TextUtils.isEmpty(dicValue)) {
                        baseView.setTv_right(dicValue);
                    } else {
                        baseView.setTv_right(value);//value有可能是字典的值；所以查不到直接赋值
                    }
                }
            });
        } else {
            baseView.setTv_right("");
        }


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

    class ClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            WorkOrderDataManager.newInstance().getDictDatasBySrclib(wo.getSrclib(), new MyCallBack<List<DictDatasBean>>() {
                @Override
                public void onResponse(List<DictDatasBean> dictDatasBeans) {
                    final BottomDialog dialog = new BottomDialog(getContext(), R.style.add_dialog);
                    dialog.setUpData(dictDatasBeans);
                    dialog.setOnTitleClickListener(new BottomDialog.OnTitleClickListener() {
                        @Override
                        public void onTitleRightClickListener(String dictId, String dictName) {
                            WorkOrderDataManager.newInstance().modifyValue(wo.getID(), dictId);
                        }
                    });
                    if (!dialog.isShowing()) {
                        dialog.show();
                    }
                }
            });
        }
    }


    @Override
    public void onChange(WorkOrder workOrder, @Nullable ObjectChangeSet changeSet) {
        if (changeSet.isDeleted()) {
            return;
        }
        LogUtil.log(workOrder.getViewName() + "组件的value被改成" + workOrder.getValue());
        setData(workOrder);
        WorkOrderDataManager.newInstance().setValueForLinkWorkOrder(workOrder);
    }
}
