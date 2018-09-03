package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.AccountInfoBean;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.ui.activity.SingleUserChooseActivity;
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.RealmObjectChangeListener;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 单选人员组件
 * Created by ycs on 2016/12/15.
 */

public class SingleUserChooseView extends RelativeLayout implements RealmObjectChangeListener<WorkOrder> {
    private TextView tv_single_user_left;
    private TextView tv_single_user_right;
    private RelativeLayout rl_single_user;
    private WorkOrder wo;

    public SingleUserChooseView(Context context) {
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
                SingleUserChooseActivity.startSelf(context, wo.getID());
            }
        });
    }

    public void setDate(final WorkOrder wo) {
        this.wo = wo;
        wo.addChangeListener(this);
        String title = wo.getName();
        final String content = wo.getValue();
        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                tv_single_user_left.setText(title + " *");
            } else {
                tv_single_user_left.setText(title);
            }
        }

        if (!TextUtils.isEmpty(content)) {
            List<String> list = new ArrayList<>();
            list.add(content);
            ApiService.Creator.get().queryValidUsersById(RequestBody.getgEnvelope(Protocol.yxyw_name_space, list, Protocol.queryValidUsersByIds))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(new FlatMapResponse<BaseDataResponse<List<AccountInfoBean>>>())
                    .flatMap(new FlatMapTopRes<List<AccountInfoBean>>(DataTypeSelector.RE))
                    .subscribe(new Subscriber<List<AccountInfoBean>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            EEMsgToastHelper.newInstance().selectWitch(e.getMessage());
                        }

                        @Override
                        public void onNext(List<AccountInfoBean> accountInfoBeans) {
                            if (accountInfoBeans == null) {
                                String userName = accountInfoBeans.get(0).getUserName();
                                tv_single_user_right.setText(userName);
                            }
                        }
                    });
        } else {
            tv_single_user_right.setText("");
        }
        if (!wo.isModified()) {
            rl_single_user.setEnabled(false);
        } else {
            rl_single_user.setEnabled(true);
        }
    }

    @Override
    public void onChange(WorkOrder workOrder, @Nullable ObjectChangeSet changeSet) {
        if (changeSet.isDeleted()) return;
        setDate(workOrder);
        WorkOrderDataManager.newInstance().setValueForLinkWorkOrder(workOrder);
    }
}
