package com.wisesignsoft.OperationManagement.utils;

/**
 * Created by ycs on 2016/12/1.
 */

public class ViewUtil {
    public static int set(String viewName) {
        int index = -1;
        switch (viewName) {
            case "Section":
                index = 0;
                break;
            case "Button":
                index = 1;
                break;
        }
        return index;
    }
}

