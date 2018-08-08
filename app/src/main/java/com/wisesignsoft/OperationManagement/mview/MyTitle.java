package com.wisesignsoft.OperationManagement.mview;

import android.app.Activity;
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
 * Created by ycs on 2016/11/18.
 */

public class MyTitle extends RelativeLayout {

    private TextView tv_back;
    private TextView tv_title;
    private ImageView iv_right;
    private TextView tv_title_right;

    public MyTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_title_view, this, true);
        tv_back = (TextView) view.findViewById(R.id.tv_title_back);
        tv_title = (TextView) view.findViewById(R.id.tv_title_title);
        iv_right = (ImageView) view.findViewById(R.id.iv_title_right);
        tv_title_right = (TextView) view.findViewById(R.id.tv_title_right);
    }

    /**
     * 左边返回键
     * @param isShow
     * @param activity
     */
    public void setBack(boolean isShow, final Activity activity) {
        if (isShow) {
            tv_back.setVisibility(VISIBLE);
        } else {
            tv_back.setVisibility(GONE);
        }
        tv_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }

    /**
     * 需要重写返回键的
     * @param isShow
     * @param listener
     */
    public void setBackListener(boolean isShow,OnClickListener listener){
        if (isShow) {
            tv_back.setVisibility(VISIBLE);
        } else {
            tv_back.setVisibility(GONE);
        }
        tv_back.setOnClickListener(listener);
    }

    /**
     * 设置中间文字
     * @param title
     */
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title))
            tv_title.setText(title);
    }

    /**
     * 设置右边图片
     * @param isShow
     * @param reId
     * @param listener
     */
    public void setIvRight(boolean isShow, int reId, OnClickListener listener) {
        if (isShow) {
            iv_right.setVisibility(VISIBLE);
        } else {
            iv_right.setVisibility(GONE);
        }
        if (reId != -1) {
            iv_right.setImageResource(reId);
        }
        if (listener != null)
            iv_right.setOnClickListener(listener);
    }
    /**
     * 设置右边文字
     * @param isShow
     * @param title
     * @param listener
     */
    public void setTvRight(boolean isShow, String title, OnClickListener listener) {
        if (isShow) {
            tv_title_right.setVisibility(VISIBLE);
        } else {
            iv_right.setVisibility(GONE);
        }
        if (!TextUtils.isEmpty(title)) {
            tv_title_right.setText(title);
        }
        if (listener != null)
            tv_title_right.setOnClickListener(listener);
    }
}
