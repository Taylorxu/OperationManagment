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
import com.wisesignsoft.OperationManagement.utils.LogUtil;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.RealmObjectChangeListener;

/**
 * 单行输入组件
 * TODO 列表控件 是一个有多个类似textView的布局。指定的model 是 ColumnsJsonBean 。所以需要 单独写一个textView
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
    public void setData(WorkOrder wo) {
        this.wo = wo;
        wo.addChangeListener(this);
        String title = wo.getName();
        String content = wo.getValue();
        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                tv_single_text.setText(title + " *");
            } else {
                tv_single_text.setText(title);
            }
        }
        if (TextUtils.isEmpty(content)) {
            et_single_text.setText("");
        }
        if (!TextUtils.isEmpty(content) && !content.equals(et_single_text.getText().toString())) {
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
        setData(workOrder);
    }
}
