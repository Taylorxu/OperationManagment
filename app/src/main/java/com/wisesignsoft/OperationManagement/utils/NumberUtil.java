package com.wisesignsoft.OperationManagement.utils;


public class NumberUtil {/*
    public static void main(String arg[]) {
        System.out.println(Math.ceil(10.891));
    }*/

    public static boolean isInt(String str) {
        boolean isIt = true;
        final char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {

            } else {
                isIt = false;
                break;
            }
        }
        return isIt;
    }
}
