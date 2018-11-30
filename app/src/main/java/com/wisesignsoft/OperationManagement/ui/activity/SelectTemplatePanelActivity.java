package com.wisesignsoft.OperationManagement.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.NewWorkOrderAdapter;
import com.wisesignsoft.OperationManagement.bean.User;
import com.wisesignsoft.OperationManagement.bean.VariousOrderBean;
import com.wisesignsoft.OperationManagement.db.MySharedpreferences;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.mview.SingleSelectView;
import com.wisesignsoft.OperationManagement.net.response.CustomSubscriber;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SelectTemplatePanelActivity extends BaseActivity implements View.OnClickListener {
    private MyTitle mt_new_template;
    private SingleSelectView ssv_new_template;
    private Button bt_next;
    private List<VariousOrderBean> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_template);
        init();
        request();
    }

    private void init() {
        mt_new_template = (MyTitle) findViewById(R.id.mt_new_template);
        ssv_new_template = (SingleSelectView) findViewById(R.id.ssv_new_template);
        bt_next = (Button) findViewById(R.id.bt_next);

        mt_new_template.setBack(true, this);
        mt_new_template.setTitle("新建模板");
        bt_next.setOnClickListener(this);
    }

    private void request() {
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
                        datas = variousOrderBeans;
                        ssv_new_template.setDataSelf("类型", datas);
                    }
                });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_next:
                int position = ssv_new_template.getSelfCurrentPosition();
                if (position >= 0) {
                    String key = datas.get(position).getKey();
                    String name = datas.get(position).getName();
                    CreateTemplateActivity.startSelf(this, name, key);
                } else {
                    Toast.makeText(getBaseContext(), "请选择一个类型", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
