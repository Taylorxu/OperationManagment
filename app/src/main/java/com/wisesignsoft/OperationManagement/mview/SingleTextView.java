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
 * 单行输入组件
 */
public class SingleTextView extends LinearLayout implements RealmObjectChangeListener<WorkOrder> {

    private TextView tv_single_text;
    private EditText et_single_text;
    /*数据*/
    private WorkOrder wo;
    private String key;

    public SingleTextView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_text_view, this, true);
        tv_single_text = (TextView) view.findViewById(R.id.tv_single_text);
        et_single_text = (EditText) view.findViewById(R.id.et_single_text);
    }

    /**
     * 通用工单赋值用
     *
     * @param wo
     */
    public void setData(final WorkOrder wo) {
        this.wo = wo;
        wo.addChangeListener(this);
        String title = wo.getName();
        String content = wo.getValue();
        et_single_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               WorkOrderDataManager.newInstance().modifyValue(wo.getID(),s.toString());
            }
        });
        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                tv_single_text.setText(title + " *");
            } else {
                tv_single_text.setText(title);
            }
        }

        if (!content.equals(et_single_text.getText().toString())) {
            et_single_text.setText(content);
        }
        if (!wo.isModified()) {
            et_single_text.setEnabled(false);
            et_single_text.setFocusable(false);
            et_single_text.clearFocus();
        } else {
            et_single_text.setEnabled(true);
            et_single_text.setFocusable(true);
            et_single_text.setFocusableInTouchMode(true);
        }
    }

    /**
     * 列表控件赋值用
     *
     * @param key
     * @param value
     */
    public void setData(String key, String value, boolean request, boolean modify) {
        if (!TextUtils.isEmpty(key)) {
            if (request) {
                tv_single_text.setText(key + " *");
            } else {
                tv_single_text.setText(key);
            }
        }
        et_single_text.setEnabled(modify);
        et_single_text.setFocusable(modify);
        if (!TextUtils.isEmpty(value)) {
            et_single_text.setText(value);
        }
    }

    public String getValue() {
        return et_single_text.getText().toString().trim();
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public void onChange(WorkOrder workOrder, @Nullable ObjectChangeSet changeSet) {
        if (changeSet.isDeleted()) {
            return;
        }
        LogUtil.log(workOrder.getViewName() + "组件的value被改成" + workOrder.getValue());

        WorkOrderDataManager.newInstance().setValueForLinkWorkOrder(workOrder);
    }
}
