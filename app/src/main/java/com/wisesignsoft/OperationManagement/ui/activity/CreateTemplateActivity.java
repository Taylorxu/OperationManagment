package com.wisesignsoft.OperationManagement.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Element;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.BMForm;
import com.wisesignsoft.OperationManagement.bean.ButtonModel;
import com.wisesignsoft.OperationManagement.bean.TemplateBean;
import com.wisesignsoft.OperationManagement.bean.User;
import com.wisesignsoft.OperationManagement.db.MySharedpreferences;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.mview.LoadingView;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.mview.WorkOrderDetailView;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.BaseResponse;
import com.wisesignsoft.OperationManagement.net.response.CustomSubscriber;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.utils.GsonHelper;
import com.wisesignsoft.OperationManagement.utils.PullPaseXmlUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CreateTemplateActivity extends OrderDetailRootActivity {
    private MyTitle mt_new_template;
    private WorkOrderDetailView wodv_new_template;
    private String key;
    private String name;
    private List xmlData;
    private LinearLayout ll_new_temp;

    public static void startSelf(Context context, String name, String key) {
        Intent intent = new Intent(context, CreateTemplateActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("key", key);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_template2);
        init();
        request();
    }

    @Override
    public void setButton() {
        super.setButton(ll_new_temp, xmlData);
    }

    private void init() {
        mt_new_template = (MyTitle) findViewById(R.id.mt_new_template2);
        wodv_new_template = (WorkOrderDetailView) findViewById(R.id.wodv_new_template2);
        ll_new_temp = (LinearLayout) findViewById(R.id.ll_new_template);

        mt_new_template.setBack(true, this);
        mt_new_template.setTitle("新建模板");

        key = getIntent().getStringExtra("key");
        name = getIntent().getStringExtra("name");
    }

    private void request() {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        List<String> list = new ArrayList<>();
        list.add(key);
        list.add(User.getUserFromRealm().getUsername());

        ApiService.Creator.get().createTemplet(RequestBody.getgEnvelope(Protocol.process_name_space, list, Protocol.createTemplet))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new FlatMapResponse<BaseDataResponse<TemplateBean>>())
                .flatMap(new FlatMapTopRes<TemplateBean>(DataTypeSelector.RE))
                .subscribe(new CustomSubscriber<TemplateBean>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        loadingView.stop(loadingView);
                    }

                    @Override
                    public void onNext(TemplateBean templateBean) {
                        String str = templateBean.getFormDocument();
                        xmlData = PullPaseXmlUtil.pase(str);
                        wodv_new_template.refreshRealmData(xmlData);
                        setButton();
                        loadingView.stop(loadingView);
                    }
                });

    }


    public void commit() {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        if (!WorkOrderDataManager.newInstance().checkEmptyValue(this)) {
            loadingView.stop(loadingView);
            return;
        } else {
            WorkOrderDataManager.newInstance().fillFormValue();
        }
        String result = GsonHelper.build().objectToJsonString(WorkOrderDataManager.parameterMap);
        List<String> list = new ArrayList<>(Arrays.asList(key, result, User.getUserFromRealm().getUsername()));
        ApiService.Creator.get().saveTeplet(RequestBody.getgEnvelope(Protocol.process_name_space, list, Protocol.saveTeplet))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new FlatMapResponse<BaseResponse>())
                .subscribe(new CustomSubscriber<BaseResponse>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        loadingView.stop(loadingView);
                    }

                    @Override
                    public void onNext(BaseResponse response) {
                        loadingView.stop(loadingView);
                        if ("0".equals(response.getReturnState())) {
                            Toast.makeText(getBaseContext(), "创建模板成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getBaseContext(), response.getReturnMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
