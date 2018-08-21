package com.wisesignsoft.OperationManagement.db;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.wisesignsoft.OperationManagement.Constant;
import com.wisesignsoft.OperationManagement.MyApplication;
import com.wisesignsoft.OperationManagement.bean.User;

/**
 * Created by Mr.Z on 15/12/11.
 */
public class MySharedpreferences {
    static Context context = MyApplication.getContext();
    /*状态信息的sp*/
    static SharedPreferences statusSp = context.getSharedPreferences(Constant.STATUSINFO, Activity.MODE_PRIVATE);
    /*服务器地址*/
    static SharedPreferences serverSp = context.getSharedPreferences(Constant.SERVERURL, Activity.MODE_PRIVATE);

    /*除了服务器地址，其他都清空了*/
    public static void clear() {
        statusSp.edit().clear().commit();
    }



    /**
     * 保存状态
     *
     * @param key
     * @param status
     */
    public static void putStatusBoolean(String key, boolean status) {
        statusSp.edit().putBoolean(key, status).commit();
    }

    /**
     * 获取地图开启状态
     *
     * @return
     */
    public static boolean getMapStatusBoolean() {
        boolean status = statusSp.getBoolean(Constant.ISDING, true);
        return status;
    }

    /**
     * 设置地图状态
     *
     * @param is
     */
    public static void putMapStatusBoolean(boolean is) {
        MySharedpreferences.putStatusBoolean(Constant.ISDING, is);
    }

    /**
     * 获取登录状态
     *
     * @return
     */
    public static boolean getLoginStatusBoolean() {
        boolean status = statusSp.getBoolean(Constant.ISLOGIN, false);
        return status;
    }

    /**
     * 获取是否第一次登录状态
     *
     * @return
     */
    public static boolean getFirstStatusBoolean() {
        boolean status = statusSp.getBoolean(Constant.ISFIRST, false);
        return status;
    }

    /**
     * 保存服务器地址
     *
     * @param value
     */
    public static void putServerString(String value) {
        serverSp.edit().putString(Constant.URL, value).commit();
    }

    /**
     * 获取服务器地址
     *
     * @return
     */
    public static String getServerString() {
        String serverUrl = serverSp.getString(Constant.URL, "");
        return serverUrl;
    }
}
