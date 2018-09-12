package com.wisesignsoft.OperationManagement.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class GsonHelper {
    public static GsonHelper gsonHelper = null;
    static Gson gson;

    public GsonHelper() {
        gson = new Gson();
    }

    public static GsonHelper build() {
        if (gsonHelper == null) {
            gsonHelper = new GsonHelper();
        }
        return gsonHelper;
    }

    public <T> T getObjectByJson(String stringJson, Class<T> o) {

        T t = gson.fromJson(stringJson, o);
        return t;
    }

    public <T> T getListObjectByJson(String stringJson, Type type) {

        T r = gson.fromJson(stringJson, type);
        return r;
    }

    public String objectToJsonString(Object object) {
        return gson.toJson(object);
    }

}
