package com.wisesignsoft.OperationManagement;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestClass {
    static String jsonString = "[{\n" +
            "                \"connector\":\"like\",\n" +
            "                \"dmAttrName\":\"PROJ_NA\",\n" +
            "                \"value\":\"\"}]";

    public static void main(String arg[]) {
        List<String> ids = new ArrayList<>();
        ids.add("is_wota:899af468-02cd-4e9f-a8c9-b9399006d9aa");
        String idString = ids.toString();
        String result = idString.substring(1, idString.length() - 1);
        String links = jsonString.substring(1, jsonString.length() - 1);
        System.out.print(result);
    }
}
