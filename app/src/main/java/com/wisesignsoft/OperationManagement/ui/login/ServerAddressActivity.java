package com.wisesignsoft.OperationManagement.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.db.MySharedpreferences;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.utils.ToastUtil;


public class ServerAddressActivity extends BaseActivity implements View.OnClickListener {

    private MyTitle mt_server;
    private EditText et_server;
    private Button bt_server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_address);
        initView();
    }

    private void initView() {
        mt_server = (MyTitle) findViewById(R.id.mt_server);
        et_server = (EditText) findViewById(R.id.et_server_url);
        bt_server = (Button) findViewById(R.id.bt_server_save);

        mt_server.setBack(true, this);
        mt_server.setTitle(getResources().getString(R.string.server_title));

        String hostUrl = Protocol.getHostUrl();
        if (!TextUtils.isEmpty(hostUrl)) {
            et_server.setText(hostUrl);
        }
        bt_server.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String server_url = et_server.getText().toString().trim();
        if (TextUtils.isEmpty(server_url)) {
            ToastUtil.toast(this, getResources().getString(R.string.toast_server));
            return;
        }
        MySharedpreferences.putServerString(server_url);
        ToastUtil.toast(this, getResources().getString(R.string.toast_server_save));
        finish();
    }
}
