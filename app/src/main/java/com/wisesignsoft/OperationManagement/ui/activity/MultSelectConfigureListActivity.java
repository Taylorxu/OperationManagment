package com.wisesignsoft.OperationManagement.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.MultSelectConfigureListAdapter;
import com.wisesignsoft.OperationManagement.bean.AttrDefineListBean;
import com.wisesignsoft.OperationManagement.bean.BusinessModel;
import com.wisesignsoft.OperationManagement.bean.SingleResModelListData;
import com.wisesignsoft.OperationManagement.bean.User;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.mview.EmptyView;
import com.wisesignsoft.OperationManagement.mview.LoadingView;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.mview.RefreshRecyclerView;
import com.wisesignsoft.OperationManagement.mview.SearchView;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;
import com.wisesignsoft.OperationManagement.utils.SwipeRefreshUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.wisesignsoft.OperationManagement.Protocol.ci_name_space;
import static com.wisesignsoft.OperationManagement.Protocol.queryData;
import static com.wisesignsoft.OperationManagement.Protocol.queryModelByBmModelName;
import static com.wisesignsoft.OperationManagement.Protocol.yxyw_name_space;

public class MultSelectConfigureListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private MyTitle mt_title;
    private SearchView sv_select_account;
    private TextView tv_select_account_total;
    private SwipeRefreshLayout srl_select_account;
    private LoadingView loadingView;
    private RefreshRecyclerView rrv_select_account;
    private EmptyView ev_select_account;
    /*数据的当前页面*/
    private int currentPage = 0;
    /*搜索关键词*/
    private String searchKey = "";
    /*总数*/
    private int total;
    private List<AttrDefineListBean> attrDefineList;
    private String str_key;


    private String id;
    private String value;
    /*总的数据*/
    private List<Map<String, String>> datas = new ArrayList<>();
    /*传递过来的数据*/
    private List<Map<String, String>> beforeData = new ArrayList<>();
    /*上送的数据*/
    private List<Map<String, String>> behindData = new ArrayList<>();
    private String className;

    public static void startSelf(Context context, String value, String id, String className) {
        Intent intent = new Intent(context, MultSelectConfigureListActivity.class);
        intent.putExtra("value", value);
        intent.putExtra("id", id);
        intent.putExtra("className", className);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_account);
        init();
        requestModelName();
    }

    private void init() {
        /*处理传递过来的数据*/
        id = getIntent().getStringExtra("id");
        value = getIntent().getStringExtra("value");
        className = getIntent().getStringExtra("className");
        solvedData(value);

        loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        mt_title = (MyTitle) findViewById(R.id.mt_select_account);
        sv_select_account = (SearchView) findViewById(R.id.sv_select_account);
        tv_select_account_total = (TextView) findViewById(R.id.tv_select_account_total);
        srl_select_account = (SwipeRefreshLayout) findViewById(R.id.srl_select_account);
        rrv_select_account = (RefreshRecyclerView) findViewById(R.id.rrv_select_account);
        ev_select_account = (EmptyView) findViewById(R.id.ev_select_account);
        /*设置标题*/
        mt_title.setBack(true, this);
        mt_title.setTitle("选择台账");
        mt_title.setTvRight(true, "确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (behindData != null) {
                    Gson gson = new Gson();
                    String json = gson.toJson(behindData);
                    WorkOrderDataManager.newInstance().modifyValue(id, json);
                    finish();
                }
            }
        });
        /*设置刷新和列表*/
        SwipeRefreshUtil.setConfig(srl_select_account, this);
        SwipeRefreshUtil.setRecyclerConfig(rrv_select_account, this, onLoadMoreListener);
        /*设置空页面*/
        ev_select_account.setData(R.mipmap.home, getResources().getString(R.string.empty_text));
        ev_select_account.setOnRefreshListener(onRefreshListener);
        /*设置适配器*/
        MultSelectConfigureListAdapter adapter = new MultSelectConfigureListAdapter(this, datas);
        rrv_select_account.setAdapter(adapter);
        /*设置搜索*/
        sv_select_account.setISearchViewListener(new SearchListener());
        sv_select_account.setHint("请输入关键字");
        /*item点击事件*/
        adapter.setOnIContractInfo(new MultSelectConfigureListAdapter.IContractInfo() {
            @Override
            public void setOnClick(int position) {
                Map<String, String> map = datas.get(position);
                if ("true".equals(map.get("isSelect"))) {
                    behindData.add(map);
                } else {
                    behindData.remove(map);
                }
            }
        });
    }

    /**
     * 处理数据
     */
    private void solvedData(String value) {
        if (TextUtils.isEmpty(value)) {
            return;
        }
        Gson gson = new Gson();
        beforeData = gson.fromJson(value, new TypeToken<List<Map<String, String>>>() {
        }.getType());
        behindData.clear();
        behindData.addAll(beforeData);
    }

    private void initData() {
        for (Map<String, String> map : datas) {
            map.put("isSelect", "false");
            String id = map.get("OBJECTID");
            for (Map<String, String> map1 : beforeData) {
                String id1 = map1.get("OBJECTID");
                if (id.equals(id1)) {
                    map.put("isSelect", "true");
                }
            }
        }
        rrv_select_account.notifyData();
    }

    @Override
    public void onRefresh() {
        requestFirstPage();
    }


    class SearchListener implements SearchView.ISearchView {

        @Override
        public void setOnSearchListener(String key) {
            searchKey = key;
            requestFirstPage();
        }

        @Override
        public void setOnCancelListener() {
            searchKey = "";
            requestFirstPage();
        }
    }

    private EmptyView.IRefreshListener onRefreshListener = new EmptyView.IRefreshListener() {
        @Override
        public void onRefresh() {
            requestFirstPage();
        }
    };

    private RefreshRecyclerView.OnLoadMoreListener onLoadMoreListener = new RefreshRecyclerView.OnLoadMoreListener() {
        @Override
        public void loadMoreListener() {
            requestNextPage();
        }
    };

    private void setEmpty(List list) {
        if (list.size() > 0) {
            ev_select_account.setVisibility(View.GONE);
            srl_select_account.setVisibility(View.VISIBLE);
        } else {
            ev_select_account.setVisibility(View.VISIBLE);
            srl_select_account.setVisibility(View.GONE);
        }
    }

    private void requestFirstPage() {
        currentPage = 0;
        rrv_select_account.setLoadMoreEnable(true);
        putParameter();
    }

    private void requestNextPage() {
        currentPage += 10;
        if (currentPage > total) {
            Toast.makeText(getBaseContext(), "已经没有更多数据了", Toast.LENGTH_SHORT).show();
            rrv_select_account.setLoadMoreEnable(false);
            return;
        }
        putParameter();
    }

    public String getKey() {
        return str_key;
    }

    private String getKey(List<AttrDefineListBean> attrDefineList) {
        for (AttrDefineListBean bean : attrDefineList) {
            if (bean.getHasBusinessKey()) {
                return bean.getDmAttrName();
            }
        }
        return null;
    }

    private void requestModelName() {
        List<String> list = new ArrayList<>();
        list.add(className);
        ApiService.Creator.get().queryModelByBmModelName(RequestBody.getgEnvelope(ci_name_space, list, queryModelByBmModelName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new FlatMapResponse<BaseDataResponse<BusinessModel>>())
                .flatMap(new FlatMapTopRes<BusinessModel>(DataTypeSelector.RE))
                .subscribe(new Subscriber<BusinessModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        EEMsgToastHelper.newInstance().selectWitch(e.getMessage());
                    }

                    @Override
                    public void onNext(BusinessModel businessModel) {
                        attrDefineList = businessModel.getAttrDefineList();
                        str_key = getKey();
                        putParameter();
                    }
                });
    }

    private void putParameter() {
        List<String> list = new ArrayList<>();
        StringBuffer findStr = new StringBuffer("[{");
        findStr.append(" \"connector\":\"like\",")
                .append("\"dmAttrName\":\"" + str_key + "\",")
                .append("\"value\":\"" + searchKey + "\"}]");

        /*arg0-业务模型编码*/
        list.add(className);
        list.add("0");/*arg1-数据状态,0 表示有效,1表示无效,-1或者空表示全部,为整型*/
        list.add(findStr.toString()); /*arg2-查询条件*/
        list.add("[{}]");  /*arg3*/
        list.add(currentPage + "");  /*arg4*/
        list.add("10");/*arg5*/
        list.add(User.getUserFromRealm().getUsername());/*arg6*/
        requestListViewData(list);
    }

    /**
     * 获取接口数据
     *
     * @param list
     */
    private void requestListViewData(List<String> list) {
        ApiService.Creator.get().queryData(RequestBody.getgEnvelope(yxyw_name_space, list, queryData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new FlatMapResponse<BaseDataResponse<SingleResModelListData>>())
                .flatMap(new FlatMapTopRes<SingleResModelListData>(DataTypeSelector.RE))
                .subscribe(new Subscriber<SingleResModelListData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        EEMsgToastHelper.newInstance().selectWitch(e.getMessage());
                        if (srl_select_account.isRefreshing())
                            srl_select_account.setRefreshing(false);

                    }

                    @Override
                    public void onNext(SingleResModelListData response) {
                        loadingView.stop(loadingView);
                        total = response.getTotal();
                        tv_select_account_total.setText("共有" + total + "条数据");
                        if (srl_select_account.isRefreshing()) {
                            srl_select_account.setRefreshing(false);
                        }
                        if (currentPage == 0) {
                            datas.clear();
                        }
                        datas.addAll(response.getData());
                        setEmpty(datas);
                        initData();
                    }
                });
    }


}
