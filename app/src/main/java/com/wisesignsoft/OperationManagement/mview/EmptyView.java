package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;


/**
 * Created by ycs on 2016/11/10.
 */

public class EmptyView extends RelativeLayout {
    private ImageView mImageView;
    private TextView mTextView;
    private SwipeRefreshLayout mRefresh;

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_empty, this, true);
        mRefresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_empty);
        mImageView = (ImageView) view.findViewById(R.id.iv_empty);
        mTextView = (TextView) view.findViewById(R.id.tv_empty);
        mRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
    }

    /**
     * 设置图片和文字
     *
     * @param reId
     * @param tv
     */
    public void setData(int reId, String tv) {
//        if (!TextUtils.isEmpty(tv)) {
//            mTextView.setText(tv);
//        }
//        try {
//            mImageView.setImageResource(R.mipmap.gap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 设置刷新监听
     *
     * @param onRefreshListener
     */
    public void setOnRefreshListener(final IRefreshListener onRefreshListener) {
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshListener.onRefresh();
                mRefresh.setRefreshing(false);
            }
        });
    }

    public void close() {
        mRefresh.setRefreshing(false);
    }

    public interface IRefreshListener {
        void onRefresh();
    }
}
