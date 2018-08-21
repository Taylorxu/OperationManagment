package com.wisesignsoft.OperationManagement.utils;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.mview.RefreshRecyclerView;


/**
 * Created by ycs on 2016/11/28.
 */

public class SwipeRefreshUtil {
    public static void setConfig(SwipeRefreshLayout layout, SwipeRefreshLayout.OnRefreshListener listener) {
        layout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        layout.setOnRefreshListener(listener);
    }

    public static void setRecyclerConfig(RefreshRecyclerView recyclerConfig, Context context, RefreshRecyclerView.OnLoadMoreListener loadMoreListener) {
        recyclerConfig.setLayoutManager(new LinearLayoutManager(context));
        recyclerConfig.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        recyclerConfig.setLoadMoreEnable(true);//允许加载更多
        recyclerConfig.setFooterResource(R.layout.foot_view);//设置脚布局
        recyclerConfig.setOnLoadMoreListener(loadMoreListener);
    }
}
