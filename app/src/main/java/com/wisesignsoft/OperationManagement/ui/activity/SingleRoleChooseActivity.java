package com.wisesignsoft.OperationManagement.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.SingleRoleAdapter;
import com.wisesignsoft.OperationManagement.bean.RoleBean;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.mview.LoadingView;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.mview.SearchView;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.utils.DividerItemDecoration;
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.wisesignsoft.OperationManagement.Protocol.findRoleByGroupId;
import static com.wisesignsoft.OperationManagement.Protocol.role_name_space;

public class SingleRoleChooseActivity extends BaseActivity {

    private MyTitle mt_single_user;
    private SearchView sv_single_user;
    private TextView tv_single_user_total;
    private RecyclerView rv_single_user;
    private List<RoleBean> returnValue;
    private String groupId;
    private String id;

    public static void startSelf(Context context, String groupId, String id) {
        Intent intent = new Intent(context, SingleRoleChooseActivity.class);
        intent.putExtra("groupId", groupId);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_role);
        init();
        request();
    }

    private void init() {
        groupId = getIntent().getStringExtra("groupId");
        id = getIntent().getStringExtra("id");
        mt_single_user = (MyTitle) findViewById(R.id.mt_single_user);
        sv_single_user = (SearchView) findViewById(R.id.sv_single_user);
        tv_single_user_total = (TextView) findViewById(R.id.tv_single_user_total);
        rv_single_user = (RecyclerView) findViewById(R.id.rv_single_user);

        mt_single_user.setBack(true, this);
        mt_single_user.setTitle("选择角色");

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rv_single_user.setLayoutManager(manager);
        rv_single_user.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    public void setDate() {
        SingleRoleAdapter adapter = new SingleRoleAdapter(this, returnValue);
        rv_single_user.setAdapter(adapter);
        adapter.setOnISingleRoleClickListener(new SingleRoleAdapter.ISingleRoleClickListener() {
            @Override
            public void setOnISingleRoleClickListener(String name) {
                WorkOrderDataManager.newInstance().modifyValue(id, name);
                finish();
            }
        });
    }

    private void request() {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        List<String> list = new ArrayList<>();
        list.add(groupId);
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
                        loadingView.stop(loadingView);
                        e.printStackTrace();
                        EEMsgToastHelper.newInstance().selectWitch(e.getMessage());
                    }

                    @Override
                    public void onNext(List<RoleBean> roleBeans) {
                        returnValue = roleBeans;
                        setDate();
                        loadingView.stop(loadingView);
                    }
                });


    }
}
