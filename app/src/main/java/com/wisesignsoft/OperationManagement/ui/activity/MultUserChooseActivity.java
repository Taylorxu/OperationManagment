package com.wisesignsoft.OperationManagement.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.MultUserAdapter;
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
import com.wisesignsoft.OperationManagement.utils.DividerItemDecoration;
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.wisesignsoft.OperationManagement.Protocol.queryValidUsersByUserName;
import static com.wisesignsoft.OperationManagement.Protocol.yxyw_name_space;

public class MultUserChooseActivity extends BaseActivity implements SearchView.ISearchView {
    private MyTitle mt_mult_user;
    private SearchView sv_mult_user;
    private TextView tv_mult_user_total;
    private RecyclerView rv_mult_user;
    private EmptyView ev_select_user;
    /*选中的数据*/
    private List<AccountInfoBean> returnValue = new ArrayList<>();
    private List<AccountInfoBean> datas = new ArrayList<>();
    private String ids;
    private String id;
    private String key;
    private MultUserAdapter adapter;

    public static void startSelf(Context context, String ids, String id) {
        Intent intent = new Intent(context, MultUserChooseActivity.class);
        intent.putExtra("ids", ids);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mult_user_choose);
        init();
        requestWithoutParam();
    }

    private void init() {
        /*获取前面的页面传递的数据*/
        ids = getIntent().getStringExtra("ids");
        id = getIntent().getStringExtra("id");

        mt_mult_user = (MyTitle) findViewById(R.id.mt_mult_user);
        sv_mult_user = (SearchView) findViewById(R.id.sv_mult_user);
        tv_mult_user_total = (TextView) findViewById(R.id.tv_mult_user_total);
        rv_mult_user = (RecyclerView) findViewById(R.id.rv_mult_user);
        ev_select_user = (EmptyView) findViewById(R.id.ev_select_user);
        ev_select_user.setOnRefreshListener(new EmptyView.IRefreshListener() {
            @Override
            public void onRefresh() {
                requestWithoutParam();
            }
        });

        mt_mult_user.setBack(true, this);
        mt_mult_user.setTitle("选择人员");
        mt_mult_user.setTvRight(true, "确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkOrderDataManager.newInstance().modifyValue(id, MultUserChooseActivity.this.toString(returnValue));
                finish();
            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rv_mult_user.setLayoutManager(manager);
        rv_mult_user.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter = new MultUserAdapter(this, datas);
        rv_mult_user.setAdapter(adapter);
        sv_mult_user.setISearchViewListener(this);
    }


    private void request(final List<String> list) {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();


        ApiService.Creator.get().queryValidUsersByUserName(RequestBody.getgEnvelope(yxyw_name_space, list, queryValidUsersByUserName))
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
                        datas.addAll(response);
                        if (list.size() > 0) {
                            setResults(MultUserChooseActivity.this.toString(returnValue));
                        } else {
                            setResults(ids);
                        }
                        initData();

                    }

                });
    }

    private void requestSearch() {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        List<String> list = new ArrayList<>();
        list.add(key);
        list.add("");
        request(list);
    }


    private void requestWithoutParam() {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        List<String> list = new ArrayList<>();
        request(list);
    }

    private void initData() {
        setEmpty();
        adapter.notifyDataSetChanged();
        adapter.setIMultUserClickListener(new MultUserAdapter.IMultUserClickListener() {
            @Override
            public void setOnMultUserClickListener(int position, boolean isSelect) {
                if (isSelect) {
                    returnValue.add(datas.get(position));
                } else {
                    returnValue.remove(datas.get(position));
                }

            }
        });
    }

    private void setResults(String ids) {
        if (TextUtils.isEmpty(ids)) {
            return;
        }
        String[] temps = ids.split(",");
        for (AccountInfoBean bean : datas) {
            bean.setSelect(false);
            for (String temp : temps) {
                if (temp.equals(bean.getUserId())) {
                    bean.setSelect(true);
                    returnValue.add(bean);
                }
            }
        }
    }

    private String toString(List<AccountInfoBean> results) {
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

    private void setEmpty() {
        if (datas == null || datas.size() == 0) {
            ev_select_user.setVisibility(View.VISIBLE);
            rv_mult_user.setVisibility(View.GONE);
        } else {
            ev_select_user.setVisibility(View.GONE);
            rv_mult_user.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setOnSearchListener(String key) {
        this.key = key;
        requestSearch();
    }

    @Override
    public void setOnCancelListener() {
        this.key = "";
        requestWithoutParam();
    }
}
