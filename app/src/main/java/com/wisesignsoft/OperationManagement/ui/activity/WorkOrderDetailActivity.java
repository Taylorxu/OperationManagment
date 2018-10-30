package com.wisesignsoft.OperationManagement.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.TaskDetailBean;
import com.wisesignsoft.OperationManagement.mview.KeyValueView;
import com.wisesignsoft.OperationManagement.mview.LoadingView;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.mview.WorkOrderDetailView;
import com.wisesignsoft.OperationManagement.net.response.CustomSubscriber;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;
import com.wisesignsoft.OperationManagement.utils.PullPaseXmlUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WorkOrderDetailActivity extends BaseActivity {

    private MyTitle mt_wo_detail;
    private KeyValueView kvv_wo_detail;
    private WorkOrderDetailView wodv_wo_detail;
    private String key;

    public static void startSelf(Context context, String key) {
        Intent intent = new Intent(context, WorkOrderDetailActivity.class);
        intent.putExtra("key", key);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_order_detail);
        initView();
        request();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        key = getIntent().getStringExtra("key");
        mt_wo_detail = (MyTitle) findViewById(R.id.mt_work_order_detail);
        kvv_wo_detail = (KeyValueView) findViewById(R.id.kvv_work_order_detail);
        wodv_wo_detail = (WorkOrderDetailView) findViewById(R.id.wodv_work_order_detail);

        mt_wo_detail.setBack(true, this);
        mt_wo_detail.setTitle("工单详情");
        mt_wo_detail.setTvRight(true, "操作记录", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                WonhInfoActivity.startSelf(WorkOrderDetailActivity.this,key);
            }
        });
        kvv_wo_detail.setValueText(key);
    }

    /**
     * 请求数据
     */
    private void request() {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        List<String> list = new ArrayList<>();
        list.add(key);
        ApiService.Creator.get().findWorkOrderDetailByPiKey(RequestBody.getgEnvelope(Protocol.process_name_space, list, Protocol.findWorkOrderDetailByPiKey))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new FlatMapResponse<String>())
                .subscribe(new CustomSubscriber<String>() {

                    @Override
                    public void onError(Throwable e) {
                        loadingView.stop(loadingView);
                    }

                    @Override
                    public void onNext(String bmform) {
                        loadingView.stop(loadingView);
                        List datas = PullPaseXmlUtil.pase(bmform);
                        wodv_wo_detail.refreshRealmData(datas);
                    }
                });
    }
}
