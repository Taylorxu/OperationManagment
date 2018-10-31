package com.wisesignsoft.OperationManagement.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.MyTemplateAdapter;
import com.wisesignsoft.OperationManagement.adapter.NewWorkOrderAdapter;
import com.wisesignsoft.OperationManagement.bean.TempletListBean;
import com.wisesignsoft.OperationManagement.bean.User;
import com.wisesignsoft.OperationManagement.bean.VariousOrderBean;
import com.wisesignsoft.OperationManagement.db.MySharedpreferences;
import com.wisesignsoft.OperationManagement.mview.EmptyView;
import com.wisesignsoft.OperationManagement.mview.LoadingView;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.mview.RefreshRecyclerView;
import com.wisesignsoft.OperationManagement.mview.SearchView;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.CustomSubscriber;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.utils.SwipeRefreshUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyTemplateActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, RefreshRecyclerView.OnLoadMoreListener, SearchView.ISearchView {

    private MyTitle mt_my_template;
    private SearchView sv_my_template;
    private RefreshRecyclerView rrv_my_template;
    /*数据的当前页面*/
    private int currentPage = 0;
    /*搜索关键词*/
    private String key = "";
    /*总数*/
    private String total;
    /*工单类型拼接*/
    private String keys;
    /*数据*/
    private List<TempletListBean.DataBean> datas = new ArrayList<>();
    private SwipeRefreshLayout srl_my_template;
    private LoadingView loadingView;
    private EmptyView ev_my_template;
    private int type;

    public static void startSelf(Context context, int type) {
        Intent intent = new Intent(context, MyTemplateActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_template);
        init();
        requestKey();
    }


    private void init() {
        /*获取type*/
        type = getIntent().getIntExtra("type", 0);
        /*加载框*/
        loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        /*初始化控件*/
        mt_my_template = (MyTitle) findViewById(R.id.mt_my_template);
        sv_my_template = (SearchView) findViewById(R.id.sv_my_template);
        rrv_my_template = (RefreshRecyclerView) findViewById(R.id.rrv_my_template);
        srl_my_template = (SwipeRefreshLayout) findViewById(R.id.srl_my_template);
        ev_my_template = (EmptyView) findViewById(R.id.ev_my_template);
        /*设置标题*/
        mt_my_template.setBack(true, this);
        mt_my_template.setTitle("我的模板");
        /*设置刷新和列表*/
        SwipeRefreshUtil.setConfig(srl_my_template, this);
        SwipeRefreshUtil.setRecyclerConfig(rrv_my_template, this, this);
        /*设置空页面*/
        ev_my_template.setData(R.mipmap.home, getResources().getString(R.string.empty_text));
        ev_my_template.setOnRefreshListener(listener);
        /*设置适配器*/
        MyTemplateAdapter adapter = new MyTemplateAdapter(this, datas);
        rrv_my_template.setAdapter(adapter);
        /*搜索监听*/
        sv_my_template.setISearchViewListener(this);
        sv_my_template.setHint("请输入标题");
        adapter.setOnIMyTemplate(new MyTemplateAdapter.IMyTemplate() {
            @Override
            public void setOnClick(String id, String name) {
                if (type == 0) {
                    TemplateDetailActivity.startSelf(MyTemplateActivity.this, id);
                } else {
//                    NewTemplateActivity3.startSelf(MyTemplateActivity.this, name, id);
                }
            }
        });
    }

    /**
     * 查询可创建的工单
     */
    private void requestKey() {
        List<String> list = new ArrayList<>();
        list.add(User.getUserFromRealm().getUsername());
        ApiService.Creator.get().findCanCreateProcess(RequestBody.getgEnvelope(Protocol.process_name_space, list, Protocol.findCanCreateProcess))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new FlatMapResponse<List<VariousOrderBean>>())
                .subscribe(new CustomSubscriber<List<VariousOrderBean>>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(List<VariousOrderBean> variousOrderBeans) {
                        setKeys(variousOrderBeans);
                        requestFirst();
                    }
                });
    }

    private void setKeys(List<VariousOrderBean> datas) {
        if (datas != null && datas.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < datas.size(); i++) {
                String key = datas.get(i).getKey();
                if (i == datas.size() - 1) {
                    sb.append(key);
                } else {
                    sb.append(key).append(",");
                }
            }
            keys = sb.toString();
        }
    }

    /*网络请求数据*/
    private void request(List<String> list) {
        ApiService.Creator.get().getTempletsByKeys(RequestBody.getgEnvelope(Protocol.process_name_space, list, Protocol.getWoTempletListByUserAccount))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new FlatMapResponse<TempletListBean>())
                .subscribe(new CustomSubscriber<TempletListBean>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (srl_my_template.isRefreshing()) {
                            srl_my_template.setRefreshing(false);
                        }
                        loadingView.stop(loadingView);
                    }

                    @Override
                    public void onNext(TempletListBean templetListBean) {
                        loadingView.stop(loadingView);
                        total = templetListBean.getTotal();
                        if (srl_my_template.isRefreshing()) {
                            srl_my_template.setRefreshing(false);
                        }
                        if (currentPage == 0) {
                            datas.clear();
                        }
                        datas.addAll(templetListBean.getData());
                        setEmpty(datas);
                        rrv_my_template.notifyData();
                    }
                });


    }

    /*请求第一页数据*/
    private void requestFirst() {
        currentPage = 0;
        rrv_my_template.setLoadMoreEnable(true);
        List<String> list = new ArrayList<>();
        list.add(currentPage + "");
        list.add("10");
        list.add(User.getUserFromRealm().getUsername());
        list.add(keys);
        list.add(key);
        request(list);
    }

    /*请求下一页数据*/
    private void requestNext() {
        currentPage += 10;
        if (currentPage > Integer.parseInt(total)) {
            Toast.makeText(this, "已经没有更多数据了", Toast.LENGTH_SHORT).show();
            rrv_my_template.setLoadMoreEnable(false);
            return;
        }
        List<String> list = new ArrayList<>();
        list.add(currentPage + "");
        list.add("10");
        list.add(User.getUserFromRealm().getUsername());
        list.add(keys);
        list.add(key);
        request(list);
    }

    /*设置空页面*/
    private void setEmpty(List list) {
        if (list.size() > 0) {
            srl_my_template.setVisibility(View.VISIBLE);
            ev_my_template.setVisibility(View.GONE);
        } else {
            srl_my_template.setVisibility(View.GONE);
            ev_my_template.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRefresh() {
        requestFirst();
    }

    @Override
    public void loadMoreListener() {
        requestNext();
    }

    /**
     * 空页面刷新
     */
    private EmptyView.IRefreshListener listener = new EmptyView.IRefreshListener() {
        @Override
        public void onRefresh() {
            requestFirst();
        }
    };

    @Override
    public void setOnSearchListener(String key) {
        this.key = key;
        requestFirst();
    }

    @Override
    public void setOnCancelListener() {
        this.key = "";
        requestFirst();
    }
}
