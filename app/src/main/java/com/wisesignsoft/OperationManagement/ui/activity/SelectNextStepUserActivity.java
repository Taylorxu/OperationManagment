package com.wisesignsoft.OperationManagement.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.SelectNextStepUserAdapter;
import com.wisesignsoft.OperationManagement.bean.AccountInfoBean;
import com.wisesignsoft.OperationManagement.bean.UserInfofRoleBean;
import com.wisesignsoft.OperationManagement.bean.TaskStrategy;
import com.wisesignsoft.OperationManagement.bean.User;
import com.wisesignsoft.OperationManagement.bean.UserInfofRoleBean;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.MySharedpreferences;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.mview.EmptyView;
import com.wisesignsoft.OperationManagement.mview.LoadingView;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.mview.SearchView;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.BaseResponse;
import com.wisesignsoft.OperationManagement.net.response.CustomSubscriber;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.utils.DividerItemDecoration;
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.wisesignsoft.OperationManagement.Protocol.queryValidUsersByUserName;
import static com.wisesignsoft.OperationManagement.Protocol.yxyw_name_space;

public class SelectNextStepUserActivity extends BaseActivity implements SearchView.ISearchView {
    private MyTitle mt_mult_user;
    private SearchView sv_mult_user;
    private TextView tv_mult_user_total;
    private RecyclerView rv_mult_user;
    private EmptyView ev_select_user;
    /*选中的数据*/
    private List<UserInfofRoleBean> checkedData = new ArrayList<>();
    /*数据源*/
    private List<UserInfofRoleBean> userListData = new ArrayList<>();
    private String id;
    private String ID;
    private String taskKey;
    private String key;
    private SelectNextStepUserAdapter adapter;

    public static void startSelf(Context context, String id, String ID, String taskKey) {
        Intent intent = new Intent(context, SelectNextStepUserActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("ID", ID);
        intent.putExtra("taskKey", taskKey);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mult_user_choose);
        init();
        request();
    }

    private void init() {
        /*获取前面的页面传递的数据*/
        id = getIntent().getStringExtra("id");
        ID = getIntent().getStringExtra("ID");
        taskKey = getIntent().getStringExtra("taskKey");

        mt_mult_user = (MyTitle) findViewById(R.id.mt_mult_user);
        sv_mult_user = (SearchView) findViewById(R.id.sv_mult_user);
        tv_mult_user_total = (TextView) findViewById(R.id.tv_mult_user_total);
        rv_mult_user = (RecyclerView) findViewById(R.id.rv_mult_user);
        ev_select_user = (EmptyView) findViewById(R.id.ev_select_user);
        ev_select_user.setOnRefreshListener(new EmptyView.IRefreshListener() {
            @Override
            public void onRefresh() {
                request();
            }
        });

        mt_mult_user.setBack(true, this);
        mt_mult_user.setTitle("工单处理人");
        mt_mult_user.setTvRight(true, "确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = SelectNextStepUserActivity.this.lisTtoString(checkedData);
                TaskStrategy taskStrategy = new TaskStrategy();
                taskStrategy.setStrategyKey(taskKey);
                taskStrategy.setStrategyValue(result);
                WorkOrderDataManager.newInstance().modifyButtonModel("taskStrategy", taskStrategy);
                commit();
            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rv_mult_user.setLayoutManager(manager);
        rv_mult_user.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter = new SelectNextStepUserAdapter(this, userListData);
        rv_mult_user.setAdapter(adapter);
        adapter.setIMultUserClickListener(new SelectNextStepUserAdapter.IMultUserClickListener() {
            @Override
            public void setOnMultUserClickListener(int position, boolean isSelect) {
                if (isSelect) {
                    checkedData.add(userListData.get(position));
                } else {
                    checkedData.remove(position);
                }

            }
        });
        sv_mult_user.setISearchViewListener(this);
        sv_mult_user.setHint("请输入用户名");
    }

    private void requestSearch() {
        ev_select_user.close();
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        List<String> list = new ArrayList<>();
        list.add(key);
        list.add(id);
        ApiService.Creator.get().queryValidUsersByUserNameII(RequestBody.getgEnvelope(yxyw_name_space, list, queryValidUsersByUserName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new FlatMapResponse<BaseDataResponse<List<UserInfofRoleBean>>>())
                .flatMap(new FlatMapTopRes<List<UserInfofRoleBean>>(DataTypeSelector.RE))
                .subscribe(new CustomSubscriber<List<UserInfofRoleBean>>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        loadingView.stop(loadingView);
                    }

                    @Override
                    public void onNext(List<UserInfofRoleBean> data) {
                        initData(data, loadingView);
                    }
                });
    }


    //人员选择列表数据查询
    private void request() {
        ev_select_user.close();
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        List<String> list = new ArrayList<>();
        list.add(id);
        ApiService.Creator.get().findUserByRoleId(RequestBody.getgEnvelope(Protocol.role_name_space, list, Protocol.findUserByRoleId))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new FlatMapResponse<List<UserInfofRoleBean>>())
                .subscribe(new CustomSubscriber<List<UserInfofRoleBean>>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        loadingView.stop(loadingView);
                    }

                    @Override
                    public void onNext(List<UserInfofRoleBean> data) {
                        initData(data, loadingView);
                    }
                });
    }

//TODO 选完人后提交工单
    public void commit() {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
    }

    //渲染数据
    private void initData(List<UserInfofRoleBean> data, LoadingView loadingView) {
        loadingView.stop(loadingView);
        if (userListData.size() > 0) userListData.clear();
        userListData.addAll(data);
        setResults(lisTtoString(checkedData));
        setEmpty();
        adapter.notifyDataSetChanged();

    }


    private void setResults(String ids) {
        if (TextUtils.isEmpty(ids)) {
            return;
        }
        String[] temps = ids.split(",");
        for (UserInfofRoleBean bean : userListData) {
            bean.setSelect(false);
            for (String temp : temps) {
                if (temp.equals(bean.getUserId())) {
                    bean.setSelect(true);
                }
            }
        }
    }

    private String lisTtoString(List<UserInfofRoleBean> results) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < results.size(); i++) {
            String id = results.get(i).getUserId();
            if (i == results.size() - 1) {
                sb.append(id);
            } else {
                sb.append(id).append(",");
            }
        }
        return sb.toString();
    }

    @Override
    public void setOnSearchListener(String key) {
        this.key = key;
        requestSearch();
    }

    @Override
    public void setOnCancelListener() {
        this.key = "";
        request();
    }


    private void setEmpty() {
        if (userListData == null || userListData.size() == 0) {
            ev_select_user.setVisibility(View.VISIBLE);
            rv_mult_user.setVisibility(View.GONE);
        } else {
            ev_select_user.setVisibility(View.GONE);
            rv_mult_user.setVisibility(View.VISIBLE);
        }
    }
}
