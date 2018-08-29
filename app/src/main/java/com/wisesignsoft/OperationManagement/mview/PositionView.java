package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.MySharedpreferences;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.utils.ToastUtil;

/**
 * Created by ycs on 2017/1/18.
 */

public class PositionView extends RelativeLayout implements View.OnClickListener {

    private TextView tv_title;
    private TextView tv_content;
    private ImageView bt_position;
    private WorkOrder wo;
    private LoadingView loadingView;
    String location;

    public PositionView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.position_view, this, true);
        tv_title = (TextView) view.findViewById(R.id.tv_position_title);
        tv_content = (TextView) view.findViewById(R.id.tv_position_content);
        bt_position = (ImageView) view.findViewById(R.id.bt_position);
        bt_position.setOnClickListener(this);
        loadingView = LoadingView.getLoadingView(context);

        location = MySharedpreferences.getLocation();
        /*if (TextUtils.isEmpty(location)) {
            Intent intent = new Intent(context, MyService.class);
            intent.putExtra("time", 60 * 1000);
            context.startService(intent);
        }*/
    }

    public void setData(WorkOrder wo) {
        this.wo = wo;
        String title = wo.getName();
        String content = wo.getValue();
        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                tv_title.setText(title + " *");
            } else {
                tv_title.setText(title);
            }
        }
        if (TextUtils.isEmpty(content)) {
            tv_content.setText("");
        }
        if (!TextUtils.isEmpty(content) && !content.equals(tv_content.getText().toString())) {
            try {
                if (content.indexOf("|") > -1) {
                    String[] strings = content.split("\\|");
                    if (strings.length >= 2) tv_content.setText(strings[2]);
                } else {
                    tv_content.setText(content);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!wo.isModified()) {
            bt_position.setVisibility(GONE);
        } else {
            bt_position.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        String temp = MySharedpreferences.getLocation();
        String address = null;
        loadingView.show();//根据要求，添加一个loading = ^ =
        if (!TextUtils.isEmpty(temp)) address = temp.split("\\|")[2];

        if (!TextUtils.isEmpty(address) && !address.equals("null")) {
            try {
                tv_content.setText(address);
                WorkOrderDataManager.newInstance().modifyValue(wo.getID(), temp);
                loadingView.stop(loadingView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ToastUtil.toast(getContext(), "无法获取地址");
            tv_content.setText("");
            loadingView.stop(loadingView);
        }
    }

}
