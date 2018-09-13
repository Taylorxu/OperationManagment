package com.wisesignsoft.OperationManagement.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.TaskOrderTrackAdapter;
import com.wisesignsoft.OperationManagement.adapter.TaskOrderTrackAdapter;
import com.wisesignsoft.OperationManagement.bean.TaskOrderTrackBean;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.net.response.CustomSubscriber;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TaskOrderTrackActivity extends BaseActivity {

    private MyTitle mt_wonh;
    private ImageView loadingView;
    private RecyclerView rv_wonh;
    private List<TaskOrderTrackBean.WonhListBean> datas = new ArrayList<>();
    private TaskOrderTrackAdapter adapter;
    private String key;

    public static void startSelf(Context context, String key) {
        Intent intent = new Intent(context, TaskOrderTrackActivity.class);
        intent.putExtra("key", key);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wonh_info);
        init();
        request();
    }

    private void init() {
        key = getIntent().getStringExtra("key");
        mt_wonh = (MyTitle) findViewById(R.id.mt_wonh);
        rv_wonh = (RecyclerView) findViewById(R.id.rv_wonh);
        loadingView = (ImageView) findViewById(R.id.iv_loading);

        mt_wonh.setBack(true, this);
        mt_wonh.setTitle("工单处理过程");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv_wonh.setLayoutManager(layoutManager);
        adapter = new TaskOrderTrackAdapter(this, datas);
        rv_wonh.setAdapter(adapter);
    }

    private void request() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_loading);
        loadingView.startAnimation(animation);
        List<String> list = new ArrayList<>();
        list.add(key);
        ApiService.Creator.get().findWonhInfo(RequestBody.getgEnvelope(Protocol.process_name_space, list, Protocol.findWonhInfo))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new FlatMapResponse<TaskOrderTrackBean>())
                .subscribe(new CustomSubscriber<TaskOrderTrackBean>() {
                    @Override
                    public void onCompleted() {
                        crossfade();
                    }

                    @Override
                    public void onError(Throwable e) {
                        crossfade();
                    }

                    @Override
                    public void onNext(TaskOrderTrackBean response) {
                        if (datas != null) {
                            datas.clear();
                        }
                        datas.addAll(response.getWonhList());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    public void crossfade() {
        rv_wonh.setAlpha(0f);
        rv_wonh.setVisibility(View.VISIBLE);
        rv_wonh.animate().alpha(1f)
                .setDuration(1000)
                .setListener(null);

        loadingView.animate()
                .alpha(0f)
                .setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loadingView.setVisibility(View.GONE);
                    }
                });

    }

}
