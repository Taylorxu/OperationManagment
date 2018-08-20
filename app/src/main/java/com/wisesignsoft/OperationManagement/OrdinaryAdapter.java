package com.wisesignsoft.OperationManagement;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.bean.OrdinaryModel;

import java.util.List;

/**
 * Created by ycs on 2016/11/29.
 */

public class OrdinaryAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<OrdinaryModel> list;
    private Fragment fragment;
    private IOrdinary iOrdinary;

    public OrdinaryAdapter(Context context, List<OrdinaryModel> list, Fragment fragment) {
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    private static class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_ordinary;
        ImageView iv_ordinary;

        public MyHolder(View itemView) {
            super(itemView);
            tv_ordinary = (TextView) itemView.findViewById(R.id.tv_ordinary);
            iv_ordinary = (ImageView) itemView.findViewById(R.id.iv_ordinary);
        }
    }

    public interface IOrdinary {
        void setOnIOrdinary(OrdinaryModel resUrl);
    }

    public void setOnIOrdinary(IOrdinary iOrdinary) {
        this.iOrdinary = iOrdinary;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ordinary, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyHolder) {
            final OrdinaryModel bean = list.get(position);
            ((MyHolder) holder).tv_ordinary.setText(bean.getName());
            ((MyHolder) holder).iv_ordinary.setImageResource(bean.getReId());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iOrdinary.setOnIOrdinary(bean);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (list == null || list.size() == 0) ? 0 : list.size();
    }

}
