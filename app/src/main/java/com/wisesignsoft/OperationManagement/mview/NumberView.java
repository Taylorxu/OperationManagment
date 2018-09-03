package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.utils.LogUtil;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.RealmObjectChangeListener;

/**
 * 数字组件
 * Created by ycs on 2016/12/12.
 */
public class NumberView extends LinearLayout implements RealmObjectChangeListener<WorkOrder> {
    private TextView tv_number;
    private EditText et_number;
    private WorkOrder wo;

    public NumberView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.number_view, this, true);
        tv_number = (TextView) view.findViewById(R.id.tv_number);
        et_number = (EditText) view.findViewById(R.id.et_number);

    }

    public void setData(WorkOrder wo) {
        this.wo = wo;
        wo.addChangeListener(this);
        String title = wo.getName();
        String content = wo.getValue();
        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                tv_number.setText(title + " *");
            } else {
                tv_number.setText(title);
            }
        }
        if (!TextUtils.isEmpty(content) && !content.equals(et_number.getText().toString().trim())) {
            et_number.setText(content);
        }
        if (!wo.isModified()) {
            et_number.clearFocus();
            et_number.setClickable(false);
            et_number.setFocusable(false);
        } else {
            et_number.setClickable(true);
            et_number.setFocusable(true);
            et_number.setFocusableInTouchMode(true);
        }
    }
    /**
     * 列表控件赋值用
     * @param key
     * @param value
     */
    public void setData(String key,String value){
        if (!TextUtils.isEmpty(key)) {
            tv_number.setText(key);
        }
        if(!TextUtils.isEmpty(value)){
            et_number.setText(value);
        }
    }

    public String getValue() {
        return et_number.getText().toString().trim();
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
