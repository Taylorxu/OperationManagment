package com.wisesignsoft.OperationManagement;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class TestClass {
    static String jsonString = "[{\n" +
            "                \"connector\":\"like\",\n" +
            "                \"dmAttrName\":\"PROJ_NA\",\n" +
            "                \"value\":\"\"}]";

    public static void main(String arg[]) {

        Gson gson = new Gson();
        List links = gson.fromJson(jsonString, new TypeToken<List<Map<String, Object>>>() {
        }.getType());
        System.out.print(links);
    }
}
