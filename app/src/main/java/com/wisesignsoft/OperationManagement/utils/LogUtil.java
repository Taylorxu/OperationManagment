package com.wisesignsoft.OperationManagement.utils;

import android.util.Log;

import com.wisesignsoft.OperationManagement.Appconfig;


/**
 * Created by ycs on 2016/11/17.
 */

public class LogUtil {

    public static void log(String msg) {
        if (Appconfig.isLog)
            Log.i("YCS", "log: " + msg);

    }
}
