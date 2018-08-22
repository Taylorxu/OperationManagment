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

/**
 * 单行输入组件
 * Created by ycs on 2016/11/30.
 */
public class SingleTextView extends LinearLayout {

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
}
