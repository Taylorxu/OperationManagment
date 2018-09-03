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

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.ColumnsProperty;
import com.wisesignsoft.OperationManagement.bean.Dict;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;

import java.util.Calendar;
import java.util.Map;

/**
 * 时间日期组件
 * 台账组件
 */
public class TimeViewForDataGrid extends LinearLayout implements View.OnClickListener {
    private TextView data_title;
    private TextView data_content;
    private WorkOrder wo;
    private String temp = "";
    private String key;

    public TimeViewForDataGrid(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.time_view, this, true);
        data_title = (TextView) view.findViewById(R.id.tv_data_title);
        data_content = (TextView) view.findViewById(R.id.tv_data_content);

        data_content.setOnClickListener(this);
    }

    public void setData(WorkOrder wo, ColumnsProperty b, Map<String, Dict> map) {
        this.wo = wo;
        String title = b.getName();
        String content = "";
        if (map.size() > 0) map.get(b.getDmAttrName()).getValue();
        if (b.isRequired()) {
            data_title.setText(title + " *");
        } else {
            data_title.setText(title);
        }
        data_content.setText(content);
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
                    }
                }, c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE), true).show();

    }

    private void openData() {
        Calendar c = Calendar.getInstance();
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(getContext(),
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
                        }
                    }
                }
                // 设置初始日期
                , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                .get(Calendar.DAY_OF_MONTH)).show();
    }

    public String getValue() {
        return temp;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
