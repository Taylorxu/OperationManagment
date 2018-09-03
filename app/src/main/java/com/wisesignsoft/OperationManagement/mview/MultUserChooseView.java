package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.MultUserChooseViewAdapter;
import com.wisesignsoft.OperationManagement.bean.AccountInfoBean;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.ui.activity.MultUserChooseActivity;
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
 * 人员多选控件
 * Created by ycs on 2016/12/12.
 */
public class MultUserChooseView extends LinearLayout implements RealmObjectChangeListener<WorkOrder> {

    private RecyclerView rv;
    private List<AccountInfoBean> datas = new ArrayList<>();
    private WorkOrder wo;
    private MultUserChooseViewAdapter adapter;
    private TextView tv_mult_user_title;

    public MultUserChooseView(Context context) {
        super(context);
        init(context);
    }

    private void init(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.mult_user_choose, this, true);
        rv = (RecyclerView) view.findViewById(R.id.rv_mult_user_choose);
        tv_mult_user_title = (TextView) view.findViewById(R.id.tv_mult_user_title);

        RecyclerView.LayoutManager manager = new GridLayoutManager(context, 4);
        rv.setLayoutManager(manager);
        adapter = new MultUserChooseViewAdapter(context, datas);
        rv.setAdapter(adapter);
        adapter.setOnIMultUserChooseListener(new MultUserChooseViewAdapter.IMultUserChoose() {
            @Override
            public void setOnIMultUserChoose(AccountInfoBean bean) {
                datas.remove(bean);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void setOnAddUser() {
                if (wo.isModified()) {
                    MultUserChooseActivity.startSelf(context, wo.getValue(), wo.getID());
                }
            }
        });
    }

    public void setData(WorkOrder wo) {
        this.wo = wo;
        wo.addChangeListener(this);
        String title = wo.getName();
        String ids = wo.getValue();
        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                tv_mult_user_title.setText(title + " *");
            } else {
                tv_mult_user_title.setText(title);
            }
        }
        if (!TextUtils.isEmpty(ids)) {
            List<String> list = new ArrayList<>();
            list.add(ids);

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
                            if (datas != null) {
                                datas.clear();
                            }
                            datas.addAll(accountInfoBeans);
                            adapter.notifyDataSetChanged();
                        }
                    });
        }
    }

    @Override
    public void onChange(WorkOrder workOrder, @Nullable ObjectChangeSet changeSet) {
        if (changeSet.isDeleted()) return;
        setData(workOrder);
        WorkOrderDataManager.newInstance().setValueForLinkWorkOrder(workOrder);
    }
}
