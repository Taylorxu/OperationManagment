package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;

/**
 * Created by ycs on 2016/12/1.
 */

public class SectionView extends RelativeLayout implements View.OnClickListener {

    private TextView tv_section_view;
    private LinearLayout ll_section_view;
    private RelativeLayout rl_section_view;

    private boolean isShow = false;

    public SectionView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.section_view, this, true);
        rl_section_view = (RelativeLayout) view.findViewById(R.id.rl_top);
        tv_section_view = (TextView) view.findViewById(R.id.tv_section_view);
        ll_section_view = (LinearLayout) view.findViewById(R.id.ll_section_view);

        rl_section_view.setOnClickListener(this);
    }

    public void setData(String str, boolean isShow) {
        if (!TextUtils.isEmpty(str)) {
            tv_section_view.setText(str);
        }
        if (isShow) {
            ll_section_view.setVisibility(VISIBLE);
        } else {
            ll_section_view.setVisibility(GONE);
        }
    }

    public LinearLayout getLl_section_view() {
        return ll_section_view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_top) {
            isShow = !isShow;
            if (isShow) {
                ll_section_view.setVisibility(VISIBLE);
            } else {
                ll_section_view.setVisibility(GONE);
            }
        }
    }
}
