package com.wisesignsoft.OperationManagement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.EventClassificationModel;

import java.util.ArrayList;
import java.util.List;

public class MultTreeSelectionAdapter extends RecyclerView.Adapter {
    private static List<EventClassificationModel> selectedData = new ArrayList<>();
    private int TYPE0 = 0;
    private int TYPE1 = 1;
    private IMultChoose iMultChoose;
    private Context context;

    public static void setDatas(List<EventClassificationModel> datas) {
        selectedData = datas;
    }

    public MultTreeSelectionAdapter(Context context, List<EventClassificationModel> datas) {
        this.context = context;
        selectedData = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mult_depart_view, parent, false);
        View view1 = LayoutInflater.from(context).inflate(R.layout.item_mult_user_view1, parent, false);
        if (viewType == TYPE0) {
            return new MyHolder2(view1);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            String name = selectedData.get(position).getDictName();
            ((ViewHolder) holder).departname.setText(name);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iMultChoose.setOnIMultChoose(selectedData.get(position));
                }
            });
        } else if (holder instanceof MyHolder2) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iMultChoose.setOnAddClick();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (selectedData == null || selectedData.size() == 0) ? 1 : selectedData.size();
    }


    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView departname;

        public ViewHolder(View itemView) {
            super(itemView);
            departname = (TextView) itemView.findViewById(R.id.tv_item_mult_depart_view);
        }
    }

    private static class MyHolder2 extends RecyclerView.ViewHolder {
        ImageView iv;

        public MyHolder2(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_mult_user_view1);
        }
    }


    public interface IMultChoose {
        void setOnIMultChoose(EventClassificationModel bean);

        void setOnAddClick();
    }

    public void setOnIMultChooseListener(IMultChoose chooseListener) {
        iMultChoose = chooseListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (selectedData == null || selectedData.size() == 0 || position == selectedData.size() - 1) {
            return TYPE0;
        }
        return TYPE1;
    }
}
