package com.wisesignsoft.OperationManagement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        int error_code = intent.getIntExtra("error_code", 0);
        String msg = intent.getStringExtra("msg");
        if (!"0".equals(error_code)) {
            Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}