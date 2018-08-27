package com.wisesignsoft.OperationManagement.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.TaskDetailBean;
import com.wisesignsoft.OperationManagement.bean.User;
import com.wisesignsoft.OperationManagement.mview.LoadingView;
import com.wisesignsoft.OperationManagement.mview.MyDialog;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.mview.WorkOrderDetailView;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.utils.LogUtil;
import com.wisesignsoft.OperationManagement.utils.PullPaseXmlUtil;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderSolvedActivity extends BaseActivity {

    private MyTitle mt_order_solved;
    private WorkOrderDetailView wodv_solved;
    private LoadingView loadingView;
    private String pikey, current;
    private String taskId;
    private String taskNodeType;
    private LinearLayout ll_new_temp;
    //    private BMForm bmForm;
    public static String extraKeyCurrent = "CURRENT", extraKeyPikey = "PIKEY";

    public static void startSelf(Context context, String current, String picky) {
        Intent intent = new Intent(context, OrderSolvedActivity.class);
        intent.putExtra(extraKeyCurrent, current);
        intent.putExtra(extraKeyPikey, picky);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_solved);
        init();
        requestDetail();
    }


    private void init() {
        loadingView = LoadingView.getLoadingView(this);
        loadingView.show();
        mt_order_solved = (MyTitle) findViewById(R.id.mt_order_solved);
        wodv_solved = (WorkOrderDetailView) findViewById(R.id.wodv_solved);
        ll_new_temp = (LinearLayout) findViewById(R.id.ll_new_template);

        mt_order_solved.setBackListener(true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        mt_order_solved.setTitle(getResources().getString(R.string.order_solved_title));
        mt_order_solved.setTvRight(true, "操作记录", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                WonhInfoActivity.startSelf(OrderSolvedActivity.this, pikey);
            }
        });
    }

    private void requestDetail() {
        current = getIntent().getStringExtra(extraKeyCurrent);
        pikey = getIntent().getStringExtra(extraKeyPikey);
        List<String> list = new ArrayList<>();
        list.add(current);
        list.add(pikey);
        list.add(User.getUserFromRealm().getUsername());
        ApiService.Creator.get().openTaskDetail(RequestBody.getgEnvelope(Protocol.process_name_space, list, Protocol.openTaskDetail))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new FlatMapResponse<TaskDetailBean>())
                .subscribe(new Subscriber<TaskDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        loadingView.stop(loadingView);
                    }

                    @Override
                    public void onNext(TaskDetailBean taskDetailBean) {
                        List datas = PullPaseXmlUtil.pase(taskDetailBean.getFormDocument());
                        wodv_solved.refreshRealmData(datas);
//                        crossfade();
                        loadingView.stop(loadingView);
                    }
                });
    }

    public void crossfade() {
        wodv_solved.ll_work_order_detail.setAlpha(0f);
        wodv_solved.ll_work_order_detail.setVisibility(View.VISIBLE);
        wodv_solved.ll_work_order_detail.animate().alpha(1f)
                .setDuration(1000)
                .setListener(null);

        loadingView.rootView.animate()
                .alpha(0f)
                .setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loadingView.stop(loadingView);
                    }
                });

    }

    public void commit() {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();

    }

    private void setButton(List datas) {

    }

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

    /**
     * 保存工单到草稿
     */
    private void requestSure() {
        final LoadingView loadingView = LoadingView.getLoadingView(this);
        loadingView.show();

    }

    private void requestCancel() {
        finish();
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