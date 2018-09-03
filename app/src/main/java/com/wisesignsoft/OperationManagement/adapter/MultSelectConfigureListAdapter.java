package com.wisesignsoft.OperationManagement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.ui.activity.MultSelectConfigureListActivity;

import java.util.List;
import java.util.Map;

/**
 * Created by ycs on 2016/12/7.
 */

public class MultSelectConfigureListAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Map<String, String>> datas;
    private IContractInfo iContractInfo;

    public MultSelectConfigureListAdapter(Context context, List<Map<String, String>> datas) {
        this.context = context;
        this.datas = datas;
    }

    private static class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_time;
        ImageView iv_mult_account;

        public MyHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_item_select_account_title);
            tv_time = (TextView) itemView.findViewById(R.id.tv_item_select_account_time);
            iv_mult_account = (ImageView) itemView.findViewById(R.id.iv_mult_account);
        }
    }

    public interface IContractInfo {
        void setOnClick(int position);
    }

    public void setOnIContractInfo(IContractInfo iContractInfo) {
        this.iContractInfo = iContractInfo;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_account2, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyHolder) {
            Map<String, String> bean = datas.get(position);
            String key = ((MultSelectConfigureListActivity) context).getKey();
            String name = bean.get(key);
            Log.i("YCS", "onBindViewHolder: name:" + name);
            if (!TextUtils.isEmpty(name)) {
                ((MyHolder) holder).tv_title.setText(name);
            } else {
                ((MyHolder) holder).tv_title.setText("无标题");
            }
            String content = bean.get("CUST_NAME") + "(" + bean.get("CUST_NO") + ")";
            String time = bean.get("CREATEDATE");
            ((MyHolder) holder).tv_time.setText(time);
            if ("true".equals(bean.get("isSelect"))) {
                ((MyHolder) holder).iv_mult_account.setSelected(true);
            } else {
                ((MyHolder) holder).iv_mult_account.setSelected(false);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MyHolder) holder).iv_mult_account.setSelected(!((MyHolder) holder).iv_mult_account.isSelected());
                    notifyDataSetChanged();
                    iContractInfo.setOnClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (datas == null || datas.size() == 0) ? 0 : datas.size();
    }
}
