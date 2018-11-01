package com.wisesignsoft.OperationManagement.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.SingleUserAdapter;
import com.wisesignsoft.OperationManagement.bean.AccountInfoBean;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.mview.EmptyView;
import com.wisesignsoft.OperationManagement.mview.LoadingView;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.mview.SearchView;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.wisesignsoft.OperationManagement.Protocol.queryValidUsersByUserName;
import static com.wisesignsoft.OperationManagement.Protocol.yxyw_name_space;

public class SingleUserChooseActivity extends BaseActivity implements SearchView.ISearchView {
    private MyTitle mt_single_user;
    private SearchView sv_single_user;
    private TextView tv_single_user_total;
    private RecyclerView rv_single_user;
    private EmptyView ev_select_user;
    private List<AccountInfoBean> datas = new ArrayList<>();
    private String id;
    private SingleUserAdapter adapter;
    /*搜索关键字*/
    private String key;

    public static void startSelf(Context context, String id) {
        Intent intent = new Intent(context, SingleUserChooseActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_user_choose);
        init();
        requestWithoutParam();
    }

    private void init() {
        mt_single_user = (MyTitle) findViewById(R.id.mt_single_user);
        sv_single_user = (SearchView) findViewById(R.id.sv_single_user);
        tv_single_user_total = (TextView) findViewById(R.id.tv_single_user_total);
        rv_single_user = (RecyclerView) findViewById(R.id.rv_single_user);
        ev_select_user = (EmptyView) findViewById(R.id.ev_select_user);

        ev_select_user.setOnRefreshListener(new EmptyView.IRefreshListener() {
            @Override
            public void onRefresh() {
                requestWithoutParam();
            }
        });
        mt_single_user.setBack(true, this);
        mt_single_user.setTitle("选择人员");

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rv_single_user.setLayoutManager(manager);
        adapter = new SingleUserAdapter(this, datas);
        rv_single_user.setAdapter(adapter);

        id = getIntent().getStringExtra("id");
        sv_single_user.setISearchViewListener(this);
    }

    private void requestWithoutParam() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        request(list);
    }

    private void requestWithParam() {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        List<String> list = new ArrayList<>();
        list.add(key);
        list.add("");
        request(list);
    }

    private void request(List<String> list) {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();


        ApiService.Creator.get().<AccountInfoBean>queryValidUsersByUserName(RequestBody.getgEnvelope(yxyw_name_space, list, queryValidUsersByUserName))
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
                        loadingView.stop(loadingView);
                        ev_select_user.close();
                        e.printStackTrace();
                        EEMsgToastHelper.newInstance().selectWitch(e.getMessage());
                    }

                    @Override
                    public void onNext(List<AccountInfoBean> response) {

                        loadingView.stop(loadingView);
                        ev_select_user.close();
                        if (datas != null) {
                            datas.clear();
                        }
                        if (response != null) {
                            datas.addAll(response);
                        }
                        setDate();
                    }

                });
    }

    private void setDate() {
        setEmpty();
        adapter.notifyDataSetChanged();
        adapter.setOnISingleUserClickListener(new SingleUserAdapter.ISingleUserClickListener() {
            @Override
            public void setOnUserNameClickListener(String userId) {
                WorkOrderDataManager.newInstance().modifyValue(id, userId);
                finish();
            }
        });
    }


    private void setEmpty() {
        if (datas == null || datas.size() == 0) {
            ev_select_user.setVisibility(View.VISIBLE);
            rv_single_user.setVisibility(View.GONE);
        } else {
            ev_select_user.setVisibility(View.GONE);
            rv_single_user.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setOnSearchListener(String key) {
        this.key = key;
        requestWithParam();
    }

    @Override
    public void setOnCancelListener() {
        this.key = "";
        requestWithoutParam();
    }
}
