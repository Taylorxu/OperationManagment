package com.wisesignsoft.OperationManagement.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.SelectAccountAdapter;
import com.wisesignsoft.OperationManagement.bean.AttrDefineListBean;
import com.wisesignsoft.OperationManagement.bean.BusinessModel;
import com.wisesignsoft.OperationManagement.bean.ConfigureBean;
import com.wisesignsoft.OperationManagement.bean.ResModeValue;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.wisesignsoft.OperationManagement.Protocol.ci_name_space;
import static com.wisesignsoft.OperationManagement.Protocol.queryData;
import static com.wisesignsoft.OperationManagement.Protocol.queryModelByBmModelName;
import static com.wisesignsoft.OperationManagement.Protocol.yxyw_name_space;

public class SelectAccountActivity4 extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private MyTitle mt_title;
    private SearchView sv_select_account;
    private TextView tv_select_account_total;
    private SwipeRefreshLayout srl_select_account;
    private LoadingView loadingView;
    private RefreshRecyclerView rrv_select_account;
    private EmptyView ev_select_account;
    /*属性表*/
    private List<AttrDefineListBean> attrDefineList;
    /*数据的当前页面*/
    private int currentPage = 0;
    /*搜索关键词*/
    private String searchKey = "";
    /*总数*/
    private int total;
    private List<Map<String, String>> datas = new ArrayList<>();
    private List<ResModeValue.ConfigValueJsonBean> bean;
    private String calssName;
    private String dmAttrName;
    private String id;
    private String str_key;

    public static void startSelf(Context context, String calssName, String dmAttrName, String id) {
        Intent intent = new Intent(context, SelectAccountActivity4.class);
        intent.putExtra("calssName", calssName);
        intent.putExtra("dmAttrName", dmAttrName);
        intent.putExtra("id", id);
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
        //处理一下传递过来的数据
        calssName = getIntent().getStringExtra("calssName");
        dmAttrName = getIntent().getStringExtra("dmAttrName");
        id = getIntent().getStringExtra("id");

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
        /*设置刷新和列表*/
        SwipeRefreshUtil.setConfig(srl_select_account, this);
        SwipeRefreshUtil.setRecyclerConfig(rrv_select_account, this, onLoadMoreListener);
        /*设置空页面*/
        ev_select_account.setData(R.mipmap.home, getResources().getString(R.string.empty_text));
        ev_select_account.setOnRefreshListener(onRefreshListener);
        /*设置适配器*/
        SelectAccountAdapter adapter = new SelectAccountAdapter(this, datas);
        rrv_select_account.setAdapter(adapter);
        /*设置搜索*/
        sv_select_account.setISearchViewListener(new SearchListener());
        sv_select_account.setHint("请输入关键字");
        /*item点击事件*/
        adapter.setOnIContractInfo(new SelectAccountAdapter.IContractInfo() {
            @Override
            public void setOnClick(int position) {
                Map<String, String> allMap = datas.get(position);
                Map<String, ConfigureBean> map = new HashMap<String, ConfigureBean>();
                ConfigureBean bean = new ConfigureBean();
                bean.setOBJECTID(allMap.get("OBJECTID"));
                bean.setBmModelName(calssName);
                bean.setTextValue(allMap.get("NAME"));
                map.put(dmAttrName, bean);
                Gson gson = new Gson();
                String json = gson.toJson(map);
                WorkOrderDataManager.newInstance().modifyValue(id, json);
                finish();
            }
        });
    }


    private void requestModelName() {
        List<String> list = new ArrayList<>();
        list.add(calssName);
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
        list.add(calssName);
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
                        if (srl_select_account.isRefreshing())
                            srl_select_account.setRefreshing(false);
                        if (currentPage == 0) {
                            datas.clear();
                        }
                        datas.addAll(response.getData());
                        setEmpty(datas);
                        rrv_select_account.notifyData();
                    }
                });
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

    public String getKey() {
        for (AttrDefineListBean bean : attrDefineList) {
            if (bean.getHasBusinessKey()) {
                return bean.getDmAttrName();
            }
        }
        return null;
    }

    @Override
    public void onRefresh() {
        requestFirstPage();
    }
}
