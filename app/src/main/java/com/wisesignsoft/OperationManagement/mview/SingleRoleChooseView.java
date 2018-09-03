package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.GroupBean;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.ui.activity.SingleRoleChooseActivity;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.RealmObjectChangeListener;

/**
 * 角色单选组件
 * Created by ycs on 2016/12/12.
 */
public class SingleRoleChooseView extends RelativeLayout implements RealmObjectChangeListener<WorkOrder> {

    private TextView tv_single_user_left;
    private TextView tv_single_user_right;
    private RelativeLayout rl_single_user;
    private WorkOrder wo;

    public SingleRoleChooseView(Context context) {
        super(context);
        init(context);
    }

    private void init(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_user_choose, this, true);
        rl_single_user = (RelativeLayout) view.findViewById(R.id.rl_single_user);
        tv_single_user_left = (TextView) view.findViewById(R.id.tv_single_user_left);
        tv_single_user_right = (TextView) view.findViewById(R.id.tv_single_user_right);
        rl_single_user.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                GroupBean bean = gson.fromJson(wo.getParentRoleJSON(), GroupBean.class);
                SingleRoleChooseActivity.startSelf(context, bean.getGroupId(), wo.getID());
            }
        });
    }

    public void setData(WorkOrder wo) {
        this.wo = wo;
        wo.addChangeListener(this);
        String title = wo.getName();
        String content = wo.getValue();
        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                tv_single_user_left.setText(title + " *");
            } else {
                tv_single_user_left.setText(title);
            }
        }
        if (!TextUtils.isEmpty(content)) {
            tv_single_user_right.setText(content);
        } else {
            tv_single_user_right.setText("");
        }
        if (!wo.isModified()) {
            rl_single_user.setFocusable(false);
            rl_single_user.setClickable(false);
            rl_single_user.clearFocus();
        } else {
            rl_single_user.setFocusable(true);
            rl_single_user.setClickable(true);
            rl_single_user.setFocusableInTouchMode(true);
        }
    }

    @Override
    public void onChange(WorkOrder workOrder, @Nullable ObjectChangeSet changeSet) {
        if (changeSet.isDeleted()) return;
        setData(workOrder);
    }
}
