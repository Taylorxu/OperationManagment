package com.wisesignsoft.OperationManagement.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.NewWorkOrderAdapter;
import com.wisesignsoft.OperationManagement.bean.User;
import com.wisesignsoft.OperationManagement.bean.VariousOrderBean;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.CustomSubscriber;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SelectOrderPanelActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rl_my_template;
    private Button bt_next;
    private List<VariousOrderBean> datas;
    private RecyclerView rv_new_order;
    private NewWorkOrderAdapter adapter;
    private MyTitle mt_new_work_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_work_order);
        init();
        request();
    }

    private void init() {
        mt_new_work_order = (MyTitle) findViewById(R.id.mt_order_title);
        rl_my_template = (RelativeLayout) findViewById(R.id.rl_my_template);
        bt_next = (Button) findViewById(R.id.bt_next);
        bt_next.setOnClickListener(this);
        rv_new_order = (RecyclerView) findViewById(R.id.rv_new_word_order);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 3);
        rv_new_order.setLayoutManager(manager);
        mt_new_work_order.setBack(true, this);
        mt_new_work_order.setTitle("新建工单");
        rl_my_template.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_my_template:
//                MyTemplateActivity.startSelf(SelectOrderPanelActivity.this, 9);
                break;
            case R.id.bt_next:
                int currentPosition = adapter.getCurrentPosition();
                if (currentPosition == -1) {
                    Toast.makeText(SelectOrderPanelActivity.this, "请选中一个类型", Toast.LENGTH_SHORT).show();
                } else {
//                    NewWorkOrderActivity2.startSelf(SelectOrderPanelActivity.this, datas.get(currentPosition).getKey(), datas.get(currentPosition).getName());
                }
                break;
        }
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
                        adapter = new NewWorkOrderAdapter(getBaseContext(), datas);
                        rv_new_order.setAdapter(adapter);
                    }
                });
    }


}
