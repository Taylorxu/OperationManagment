package com.wisesignsoft.OperationManagement.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.BMForm;
import com.wisesignsoft.OperationManagement.bean.ButtonModel;
import com.wisesignsoft.OperationManagement.bean.NextNode;
import com.wisesignsoft.OperationManagement.bean.ReNewTemplateBean;
import com.wisesignsoft.OperationManagement.bean.TaskDetailBean;
import com.wisesignsoft.OperationManagement.bean.User;
import com.wisesignsoft.OperationManagement.db.MyCallBack;
import com.wisesignsoft.OperationManagement.db.MySharedpreferences;
import com.wisesignsoft.OperationManagement.db.PublicRequest;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.mview.ButtonView;
import com.wisesignsoft.OperationManagement.mview.KeyValueView;
import com.wisesignsoft.OperationManagement.mview.LoadingView;
import com.wisesignsoft.OperationManagement.mview.MyDialog;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.mview.WorkOrderDetailView;
import com.wisesignsoft.OperationManagement.net.response.BaseResponse;
import com.wisesignsoft.OperationManagement.net.response.CustomSubscriber;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.utils.ActivityTaskManager;
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;
import com.wisesignsoft.OperationManagement.utils.GsonHelper;
import com.wisesignsoft.OperationManagement.utils.PullPaseXmlUtil;
import com.wisesignsoft.OperationManagement.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReNewTemplateActivity extends OrderDetailRootActivity implements View.OnClickListener {
    private MyTitle mt_new_work_order2;
    private WorkOrderDetailView wodv_new_work_order2;
    private String type;
    private KeyValueView kvv_type;
    private KeyValueView kvv_code;
    private TextView tv_back;
    private String key;
    private String name;
    private String pikey;
    private String taskId;
    private String taskNodeType;
    private LinearLayout ll_new_temp;
    private String code;
    private List xmlDatas;

    public static void startSelf(Context context, String name, String key) {
        Intent intent = new Intent(context, ReNewTemplateActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("key", key);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_work_order2);
        ActivityTaskManager.newInstance().setList(this);
        init();
        request();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void init() {
        mt_new_work_order2 = (MyTitle) findViewById(R.id.mt_new_work_order_2);
        wodv_new_work_order2 = (WorkOrderDetailView) findViewById(R.id.wodv_new_work_order2);
        kvv_type = (KeyValueView) findViewById(R.id.kvv_new_work_order_type);
        kvv_code = (KeyValueView) findViewById(R.id.kvv_new_work_order_code);
        tv_back = (TextView) findViewById(R.id.tv_back);
        ll_new_temp = (LinearLayout) findViewById(R.id.ll_new_template);

        mt_new_work_order2.setBackListener(true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        mt_new_work_order2.setTitle("新建工单");

        key = getIntent().getStringExtra("key");
        name = getIntent().getStringExtra("name");
        kvv_type.setValueText(name);
        tv_back.setOnClickListener(this);
    }

    public void setMapInit(ReNewTemplateBean response) {
        pikey = code = response.getResult().getPIKEY();
        kvv_code.setValueText(code);
        taskId = response.getResult().getCURRENT_TASKID();
        taskNodeType = response.getResult().getTaskNodeType();
        Map<String, String> map = new HashMap<>();
        map.put("PIKEY", pikey);
        map.put("taskId", taskId);
        map.put("osType", "android");
        map.put("taskNodeType", taskNodeType);
        WorkOrderDataManager.newInstance().setMapInit(map);

    }

    //请求模板的数据
    private void request() {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        List<String> list = new ArrayList<>();
        list.add(key);
        list.add(User.getUserFromRealm().getUsername());
        ApiService.Creator.get().createProcessByTemplet(RequestBody.getgEnvelope(Protocol.process_name_space, list, Protocol.createProcessByTemplet))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new FlatMapResponse<ReNewTemplateBean>())
                .subscribe(new CustomSubscriber<ReNewTemplateBean>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        loadingView.stop(loadingView);
                    }

                    @Override
                    public void onNext(ReNewTemplateBean reNewTemplateBean) {
                        if (reNewTemplateBean.isState()) {
                            setMapInit(reNewTemplateBean);
                            xmlDatas = PullPaseXmlUtil.pase(reNewTemplateBean.getResult().getFormDocument());
                            wodv_new_work_order2.refreshRealmData(xmlDatas);
                            setButton();
                        }
                        loadingView.stop(loadingView);
                    }
                });
    }

    //提交模板数据
    public void commit() {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        PublicRequest.newInstance().submitTask(this, new MyCallBack<Integer>() {
            @Override
            public void onResponse(Integer state) {
                loadingView.stop(loadingView);
                if (state == 0) {//成功
                    Toast.makeText(getBaseContext(), "提交成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (state == 1) {
                    Toast.makeText(getBaseContext(), "提交失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public  void setButton() {
         super.setButton(ll_new_temp,xmlDatas);
    }

    //返回时，弹出是否保存草稿对话框
    private void save() {
        MyDialog dialog = new MyDialog(this);
        dialog.setData("是否保存为草稿", dialog, new MyDialog.IMyDialog2() {
            @Override
            public void setMyDialogSure() {
                requestSure();
            }

            @Override
            public void setMyDialogCancel() {
                requestCancel();
            }
        });
        dialog.show();
    }

    //保存工单到草稿
    private void requestSure() {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        String result = GsonHelper.build().objectToJsonString(WorkOrderDataManager.newInstance().getReturnStringModelForDraft());
        List<String> list = new ArrayList<>(Arrays.asList(result, User.getUserFromRealm().getUsername()));
        PublicRequest.newInstance().requestSure(list, new MyCallBack<Integer>() {
            @Override
            public void onResponse(Integer state) {
                loadingView.stop(loadingView);
                if (state == 0) {
                    Toast.makeText(getBaseContext(), "保存草稿成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "保存草稿失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void requestCancel() {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        List<String> list = new ArrayList<>();
        list.add(code);
        list.add(User.getUserFromRealm().getUsername());
        PublicRequest.newInstance().requestCancel(list, new MyCallBack<Integer>() {
            @Override
            public void onResponse(Integer state) {
                loadingView.stop(loadingView);
                if (state == 0) {
                    Toast.makeText(getBaseContext(), "取消保存草稿成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "取消保存草稿失败", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            save();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
