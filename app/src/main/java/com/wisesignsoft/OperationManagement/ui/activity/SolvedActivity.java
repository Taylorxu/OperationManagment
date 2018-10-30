package com.wisesignsoft.OperationManagement.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.SovledAdapter;
import com.wisesignsoft.OperationManagement.bean.TaskItemBean;
import com.wisesignsoft.OperationManagement.bean.User;
import com.wisesignsoft.OperationManagement.mview.EmptyView;
import com.wisesignsoft.OperationManagement.mview.LoadingView;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.mview.SearchView;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;
import com.wisesignsoft.OperationManagement.utils.SwipeRefreshUtil;
import com.wisesignsoft.OperationManagement.mview.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SolvedActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, RefreshRecyclerView.OnLoadMoreListener, SearchView.ISearchView {

    private TextView tv_doing;
    private TextView tv_total;
    private TextView tv_finish;
    private MyTitle mt_solved;
    private SearchView sv_solved;
    private EmptyView ev_solved;
    private RefreshRecyclerView rv_solved;
    private SwipeRefreshLayout srl_solved;
    private LoadingView loadingView;
    private int currentPage = 0;
    private String key = "", total = "";
    private String medthod = Protocol.findHandledProcess;
    private List<TaskItemBean> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sovled);
        init();
    }

    private void init() {
        mt_solved = (MyTitle) findViewById(R.id.mt_sovled_title);
        tv_doing = (TextView) findViewById(R.id.tv_sovled_doing);
        tv_finish = (TextView) findViewById(R.id.tv_sovled_finish);
        sv_solved = (SearchView) findViewById(R.id.sv_sovled);
        srl_solved = (SwipeRefreshLayout) findViewById(R.id.srl_solved);
        rv_solved = (RefreshRecyclerView) findViewById(R.id.rv_solved);
        ev_solved = (EmptyView) findViewById(R.id.ev_solved);
        tv_total = (TextView) findViewById(R.id.tv_total);
        /*设置标题*/
        mt_solved.setBack(true, this);
        mt_solved.setTitle("已处理");
        /*设置刷新和列表*/
        SwipeRefreshUtil.setConfig(srl_solved, this);
        SwipeRefreshUtil.setRecyclerConfig(rv_solved, this, this);
        /*设置空页面*/
        ev_solved.setData(R.mipmap.home, getResources().getString(R.string.empty_text));
        ev_solved.setOnRefreshListener(listener);
        SovledAdapter sovledAdapter = new SovledAdapter(getBaseContext(), datas);
        sovledAdapter.setOnItemSolvedListener(new SovledAdapter.ISolvedItemClickListener() {
            @Override
            public void setOnItemSolvedClickListener(String key) {
                WorkOrderDetailActivity.startSelf(getBaseContext(), key);
            }
        });
        /*设置适配器*/
        rv_solved.setAdapter(sovledAdapter);
        tv_doing.setOnClickListener(this);
        tv_finish.setOnClickListener(this);
        selectDoing();
        sv_solved.setISearchViewListener(this);
        sv_solved.setHint("请输入标题或工单号");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sovled_doing:
                selectDoing();
                break;
            case R.id.tv_sovled_finish:
                selectFinish();
                break;
        }
    }

    /*进行中模式*/
    private void selectDoing() {
        loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        tv_doing.setSelected(true);
        tv_finish.setSelected(false);
        medthod = Protocol.findHandledProcess;
        requestFirst();

    }

    /*已完成模式*/
    private void selectFinish() {
        loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        tv_doing.setSelected(false);
        tv_finish.setSelected(true);
        medthod = Protocol.findEndedProcess;
        requestFirst();
    }


    /**
     * 请求第一页数据
     */
    private void requestFirst() {
        currentPage = 0;
        rv_solved.setLoadMoreEnable(true);
        request();
    }

    /**
     * 请求下一页数据
     */
    private void requestNext() {
        currentPage += 10;
        if (currentPage > Integer.parseInt(total)) {
            Toast.makeText(SolvedActivity.this, "已经没有更多数据了", Toast.LENGTH_SHORT).show();
            rv_solved.setLoadMoreEnable(false);
            return;
        }
        request();
    }


    /**
     * 请求网络
     */
    private void request() {
        List<String> param = new ArrayList<>();
        param.add(key);
        param.add(User.getUserFromRealm().getUsername());
        param.add(currentPage + "");
        param.add("10");
        ApiService.Creator.get().findUnhandleProcess(RequestBody.getgEnvelope(Protocol.yxyw_name_space, param, medthod))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new FlatMapResponse<BaseDataResponse<List<TaskItemBean>>>())
                .subscribe(new Subscriber<BaseDataResponse<List<TaskItemBean>>>() {
                    @Override
                    public void onCompleted() {
                        loadingView.stop(loadingView);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingView.stop(loadingView);
                        EEMsgToastHelper.newInstance().selectWitch(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseDataResponse<List<TaskItemBean>> dataResponse) {
                        loadingView.stop(loadingView);
                        total = dataResponse.getTotal();
                        if (srl_solved.isRefreshing()) {
                            srl_solved.setRefreshing(false);
                        }
                        if (currentPage == 0) {
                            datas.clear();

                        }
                        tv_total.setText("共" + total + "条数据");
                        datas.addAll(dataResponse.getData());
                        setEmpty(datas);
                        rv_solved.notifyData();
                    }
                });


    }

    private void setEmpty(List list) {
        if (list.size() > 0) {
            ev_solved.setVisibility(View.GONE);
            srl_solved.setVisibility(View.VISIBLE);
        } else {
            ev_solved.setVisibility(View.VISIBLE);
            srl_solved.setVisibility(View.GONE);
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
