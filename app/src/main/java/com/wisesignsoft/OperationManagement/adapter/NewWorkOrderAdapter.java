package com.wisesignsoft.OperationManagement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.CanCreateProcessBean;

import java.util.List;

/**
 * Created by ycs on 2016/11/29.
 */

public class NewWorkOrderAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<CanCreateProcessBean> datas;
    private int currentPosition = -1;

    public NewWorkOrderAdapter(Context context, List<CanCreateProcessBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    private static class MyHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_new_work_order);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_new_work_order, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyHolder) {
            CanCreateProcessBean bean = datas.get(position);
            ((MyHolder) holder).tv.setText(bean.getName());
            ((MyHolder) holder).tv.setSelected(bean.isSelect());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (currentPosition != -1) {
                        datas.get(currentPosition).setSelect(false);
                    }
                    currentPosition = position;
                    datas.get(position).setSelect(true);
                    notifyDataSetChanged();
                }
            });
        }
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public int getItemCount() {
        return (datas == null || datas.size() == 0) ? 0 : datas.size();
    }
}
