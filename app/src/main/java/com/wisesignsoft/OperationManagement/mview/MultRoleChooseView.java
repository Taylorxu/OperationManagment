package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.MultRoleChooseViewAdapter;
import com.wisesignsoft.OperationManagement.bean.GroupBean;
import com.wisesignsoft.OperationManagement.bean.RoleBean;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.ui.activity.MultRoleChooseActivity;
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.RealmObjectChangeListener;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.wisesignsoft.OperationManagement.Protocol.findRoleByGroupId;
import static com.wisesignsoft.OperationManagement.Protocol.role_name_space;

/**
 * 角色多选
 */
public class MultRoleChooseView extends LinearLayout implements RealmObjectChangeListener<WorkOrder> {
    private RecyclerView rv;
    private List<RoleBean> datas = new ArrayList<>();
    private WorkOrder wo;
    private MultRoleChooseViewAdapter adapter;

    public MultRoleChooseView(Context context) {
        super(context);
        init(context);
    }

    private void init(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.mult_role_choose, this, true);
        rv = (RecyclerView) view.findViewById(R.id.rv_mult_role_choose);

        RecyclerView.LayoutManager manager = new GridLayoutManager(context, 5);
        rv.setLayoutManager(manager);
        adapter = new MultRoleChooseViewAdapter(context, datas);
        rv.setAdapter(adapter);
        adapter.setOnIMultUserChooseListener(new MultRoleChooseViewAdapter.IMultRoleChoose() {
            @Override
            public void setOnIMultRoleChoose(RoleBean bean) {
                datas.remove(bean);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void setOnAddClick() {
                if (wo.isModified()) {
                    Gson gson = new Gson();
                    GroupBean bean = gson.fromJson(wo.getParentRoleJSON(), GroupBean.class);
                    MultRoleChooseActivity.startSelf(context, bean.getGroupId(), wo.getID(), wo.getValue());
                }
            }
        });
    }

    public void setData(WorkOrder wo) {
        this.wo = wo;
        wo.addChangeListener(this);
        List<String> list = new ArrayList<>();
        String ids = wo.getValue();
        list.add(ids);
        ApiService.Creator.get().findRoleByGroupId(RequestBody.getgEnvelope(role_name_space, list, findRoleByGroupId))
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .flatMap(new FlatMapResponse<BaseDataResponse<List<RoleBean>>>())
                .flatMap(new FlatMapTopRes<List<RoleBean>>(DataTypeSelector.RE))
                .subscribe(new Subscriber<List<RoleBean>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        EEMsgToastHelper.newInstance().selectWitch(e.getMessage());
                    }

                    @Override
                    public void onNext(List<RoleBean> roleBeans) {
                        if (datas != null) {
                            datas.clear();
                        }
                        datas.addAll(roleBeans);
                        adapter.notifyDataSetChanged();
                    }
                });


    }

    @Override
    public void onChange(WorkOrder workOrder, @Nullable ObjectChangeSet changeSet) {
        if (changeSet.isDeleted()) return;
        setData(workOrder);
    }
}
