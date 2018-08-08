package com.wisesignsoft.OperationManagement.mview;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.wisesignsoft.OperationManagement.R;


/**
 * Created by ycs on 2016/10/31.
 */

public class LoadingView extends Dialog {
    private static LoadingView loadingView;

    private LoadingView(Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    public static synchronized LoadingView getLoadingView(Context context) {
        if (loadingView != null) {
            loadingView.cancel();
            loadingView.dismiss();
            loadingView = null;
        }
        loadingView = new LoadingView(context, R.style.LoadingStyle);
        return loadingView;
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.loadingview, null);
        ImageView iv_loading = (ImageView) view.findViewById(R.id.iv_loading);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_loading);
        iv_loading.startAnimation(animation);
        setContentView(view);
    }

    public void stop(LoadingView loadingView) {
        if (loadingView != null) {
            loadingView.cancel();
            loadingView.dismiss();
        }
    }
}
