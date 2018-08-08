package com.wisesignsoft.OperationManagement.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by maurice
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler instance = new CrashHandler();
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    public static CrashHandler getInstance() {
        return instance;
    }

    public void init(Context ctx) {
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //1.获取应用程序版本信息
        StringBuilder sb = new StringBuilder();
        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(mContext.getPackageName(), 0);
            String versionName = info.versionName;
            sb.append("程序版本号为:" + versionName);
            sb.append("\n");
            //2.获取手机硬件信息
            Build build = new Build();//手机硬件信息封装在Builde中，通过反射获取其字段，包括私有 暴力破解
            Field[] fields = build.getClass().getDeclaredFields();
            for (int i = 1; i < fields.length; i++) {
                //暴力访问
                fields[i].setAccessible(true);
                String name = fields[i].getName();
                String value = fields[i].get(null).toString();
                sb.append(name + " = " + value);
                sb.append("\n");
            }
            //3.获取异常报错信息
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String errorInfo = sw.toString();
            sb.append(errorInfo);
            LogUtil.log(sb.toString());
            writeFile(sb);
            //4.上传到服务器
        } catch (Exception e) {
            e.printStackTrace();
        }
        //最后让应用程序自杀
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    private void writeFile(StringBuilder sb) {
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                FileOutputStream fos = null;
                fos = new FileOutputStream(path + "/" + fileName, true);
                fos.write(sb.toString().getBytes());
                fos.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
