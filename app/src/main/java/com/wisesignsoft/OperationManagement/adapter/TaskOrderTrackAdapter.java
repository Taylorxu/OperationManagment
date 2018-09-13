package com.wisesignsoft.OperationManagement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.TaskOrderTrackBean;

import java.util.List;

public class TaskOrderTrackAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<TaskOrderTrackBean.WonhListBean> datas;

    public TaskOrderTrackAdapter(Context context, List<TaskOrderTrackBean.WonhListBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_user;
        TextView tv_line;
        TextView tv_time;

        public MyHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_wonh_name);
            tv_user = (TextView) itemView.findViewById(R.id.tv_wonh_user);
            tv_line = (TextView) itemView.findViewById(R.id.tv_wonh_line);
            tv_time = (TextView) itemView.findViewById(R.id.tv_wonh_time);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wonh_info, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            TaskOrderTrackBean.WonhListBean bean = datas.get(position);
            String name = bean.getNodeName();
            String user = bean.getNodeUser();
            String line = bean.getNodeLineName();
            String time = bean.getNodeOpTime();
            ((MyHolder) holder).tv_name.setText(name);
            ((MyHolder) holder).tv_user.setText(user);
            ((MyHolder) holder).tv_line.setText(line);
            ((MyHolder) holder).tv_time.setText(time);
        }
    }

    @Override
    public int getItemCount() {
        return (datas == null || datas.size() == 0) ? 0 : datas.size();
    }
}
