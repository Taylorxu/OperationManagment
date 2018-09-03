package com.wisesignsoft.OperationManagement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.RoleBean;

import java.util.List;

/**
 * Created by ycs on 2016/12/12.
 */

public class SingleRoleAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<RoleBean> datas;
    private ISingleRoleClickListener listener;

    public SingleRoleAdapter(Context context, List<RoleBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    private static class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_single_user;

        public MyHolder(View itemView) {
            super(itemView);
            tv_single_user = (TextView) itemView.findViewById(R.id.tv_item_single_user);
        }
    }

    public interface ISingleRoleClickListener {
        void setOnISingleRoleClickListener(String id);
    }

    public void setOnISingleRoleClickListener(ISingleRoleClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_single_user, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            final RoleBean bean = datas.get(position);
            ((MyHolder) holder).tv_single_user.setText(bean.getRoleName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.setOnISingleRoleClickListener(bean.getRoleId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (datas == null || datas.size() == 0) ? 0 : datas.size();
    }
}
