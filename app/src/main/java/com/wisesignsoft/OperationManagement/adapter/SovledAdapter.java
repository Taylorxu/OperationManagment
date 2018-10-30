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

/**
 * Created by ycs on 2016/11/25.
 */

public class SovledAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<TaskItemBean> datas;
    private ISolvedItemClickListener listener;

    public SovledAdapter(Context context, List<TaskItemBean> datas) {
        this.context = context;
        this.datas = datas;
    }
    public interface ISolvedItemClickListener{
        void setOnItemSolvedClickListener(String key);
    }
    public void setOnItemSolvedListener(ISolvedItemClickListener listener){
        this.listener = listener;
    }
    private static class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_solved_code;
        TextView tv_solved_type;
        TextView tv_solved_time;
        TextView tv_solved_identity;

        public MyHolder(View itemView) {
            super(itemView);
            tv_solved_code = (TextView) itemView.findViewById(R.id.tv_item_solced_code);
            tv_solved_type = (TextView) itemView.findViewById(R.id.tv_item_solced_type);
            tv_solved_time = (TextView) itemView.findViewById(R.id.tv_item_solved_time);
            tv_solved_identity = (TextView) itemView.findViewById(R.id.tv_item_solved_identity);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_solved, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            final TaskItemBean  bean = datas.get(position);
            ((MyHolder) holder).tv_solved_code.setText(bean.getTITLE()+"("+bean.getPIKEY()+")");
            ((MyHolder) holder).tv_solved_time.setText(bean.getCREATEDATE());
            ((MyHolder) holder).tv_solved_identity.setText(bean.getCREATOR());
            ((MyHolder) holder).tv_solved_type.setText(bean.getPROCESS_KEY_NAME());
            if(listener != null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.setOnItemSolvedClickListener(bean.getPIKEY());
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return (datas == null || datas.size() == 0) ? 0 : datas.size();
    }
}
