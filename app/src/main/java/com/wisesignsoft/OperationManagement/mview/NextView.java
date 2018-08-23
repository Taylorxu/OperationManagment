package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;

/**
 * Created by ycs on 2016/11/30.
 */

public class NextView extends RelativeLayout {

    private RelativeLayout rl_next_view;
    private TextView tv_next_view;
    private ImageView iv_next_view;

    public NextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.next_view, this, true);
        rl_next_view = (RelativeLayout) view.findViewById(R.id.rl_next_view);
        tv_next_view = (TextView) view.findViewById(R.id.tv_next_view);
        iv_next_view = (ImageView) view.findViewById(R.id.iv_next_view);
    }

    public void setData(String title) {
        if (!TextUtils.isEmpty(title)) {
            tv_next_view.setText(title);
        }
    }

    public void setIV(boolean isShow) {
        if (isShow) {
            iv_next_view.setVisibility(VISIBLE);
        } else {
            iv_next_view.setVisibility(GONE);
        }
    }
}
