package com.wisesignsoft.OperationManagement.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ycs on 2016/11/17.
 */

public class MyUtils {
    /**
     * 手机号验证
     *
     * @param tel
     * @return 验证通过返回true
     */
    public static boolean isPhone(String tel) {
        Pattern p;
        Matcher m;
        boolean b;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(tel);
        b = m.matches();
        return b;
    }
}
