package com.wisesignsoft.OperationManagement.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.TaskDetailBean;
import com.wisesignsoft.OperationManagement.bean.VariousOrderBean;
import com.wisesignsoft.OperationManagement.mview.KeyValueView;
import com.wisesignsoft.OperationManagement.mview.LoadingView;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.mview.WorkOrderDetailView;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.CustomSubscriber;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.utils.PullPaseXmlUtil;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TemplateDetailActivity extends BaseActivity {

    private MyTitle mt_template_detail;
    private KeyValueView kvv_template_detail;
    private WorkOrderDetailView wodv_template_detail;
    private String id;

    public static void startSelf(Context context, String ID) {
        Intent intent = new Intent(context, TemplateDetailActivity.class);
        intent.putExtra("ID", ID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_detail);
        init();
        request();
    }

    private void init() {
        mt_template_detail = (MyTitle) findViewById(R.id.mt_template_detail);
        kvv_template_detail = (KeyValueView) findViewById(R.id.kvv_template_detail);
        wodv_template_detail = (WorkOrderDetailView) findViewById(R.id.wodv_template_detail);

        mt_template_detail.setBack(true, this);
        mt_template_detail.setTitle("模板详情");

        kvv_template_detail.setValueText("从草稿中选择");
        id = getIntent().getStringExtra("ID");
    }

    private void request() {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        List<String> list = new ArrayList<>();
        list.add(id);
        ApiService.Creator.get().openTemplateDetail(RequestBody.getgEnvelope(Protocol.process_name_space, list, Protocol.openTeplet))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new FlatMapResponse<BaseDataResponse<TaskDetailBean>>())
                .subscribe(new CustomSubscriber<BaseDataResponse<TaskDetailBean>>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        loadingView.stop(loadingView);
                    }

                    @Override
                    public void onNext(BaseDataResponse<TaskDetailBean> taskDetailBean) {
                        String str = taskDetailBean.getReturnValue().getFormDocument();
                        List datas = PullPaseXmlUtil.pase(str);
                        wodv_template_detail.refreshRealmData(datas);
                        loadingView.stop(loadingView);
                    }
                });


    }
}
