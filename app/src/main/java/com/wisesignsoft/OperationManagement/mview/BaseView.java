package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;

/**
 * Created by ycs on 2016/12/15.
 */

public class BaseView extends RelativeLayout {

    private TextView tv_left;
    private TextView tv_right;

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.base_view, this, true);
        tv_left = (TextView) view.findViewById(R.id.tv_base_left);
        tv_right = (TextView) view.findViewById(R.id.tv_base_right);
    }

    public void setTv_left(String title) {
        if (!TextUtils.isEmpty(title)) {
            tv_left.setText(title);
        }
    }

    public void setTv_right(String content) {
        if (!TextUtils.isEmpty(content)) {
            tv_right.setText(content);
        } else {
            tv_right.setText("");
        }
    }

    public String getTvRightText() {
        return tv_right.getText().toString();
    }
}
