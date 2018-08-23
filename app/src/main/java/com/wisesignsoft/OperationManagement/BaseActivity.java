package com.wisesignsoft.OperationManagement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.utils.LogUtil;

import io.realm.ObjectChangeSet;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmObjectChangeListener;

/**
 * Created by ycs on 2016/11/17.
 */

public class BaseActivity extends AppCompatActivity {
    private static final int IMAGE_PICKER = 1;
    private static final int CAMERA = 2;
    private static final int MY_PERMISSIONS_REQUEST = 0;//拍照的权限

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Realm.getDefaultInstance().removeAllChangeListeners();
    }
}
