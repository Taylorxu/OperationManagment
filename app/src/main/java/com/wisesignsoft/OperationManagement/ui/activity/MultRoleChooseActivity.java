package com.wisesignsoft.OperationManagement.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.MultRoleAdapter;
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

public class MultRoleChooseActivity extends BaseActivity {
    private MyTitle mt_mult_role;
    private SearchView sv_mult_role;
    private TextView tv_mult_role_total;
    private RecyclerView rv_mult_role;
    private Button bt_mult_role;
    /*所有数据*/
    private List<RoleBean> returnValue;
    /*提交的数据*/
    private List<RoleBean> results = new ArrayList<>();

    private String groupId;
    private String id;
    private String value;

    public static void startSelf(Context context, String groupId, String id, String value) {
        Intent intent = new Intent(context, MultUserChooseActivity.class);
        intent.putExtra("groupId", groupId);
        intent.putExtra("id", id);
        intent.putExtra("value", value);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mult_role_choose);
        init();
        request();
    }

    private void init() {
        /*获取来自上个页面的数据*/
        groupId = getIntent().getStringExtra("groupId");
        id = getIntent().getStringExtra("id");
        value = getIntent().getStringExtra("value");

        mt_mult_role = (MyTitle) findViewById(R.id.mt_mult_role);
        sv_mult_role = (SearchView) findViewById(R.id.sv_mult_role);
        tv_mult_role_total = (TextView) findViewById(R.id.tv_mult_role_total);
        rv_mult_role = (RecyclerView) findViewById(R.id.rv_mult_role);
        bt_mult_role = (Button) findViewById(R.id.bt_mult_role);

        mt_mult_role.setBack(true, this);
        mt_mult_role.setTitle("选择角色");
        mt_mult_role.setTvRight(true, "确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < results.size(); i++) {
                    String id = results.get(i).getRoleId();
                    if (i == results.size() - 1) {
                        sb.append(id);
                    } else {
                        sb.append(id).append(",");
                    }
                }
                WorkOrderDataManager.newInstance().modifyValue(id, sb.toString());
                finish();
            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rv_mult_role.setLayoutManager(manager);
        rv_mult_role.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
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
                        loadingView.stop(loadingView);
                        returnValue = roleBeans;
                        setResults();
                        initData();
                    }
                });
    }

    private void initData() {
        MultRoleAdapter adapter = new MultRoleAdapter(this, returnValue);
        rv_mult_role.setAdapter(adapter);
        adapter.setIMultRoleClickListener(new MultRoleAdapter.IMultRoleClickListener() {
            @Override
            public void setOnMultRoleClickListener(int position, boolean isSelect) {
                if (isSelect) {
                    results.add(returnValue.get(position));
                } else {
                    results.remove(returnValue.get(position));
                }
            }
        });
    }

    private void setResults() {
        String[] befores = value.split(",");
        for (RoleBean bean : returnValue) {
            String id = bean.getRoleId();
            bean.setSelect(false);
            for (String before : befores) {
                if (id.equals(before)) {
                    bean.setSelect(true);
                    results.add(bean);
                }
            }
        }
    }
}
