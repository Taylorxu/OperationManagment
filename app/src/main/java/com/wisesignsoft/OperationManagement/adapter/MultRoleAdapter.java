package com.wisesignsoft.OperationManagement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.RoleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ycs on 2016/12/12.
 */

public class MultRoleAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<RoleBean> datas;
    private IMultRoleClickListener listener;
    private List<RoleBean> returnDatas = new ArrayList<>();

    public MultRoleAdapter(Context context, List<RoleBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    private static class MyHolder extends RecyclerView.ViewHolder {
        ImageView iv_mult_user;
        TextView tv_mult_user;

        public MyHolder(View itemView) {
            super(itemView);
            iv_mult_user = (ImageView) itemView.findViewById(R.id.iv_mult_user);
            tv_mult_user = (TextView) itemView.findViewById(R.id.tv_mult_user);
        }
    }

    public interface IMultRoleClickListener {
        void setOnMultRoleClickListener(int position, boolean isSelect);
    }

    public void setIMultRoleClickListener(IMultRoleClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mult_user, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyHolder) {
            final RoleBean bean = datas.get(position);
            ((MyHolder) holder).tv_mult_user.setText(bean.getRoleName());
            ((MyHolder) holder).iv_mult_user.setSelected(bean.isSelect());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bean.setSelect(!bean.isSelect());
                    notifyDataSetChanged();
                    if (listener != null) {
                        listener.setOnMultRoleClickListener(position,bean.isSelect());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (datas == null || datas.size() == 0) ? 0 : datas.size();
    }
}
