package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;
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
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;
import com.wisesignsoft.OperationManagement.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.RealmObjectChangeListener;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 人员展示控件
 */

public class DataDisplayUserView extends RelativeLayout implements RealmObjectChangeListener<WorkOrder> {

    private TextView tv_data_display_user_text;
    private TextView tv_data_display_user;

    public DataDisplayUserView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.data_display_user, this, true);
        tv_data_display_user_text = (TextView) view.findViewById(R.id.tv_data_display_user_text);
        tv_data_display_user = (TextView) view.findViewById(R.id.tv_data_display_user);
    }

    public void setData(WorkOrder wo) {
        wo.addChangeListener(this);
        String title = wo.getName();
        String content = wo.getValue();
        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                tv_data_display_user_text.setText(title + " *");
            } else {
                tv_data_display_user_text.setText(title);
            }
        }
        if (!TextUtils.isEmpty(content)) {
            getUserName(content);
        } else if (!TextUtils.isEmpty(wo.getDisplayName())) {
            tv_data_display_user.setText(wo.getDisplayName());
        }
    }

    private void getUserName(String id) {
        List<String> list = new ArrayList<>();
        list.add(id);
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
                        String userName = accountInfoBeans.get(0).getUserName();
                        if (!TextUtils.isEmpty(userName)) {
                            tv_data_display_user.setText(userName);
                        } else {
                            tv_data_display_user.setText("");
                        }
                    }
                });
    }

    @Override
    public void onChange(WorkOrder workOrder, @Nullable ObjectChangeSet changeSet) {
        if (changeSet.isDeleted()) {
            return;
        }
        LogUtil.log(workOrder.getViewName() + "组件的value被改成" + workOrder.getValue());
        setData(workOrder);
        WorkOrderDataManager.newInstance().setValueForLinkWorkOrder(workOrder);

    }
}
