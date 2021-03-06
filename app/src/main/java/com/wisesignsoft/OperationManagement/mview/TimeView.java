package com.wisesignsoft.OperationManagement.mview;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.wisesignsoft.OperationManagement.MainActivity;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.utils.LogUtil;

import java.util.Calendar;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.RealmObjectChangeListener;

/**
 * 时间日期选择组件
 */
public class TimeView extends LinearLayout implements View.OnClickListener, RealmObjectChangeListener<WorkOrder> {
    private TextView data_title;
    private TextView data_content;
    private WorkOrder wo;
    private String temp = "";

    public TimeView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.time_view, this, true);
        data_title = (TextView) view.findViewById(R.id.tv_data_title);
        data_content = (TextView) view.findViewById(R.id.tv_data_content);

        data_content.setOnClickListener(this);
    }

    public void setData(WorkOrder wo) {
        this.wo = wo;
        wo.addChangeListener(this);
        String title = wo.getName();
        String content = wo.getValue();
        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                data_title.setText(title + " *");
            } else {
                data_title.setText(title);
            }
        }
        if (!TextUtils.isEmpty(content)) {
            data_content.setText(content);
        } else {
            data_content.setText("");
        }
        if (!wo.isModified()) {
            data_content.clearFocus();
            data_content.setClickable(false);
            data_content.setFocusable(false);
        } else {
            data_content.setClickable(true);
            data_content.setFocusable(true);
            data_content.setFocusableInTouchMode(true);
        }
    }

    /**
     * 列表控件赋值用
     *
     * @param key
     * @param value
     */
    public void setData(String key, String value) {
        if (!TextUtils.isEmpty(key)) {
            data_title.setText(key);
        }
        if (!TextUtils.isEmpty(value)) {
            data_content.setText(value);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_data_content:
                if (!TextUtils.isEmpty(temp)) {
                    temp = "";
                }
                openData();
                break;
        }
    }

    private void openTime() {
        Calendar c = Calendar.getInstance();
        new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        int mHour = hour;
                        int mMinute = minute;
                        //更新EditText控件时间 小于10加0
                        temp = temp + " " + new StringBuilder().append(mHour < 10 ? 0 + mHour : mHour).append(":").append(mMinute < 10 ? 0 + mMinute : mMinute).append(":00").toString();
                        data_content.setText(temp);
                        WorkOrderDataManager.newInstance().modifyValue(wo.getID(), temp);
                    }
                }, c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE), true).show();

    }

    DatePickerDialog datePickerDialog;

    private void openData() {
        if (datePickerDialog != null && datePickerDialog.isShowing()) {
            return;
        }
        Calendar c = Calendar.getInstance();
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        datePickerDialog = new DatePickerDialog(getContext(),
                // 绑定监听器
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String monthStr;
                        String dayStr;
                        int month = monthOfYear + 1;
                        int day = dayOfMonth;
                        if (month < 10) {
                            monthStr = "0" + month;
                        } else {
                            monthStr = month + "";
                        }
                        if (day < 10) {
                            dayStr = "0" + day;
                        } else {
                            dayStr = day + "";
                        }
                        String str = year + "-" + monthStr + "-" + dayStr;
                        temp = temp + str;
                        if (wo.isHasDateTime()) {
                            openTime();
                        } else {
                            data_content.setText(temp);
                            WorkOrderDataManager.newInstance().modifyValue(wo.getID(), temp);
                        }
                    }
                }
                // 设置初始日期
                , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                .get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public String getValue() {
        return temp;
    }

    @Override
    public void onChange(WorkOrder workOrder, @Nullable ObjectChangeSet changeSet) {
        if (changeSet.isDeleted()) {
            return;
        }
        LogUtil.log(workOrder.getViewName() + "组件的value被改成" + workOrder.getValue());
        setData(workOrder);
    }
}
