package com.wisesignsoft.OperationManagement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;

import java.util.List;

/**
 * Created by ycs on 2016/12/2.
 */

public class SingleSelectViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<DictDatasBean> list;
    private String checkedId;
    private ISingleSelectViewClickListener listener;
    private boolean isClick;

    public SingleSelectViewAdapter(Context context, List<DictDatasBean> list) {
        this.context = context;
        this.list = list;
    }

    private static class MyHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_new_work_order);
        }
    }

    public interface ISingleSelectViewClickListener {
        void setOnISingleSelectViewClick(int position);
    }

    public void setOnISingleSelectClickListener(ISingleSelectViewClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_new_work_order, parent, false);
        return new SingleSelectViewAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof SingleSelectViewAdapter.MyHolder) {
            final DictDatasBean str = list.get(position);
            ((MyHolder) holder).tv.setText(str.getDictName());
            if (str.getDictId().equals(checkedId)) {
                ((MyHolder) holder).tv.setSelected(true);
            } else {
                ((MyHolder) holder).tv.setSelected(false);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isClick) {
                        checkedId = str.getDictId();
                        notifyDataSetChanged();
                        listener.setOnISingleSelectViewClick(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (list == null || list.size() == 0) ? 0 : list.size();
    }


    public void setCheckedId(String checkedId) {
        this.checkedId = checkedId;
    }

    public void setClick(boolean isClick) {
        this.isClick = isClick;
    }
}
