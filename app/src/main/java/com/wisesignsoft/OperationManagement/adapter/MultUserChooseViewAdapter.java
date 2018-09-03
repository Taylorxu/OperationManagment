package com.wisesignsoft.OperationManagement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.AccountInfoBean;

import java.util.List;

/**
 * Created by ycs on 2016/12/12.
 */

public class MultUserChooseViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<AccountInfoBean> datas;
    private IMultUserChoose iMultUserChoose;
    private int TYPE0=0;
    private int TYPE1=1;
    public MultUserChooseViewAdapter(Context context, List<AccountInfoBean> datas){
        this.context = context;
        this.datas = datas;
    }
    private static class MyHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_item_mult_user_view);
        }
    }
    private static class MyHolder2 extends RecyclerView.ViewHolder {
        ImageView iv;
        public MyHolder2(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_mult_user_view1);
        }
    }

    public interface IMultUserChoose{
        void setOnIMultUserChoose(AccountInfoBean bean);
        void setOnAddUser();
    }
    public void setOnIMultUserChooseListener(IMultUserChoose chooseListener){
        iMultUserChoose = chooseListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mult_user_view,parent,false);
        View view1 = LayoutInflater.from(context).inflate(R.layout.item_mult_user_view1,parent,false);
       if(viewType == TYPE0){
            return new MyHolder2(view1);
        }
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyHolder){
            final AccountInfoBean bean = datas.get(position);
            ((MyHolder) holder).tv.setText(bean.getUserName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iMultUserChoose.setOnIMultUserChoose(bean);
                }
            });
        }else if(holder instanceof MyHolder2){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   iMultUserChoose.setOnAddUser();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (datas == null || datas.size() == 0)?1:datas.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(datas == null || datas.size() == 0||position == datas.size()){
            return TYPE0;
        }
        return TYPE1;
    }
}
