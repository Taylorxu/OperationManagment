package com.wisesignsoft.OperationManagement.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.Constant;
import com.wisesignsoft.OperationManagement.MainActivity;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.User;
import com.wisesignsoft.OperationManagement.db.MySharedpreferences;
import com.wisesignsoft.OperationManagement.mview.LoadingView;
import com.wisesignsoft.OperationManagement.net.response.LoginResponse;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;
import com.wisesignsoft.OperationManagement.utils.GsonHelper;
import com.wisesignsoft.OperationManagement.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_phone;
    private EditText et_pwd;
    private Button bt_login;
    private TextView tv_net_url;
    private String phone, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        et_phone = (EditText) findViewById(R.id.et_login_phone);
        et_pwd = (EditText) findViewById(R.id.et_login_pwd);
        bt_login = (Button) findViewById(R.id.bt_login);
        tv_net_url = (TextView) findViewById(R.id.tv_net_url);

        bt_login.setOnClickListener(this);
        tv_net_url.setOnClickListener(this);
        et_pwd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    submit();
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                submit();
                break;
            case R.id.tv_net_url:
                toServer();
                break;
        }
    }


    private void submit() {
        LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        phone = et_phone.getText().toString().trim();
        pwd = et_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.toast(this, getResources().getString(R.string.login_phone_empty));
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            ToastUtil.toast(this, getResources().getString(R.string.login_pwd_empty));
            return;
        }

        login(loadingView);
    }

    private void login(final LoadingView loadingView) {
        List<String> list = new ArrayList<>();
        list.add(phone);
        list.add(pwd);
        ApiService.Creator.get().login(RequestBody.getgEnvelope(Protocol.yxyw_name_space, list, Protocol.login))
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        LoginResponse result = GsonHelper.build().getObjectByJson(response.body(), LoginResponse.class);
                        if (result.returnState.equals("-1")) {
                            ToastUtil.showMessage(getBaseContext(), result.getReturnMsg());
                        } else {
                            User user = new User();
                            user.setUserId(result.getReturnValue().getUserId());
                            user.setUsername(phone);
                            MySharedpreferences.putUser(user);
                            MySharedpreferences.putStatusBoolean(Constant.ISLOGIN, true);
                            MySharedpreferences.putMapStatusBoolean(true);
                            toMain();
                        }
                        loadingView.stop(loadingView);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        loadingView.stop(loadingView);
                        EEMsgToastHelper.newInstance().selectWitch(t.getMessage());
                    }
                });

    }

    private void toServer() {
        Intent intent = new Intent();
        intent.setClass(this, ServerAddressActivity.class);
        startActivity(intent);
    }

    private void toMain() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
