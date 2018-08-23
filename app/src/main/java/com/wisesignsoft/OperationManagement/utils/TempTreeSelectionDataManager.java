package com.wisesignsoft.OperationManagement.utils;

import com.wisesignsoft.OperationManagement.ui.activity.AllDeptTreeActivity;
import com.wisesignsoft.OperationManagement.ui.activity.EventClassificationActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个临时存储activity对象的地方，方便同时销毁多个对象
 * Created by ycs on 2016/12/2.
 */
public class TempTreeSelectionDataManager {
    private static TempTreeSelectionDataManager manager;
    private static List<EventClassificationActivity> list = new ArrayList<>();
    private static List<AllDeptTreeActivity> list2 = new ArrayList<>();
    private static String temp;

    private TempTreeSelectionDataManager() {
    }

    public static synchronized TempTreeSelectionDataManager getManager() {
        if (manager == null) {
            manager = new TempTreeSelectionDataManager();
        }
        return manager;
    }

    public void addEventClassificationActivity(EventClassificationActivity activity) {
        list.add(activity);
    }

    public void removeEventClassificationActivity(EventClassificationActivity activity) {
        try {
            list.remove(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearEventClassificationActivity() {
        if (list.size() != 0) {
            for (EventClassificationActivity activity : list) {
                activity.finish();
            }
        }
        list.clear();
    }
    public void addAllDeptTreeActivity(AllDeptTreeActivity activity) {
        list2.add(activity);
    }

    public void removeAllDeptTreeActivity(AllDeptTreeActivity activity) {
        try {
            list2.remove(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAllDeptTreeActivity() {
        if (list2.size() != 0) {
            for (AllDeptTreeActivity activity : list2) {
                activity.finish();
            }
        }
        list2.clear();
    }

    public void setTemp(String temp) {
        TempTreeSelectionDataManager.temp = temp;
    }

    public String getTemp() {
        return temp;
    }

    public void clearTemp() {
        temp = null;
    }
}
