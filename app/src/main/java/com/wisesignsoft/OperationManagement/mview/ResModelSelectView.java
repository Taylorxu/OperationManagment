package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.ResModeValue;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.ui.activity.SingleResModelSelectorActivity;
import com.wisesignsoft.OperationManagement.utils.GsonHelper;

import java.util.List;

/**
 * 台账单选组件
 */
public class ResModelSelectView extends FrameLayout {

    private BaseView bv_res_model_select;
    private ResModeValue resModelValueJson;
    private WorkOrder workOrder;

    public ResModelSelectView(Context context) {
        super(context);
        init(context);
    }

    private void init(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.res_model_select, this, true);
        bv_res_model_select = (BaseView) view.findViewById(R.id.bv_res_model_select);

        bv_res_model_select.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resModelValueJson.getConfigValueJson() != null) {//单选情况
                    ResModeValue resModeValue = GsonHelper.build().getObjectByJson(workOrder.getResModelValueJson(), ResModeValue.class);
                    SingleResModelSelectorActivity.start(context, resModeValue, workOrder.getID());
                } else {

                }


            }
        });
    }

    public void setDate(WorkOrder wo) {
        this.workOrder = wo;
        String title = wo.getName();
        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                bv_res_model_select.setTv_left(title + " *");
            } else {
                bv_res_model_select.setTv_left(title);
            }
        }
        if (!wo.isModified()) {
            bv_res_model_select.setEnabled(false);
        } else {
            bv_res_model_select.setEnabled(true);
        }
        String att = wo.getResModelValueJson();
        Gson gson = new Gson();
        resModelValueJson = gson.fromJson(att, ResModeValue.class);


    }
}
