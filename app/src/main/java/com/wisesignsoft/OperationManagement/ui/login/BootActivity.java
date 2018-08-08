package com.wisesignsoft.OperationManagement.ui.login;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.MainActivity;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.db.MySharedpreferences;


public class BootActivity extends BaseActivity {

    private ImageView iv_boot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
        String url = MySharedpreferences.getServerString();
        if (TextUtils.isEmpty(url)) {
            MySharedpreferences.putServerString("http://omm.trustfar.cn:9180/IMS/");
        }
        initView();
    }

    private void initView() {
        iv_boot = (ImageView) findViewById(R.id.iv_boot);
        setAnimation(iv_boot);
    }

    private void setAnimation(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0.1f, 1.0f).setDuration(2000);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (MySharedpreferences.getLoginStatusBoolean()) {
                    toMain();
                } else {
                    toLogin();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        objectAnimator.setRepeatCount(0);
        objectAnimator.start();
    }

    private void toLogin() {
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void toMain() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
