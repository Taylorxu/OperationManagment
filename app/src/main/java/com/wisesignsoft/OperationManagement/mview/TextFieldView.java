package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;

public class TextFieldView extends RelativeLayout {

    private EditText et;
    private TextView tv_text_field;
    private WorkOrder wo;

    public TextFieldView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.text_field_view, this, true);
        et = (EditText) view.findViewById(R.id.et_text_field);
        tv_text_field = (TextView) view.findViewById(R.id.tv_text_field);
    }

    public void setData(WorkOrder wo) {
        this.wo = wo;
        String title = wo.getName();
        String content = wo.getValue();
        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                tv_text_field.setText(title + " *");
            } else {
                tv_text_field.setText(title);
            }
        }
        if (TextUtils.isEmpty(content)) {
            et.setText("");
        }
        if (!TextUtils.isEmpty(content) && !content.equals(et.getText().toString().trim())) {
            et.setText(content);
        }
        if (!wo.isModified()) {
            et.setEnabled(false);
            et.setFocusable(false);
            et.clearFocus();
        } else {
            et.setEnabled(true);
            et.setFocusable(true);
            et.setFocusableInTouchMode(true);
        }
    }

    /**
     * 列表控件赋值用
     *
     * @param key
     * @param value
     */
    public void setData(String key, String value) {
        if (!TextUtils.isEmpty(key)) {
            tv_text_field.setText(key);
        }
        if (!TextUtils.isEmpty(value)) {
            et.setText(value);
        }
    }

    public String getValue() {
        return et.getText().toString().trim();
    }
}
