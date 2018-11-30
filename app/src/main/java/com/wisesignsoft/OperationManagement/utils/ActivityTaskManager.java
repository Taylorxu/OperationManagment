package com.wisesignsoft.OperationManagement.utils;


import com.wisesignsoft.OperationManagement.ui.activity.CreateOrderActivity;
import com.wisesignsoft.OperationManagement.ui.activity.OrderSolvedActivity;
import com.wisesignsoft.OperationManagement.ui.activity.ReNewTemplateActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 当 {@OrderSolvedActivity} 界面中的提交按钮，需要跳转到选择下一处理人的界面{@SelectNextStepUserActivity}。
 * 在此界面成功提交单子后，要finish OrderSolvedActivity
 * @param <T>
 */
public class ActivityTaskManager<T> {
    static ActivityTaskManager activityTaskManager=null;

    public static ActivityTaskManager newInstance() {

        if (activityTaskManager == null) {
            activityTaskManager = new ActivityTaskManager();
        }
        return activityTaskManager;
    }

    private List<T> taskList = new ArrayList<>();

   


    public void setList(T activity) {
        try {
            taskList.add(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delList( ) {
        try {
            taskList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        if (taskList.size() > 0) {
            for (T activity : taskList) {
                if(activity instanceof OrderSolvedActivity){
                    ((OrderSolvedActivity)activity).finish();
                }else if(activity instanceof CreateOrderActivity){
                    ((CreateOrderActivity)activity).finish();
                }else if(activity instanceof ReNewTemplateActivity){
                    ((ReNewTemplateActivity)activity).finish();
                }
            }
        }
    }


}
