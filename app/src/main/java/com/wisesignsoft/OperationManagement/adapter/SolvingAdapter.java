package com.wisesignsoft.OperationManagement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.TaskItemBean;

import java.util.List;
 

public class SolvingAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<TaskItemBean> datas;
    private ISolving iSolving;

    public SolvingAdapter(Context context, List<TaskItemBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    private static class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_solving_code;
        TextView tv_solving_type;
        TextView tv_solving_time;
        TextView tv_solving_identity;

        public MyHolder(View itemView) {
            super(itemView);
            tv_solving_code = (TextView) itemView.findViewById(R.id.tv_item_solcing_code);
            tv_solving_type = (TextView) itemView.findViewById(R.id.tv_item_solcing_type);
            tv_solving_time = (TextView) itemView.findViewById(R.id.tv_item_solving_time);
            tv_solving_identity = (TextView) itemView.findViewById(R.id.tv_item_solving_identity);
        }
    }

    public interface ISolving {
        void setOnClick(String currentDealer, String pikey);
    }

    public void setOnISolving(ISolving iSolving) {
        this.iSolving = iSolving;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_solving, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            final TaskItemBean bean = datas.get(position);
            ((MyHolder) holder).tv_solving_code.setText(bean.getTITLE() + "(" + bean.getPIKEY() + ")");
            ((MyHolder) holder).tv_solving_time.setText(bean.getCREATEDATE());
            ((MyHolder) holder).tv_solving_identity.setText(bean.getCREATOR());
            ((MyHolder) holder).tv_solving_type.setText(bean.getPROCESS_KEY_NAME());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iSolving.setOnClick(bean.getCURRENT_DEALER(),bean.getPIKEY());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (datas == null || datas.size() == 0) ? 0 : datas.size();
    }
}
