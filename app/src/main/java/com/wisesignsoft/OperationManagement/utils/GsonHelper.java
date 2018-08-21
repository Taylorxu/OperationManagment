package com.wisesignsoft.OperationManagement.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wisesignsoft.OperationManagement.net.response.BaseListDataResponse;

import java.util.List;

public class GsonHelper {
    public static GsonHelper gsonHelper = null;

    public static GsonHelper build() {
        if (gsonHelper == null) {
            gsonHelper = new GsonHelper();
        }
        return gsonHelper;
    }

    public <T> T getObjectByJson(String stringJson, Class<T> o) {
        Gson gson = new Gson();
        T t = gson.fromJson(stringJson, o);
        return t;
    }/*

    public List<D> getListObjectByJson(String stringJson) {
        Gson gson = new Gson();
        List<D> r = gson.fromJson(stringJson, new TypeToken<List<D>>() {
        }.getType());
        return r;
    }*/

}
